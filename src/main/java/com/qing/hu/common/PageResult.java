package com.qing.hu.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页结果类
 */
@Data
@Setter
@Getter
public class PageResult<T> {

    private Long total;//总记录数
    private List<T> rows;//记录
    private int pageNum;
    private int pageSize;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }



    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
