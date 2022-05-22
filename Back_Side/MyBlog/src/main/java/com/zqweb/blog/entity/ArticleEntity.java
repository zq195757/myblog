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
@TableName("article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 文章标题
	 */
	private String titile;
	/**
	 * 文章作者
	 */
	private String owner;
	/**
	 * 文章链接地址
	 */
	private String linkAddr;
	/**
	 * 文章发表时间
	 */
	private Date createTime;
	/**
	 * 文章更新时间
	 */
	private Date updateTime;
	/**
	 * 文章点赞量
	 */
	private Integer likeNum;
	/**
	 * 文章评论量
	 */
	private Integer commentNum;
	/**
	 * 文章转发量
	 */
	private Integer mailNum;
	/**
	 * 文章阅读量
	 */
	private Integer readNum;
	/**
	 * 文章大类别
	 */
	private String bigSubject;
	/**
	 * 文章小类别
	 */
	private String smallSubject;
	/**
	 * 文章详细类别
	 */
	private String detailSubject;
	/**
	 * 文章总字数
	 */
	private String totalChars;
	/**
	 * 文章创作类型（0：原创 1：引用）
	 */
	private Integer createType;
	/**
	 * 文章所属详细归类（关联了smallSubject，smallSubject又关联了subject）
	 */
	private Long detailSubjectId;

}
