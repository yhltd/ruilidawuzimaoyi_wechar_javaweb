// 解析省市区三级规划
import areaData from './data_area'

const area = areaData.list;
const parse = {

    queryProvince() {
        return area.province_list;
    },
    queryCity(key) {
        console.log("获取市级城市", key)
        return getTitle(area.city_list, key);
    },
    queryCountry(key) {
        console.log("获取县级城市", key)
        return getTitle(area.county_list, key);

    }

}
const deepCopy = (obj) => {
    return JSON.parse(JSON.stringify(obj));
};
const json = {id: "", name: ''}

let getTitle = (obj, keyword) => {
        let arr = [];
        for (let i in obj) {
            if (i.startsWith(keyword)) {
                let tmp = deepCopy(json);
                tmp.id = i;
                tmp.name = obj[i];
                arr.push(tmp);
            }

        }

        console.log(arr)
        return arr;
    }
;

export default parse;