package com.cl.dao;

import com.cl.entity.XiangmufenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XiangmufenleiView;


/**
 * 项目分类
 * 
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
public interface XiangmufenleiDao extends BaseMapper<XiangmufenleiEntity> {
	
	List<XiangmufenleiView> selectListView(@Param("ew") Wrapper<XiangmufenleiEntity> wrapper);

	List<XiangmufenleiView> selectListView(Pagination page,@Param("ew") Wrapper<XiangmufenleiEntity> wrapper);
	
	XiangmufenleiView selectView(@Param("ew") Wrapper<XiangmufenleiEntity> wrapper);
	

}
