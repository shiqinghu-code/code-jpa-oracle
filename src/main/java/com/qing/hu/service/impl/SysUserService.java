package com.qing.hu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.qing.hu.common.Result;
import com.qing.hu.entity.SysUser;
import com.qing.hu.manager.ISysUserManager;
import com.qing.hu.service.ISysUserService;
@Service
public class SysUserService implements ISysUserService {
	@Autowired
	ISysUserManager sysUserManager;
	
	@Override
	public Result add(SysUser s) {
		// TODO Auto-generated method stub
		 int i= sysUserManager.saveSelective(s);
		 return Result.success(i);
	}

	@Override
	public Result delete(SysUser s) {
		// TODO Auto-generated method stub
		 int i= sysUserManager.delete(s);
		 return Result.success(i);
	}

	@Override
	public Result updae(SysUser s) {
		// TODO Auto-generated method stub
		int i= sysUserManager.updateByPrimaryKeySelective(s);
		return Result.success(i);
	}

	@Override
	public Result findById(String id) {
		// TODO Auto-generated method stub
		SysUser s= sysUserManager.findById(id);
		return Result.success(s);
	}

	@Override
	public Result findByPage(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageInfo<SysUser> ss=sysUserManager.getPageList(null, pageNum, pageSize);
		return Result.success(ss);
	}

}
