package project.demo.web.service.impl;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.web.dao.BookDao;
import project.demo.web.domain.Book;
import project.demo.web.domain.PageBean;
import project.demo.web.service.AdminService;


import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean addBook(Book book) {
        int addNum = bookDao.add(book);
        /*if(addNum==1) return true;
        else return false;*/
        return addNum==1;
    }

    @Override
    public boolean deleteBook(String ISBN) {
        int deleteNum = bookDao.delete(ISBN);
        return deleteNum==1;
    }

    @Override
    public boolean deleteInBathes(String[] ids) {
        int deleteNum = bookDao.deleteInBathes(ids);
        return deleteNum== ids.length;
    }

    @Override
    public boolean updateBook(Book book,String oldISBN) {
        int updateNum = bookDao.update(book,oldISBN);
        return updateNum==1;
    }

    @Override
    public List<Book> selectAllBook() {
        return bookDao.selectAll();
    }

    @Override
    public Book selectBookByID(String ISBN) {
        return bookDao.selectByID(ISBN);
    }

    @Override
    public PageBean<Book> selectByPage(Integer page, Integer pageSize) {
        //获取总记录数
        Long count = bookDao.count();
        //获取分页查询结果
        Integer start = (page-1)*pageSize;
        List<Book> books = bookDao.selectByPage(start, pageSize);
        //封装Page对象
        PageBean<Book> pageBean = new PageBean<>(count,books);
        return pageBean;

    }

    @Override
    public PageBean<Book> selectByPageAndCondition(Integer page,Integer pageSize,String prop,String order,Book book) {
        //将数据处理成模糊表达式（也可以通过MyBatis函数contact处理)
        /*String ISBN =book.getISBN();
        if(ISBN!=null&&ISBN.length()>0){//如果有数据，才添加模糊表达式，否则查询不符合条件
            ISBN = "%"+ISBN+"%";
            book.setISBN(ISBN);
        }*/
        //获取总记录数
        Long count = bookDao.countByCondition(book);
        //计算开始索引
        Integer start = (page-1)*pageSize;
        //获取查询结果
        List<Book> books = bookDao.selectByPageAndCondition(start, pageSize,prop,order, book);
        //封装Page对象
        PageBean<Book> pageBean = new PageBean<>(count,books);
        return pageBean;
    }
}
