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
@TableName("site_feedback")
public class SiteFeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 反馈类型(0：bug修复  1：需求增添)
	 */
	private Integer type;
	/**
	 * 联系邮箱
	 */
	private String connectionEmail;
	/**
	 * 反馈标题
	 */
	private String title;
	/**
	 * 反馈详细描述
	 */
	private String detail;
	/**
	 * 反馈提交时间
	 */
	private Date publishTime;

}
