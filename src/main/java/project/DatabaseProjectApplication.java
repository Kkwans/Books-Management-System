package project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableTransactionManagement//统一开启Spring事务管理
@ServletComponentScan //开启Servlet组件支持（Filter过滤器）
//@MapperScan("project.demo.web.dao")  //扫描的是*Dao.xml中namespace指向的包,等价于每个dao类添加@Mapper注解
public class DatabaseProjectApplication{

    public static void main(String[] args) {
        SpringApplication.run(DatabaseProjectApplication.class, args);
    }
}
