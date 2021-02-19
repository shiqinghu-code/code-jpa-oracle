package com.qing.hu.manager.impl;

import com.qing.hu.entity.SysUser;
import com.qing.hu.mapper.SysUserMapper;
import com.qing.hu.mapper.base.BaseMapper;
import com.qing.hu.manager.base.impl.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import com.qing.hu.manager.ISysUserManager;

@Service
public class SysUserManager  extends BaseManager<SysUser, String, Object> implements ISysUserManager {
    @Autowired 
    private SysUserMapper sysUserMapper; 
    // 重写BaseManagerImpl抽象方法，将当前Mapper返回 
    @Override 
    protected BaseMapper<SysUser> getBaseMapper() { return sysUserMapper; } 

}