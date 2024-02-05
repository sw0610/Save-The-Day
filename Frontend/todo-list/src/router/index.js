// import Vue from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

import TaskApp from '../views/TaskApp'
import TaskDetail from '../views/TaskDetail'

const routes = [
    {
        path: "/", 
        component:TaskApp
    },
    {
        path: '/detail', 
        component:TaskDetail
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export {router}