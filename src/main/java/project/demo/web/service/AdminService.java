package project.demo.web.service;

import org.apache.ibatis.annotations.Insert;
import org.springframework.transaction.annotation.Transactional;
import project.demo.web.domain.Book;
import project.demo.web.domain.PageBean;

import java.util.List;

//@Service 不能写在接口上！！！！！！！！！
/*@Service注解是标注在实现类上的，因为@Service是把spring容器中的bean进行实例化，
也就是等同于new操作，只有实现类是可以进行new实例化的，而接口则不能，所以是加在实现类上的。*/
@Transactional//给所有方法开启Spring事务
public interface AdminService {
    //添加Book数据行，{}中为Book中的属性名
    public boolean addBook(Book book);

    //根据id删除book行
    public boolean deleteBook(String ISBN);

    //根据id删除book行
    public boolean deleteInBathes(String[] ids);

    //根据id更新属性值
    public boolean updateBook(Book book,String oldISBN);

    //查询所有数据
    public List<Book> selectAllBook();

    //根据id查询book行
    public Book selectBookByID(String ISBN);


    public PageBean<Book> selectByPage(Integer page, Integer pageSize);

    public PageBean<Book> selectByPageAndCondition(Integer page,Integer pageSize,String prop,String order,Book book);
}
