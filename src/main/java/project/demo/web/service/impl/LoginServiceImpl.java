package project.demo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.web.dao.AdminDao;
import project.demo.web.dao.BookDao;
import project.demo.web.domain.Admin;
import project.demo.web.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin adminLogin(String id, String pass) {
        return adminDao.selectAdmin(id, pass);
    }
}
