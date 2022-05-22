package com.zqweb.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 与网站标签表构成主外键关联
 * 
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@Data
@TableName("site_subject")
public class SiteSubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 分类名
	 */
	private String name;
	/**
	 * 分类跳转地址
	 */
	private String linkAddr;
	/**
	 * 外键labelID
	 */
	private Long labelId;

}
