package com.zqweb.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.entity.SiteSubjectEntity;

import java.util.Map;

/**
 * 与网站标签表构成主外键关联
 *
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
public interface SiteSubjectService extends IService<SiteSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

