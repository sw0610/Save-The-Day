<template>
    <div id="Task">
        <Task-Header></Task-Header>
        <Weekly-Calender @send-date="receiveDate"></Weekly-Calender>
        <New-Task></New-Task>
        <Task-List :dateData="dateData"></Task-List>
    </div>
    
</template>

<script>
import TaskHeader from '../components/Task/TaskHeader.vue'
import WeeklyCalender from '../components/Task/WeeklyCalendar.vue'
import NewTask from '../components/Task/NewTask.vue'
import TaskList from '../components/Task/TaskList.vue'

export default {
    components: {
        'Task-Header': TaskHeader,
        'Weekly-Calender': WeeklyCalender,
        'New-Task' : NewTask,
        'Task-List': TaskList
    },
    data(){
        return{
            dateData:new Date(),
        }
    },
    methods: {
        receiveDate(value) {
            this.dateData = value; // Weekly-Calendar로부터 받아온 데이터를 Task-List로 전달
        
        }
    }
}
</script>
<style scoped>
    .Task{
        /* display: flex; */
        margin: 0%;
        /* flex-direction: column; */

        height: 100vh;
    }
    /* Task-List{
        flex-grow: 1;
        background-color: rebeccapurple;
    } */
</style>