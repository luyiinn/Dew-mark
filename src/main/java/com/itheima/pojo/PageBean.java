package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查找结果封装集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private long total;//总记录数
    private List rows;//数据列表

}
