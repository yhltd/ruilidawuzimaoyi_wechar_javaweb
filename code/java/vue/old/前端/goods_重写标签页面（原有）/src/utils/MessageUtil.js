/* 本方法用于消息提示 */
import {Message} from "element-ui"

/**
 *
 * @type {{success((String|Number)): void, warning((String|Number)): void, error((String|Number)): void, info((String|Number)): void}}
 */
const util = {
    /**
     *
     * @param {String| Number} msg
     */
    info(msg) {
        Message.info(msg);

    },
    /**
     *
     * @param {String| Number} msg
     */
    success(msg) {
        Message.success({
            message: msg,
            type: 'success'
        });
    },

    /**
     *
     * @param {String| Number} msg
     */
    warning(msg) {
        Message.warning({
            message: msg,
            type: 'warning'
        });
    },

    /**
     *
     * @param {String| Number} msg
     */
    error(msg) {
        Message.error(msg);
    },

}
export default util;