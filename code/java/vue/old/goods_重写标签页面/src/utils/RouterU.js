/* 用于路由跳转 */
import Route from '../router/index.js'

/**
 * 用于统一路由跳转 均打开新标签页
 * @type {{to_params(String, (Map|{key: String, value: (String|Number)})): void, to(String): void}}
 */
const router = {
    /**
     * 无参跳转
     *
     * 打开新页面
     * @param {String} url 跳转地址
     */
    to(url) {
        let routeUrl = Route.resolve({
            path: url
        });
        window.open(routeUrl.href, '_blank');
    },

    /**
     * 带参路由跳转
     *
     * 打开新页面
     * @param {String} url 跳转路由地址
     * @param {JSON} params 参数列表
     * @param {String} params.key 参数名
     * @param {String|Number} params.value 参数值
     */
    to_params(url, params) {
        let routeUrl = Route.resolve({
            path: url,
            param: params
        });
        window.open(routeUrl.href, '_blank');
    }
}

export default router