package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteDetailSubjectDao;
import com.zqweb.blog.entity.SiteDetailSubjectEntity;
import com.zqweb.blog.service.SiteDetailSubjectService;


@Service("siteDetailSubjectService")
public class SiteDetailSubjectServiceImpl extends ServiceImpl<SiteDetailSubjectDao, SiteDetailSubjectEntity> implements SiteDetailSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteDetailSubjectEntity> page = this.page(
                new Query<SiteDetailSubjectEntity>().getPage(params),
                new QueryWrapper<SiteDetailSubjectEntity>()
        );

        return new PageUtils(page);
    }

}