package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 游乐项目
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-03-02 14:28:28
 */
@TableName("youlexiangmu")
public class YoulexiangmuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YoulexiangmuEntity() {
		
	}
	
	public YoulexiangmuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 项目名称
	 */
					
	private String xiangmumingcheng;
	
	/**
	 * 项目分类
	 */
					
	private String xiangmufenlei;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 开放时间
	 */
					
	private String kaifangshijian;
	
	/**
	 * 项目简介
	 */
					
	private String xiangmujianjie;
	
	/**
	 * 价格
	 */
					
	private Double jiage;
	
	/**
	 * 购买须知
	 */
					
	private String goumaixuzhi;
	
	/**
	 * 使用说明
	 */
					
	private String shiyongshuoming;
	
	/**
	 * 费用说明
	 */
					
	private String feiyongshuoming;
	
	/**
	 * 项目详情
	 */
					
	private String xiangmuxiangqing;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：项目名称
	 */
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	/**
	 * 获取：项目名称
	 */
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
	/**
	 * 设置：项目分类
	 */
	public void setXiangmufenlei(String xiangmufenlei) {
		this.xiangmufenlei = xiangmufenlei;
	}
	/**
	 * 获取：项目分类
	 */
	public String getXiangmufenlei() {
		return xiangmufenlei;
	}
	/**
	 * 设置：图片
	 */
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
	/**
	 * 设置：开放时间
	 */
	public void setKaifangshijian(String kaifangshijian) {
		this.kaifangshijian = kaifangshijian;
	}
	/**
	 * 获取：开放时间
	 */
	public String getKaifangshijian() {
		return kaifangshijian;
	}
	/**
	 * 设置：项目简介
	 */
	public void setXiangmujianjie(String xiangmujianjie) {
		this.xiangmujianjie = xiangmujianjie;
	}
	/**
	 * 获取：项目简介
	 */
	public String getXiangmujianjie() {
		return xiangmujianjie;
	}
	/**
	 * 设置：价格
	 */
	public void setJiage(Double jiage) {
		this.jiage = jiage;
	}
	/**
	 * 获取：价格
	 */
	public Double getJiage() {
		return jiage;
	}
	/**
	 * 设置：购买须知
	 */
	public void setGoumaixuzhi(String goumaixuzhi) {
		this.goumaixuzhi = goumaixuzhi;
	}
	/**
	 * 获取：购买须知
	 */
	public String getGoumaixuzhi() {
		return goumaixuzhi;
	}
	/**
	 * 设置：使用说明
	 */
	public void setShiyongshuoming(String shiyongshuoming) {
		this.shiyongshuoming = shiyongshuoming;
	}
	/**
	 * 获取：使用说明
	 */
	public String getShiyongshuoming() {
		return shiyongshuoming;
	}
	/**
	 * 设置：费用说明
	 */
	public void setFeiyongshuoming(String feiyongshuoming) {
		this.feiyongshuoming = feiyongshuoming;
	}
	/**
	 * 获取：费用说明
	 */
	public String getFeiyongshuoming() {
		return feiyongshuoming;
	}
	/**
	 * 设置：项目详情
	 */
	public void setXiangmuxiangqing(String xiangmuxiangqing) {
		this.xiangmuxiangqing = xiangmuxiangqing;
	}
	/**
	 * 获取：项目详情
	 */
	public String getXiangmuxiangqing() {
		return xiangmuxiangqing;
	}
	/**
	 * 设置：赞
	 */
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
	/**
	 * 设置：踩
	 */
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}

}
