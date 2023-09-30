package project.demo.web.domain;

//状态码
public class Code {
    //200为状态码，表示响应正常，最后的1表示成功，0表示失败/查询数据为空
    public static final Integer ADD_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer SELECT_OK = 20041;

    public static final Integer ADD_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer SELECT_ERR = 20040;

    public static final Integer SYSTEM_ERR = 50001;//系统异常
    public static final Integer BUSINESS_ERR = 50002;//业务异常
    public static final Integer UNKNOWN_ERR = 50003;//未知异常
}

/*public enum Code {

    ADD_OK(20011),DELETE_OK(20021),UPDATE_OK(20031),SELECT_OK(20041),
    ADD_ERR(20010),DELETE_ERR(20020),UPDATE_ERR(20030),SELECT_ERR(20040);
    //200为状态码，表示响应正常，最后的1表示成功，0表示失败/查询数据为空
    private Integer code;

    Code(Integer code) {
        this.code = code;
    }
}*/
