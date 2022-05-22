package com.zqweb.blog.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zqweb.blog.entity.SiteSubjectEntity;
import com.zqweb.blog.service.SiteSubjectService;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.R;



/**
 * 与网站标签表构成主外键关联
 *
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@RestController
@RequestMapping("blog/sitesubject")
public class SiteSubjectController {
    @Autowired
    private SiteSubjectService siteSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = siteSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SiteSubjectEntity siteSubject = siteSubjectService.getById(id);

        return R.ok().put("siteSubject", siteSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SiteSubjectEntity siteSubject){
		siteSubjectService.save(siteSubject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SiteSubjectEntity siteSubject){
		siteSubjectService.updateById(siteSubject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		siteSubjectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
