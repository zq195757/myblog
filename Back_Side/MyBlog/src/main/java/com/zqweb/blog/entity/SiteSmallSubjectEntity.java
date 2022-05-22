package com.zqweb.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@Data
@TableName("site_small_subject")
public class SiteSmallSubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 小分类名
	 */
	private String name;
	/**
	 * 链接跳转地址
	 */
	private String linkAddr;
	/**
	 * 关联的大分类id
	 */
	private Long subjectId;

}
