package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.util.Base64Util;
import com.huarun.OtherStructure.FaceUserInfo;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.baidu.FaceRegObject;
import com.huarun.pojo.ClassDO;
import com.huarun.pojo.CourseStudent;
import com.huarun.pojo.MajorDO;
import com.huarun.pojo.StudentDO;
import com.huarun.service.*;
import com.huarun.utils.FacePictureType;
import com.huarun.utils.Picture;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SignCaseService signCaseService;

    //学生信息的展示是：StudentInfoShow 结构
    @RequestMapping(value = "/getOneCourseAllStudent", method = RequestMethod.GET)
    public void getOneCourseAllStudent(int course_id, HttpServletResponse response) throws Exception {
        List<CourseStudent> list = courseStudentService.queryOneCourseAllStudent(course_id);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (CourseStudent courseStudent : list) {
            StudentDO studentDO = studentService.getStudentInfoByStuID(courseStudent.getStudent_id());
            MajorDO majorDO = majorService.getMajorInfoByID(studentDO.getMajor_id());
            ClassDO classDO = classService.getClassInfoByClassID(studentDO.getClass_id());

            rows.add(new StudentInfoShow(studentDO, classDO, majorDO));
        }
        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/getSystemAllStudents", method = RequestMethod.GET)
    public void getSystemAllStudents(HttpServletResponse response) throws Exception {

        List<StudentDO> list = studentService.getAllStudentsInfo();

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (StudentDO studentDO : list) {
            MajorDO majorDO = majorService.getMajorInfoByID(studentDO.getMajor_id());
            ClassDO classDO = classService.getClassInfoByClassID(studentDO.getClass_id());
            rows.add(new StudentInfoShow(studentDO, classDO, majorDO));
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        留待优化，没有返回码了，哭哭！！！
//        JSONObject ret = new JSONObject();
//        ret.put("status_code", StatusCode.SUCCESS);
//        ret.put("msg", "成功");
//
//        JSONObject result = new JSONObject();
//        result.put("result", ret);
//        array.add(result);

        ResponseUtil.write(response, array);
    }

    @RequestMapping(value = "/addOneStudent", method = RequestMethod.POST)
    public void addOneStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject result = new JSONObject();

        System.out.println("进入    addOneStudent");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_name"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("major_id"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("class_id"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_sex"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_id_card"));


        //所有数据的校验由前端之后来做
        String name = multipartRequest.getParameter("student_name");
        int major_id = Integer.parseInt(multipartRequest.getParameter("major_id"));
        int class_id = Integer.parseInt(multipartRequest.getParameter("class_id"));
        String sex = multipartRequest.getParameter("student_sex");
        String ID_card = multipartRequest.getParameter("student_id_card");

        if (name == null || sex == null || ID_card == null || multipartRequest.getParameter("major_id") == null
                || multipartRequest.getParameter("class_id") == null) {
            result.put("status_code", StatusCode.PARAM_ERROR);
            result.put("msg", "输入参数为空，请检查！");
            System.out.println("result == " + result);
            ResponseUtil.write(response, result);
            return;
        }

        MultipartFile file = multipartRequest.getFile("student_picture");
        System.out.println("student_picture === " + file.getOriginalFilename());

        //人脸检测图片（质量-》人脸-）
        Picture image = new Picture(Base64Util.encode(file.getBytes()), "BASE64");
        //证件照，不进行活体检测
        Map<String, Object> ret = FaceRegObject.faceDetect(image, false, FacePictureType.CERT);

        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));

        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }
        //人脸注册
        //为学生先生成 id
        int tmp = Integer.parseInt(studentService.getMaxStuID().substring(1));
        tmp = tmp + 1;
        System.out.println("tmp == " + tmp);
        StudentDO studentDO = new StudentDO(
                0,
                "0" + tmp,
                name,
                ID_card.substring(ID_card.length() - 6),
                major_id,
                class_id,
                ID_card,
                sex);

        System.out.println("studentDO ==" + studentDO);

        FaceUserInfo faceUserInfo = new FaceUserInfo(
                studentDO,
                majorService.getMajorInfoByID(major_id),
                classService.getClassInfoByClassID(class_id));

        System.out.println("faceUserInfo ==" + faceUserInfo);
        ret = FaceRegObject.faceAdd(image, faceUserInfo);
        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));


        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }

        //数据库保存学生信息
        studentService.addOneStudent(studentDO);

        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "添加成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/updateOneStudent", method = RequestMethod.POST)
    public void updateOneStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject result = new JSONObject();

        System.out.println("进入    updateOneStudent");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("student_name"));
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("major_id"));
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("class_id"));
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("student_sex"));
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("student_id_card"));
        System.out.println("进入 addOneStudent-------------" + multipartRequest.getParameter("id"));

        //所有数据的校验由前端之后来做
        String name = multipartRequest.getParameter("student_name");
        int major_id = Integer.parseInt(multipartRequest.getParameter("major_id"));
        int class_id = Integer.parseInt(multipartRequest.getParameter("class_id"));
        String sex = multipartRequest.getParameter("student_sex");
        String ID_card = multipartRequest.getParameter("student_id_card");
        String id = multipartRequest.getParameter("id");
        if (name == null || sex == null || ID_card == null || id == null || multipartRequest.getParameter("major_id") == null
                || multipartRequest.getParameter("class_id") == null) {
            result.put("status_code", StatusCode.PARAM_ERROR);
            result.put("msg", "输入参数为空，请检查！(人脸照片可为空！)");
            System.out.println("result == " + result);
            ResponseUtil.write(response, result);
            return;
        }
        MultipartFile file = multipartRequest.getFile("student_picture");
        System.out.println("student_picture_edit === " + file.getOriginalFilename());
        StudentDO studentDO = new StudentDO(
                0,
                id,
                name,
                "",
                major_id,
                class_id,
                ID_card,
                sex);
        if (!file.isEmpty()) {
            //人脸检测图片（质量-》人脸-）
            Picture image = new Picture(Base64Util.encode(file.getBytes()), "BASE64");
            //证件照，不进行活体检测
            Map<String, Object> ret = FaceRegObject.faceDetect(image, false, FacePictureType.CERT);

            System.out.println("status_code == " + ret.get("status_code"));
            System.out.println("msg == " + ret.get("msg"));

            if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
                result.put("status_code", ret.get("status_code"));
                result.put("msg", ret.get("msg"));
                ResponseUtil.write(response, result);
                return;
            }
            //人脸更新
            FaceUserInfo faceUserInfo = new FaceUserInfo(
                    studentDO,
                    majorService.getMajorInfoByID(major_id),
                    classService.getClassInfoByClassID(class_id));

            ret = FaceRegObject.faceUpdate(image, faceUserInfo);
            System.out.println("status_code == " + ret.get("status_code"));
            System.out.println("msg == " + ret.get("msg"));


            if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
                result.put("status_code", ret.get("status_code"));
                result.put("msg", ret.get("msg"));
                ResponseUtil.write(response, result);
                return;
            }
        }

        //数据库保存学生信息
        int ret = studentService.updateOneStudent(studentDO);
        if (ret < 1) {
            //这里应该需要回退照片,怎么解决？？
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "更新学生信息失败！请重试！");
            System.out.println("result == " + result);
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "更新学生信息成功!!");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @Transactional
    @RequestMapping(value = "/deleteOneStudent", method = RequestMethod.GET)
    public void deleteOneStudent(@RequestParam String id, HttpServletResponse response) throws Exception {

        JSONObject result = new JSONObject();

        //course_student,sm_sign_case,student
        System.out.println("进入    deleteOneStudent ");

        System.out.println("id === " + id);
        if (id.isEmpty()) {
            result.put("status_code", StatusCode.PARAM_ERROR);
            result.put("msg", "模块出错，删除学生失败，请重试！");
            System.out.println("result == " + result);
            ResponseUtil.write(response, result);
            return;
        }
        try {
            //云上删除学生信息
            courseStudentService.deleteOneStudent(id);
            signCaseService.deleteOneStudent(id);
            studentService.deleteOneStudent(id);
        } catch (Exception e) {
            result.put("status_code", StatusCode.TRANSACTIONS_EXE_ERROR);
            result.put("msg", "删除学生失败！请重试！");
            System.out.println("result == " + result);
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "删除学生成功!!");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }


    @RequestMapping(value = "/importAllStudentsInfo", method = RequestMethod.POST)
    public void importAllStudentsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int status_code = 0;
        String msg = "";

        System.out.println("进入 importAllStudentsInfo");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        System.out.println(file.getOriginalFilename());

        // 读取文件内容
        int total = 0;
        int available = 0;
        if (file == null) {
            status_code = StatusCode.FILE_NULL;
            msg = "文件为空，失败！！";
        }
        Map<String, Object> importInfo = studentService.importAllStudentsInfo(file);
        if (importInfo != null) {
            total = (int) importInfo.get("total");
            available = (int) importInfo.get("available");
            status_code = StatusCode.SUCCESS;
            msg = "成功！！！";
        }

        //返回
        JSONObject result = new JSONObject();

        result.put("status_code", status_code);
        result.put("msg", msg);
        result.put("available", available);
        result.put("total", total);

        System.out.println("importOneCourseStudents result == " + result);

        ResponseUtil.write(response, result);
    }


    @RequestMapping(value = "/exportStudents", method = RequestMethod.GET)
    public void exportStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("进入了exportStudents ");

        String fileName = "studentsInfo.xlsx";

        List<StudentDO> studentDOList = studentService.getAllStudentsInfo();

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (StudentDO studentDO : studentDOList) {
            MajorDO majorDO = majorService.getMajorInfoByID(studentDO.getMajor_id());
            ClassDO classDO = classService.getClassInfoByClassID(studentDO.getClass_id());
            rows.add(new StudentInfoShow(studentDO, classDO, majorDO));
        }

        // 获取生成的文件
        File file = studentService.exportStudents(rows);

        // 写出文件
        if (file != null) {
            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[8192];

            int len;
            while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
            inputStream.close();
            outputStream.close();
        }
    }
}