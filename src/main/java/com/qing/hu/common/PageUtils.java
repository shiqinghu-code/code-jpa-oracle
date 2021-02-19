package com.qing.hu.common;

import com.github.pagehelper.PageInfo;

public class PageUtils {

	public static String DEFAULT_ROWS = "15";  
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest 
     * @param page
     * @return
     */
    public static PageResult getPageResult( PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotal(pageInfo.getTotal());
       // pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setRows(pageInfo.getList());
        return pageResult;
    }
}