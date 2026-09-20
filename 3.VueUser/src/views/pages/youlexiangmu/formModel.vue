<template>
	<div class="app-contain" :style='{"padding":"0 18%","margin":"0px auto","alignItems":"flex-start","flexWrap":"wrap","display":"flex","width":"100%","position":"relative","justifyContent":"space-between"}'>
		<div class="bread_view">
			<el-breadcrumb separator="/" class="breadcrumb">
				<el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_view">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<div class="detail_view">
			<div class="swiper_view">
				<mySwiper :data="bannerList" :type="3"
				:loop="false"
				:navigation="false"
				:pagination="false"
				:paginationType="1"
				:scrollbar="false"
				:slidesPerView="1"
				:spaceBetween="20"
				:autoHeight="false"
				:centeredSlides="false"
				:freeMode="false"
				:effectType="0"
				:direction="horizontal"
				:autoplay="true"
				:slidesPerColumn="1">
				<template #default="scope">
					<img :style='{"objectFit":"contain","width":"100%","height":"500px"}' :src="scope.row?$config.url + scope.row:''">
				</template>
			</mySwiper>
			</div>
			<div class="thumbs_view">
				<div class="zan" v-if="!crazilyType&&!thumbsupType" @click="thumbsupOrCrazilyClick(21)">
					<i class="iconfont icon-thumb-up-line2"></i>
					<span>赞({{detail.thumbsupnum}})</span>
				</div>
				<div class="zan" v-if="!crazilyType&&!thumbsupType" @click="thumbsupOrCrazilyClick(22)">
					<i class="iconfont icon-thumb-down-line2"></i>
					<span>踩({{detail.crazilynum}})</span>
				</div>
				<div class="zan" v-if="thumbsupType" @click="cancelThumbsupOrCrazilyClick(21)">
					<i class="iconfont iconfontActive icon-thumb-up-fill3"></i>
					<span class="textActive">取消赞({{detail.thumbsupnum}})</span>
				</div>
				<div class="zan" v-if="crazilyType" @click="cancelThumbsupOrCrazilyClick(22)">
					<i class="iconfont iconfontActive icon-thumb-down-fill2"></i>
					<span class="textActive">取消踩({{detail.crazilynum}})</span>
				</div>
			</div>
			
			<div class="info_view">
				<div class="title_view">
					<div class="detail_title">
						{{detail.xiangmumingcheng}}
					</div>
				</div>
				<div class="info_item">
					<div class="info_label">项目分类</div>
					<div  class="info_text" >{{detail.xiangmufenlei}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">开放时间</div>
					<div  class="info_text" >{{detail.kaifangshijian}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">项目简介</div>
					<div  class="info_text" >{{detail.xiangmujianjie}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">价格</div>
					<div  class="info_text" >{{detail.jiage}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">购买须知</div>
					<div  class="info_text" >{{detail.goumaixuzhi}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">使用说明</div>
					<div  class="info_text" >{{detail.shiyongshuoming}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">费用说明</div>
					<div  class="info_text" >{{detail.feiyongshuoming}}</div>
				</div>
				<div class="btn_view">
					<el-button class="cross_btn" v-if="btnFrontAuth(tableName,'门票购买')" @click="menpiaogoumaionAcross('门票购买','menpiaogoumai','','','')" type="warning">门票购买</el-button>
					<el-button class="cross_btn" v-if="btnFrontAuth(tableName,'打卡')" @click="dakaxinxionAcross('打卡','dakaxinxi','','','')" type="warning">打卡</el-button>
					<el-button v-if="centerType&&(detail.ispay=='未支付'||!detail.ispay)&&btnFrontAuth('youlexiangmu','支付')" class="approval_btn" @click="payClick">支付</el-button>
					<el-button class="edit_btn" v-if="centerType&&btnAuth('youlexiangmu','修改')" type="primary" @click="editClick">修改</el-button>
					<el-button class="del_btn" v-if="centerType&&btnAuth('youlexiangmu','删除')" type="danger" @click="delClick">删除</el-button>
				</div>
			</div>
		</div>
		<el-tabs type="border-card" v-model="activeName" class="tabs_view">
			<el-tab-pane label="项目详情" name="first">
				<div v-html="detail.xiangmuxiangqing"></div>
			</el-tab-pane>
            <el-tab-pane label="地图" name="mapActive">
				<iframe MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src="https://map.baidu.com/" height="600px" width="100%"></iframe>
            </el-tab-pane>
		</el-tabs>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'youlexiangmu'
	const formName = '游乐项目'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	//查看权限验证
	const btnFrontAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isFrontAuth(e,a)
		}
	}
	// 返回
	const backClick = () =>{
		history.back()
	}
	// 轮播图
	const bannerList = ref([])
	// 详情
	const title = ref('')
	const detail = ref({})
    const activeName = ref('first')
	const getDetail = () => {
		context?.$http({
			url: `${tableName}/detail/${route.query.id}`,
			method: 'get'
		}).then(res => {
			title.value = res.data.data.xiangmumingcheng
			bannerList.value = res.data.data.tupian?res.data.data.tupian.split(','):[]
			detail.value = res.data.data
		})
	}
	// 下载文件
	const downClick = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('frontToken')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	// 判断是否从个人中心跳转
	const centerType = ref(false)
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getDetail()
		// 赞踩状态
		getThumbsupOrCrazily()
	}
	// 赞or踩
	const thumbsupType = ref(false)
	const crazilyType = ref(false)
	const thumbsupOrCrazilyInfo = ref({})
	// 获取赞踩状态
	const getThumbsupOrCrazily = () => {
		if (context?.$toolUtil.storageGet('frontToken')) {
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 21,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					thumbsupType.value = true
					thumbsupOrCrazilyInfo.value = res.data.data.list[0]
				}else {
					thumbsupType.value = false
					thumbsupOrCrazilyInfo.value = {}
				}
			})
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 22,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					crazilyType.value = true
					thumbsupOrCrazilyInfo.value = res.data.data.list[0]
				}else {
					crazilyType.value = false
					thumbsupOrCrazilyInfo.value = {}
				}
			})
		}
	}
	// 赞踩按钮
	const thumbsupOrCrazilyClick = (type) => {
		let params = {
			name: title.value,
			picture: bannerList.value[0],
			refid: detail.value.id,
			type: type,
			tablename: tableName,
			userid: context?.$toolUtil.storageGet('userid')
		}
		context?.$http({
			url: 'storeup/add',
			method: 'post',
			data: params
		}).then(res => {
			if (type == 21) detail.value.thumbsupnum += 1
			if (type == 22) detail.value.crazilynum += 1
			context?.$http({
				url: `${tableName}/update`,
				method: 'post',
				data: detail.value
			})
			getThumbsupOrCrazily()
			context?.$toolUtil.message('操作成功', 'success')
		})
	}
	//取消赞踩按钮
	const cancelThumbsupOrCrazilyClick = (type) => {
		let ids = []
		ids.push(thumbsupOrCrazilyInfo.value.id)
		context?.$http({
			url: 'storeup/delete',
			method: 'post',
			data: ids
		}).then(res => {
			if (type == 21) detail.value.thumbsupnum -= 1
			if (type == 22) detail.value.crazilynum -= 1
			context?.$http({
				url: `${tableName}/update`,
				method: 'post',
				data: detail.value
			})
			thumbsupType.value = false
			crazilyType.value = false
			thumbsupOrCrazilyInfo.value = {}
			getThumbsupOrCrazily()
			context?.$toolUtil.message('取消成功', 'success')
		})
	}
	//修改
	const editClick = () => {
		router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
	}
	//删除
	const delClick = () => {
		ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(()=>{
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: [detail.value.id]
			}).then(res=>{
				context?.$toolUtil.message('删除成功','success',()=>{
					history.back()
				})
			})
			
		})
	}
	const menpiaogoumaionAcross = (btnType,table,crossOptAudit,statusColumnName,tips,statusColumnValue) => {
		if(!context?.$toolUtil.storageGet('frontToken')){
			context?.$toolUtil.message('请登录后再操作！','error')
			return false
		}
		if(!btnAuth(tableName,btnType)){
			context?.$toolUtil.message('暂无权限操作！','error')
			return false
		}
		context?.$toolUtil.storageSet('crossObj',JSON.stringify(detail.value))
		context?.$toolUtil.storageSet('crossTable',tableName)
		context?.$toolUtil.storageSet('crossStatusColumnName',statusColumnName)
		context?.$toolUtil.storageSet('crossTips',tips)
		context?.$toolUtil.storageSet('crossStatusColumnValue',statusColumnValue)
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = detail.value
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			router.push(`/index/${table}Add?type=cross&&id=${detail.value.id}`)
		})
	}
	const dakaxinxionAcross = (btnType,table,crossOptAudit,statusColumnName,tips,statusColumnValue) => {
		if(!context?.$toolUtil.storageGet('frontToken')){
			context?.$toolUtil.message('请登录后再操作！','error')
			return false
		}
		if(!btnAuth(tableName,btnType)){
			context?.$toolUtil.message('暂无权限操作！','error')
			return false
		}
		context?.$toolUtil.storageSet('crossObj',JSON.stringify(detail.value))
		context?.$toolUtil.storageSet('crossTable',tableName)
		context?.$toolUtil.storageSet('crossStatusColumnName',statusColumnName)
		context?.$toolUtil.storageSet('crossTips',tips)
		context?.$toolUtil.storageSet('crossStatusColumnValue',statusColumnValue)
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = detail.value
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			router.push(`/index/${table}Add?type=cross&&id=${detail.value.id}`)
		})
	}
	onMounted(()=>{
		init()
	})
