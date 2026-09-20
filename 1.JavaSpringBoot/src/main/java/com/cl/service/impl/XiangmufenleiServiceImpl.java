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


import com.cl.dao.XiangmufenleiDao;
import com.cl.entity.XiangmufenleiEntity;
import com.cl.service.XiangmufenleiService;
import com.cl.entity.view.XiangmufenleiView;

@Service("xiangmufenleiService")
public class XiangmufenleiServiceImpl extends ServiceImpl<XiangmufenleiDao, XiangmufenleiEntity> implements XiangmufenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiangmufenleiEntity> page = this.selectPage(
                new Query<XiangmufenleiEntity>(params).getPage(),
                new EntityWrapper<XiangmufenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiangmufenleiEntity> wrapper) {
		  Page<XiangmufenleiView> page =new Query<XiangmufenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<XiangmufenleiView> selectListView(Wrapper<XiangmufenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiangmufenleiView selectView(Wrapper<XiangmufenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
