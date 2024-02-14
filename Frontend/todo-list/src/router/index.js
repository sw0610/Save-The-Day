// import Vue from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

import TaskApp from '../views/TaskApp'
import TaskDetail from '../views/TaskDetailApp'
import MonthlyCalendar from '../components/Task/MonthlyCalendar.vue'
import InitialPageApp from '@/views/InitialPageApp.vue'
import SignUpApp from '@/views/SignUpApp.vue'
import WrapUpApp from '@/views/WrapUpApp.vue'
import QuoteApp from '@/views/QuoteApp.vue'

// import MonthlyCalendar from '../components/Task/TestVue.vue'

const routes = [
    {
        path: "/", 
        component:InitialPageApp
    },
    {
        path: "/sign-up", 
        component:SignUpApp
    },
    {
        path: "/task", 
        name:"home",
        component:TaskApp
    },
    {
        path: '/task/new', 
        name:"newTask",
        component:TaskDetail,
        props: true
    },
    {
        path: '/task/detail/:taskId', 
        component:TaskDetail
    },
    {
        path: '/wrap-up/list', 
        component:WrapUpApp
    },
    {
        path: '/wrap-up/quote', 
        component:QuoteApp
    },
    {
        path: '/calendar', 
        name:'calendar',
        component:MonthlyCalendar
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export {router}