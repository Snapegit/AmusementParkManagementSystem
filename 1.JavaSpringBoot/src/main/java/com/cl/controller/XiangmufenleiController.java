package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.XiangmufenleiEntity;
import com.cl.entity.view.XiangmufenleiView;

import com.cl.service.XiangmufenleiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 项目分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
@RestController
@RequestMapping("/xiangmufenlei")
public class XiangmufenleiController {
    @Autowired
    private XiangmufenleiService xiangmufenleiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiangmufenleiEntity xiangmufenlei,
		HttpServletRequest request){
        EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();

		PageUtils page = xiangmufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmufenlei), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiangmufenleiEntity xiangmufenlei, 
		HttpServletRequest request){
        EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();

		PageUtils page = xiangmufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmufenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiangmufenleiEntity xiangmufenlei){
       	EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiangmufenlei, "xiangmufenlei")); 
        return R.ok().put("data", xiangmufenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiangmufenleiEntity xiangmufenlei){
        EntityWrapper< XiangmufenleiEntity> ew = new EntityWrapper< XiangmufenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiangmufenlei, "xiangmufenlei")); 
		XiangmufenleiView xiangmufenleiView =  xiangmufenleiService.selectView(ew);
		return R.ok("查询项目分类成功").put("data", xiangmufenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiangmufenleiEntity xiangmufenlei = xiangmufenleiService.selectById(id);
		xiangmufenlei = xiangmufenleiService.selectView(new EntityWrapper<XiangmufenleiEntity>().eq("id", id));
        return R.ok().put("data", xiangmufenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiangmufenleiEntity xiangmufenlei = xiangmufenleiService.selectById(id);
		xiangmufenlei = xiangmufenleiService.selectView(new EntityWrapper<XiangmufenleiEntity>().eq("id", id));
        return R.ok().put("data", xiangmufenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
    	xiangmufenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiangmufenlei);
        xiangmufenleiService.insert(xiangmufenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
    	xiangmufenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiangmufenlei);
        xiangmufenleiService.insert(xiangmufenlei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmufenlei);
        xiangmufenleiService.updateById(xiangmufenlei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiangmufenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
