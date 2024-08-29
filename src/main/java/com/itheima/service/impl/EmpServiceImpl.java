package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        //获取总记录数
        Long count = empMapper.count();
        //获取分页查询列表
        Integer start = (page-1)*pageSize;
        List<Emp> list =  empMapper.page(start,pageSize);
        //封装进pageBean
        PageBean pageBean = new PageBean(count,list);
        return pageBean;
    }*/

    /**
     * 使用pageHelper插件完成分页查询
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询
        List<Emp> list =  empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;
        //封装进pageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());//方法的含义参照修改前代码
        return pageBean;
    }


    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }


    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    /**
     * 用户登录
     * @param emp
     * @return
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
