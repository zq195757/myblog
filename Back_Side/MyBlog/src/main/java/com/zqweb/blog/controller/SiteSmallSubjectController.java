package com.zqweb.blog.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zqweb.blog.entity.SiteSmallSubjectEntity;
import com.zqweb.blog.service.SiteSmallSubjectService;
import com.zqweb.blog.utils.PageUtils;
import com.zqweb.blog.utils.R;



/**
 * 
 *
 * @author zq
 * @email zq@gmail.com
 * @date 2022-05-04 12:17:09
 */
@RestController
@RequestMapping("blog/sitesmallsubject")
public class SiteSmallSubjectController {
    @Autowired
    private SiteSmallSubjectService siteSmallSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = siteSmallSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SiteSmallSubjectEntity siteSmallSubject = siteSmallSubjectService.getById(id);

        return R.ok().put("siteSmallSubject", siteSmallSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SiteSmallSubjectEntity siteSmallSubject){
		siteSmallSubjectService.save(siteSmallSubject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SiteSmallSubjectEntity siteSmallSubject){
		siteSmallSubjectService.updateById(siteSmallSubject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		siteSmallSubjectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
