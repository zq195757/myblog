package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteSmallSubjectDao;
import com.zqweb.blog.entity.SiteSmallSubjectEntity;
import com.zqweb.blog.service.SiteSmallSubjectService;


@Service("siteSmallSubjectService")
public class SiteSmallSubjectServiceImpl extends ServiceImpl<SiteSmallSubjectDao, SiteSmallSubjectEntity> implements SiteSmallSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteSmallSubjectEntity> page = this.page(
                new Query<SiteSmallSubjectEntity>().getPage(params),
                new QueryWrapper<SiteSmallSubjectEntity>()
        );

        return new PageUtils(page);
    }

}