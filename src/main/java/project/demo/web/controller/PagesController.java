package project.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
    //@RequestMapping表示该方法处理所有请求方法和请求路径以/login开头的请求
    @RequestMapping("/login")
    public String loginPage() {
        return "pages/login.html";
    }

    /*@RequestMapping("/")
    public String indexPage() {
        return "pages/login.html";
    }*/

    @RequestMapping("/pleaseLogin")
    public String pleaseLoginPage() {
        return "pages/pleaseLogin.html";
    }

    @RequestMapping("/adminHome")
    public String adminPage() {
        return "pages/admin.html";
    }
}
