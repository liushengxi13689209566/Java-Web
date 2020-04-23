package com.huarun.utils;

public class StatusCode {
    public static final int CALL_ERROR = 405;
    public static final int CALL_FAILED = 404;

    public static final int SUCCESS = 0;

    public static final int FACE_COUNT_NOT_ONE = 1;
    public static final int NO_SEARCH_RESULT = 2;

    //登录验证,退出
    public static final int UNKNOWN_ACCOUNT = 3;
    public static final int INCORRECT_CREDENTIALS = 4;
    public static final int AUTHENTICATION_ERROR = 5;
    public static final int LOGOUT_ERROR = 6;


    public static final int FILE_NULL = 7;
    public static final int FACE_NOT_CLEAR = 8;
    public static final int FACE_OVERFLOW = 9;
    public static final int FACE_SHELTER = 10;
    public static final int AGE_OVER = 11;

    public static final int NO_USERID = 12;
    public static final int CLASS_LATE = 13;
    public static final int NO_COURSE_TIME = 14;
    public static final int PASSWORD_INCORRECT = 15;

    //    我们用 0 1 2 表示 未考勤，考勤成功，迟到，后续还可以加入更多(课时不可能超过 100 节)
    public static final char SIGN_SUCCESS = '1';
    public static final char SIGN_LATE = '2';


}
