package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XiangmufenleiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XiangmufenleiView;


/**
 * 项目分类
 *
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
public interface XiangmufenleiService extends IService<XiangmufenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiangmufenleiView> selectListView(Wrapper<XiangmufenleiEntity> wrapper);
   	
   	XiangmufenleiView selectView(@Param("ew") Wrapper<XiangmufenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiangmufenleiEntity> wrapper);
   	

}

