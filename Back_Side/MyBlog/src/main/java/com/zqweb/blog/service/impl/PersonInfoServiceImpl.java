package com.zqweb.blog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.Query;

import com.zqweb.blog.dao.PersonInfoDao;
import com.zqweb.blog.entity.PersonInfoEntity;
import com.zqweb.blog.service.PersonInfoService;


@Service("personInfoService")
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoDao, PersonInfoEntity> implements PersonInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PersonInfoEntity> page = this.page(
                new Query<PersonInfoEntity>().getPage(params),
                new QueryWrapper<PersonInfoEntity>()
        );

        return new PageUtils(page);
    }

}