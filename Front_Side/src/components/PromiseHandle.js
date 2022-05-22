// Promise异步封装组件

const { default: axios } = require("axios")
// 配置默认的axios实例，提高axios的复用率
const myAxios = axios.create({
    baseURL: "http://180.76.51.217:12345"
});


// axios异步请求的Promise封装
const axiosReqByPromised = ({config}) => {
    return  new Promise((resolve,reject) => {
        myAxios.request(config).then(res => resolve(res),err => reject(err));
    })
}


module.exports = {
    axiosReqByPromised
}
