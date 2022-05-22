package com.zqweb.blog;

import com.zqweb.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能描述
 *
 * @Author: zq
 * @Date: 2022年05月13日 11:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplication {
    @Autowired
    private ArticleService articleService;

    @Test
    // 初始化测试
    public void initTest() {
        System.out.println("ArticleService = " + articleService);
    }

}
