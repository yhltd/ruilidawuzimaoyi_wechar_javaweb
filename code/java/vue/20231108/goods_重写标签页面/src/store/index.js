import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

//创建store对象
export default new Vuex.Store({
    state: {

        //如果本地浏览器有登录信息就直接获取
        user: {
            //默认是空
            userInfo: "",
            userPower: "",
        },

    },

    //方法
    mutations: {
        //成功登录才会进来这里
        loginSuccess (state, userInfo) {
            //先把登录信息放进store.state的状态变量中方便后面调用
            state.user.userInfo = userInfo
            //把登录信息放进localStorage，让vue放进浏览器cookie中
            window.localStorage.setItem('userInfo',userInfo)
        },
        powerSuccess (state, userPower) {
            //先把登录信息放进store.state的状态变量中方便后面调用
            state.user.userPower = userPower
            //把登录信息放进localStorage，让vue放进浏览器cookie中
            window.localStorage.setItem('userPower',userPower)
        },
        //用来改变account的值的方法
        //外界只能通过该方法更改account的值
        initUserInfo(state, userInfo){
            state.user.userInfo = userInfo
        },
        initUserPower(state, userPower){
            state.user.userPower = userPower
        }
    }

})