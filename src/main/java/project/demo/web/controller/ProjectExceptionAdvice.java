package project.demo.web.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.demo.web.domain.Code;
import project.demo.web.domain.Result;
import project.demo.web.exception.BusinessException;
import project.demo.web.exception.SystemException;


import java.sql.SQLException;

/*可以利用aop对对应的异常进行包装，再交由异常处理器处理*/
@RestControllerAdvice//标识当前类为REST风格对应的异常处理器，需要被SpringMVC扫描
public class ProjectExceptionAdvice {
    /* 处理系统异常
    - 发送固定消息传递给用户，安抚用户
      - 系统繁忙，请稍后再试
      - 系统正在维护升级，请稍后再试
      - 系统出问题，请联系系统管理员等
    - 发送特定消息给运维人员，提醒维护
      - 可以发送短信、邮箱或者是公司内部通信软件
    - 记录日志
      - 发消息和记录日志对用户来说是不可见的，属于后台程序
    */
    @ExceptionHandler({SystemException.class, SQLException.class})//设置异常拦截种类
    public Result handleSystemException(Exception e){
        System.out.println("系统异常被拦截:"+e.getMessage());
        return new Result(Code.SYSTEM_ERR,null,e.getMessage());
    }


    /*处理业务异常
    发送对应消息传递给用户，提醒规范操作
    - 大家常见的就是提示用户名已存在或密码格式不正确等
    */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(Exception e){
        System.out.println("业务异常被拦截:"+e.getMessage());
        return new Result(Code.BUSINESS_ERR,null,e.getMessage());
    }


    /*- 处理其他异常
    发送固定消息传递给用户，安抚用户
    - 发送特定消息给编程人员，提醒维护（纳入预期范围内）
      - 一般是程序没有考虑全，比如未做非空校验等
    - 记录日志
    */
    @ExceptionHandler(Exception.class)//拦截其他异常，非预期异常
    public Result handleException(Exception e){
        System.out.println("其他异常被拦截:"+e.getMessage());
        return new Result(Code.UNKNOWN_ERR,null,"系统繁忙，请稍后再试！");
    }
}
