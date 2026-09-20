const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '新闻资讯管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'宣传公告',
							url:'/index/newsList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: 'icon-common47',
					child:[
						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '游乐项目管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'游乐项目',
							url:'/index/youlexiangmuList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "基于SpringBoot+Vue的游乐园管理系统"
        } 
    }
}
export default config
