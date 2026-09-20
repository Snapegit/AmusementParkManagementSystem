import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import forumList from '@/views/pages/forum/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import yuangongList from '@/views/pages/yuangong/list'
import yuangongDetail from '@/views/pages/yuangong/formModel'
import yuangongAdd from '@/views/pages/yuangong/formAdd'
import xiangmufenleiList from '@/views/pages/xiangmufenlei/list'
import xiangmufenleiDetail from '@/views/pages/xiangmufenlei/formModel'
import xiangmufenleiAdd from '@/views/pages/xiangmufenlei/formAdd'
import youlexiangmuList from '@/views/pages/youlexiangmu/list'
import youlexiangmuDetail from '@/views/pages/youlexiangmu/formModel'
import youlexiangmuAdd from '@/views/pages/youlexiangmu/formAdd'
import menpiaogoumaiList from '@/views/pages/menpiaogoumai/list'
import menpiaogoumaiDetail from '@/views/pages/menpiaogoumai/formModel'
import menpiaogoumaiAdd from '@/views/pages/menpiaogoumai/formAdd'
import dakaxinxiList from '@/views/pages/dakaxinxi/list'
import dakaxinxiDetail from '@/views/pages/dakaxinxi/formModel'
import dakaxinxiAdd from '@/views/pages/dakaxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'yuangongList',
			component: yuangongList
		}, {
			path: 'yuangongDetail',
			component: yuangongDetail
		}, {
			path: 'yuangongAdd',
			component: yuangongAdd
		}
		, {
			path: 'xiangmufenleiList',
			component: xiangmufenleiList
		}, {
			path: 'xiangmufenleiDetail',
			component: xiangmufenleiDetail
		}, {
			path: 'xiangmufenleiAdd',
			component: xiangmufenleiAdd
		}
		, {
			path: 'youlexiangmuList',
			component: youlexiangmuList
		}, {
			path: 'youlexiangmuDetail',
			component: youlexiangmuDetail
		}, {
			path: 'youlexiangmuAdd',
			component: youlexiangmuAdd
		}
		, {
			path: 'menpiaogoumaiList',
			component: menpiaogoumaiList
		}, {
			path: 'menpiaogoumaiDetail',
			component: menpiaogoumaiDetail
		}, {
			path: 'menpiaogoumaiAdd',
			component: menpiaogoumaiAdd
		}
		, {
			path: 'dakaxinxiList',
			component: dakaxinxiList
		}, {
			path: 'dakaxinxiDetail',
			component: dakaxinxiDetail
		}, {
			path: 'dakaxinxiAdd',
			component: dakaxinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
