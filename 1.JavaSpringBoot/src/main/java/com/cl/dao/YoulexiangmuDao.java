package com.cl.dao;

import com.cl.entity.YoulexiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YoulexiangmuView;


/**
 * 游乐项目
 * 
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
public interface YoulexiangmuDao extends BaseMapper<YoulexiangmuEntity> {
	
	List<YoulexiangmuView> selectListView(@Param("ew") Wrapper<YoulexiangmuEntity> wrapper);

	List<YoulexiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<YoulexiangmuEntity> wrapper);
	
	YoulexiangmuView selectView(@Param("ew") Wrapper<YoulexiangmuEntity> wrapper);
	

}
