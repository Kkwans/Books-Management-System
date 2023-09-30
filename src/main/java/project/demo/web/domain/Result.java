package project.demo.web.domain;

import lombok.Data;

//与前端进行数据传输的消息封装类
@Data
public class Result {
    //描述统一格式中的数据
    private Object data;
    //描述统一格式中的编码，用于区分操作，可以简化配置：0表示失败，1表示成功
    private Integer code;
    //描述统一格式中的消息，可选属性
    private String msg;

    public Result() {
    }
    //构造方法是方便对象的创建
    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }
    //构造方法是方便对象的创建
    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static Result success(Object data){
        return new Result(1,data,"success");
    }

    public static Result error(Object data){
        return new Result(0,data,"error");
    }
}
