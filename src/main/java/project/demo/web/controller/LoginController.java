package project.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.demo.web.domain.Admin;
import project.demo.web.domain.Result;
import project.demo.web.service.AdminService;
import project.demo.web.service.LoginService;
import project.demo.web.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController//进行rest风格的web开发
@RequestMapping("/login")//根路径,localhost:80/login访问，不需要加项目名称！！！
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping
    public Result adminLogin(@RequestBody Admin admin){
        System.out.println("管理员登录 ===> "+admin.getId()+" "+admin.getPass());
        Admin loginAdmin = service.adminLogin(admin.getId(), admin.getPass());
        System.out.println(loginAdmin!=null);
        if(loginAdmin!=null){
            //自定义信息
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", loginAdmin.getId());
            claims.put("name",loginAdmin.getName());

            //使用JWT工具类，生成身份令牌
            String token = JwtUtil.generateJwt(claims);
            System.out.println("token=" +token);
            return Result.success(token);
        }
        return  Result.error("账号或密码错误！");
    }
}
