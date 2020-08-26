package com.tplink.constant;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/24 16:04
 * 用到的字符串型常量
 */
public class StringConstant {
    /**
     * mapping路径常量
     */
    public static final String REGISTER_MAPPING = "/register";
    public static final String LOGIN_MAPPING = "/login";
    public static final String LOGIN_NUM_MAPPING= "/count";

    /**
     * JPA sql语句常量
     */
    public static final String LOGIN_SQL = "select p from People p where p.name = :name and p.password = :password";
    public static final String SELECT_NAME_SQL = "select p from People p where p.name = :name";
    public static final String LOGIN_NUM_SQL = "select p from LoginNumber p where p.time > :time and p.name = :name";

    /**
     * 数据库字段及回复字段常量
     */
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String LOGIN_TIME = "time";
    public static final String SUCCESS_SIGN = "success";
    public static final String FAIL_SIGN = "fail";
    public static final String NAME_REPEAT = "Duplicate user name";

    /**
     * 单元测试中用到的常量
     */
    public static final String REGISTER_TEST_NAME = "张三";
    public static final String REGISTER_TEST_PASSWORD = "000";
    public static final String REGISTER_TEST_EMAIL = "000@qq.com";
    public static final String REGISTER_TEST_ADDRESS = "昆明";

    /**
     * 其余常量，包括配置文件路径，AOP切点方法，AOP增强点等
     */
    public static final String UTC = "UTC";
    public static final String AOP_POINT = "pointCut()";
    public static final String AOP_PATH = "execution(* com.tplink.controller.PeopleController.login(..))";
    public static final String AOP_EXCEPTION_MESSAGE = "LoginAop处异常信息";
    public static final String PROPERTIES_PATH = "D:\\seeing\\MyDemo\\demo\\demo\\src\\main\\resources\\log4j.properties";
    public static final String PEOPLE_REPOSITORY_TEST_EXCEPTION_MESSAGE = "PeopleRepositoryTest处异常信息";
    public static final String LOGIN_REPOSITORY_TEST_EXCEPTION_MESSAGE = "LoginRepositoryTest处异常信息";
}
