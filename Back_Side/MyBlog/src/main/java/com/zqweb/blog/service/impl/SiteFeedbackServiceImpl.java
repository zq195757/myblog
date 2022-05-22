package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteFeedbackDao;
import com.zqweb.blog.entity.SiteFeedbackEntity;
import com.zqweb.blog.service.SiteFeedbackService;


@Service("siteFeedbackService")
public class SiteFeedbackServiceImpl extends ServiceImpl<SiteFeedbackDao, SiteFeedbackEntity> implements SiteFeedbackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteFeedbackEntity> page = this.page(
                new Query<SiteFeedbackEntity>().getPage(params),
                new QueryWrapper<SiteFeedbackEntity>()
        );

        return new PageUtils(page);
    }

}