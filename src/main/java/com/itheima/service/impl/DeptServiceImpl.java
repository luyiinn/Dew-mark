package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {


    /**
     * 注入Mapper接口
     */
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    /**
     * 查询部门
     * @return
     */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    /**
     * 根据id删除部门
     * @param id
     */
    @Transactional(rollbackFor = Exception.class) //spring 事务管理
    @Override
    public void delete(Integer id) {
        try {
            // 根据id删除部门
            deptMapper.deleteById(id);
            int i = 1 / 0;
            // 删除该部门下的员工数据
            empMapper.deleteByDeptId(id);

        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了删除部门的操作，删除的部门是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }


    /**
     * 新增部门
     * @param dept
     */
    @Override
    public void add(Dept dept) {
        // 补全属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 数据层
        deptMapper.insert(dept);
    }


    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Override
    public Dept select(Integer id) {
        Dept dept;
        dept = deptMapper.selectById(id);
        return dept;
    }


    /**
     * 修改数据
     * @param dept
     * @return
     */
    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

}
