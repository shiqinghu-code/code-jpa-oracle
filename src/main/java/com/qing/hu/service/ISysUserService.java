package com.qing.hu.service;

import com.qing.hu.common.Result;
import com.qing.hu.entity.SysUser;

public interface ISysUserService {

	Result add(SysUser s);

	Result delete(SysUser s);

	Result updae(SysUser s);

	Result findById(String id);

	Result findByPage(Integer pageNum, Integer pageSize);

}
