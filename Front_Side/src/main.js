import { createApp } from 'vue'
import App from './App.vue'
// 整合ElementUI
const app = createApp(App);// 获取app实例
import ElementPlus from 'element-plus';// 组件库
import 'element-plus/theme-chalk/index.css';// 样式
app.use(ElementPlus);

// 关闭Vue的生产提示
app.config.productionTip = false

// 将app实例挂载到id=app的容器上
app.mount('#app')
