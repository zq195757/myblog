package com.zqweb.blog.dao;

import com.zqweb.blog.entity.SiteLabelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 与网站分类表构成主外键关联（标签表是主表，分类表是从表）
 * 
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@Mapper
public interface SiteLabelDao extends BaseMapper<SiteLabelEntity> {
	
}
