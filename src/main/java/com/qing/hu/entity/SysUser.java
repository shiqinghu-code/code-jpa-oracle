package com.qing.hu.entity;

import com.qing.hu.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.sql.*;

@Entity
@SuperBuilder
@Data
@Table
@org.hibernate.annotations.Table(appliesTo = "sys_user",comment="用户表")
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {

    @Id
    @Column(columnDefinition = "varchar2(32) ") 
    @NotBlank(message = "用户ID不能为空")
    private String userId; 

    @Column(columnDefinition = "varchar2(20) ") 
    @NotBlank(message = "用户名不能为空")
    private String name; 


}