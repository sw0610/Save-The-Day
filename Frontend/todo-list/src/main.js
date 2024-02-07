import { createApp } from 'vue'
import App from './App.vue'
import mitt from 'mitt';
import {router} from './router/index.js'


const emitter = mitt()
const app=createApp(App)
app.config.globalProperties.$emitter = emitter
app.use(router)
app.mount('#app')
