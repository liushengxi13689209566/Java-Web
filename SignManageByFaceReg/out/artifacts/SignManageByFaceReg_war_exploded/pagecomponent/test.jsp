<%--
  Created by IntelliJ IDEA.
  User: tattoo
  Date: 2020/3/29
  Time: 4:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>实验课考勤信息查看</title>
</head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

<body>

<div>
    <select class="selectpicker">
        <option>Mustard</option>
        <option>Ketchup</option>
        <option>Barbecue</option>
    </select>
</div>

<div class="panel panel-default">
    <ol class="breadcrumb">
        <li>系统学生信息管理</li>
    </ol>
    <div class="panel-body">

        <select id="select" class="sel">
            <option value="weiguo">魏国</option>
            <option value="shuguo">蜀国</option>
            <option value="wuguo">吴国</option>
        </select>

        <select id="val" class="sel"></select>　

    </div>
</div>


  <select id="YiJi" onChange="move()">

                <!-- 根据id来获取value，onChange()事件触发move()修改二级select的text值来实现联动 -->

                <option selected value="YiJi">-- 请选择 --</option>

                <!--默认选中-->

                <option value="San">3</option>

                <option value="Si">4</option>

                <option value="Wu">5</option>

            </select>

        <select id="ErJi">

                <option selected>-- 请选择 --</option>

            </select>

    <script>

    function move() {

        var YiJi = document.getElementById("YiJi");

        var ErJi = document.getElementById("ErJi");

        //获取一级和二级的属性

        var add;

        if (YiJi.value == "San") {

            add = new Array("1", "2", "3"); //对比value值，实现对应二级text值的动态生成

        } else if (YiJi.value == "Si") {

            add = new Array("1", "2", "3", "4");

        } else if (YiJi.value == "Wu") {

            add = new Array("1", "2", "3", "4", "5");

        } else if (YiJi.value == "YiJi") {

            add = new Array("请选择");

        }

        else {

            add = null; //如果没有的话就为空,在这里是不存在这种情况，不过你也可以自己设置出来；

        }

        ErJi.length = 0;

        for (var i = 0; i < add.length; i++) {

            var aaa = new Option();

            aaa.text = add[i].split()[0];

            ErJi.add(aaa);

            //把text值添加到二级select中，显示出来

        }

    }

</script>

<script type="text/javascript">
    var select = document.getElementById("select");
    select.onchange = function () {
        var selvalue = select.value;
        var val = document.getElementById("val");
        switch (selvalue) {
            case "weiguo" :
                val.innerHTML = "<option>荀彧</option><option>曹操</option>";
                break;
            case "shuguo" :
                val.innerHTML = "<option>刘备</option><option>诸葛亮</option>";
                break;
            case "wuguo" :
                val.innerHTML = "<option>孙权</option><option>周瑜</option>";
                break;
            default :
                alert("erro");
        }
    };
</script>

</body>
</html>
