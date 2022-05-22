package com.zqweb.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.entity.SiteFeedbackEntity;

import java.util.Map;

/**
 * 
 *
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
public interface SiteFeedbackService extends IService<SiteFeedbackEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

