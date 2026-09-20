package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YoulexiangmuDao;
import com.cl.entity.YoulexiangmuEntity;
import com.cl.service.YoulexiangmuService;
import com.cl.entity.view.YoulexiangmuView;

@Service("youlexiangmuService")
public class YoulexiangmuServiceImpl extends ServiceImpl<YoulexiangmuDao, YoulexiangmuEntity> implements YoulexiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YoulexiangmuEntity> page = this.selectPage(
                new Query<YoulexiangmuEntity>(params).getPage(),
                new EntityWrapper<YoulexiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YoulexiangmuEntity> wrapper) {
		  Page<YoulexiangmuView> page =new Query<YoulexiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YoulexiangmuView> selectListView(Wrapper<YoulexiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YoulexiangmuView selectView(Wrapper<YoulexiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
