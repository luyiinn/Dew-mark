package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.awt.*;

// 部门管理的Controller
// slf4j 由lombok提供，可以创建一个log对象记录日志

@RestController
@Slf4j
//抽取公共路径，简化代码
@RequestMapping("/depts")
public class DeptController {


    /**
     * 依赖注入，面向接口的方式创建deptService对象
     */
    @Autowired
    private DeptService deptService;


    /**
     * 查询全部数据
     * @return
     */
    //  限定请求方式的方式有以下两种：
    //  @RequestMapping(value = "/dept",method = RequestMethod.GET)
//    @GetMapping("/depts")
    @GetMapping
    public Result list(){
        log.info("查询全部数据");

        // 调用service层来查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }



    /**
     * 删除部门
     * @param id
     * @return
     */
//    @DeleteMapping("/depts/{id}")
    @DeleteMapping("/{id}")
    // @PathVariable 参数绑定路径
    @Log
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
//    @PostMapping("/depts")
    @PostMapping
    // @RequestBody 接收json转换为实体对象
    @Log
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        // 调用service层方法新增部门
        deptService.add(dept);
        return Result.success();
    }


    /**
     * 根据id查询数据
     * @return
     */
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("根据id查询数据:{}",id);
        Dept dept;
        dept= deptService.select(id);
        return Result.success(dept);
    }


    /**
     * 修改部门数据
     * @return
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
