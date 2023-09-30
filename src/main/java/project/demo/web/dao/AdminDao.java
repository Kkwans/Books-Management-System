package project.demo.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.demo.web.domain.Admin;

@Mapper
public interface AdminDao {
    //根据账号密码查找admin
    @Select("select * from tb_admin where id = #{id} and pass = #{pass};")
    public Admin selectAdmin(@Param("id")String id, @Param("pass")String pass);
}
