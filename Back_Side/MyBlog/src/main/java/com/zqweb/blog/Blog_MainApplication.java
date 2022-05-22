package com.zqweb.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述：主程序类
 * @Author: zq
 * @Date: 2022年05月01日 22:07
 */
@SpringBootApplication
//@MapperScan(basePackages = "{com.zqweb.blog.dao}") // 如果dao接口注明了bean声明注解，这里就需要再声明mapper扫描路径，否则bean会找不到
public class Blog_MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Blog_MainApplication.class,args);
    }
}
