package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.SiteSubjectDao;
import com.zqweb.blog.entity.SiteSubjectEntity;
import com.zqweb.blog.service.SiteSubjectService;


@Service("siteSubjectService")
public class SiteSubjectServiceImpl extends ServiceImpl<SiteSubjectDao, SiteSubjectEntity> implements SiteSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SiteSubjectEntity> page = this.page(
                new Query<SiteSubjectEntity>().getPage(params),
                new QueryWrapper<SiteSubjectEntity>()
        );

        return new PageUtils(page);
    }

}