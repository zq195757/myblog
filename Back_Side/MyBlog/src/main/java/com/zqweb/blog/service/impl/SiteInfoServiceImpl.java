package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteInfoDao;
import com.zqweb.blog.entity.SiteInfoEntity;
import com.zqweb.blog.service.SiteInfoService;


@Service("siteInfoService")
public class SiteInfoServiceImpl extends ServiceImpl<SiteInfoDao, SiteInfoEntity> implements SiteInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteInfoEntity> page = this.page(
                new Query<SiteInfoEntity>().getPage(params),
                new QueryWrapper<SiteInfoEntity>()
        );

        return new PageUtils(page);
    }

}