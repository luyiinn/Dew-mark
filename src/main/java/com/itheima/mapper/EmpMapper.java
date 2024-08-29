package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {


    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(id) from emp")
    public Long count();

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start,Integer pageSize);

    /**
     * 员工信息查询
     * @return
     */
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 更新员工
     * @param emp
     */
    void update(Emp emp);


    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除该部门下所有员工数据
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
