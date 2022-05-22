package com.zqweb.blog.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zqweb.blog.entity.SiteInfoEntity;
import com.zqweb.blog.service.SiteInfoService;
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
@RequestMapping("blog/siteinfo")
public class SiteInfoController {
    @Autowired
    private SiteInfoService siteInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = siteInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SiteInfoEntity siteInfo = siteInfoService.getById(id);

        return R.ok().put("siteInfo", siteInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SiteInfoEntity siteInfo){
		siteInfoService.save(siteInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SiteInfoEntity siteInfo){
		siteInfoService.updateById(siteInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		siteInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
