package com.zqweb.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.entity.SiteLabelEntity;

import java.util.Map;

/**
 * 与网站分类表构成主外键关联（标签表是主表，分类表是从表）
 *
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
public interface SiteLabelService extends IService<SiteLabelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

