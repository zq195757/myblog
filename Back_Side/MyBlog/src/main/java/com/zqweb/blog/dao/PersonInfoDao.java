package com.zqweb.blog.dao;

import com.zqweb.blog.entity.PersonInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@Mapper
public interface PersonInfoDao extends BaseMapper<PersonInfoEntity> {
	
}
