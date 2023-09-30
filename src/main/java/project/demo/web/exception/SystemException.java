package project.demo.web.exception;

//自定义异常类，系统异常（可预期但不可避免的异常）
//继承RuntimeException，异常会自动向上抛出，不用throws
public class SystemException extends RuntimeException{
    private Integer code;

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
