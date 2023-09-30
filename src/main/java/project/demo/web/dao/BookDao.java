package project.demo.web.dao;

import org.apache.ibatis.annotations.*;
import project.demo.web.domain.Book;


import java.util.List;

//添加@Mapper注解，让SpringBoot能扫描到该自动代理
@Mapper
public interface BookDao {
    //添加Book数据行，{}中为Book中的属性名
    @Insert("insert into tb_book values (#{ISBN},#{name},#{type},#{press},#{num})")
    public int add(Book book);

    //根据ISBN编号删除book行
    @Delete("delete from tb_book where ISBN = #{ISBN};")
    public int delete(String ISBN);

    //根据ISBN集合批量删除book
    public int deleteInBathes(String[] ids);

    //根据ISBN更新属性值
    @Update("update tb_book set ISBN=#{book.ISBN},name = #{book.name},type = #{book.type},press = #{book.press},num=#{book.num} where ISBN = #{oldISBN};")
    public int update(@Param("book")Book book,@Param("oldISBN")String oldISBN);


    //查询所有数据
    @Select("select * from tb_book;")
    public List<Book> selectAll();

    //根据ISBN查询book行
    @Select("select * from tb_book where ISBN = #{ISBN};")
    public Book selectByID(String ISBN);

    //分页查询,start为当前页开始索引，pageSize当前页展示数量
    @Select("select * from tb_book limit #{start},#{pageSize}")/*多个属性需要使用@Param映射*/
    public List<Book> selectByPage(@Param("start")Integer start,@Param("pageSize")Integer pageSize);

    //获取总记录数
    @Select("select count(*) from tb_book")
    public Long count();

    //多条件分页查询
    public List<Book> selectByPageAndCondition
    (@Param("start")Integer start,@Param("pageSize")Integer pageSize,
     @Param("prop")String prop,@Param("order")String order,
     @Param("book") Book book);

    //获取满足条件总记录数
    public Long countByCondition(@Param("book")Book book);
}
