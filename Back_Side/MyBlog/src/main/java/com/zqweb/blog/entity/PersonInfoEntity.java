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
@TableName("person_info")
public class PersonInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 微信公众号名
	 */
	private String wxAccountName;
	/**
	 * 微信公众号二维码
	 */
	private String wxAccountQrCode;
	/**
	 * csdn博客用户id
	 */
	private Long csdnUserId;

}
