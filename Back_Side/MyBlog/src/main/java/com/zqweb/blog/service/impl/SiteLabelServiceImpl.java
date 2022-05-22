package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteLabelDao;
import com.zqweb.blog.entity.SiteLabelEntity;
import com.zqweb.blog.service.SiteLabelService;


@Service("siteLabelService")
public class SiteLabelServiceImpl extends ServiceImpl<SiteLabelDao, SiteLabelEntity> implements SiteLabelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteLabelEntity> page = this.page(
                new Query<SiteLabelEntity>().getPage(params),
                new QueryWrapper<SiteLabelEntity>()
        );

        return new PageUtils(page);
    }

}