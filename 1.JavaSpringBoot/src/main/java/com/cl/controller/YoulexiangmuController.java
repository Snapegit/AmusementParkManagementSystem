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

import com.cl.entity.YoulexiangmuEntity;
import com.cl.entity.view.YoulexiangmuView;

import com.cl.service.YoulexiangmuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 游乐项目
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
@RestController
@RequestMapping("/youlexiangmu")
public class YoulexiangmuController {
    @Autowired
    private YoulexiangmuService youlexiangmuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YoulexiangmuEntity youlexiangmu,
                @RequestParam(required = false) Double jiagestart,
                @RequestParam(required = false) Double jiageend,
		HttpServletRequest request){
        EntityWrapper<YoulexiangmuEntity> ew = new EntityWrapper<YoulexiangmuEntity>();
                if(jiagestart!=null) ew.ge("jiage", jiagestart);
                if(jiageend!=null) ew.le("jiage", jiageend);

		PageUtils page = youlexiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, youlexiangmu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YoulexiangmuEntity youlexiangmu, 
                @RequestParam(required = false) Double jiagestart,
                @RequestParam(required = false) Double jiageend,
		HttpServletRequest request){
        EntityWrapper<YoulexiangmuEntity> ew = new EntityWrapper<YoulexiangmuEntity>();
                if(jiagestart!=null) ew.ge("jiage", jiagestart);
                if(jiageend!=null) ew.le("jiage", jiageend);

		PageUtils page = youlexiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, youlexiangmu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YoulexiangmuEntity youlexiangmu){
       	EntityWrapper<YoulexiangmuEntity> ew = new EntityWrapper<YoulexiangmuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( youlexiangmu, "youlexiangmu")); 
        return R.ok().put("data", youlexiangmuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YoulexiangmuEntity youlexiangmu){
        EntityWrapper< YoulexiangmuEntity> ew = new EntityWrapper< YoulexiangmuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( youlexiangmu, "youlexiangmu")); 
		YoulexiangmuView youlexiangmuView =  youlexiangmuService.selectView(ew);
		return R.ok("查询游乐项目成功").put("data", youlexiangmuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YoulexiangmuEntity youlexiangmu = youlexiangmuService.selectById(id);
		youlexiangmu = youlexiangmuService.selectView(new EntityWrapper<YoulexiangmuEntity>().eq("id", id));
        return R.ok().put("data", youlexiangmu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YoulexiangmuEntity youlexiangmu = youlexiangmuService.selectById(id);
		youlexiangmu = youlexiangmuService.selectView(new EntityWrapper<YoulexiangmuEntity>().eq("id", id));
        return R.ok().put("data", youlexiangmu);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        YoulexiangmuEntity youlexiangmu = youlexiangmuService.selectById(id);
        if(type.equals("1")) {
        	youlexiangmu.setThumbsupnum(youlexiangmu.getThumbsupnum()+1);
        } else {
        	youlexiangmu.setCrazilynum(youlexiangmu.getCrazilynum()+1);
        }
        youlexiangmuService.updateById(youlexiangmu);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YoulexiangmuEntity youlexiangmu, HttpServletRequest request){
    	youlexiangmu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(youlexiangmu);
        youlexiangmuService.insert(youlexiangmu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YoulexiangmuEntity youlexiangmu, HttpServletRequest request){
    	youlexiangmu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(youlexiangmu);
        youlexiangmuService.insert(youlexiangmu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YoulexiangmuEntity youlexiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(youlexiangmu);
        youlexiangmuService.updateById(youlexiangmu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        youlexiangmuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