</script>
<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 0px;
		padding: 0;
		margin: 10px auto;
		background: none;
		display: block;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid #eee;
			cursor: pointer;
			border-radius: 2px;
			padding: 0 30px;
			outline: none;
			color: #666;
			background: #f9f9f9;
			width: auto;
			font-size: 14px;
			height: 32px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	// 面包屑盒子
	.bread_view {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 12px 20px;
		margin: 10px auto 10px;
		background: #f9f9f9;
		width: 100%;
		position: relative;
		:deep(.breadcrumb) {
			font-size: 14px;
			line-height: 1;
			.el-breadcrumb__separator {
				margin: 0 9px;
				color: #ccc;
				font-weight: 500;
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
					color: #333;
					display: inline-block;
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
					color: #999;
					display: inline-block;
				}
			}
		}
	}
	
	.detail_view{
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 60px 40px 20px;
		background: #f9f9f9;
		display: flex;
		width: 100%;
		justify-content: space-between;
		position: relative;
		flex-wrap: wrap;
		// 轮播图
		.swiper_view {
			padding: 0;
			margin: 20px auto;
			width: 80%;
			height: 500px;
		}
		
		// 赞踩盒子
		.thumbs_view {
			padding: 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			.zan {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 20px;
				padding: 4px 20px;
				margin: 0 20px;
				color: #999;
				background: #fff;
				display: flex;
				width: auto;
				align-items: center;
				box-sizing: border-box;
		
				.iconfont {
					padding: 0 10px 0 0;
					color: #666;
					font-size: 24px;
				}
				.iconfontActive {
					margin: 0 4px 0 0;
					color: #c00;
					font-size: 24px;
				}
				span {
					color: #666;
				}
				.textActive {
					color: #c00;
				}
			}
			.zan:hover {
			}
			.zan:active {
				transform: scale(0.8);
			}
		}
		// 文字区
		.info_view {
			border: 0px solid #eee;
			padding: 0px 20px;
			margin: 0 0 20px;
			background: none;
			width: 100%;
			box-sizing: border-box;
		
			.title_view {
				border: 0px solid #eee;
				padding: 0;
				margin: 0 0 12px;
				background: none;
				display: flex;
				width: 100%;
				line-height: 40px;
				justify-content: space-between;
				align-items: center;
		
				.detail_title {
					color: #666;
					font-weight: 500;
					font-size: 16px;
				}
				.follow {
					border: 0px solid #ffffff50;
					cursor: pointer;
					border-radius: 4px;
					padding: 4px 10px;
					color: #fff;
					background: none;
					display: flex;
					width: auto;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.iconfont {
						margin: 0 4px 0 0;
						color: #333;
					}
					.iconfontActive {
						margin: 0 4px 0 0;
						color: #f79d1c;
					}
					span {
						color: #666;
					}
					.textActive {
						color: #f79d1c;
					}
				}
				.follow:hover {
				}
				.follow:active {
					transform: scale(0.9);
				}
			}
		
			.info_item {
					border-radius: 0px;
					padding: 0;
					margin: 0 0 10px;
					background: none;
					display: flex;
					border-color: #eee;
					border-width: 0px;
					align-items: center;
					border-style: solid;
		
				.info_label {
					margin: 0 12px 0 0;
					color: #666;
					font-weight: 500;
					width: auto;
				}
				.info_text {
				}
			}
			.btn_view {
				padding: 0;
				margin: 20px 0 20px;
				display: flex;
				flex-wrap: wrap;
				// 跨表-按钮
				.cross_btn {
					border: none;
					padding: 0 10px;
					color: #fff;
					background: rgba(170, 0, 0, 1.0);
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.cross_btn:hover {
					border: none;
					color: #333;
					background: rgba(170, 0, 0, 0.5);
				}
				// 修改-按钮
				.edit_btn {
					border: none;
					padding: 0 10px;
					color: #fff;
					background: rgba(255, 85, 0, 1.0);
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.edit_btn:hover {
					border: none;
					color: #333;
					background: rgba(255, 85, 0, 0.5);
				}
				// 删除-按钮
				.del_btn {
					border: none;
					padding: 0 10px;
					color: #fff;
					background: #e96578;
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.del_btn:hover {
					border: none;
					color: #333;
					background: rgba(233, 101, 120, 0.5);
				}
			}
		}
	}
	

	//底部盒子
	.tabs_view {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 10px;
		box-shadow: none;
		margin: 20px auto;
		background: none;
		width: 100%;
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
		// 头部
		:deep(.el-tabs__nav-scroll) {
			border-radius: 0;
			padding: 2px 20px 0;
			margin: 0;
			background: #f9f9f9;
			border-color: #ddd;
			border-width: 0 0 0px;
			border-style: solid;
			height: 36px;
			.el-tabs__nav {
				.el-tabs__item {
					border: 0;
					padding: 0 20px;
					margin: 0 10px;
					color: #333;
					font-weight: 500;
					display: inline-block;
					font-size: 14px;
					line-height: 36px;
					background: none;
					position: relative;
					list-style: none;
					text-align: center;
					min-width: 60px;
					height: 36px;
				}
				.el-tabs__item:hover {
					border: 0;
					border-radius: 0;
					color: #fff;
					background: #c00;
				}
				.is-active {
					border: 0;
					border-radius: 0;
					color: #fff;
					background: #c00;
					min-width: 60px;
					text-align: center;
				}
			}
		}
		// 内容区
		:deep(.el-tabs__content) {
			border-radius: 0 0 4px 4px;
			padding: 20px;
			color: #666;
			background: none;
			font-size: 14px;
			border-color: #eee;
			border-width: 0;
			border-style: solid;
		}
	}
	


</style>