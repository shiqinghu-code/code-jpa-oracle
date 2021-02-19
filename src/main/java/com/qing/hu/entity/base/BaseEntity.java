package com.qing.hu.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

	@Column(columnDefinition = "number ")
	private Timestamp updTime;

	@Column(columnDefinition = "varchar(32) ")
	private String updUserId;

	@Column(columnDefinition = "number ")
	private Timestamp insTime;

	@Column(columnDefinition = "varchar(32) ")
	private String insUserId;

	@Column(columnDefinition = "varchar2(1)  ")
	private String delFlg;
}
