package com.zqweb.blog.dao;

import com.zqweb.blog.entity.SiteSubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 与网站标签表构成主外键关联
 * 
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@Mapper
public interface SiteSubjectDao extends BaseMapper<SiteSubjectEntity> {
	
}
