package project.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.demo.web.domain.Book;
import project.demo.web.domain.PageBean;
import project.demo.web.domain.Result;
import project.demo.web.service.AdminService;


import java.util.List;
import java.util.concurrent.locks.Lock;


@RestController//进行rest风格的web开发
@RequestMapping("/books")//根路径,localhost:80/books访问，不需要加项目名称！！！
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private BookLock bookLock;

    //增加
    @PostMapping
    public Result add(@RequestBody Book book){//@RequestBody设置读取请求体json数据
        System.out.println("添加图书 ===> "+book);
        boolean flag = service.addBook(book);
//        Integer code = flag?ADD_OK:ADD_ERR;
//        String msg = flag?"添加成功":"添加失败";
//        return new Result(code,flag,msg);
        if(flag) return Result.success(null);
        else return Result.error(null);
    }

    //删除
    @DeleteMapping("/{ISBN}")
    public synchronized Result delete(@PathVariable String ISBN){//@PathVariable设置读取路径参数
        System.out.println("删除图书 ISBN="+ISBN);
        boolean flag = service.deleteBook(ISBN);
        if(flag) return Result.success(null);
        else return Result.error(null);
    }

    //批量删除
    @DeleteMapping
    public synchronized Result deleteInBatches(@RequestBody String[] ids){
        System.out.println("批量删除 数量="+ids.length);
        boolean flag = service.deleteInBathes(ids);
        if(flag) return Result.success(null);
        else return Result.error(null);
    }

    //修改
    @PutMapping("/{oldISBN}")
    public Result update(@PathVariable String oldISBN,@RequestBody Book book){
        // 获取写锁
        Lock writeLock = bookLock.getWriteLock();
        try {
            // 加锁
            writeLock.lock();
            System.out.println("update 写锁");
            // 执行图书数据修改操作

            System.out.println("修改图书 "+oldISBN+" ===> "+book);
            boolean flag = service.updateBook(book,oldISBN);
            if(flag) return Result.success(null);
            else return Result.error(null);
        }
        finally {
            // 解锁
            writeLock.unlock();
            System.out.println("update 解锁");
        }

    }

    //查询所有
    @GetMapping
    public Result selectAll(){
        System.out.println("查询所有图书");
        List<Book> books = service.selectAllBook();
        System.out.println(books);
        return Result.success(books);
    }

    //查询ISBN是否存在
    @GetMapping("/{ISBN}")
    public Result checkISBN(@PathVariable("ISBN")String ISBN){
        System.out.println("查询ISBN是否存在");
        Book book = service.selectBookByID(ISBN);
        System.out.println(book!=null);
        if(book!=null) return Result.error(null);//已存在
        else return Result.success(null);
    }

    //分页查询
    @GetMapping("/{page}/{pageSize}")
    public Result selectByPage(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize){
        System.out.println("分页查询 page="+page+" size="+pageSize);
        PageBean<Book> pageBean = service.selectByPage(page, pageSize);
        System.out.println(pageBean);
        return Result.success(pageBean);
    }

    //分页条件查询
    @PostMapping("/{page}/{pageSize}/{prop}/{order}")
    public Result selectByPageAndCondition
        (@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,
         @PathVariable("prop") String prop,@PathVariable("order") String order,
         @RequestBody Book book){
        System.out.println("分页条件查询 page="+page+" size="+pageSize+" 排序字段="+prop+" "+order+" 条件=" +book);
        PageBean<Book> pageBean = service.selectByPageAndCondition(page, pageSize,prop,order, book);
        System.out.println(pageBean);
        return Result.success(pageBean);
    }
}
