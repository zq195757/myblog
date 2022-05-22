/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.zqweb.blog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	/*
	 由于R继承了HashMap，设置泛型属性，是无法生效的，解析会存在异常
	 可通过1个阿里提供TypeReference<T>工具类，先获取要转换的data，转换为String，最后通过调用
	 getData方法时传入特定泛型的typeReference，将String再解析为特定泛型的类型数据，最终返回
	  */
	public <T>  T getData(TypeReference<T> typeReference) {
		Object data = get("data");
		String str = JSON.toJSONString(data);
		T t = JSON.parseObject(str, typeReference);
		return t;
	}
	// getData的重载方法，获取指定key的数据，并封装成指定类型的数据，然后返回
	public <T>  T getData(String key, TypeReference<T> typeReference) {
		Object data = get(key);
		String str = JSON.toJSONString(data);
		T t = JSON.parseObject(str, typeReference);
		return t;
	}
	// 要给R存入内容，还是调用map提供的put方法
	public R setData(Object data) {
		put("data",data);
		return this;// 注意：还是要返回R类对象
	}

	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Integer getCode(){
		return (Integer)this.get("code");
	}

}
