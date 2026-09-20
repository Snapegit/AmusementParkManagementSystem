package com.cl.entity.view;

import com.cl.entity.DakaxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 打卡信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
@TableName("dakaxinxi")
public class DakaxinxiView  extends DakaxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DakaxinxiView(){
	}
 
 	public DakaxinxiView(DakaxinxiEntity dakaxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, dakaxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
