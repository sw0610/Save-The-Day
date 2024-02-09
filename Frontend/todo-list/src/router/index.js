// import Vue from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

import TaskApp from '../views/TaskApp'
import TaskDetail from '../views/TaskDetailApp'
import MonthlyCalendar from '../components/Task/MonthlyCalendar.vue'
// import MonthlyCalendar from '../components/Task/TestVue.vue'

const routes = [
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