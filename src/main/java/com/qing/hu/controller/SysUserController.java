package com.qing.hu.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qing.hu.common.Result;
import com.qing.hu.entity.SysUser;
import com.qing.hu.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试")
@RestController
@RequestMapping("/sysuser")
@Validated
public class SysUserController {
    @Autowired
    ISysUserService sysUserService;
 	@ApiOperation("add")
    @PostMapping(value = "/add")
    public Result add(@Valid SysUser s) {
        return sysUserService.add(s);
    }
 	@ApiOperation("delete")
    @PostMapping(value = "/delete")
    public Result delete(SysUser s) {
        return sysUserService.delete(s);
    }
 	@ApiOperation("updae")
    @PostMapping(value = "/updae")
    public Result updae(SysUser s) {
        return sysUserService.updae(s);
    }
 	@ApiOperation("findById")
    @GetMapping(value = "/findById")
    public Result findById(@NotEmpty(message = "id 不能为空") String id) {
        return sysUserService.findById(id);
    }
 	@ApiOperation("findByPage")
 	@GetMapping(value = "/findByPage")
    public Result findByPage(Integer pageNum, Integer pageSize) {
        return sysUserService.findByPage( pageNum,  pageSize);
    }
    
    
}