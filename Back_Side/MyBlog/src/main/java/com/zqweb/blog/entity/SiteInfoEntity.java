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
@TableName("site_info")
public class SiteInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 文章总量
	 */
	private Long articleNum;
	/**
	 * 分类总量
	 */
	private Integer bigSubjectNum;
	/**
	 * 标签总量(导航项)
	 */
	private Integer labelNum;
	/**
	 * 总访问量
	 */
	private Long accessNum;
	/**
	 * 建站时间
	 */
	private Date buildSiteTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
