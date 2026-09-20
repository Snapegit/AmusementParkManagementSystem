	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import forum from '@/views/forum/list'
	import youlexiangmu from '@/views/youlexiangmu/list'
	import yuangong from '@/views/yuangong/list'
	import dakaxinxi from '@/views/dakaxinxi/list'
	import xiangmufenlei from '@/views/xiangmufenlei/list'
	import yonghu from '@/views/yonghu/list'
	import menpiaogoumai from '@/views/menpiaogoumai/list'
	import config from '@/views/config/list'
	import users from '@/views/users/list'
	import yuangongCenter from '@/views/yuangong/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页Home',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/yuangongCenter',
			name: '员工个人中心',
			component: yuangongCenter
		}
		,{
			path: '/news',
			name: '宣传公告',
			component: news
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/youlexiangmu',
			name: '游乐项目',
			component: youlexiangmu
		}
		,{
			path: '/yuangong',
			name: '员工',
			component: yuangong
		}
		,{
			path: '/dakaxinxi',
			name: '打卡信息',
			component: dakaxinxi
		}
		,{
			path: '/xiangmufenlei',
			name: '项目分类',
			component: xiangmufenlei
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/menpiaogoumai',
			name: '门票购买',
			component: menpiaogoumai
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
