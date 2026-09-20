package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YoulexiangmuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YoulexiangmuView;


/**
 * 游乐项目
 *
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
public interface YoulexiangmuService extends IService<YoulexiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YoulexiangmuView> selectListView(Wrapper<YoulexiangmuEntity> wrapper);
   	
   	YoulexiangmuView selectView(@Param("ew") Wrapper<YoulexiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YoulexiangmuEntity> wrapper);
   	

}

