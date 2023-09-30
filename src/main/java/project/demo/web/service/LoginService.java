package project.demo.web.service;

import org.springframework.transaction.annotation.Transactional;
import project.demo.web.domain.Admin;

@Transactional//给所有方法开启Spring事务
public interface LoginService {
    public Admin adminLogin(String id, String pass);
}
