package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();


    /**
     * 根据id删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);


    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Dept select(Integer id);

    /**
     * 修改数据
     * @return
     */
    void update(Dept dept);
}
