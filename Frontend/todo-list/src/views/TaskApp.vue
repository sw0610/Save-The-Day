<template>
    <div id="Task">
        <Task-Header></Task-Header>
        <Weekly-Calender></Weekly-Calender>
        <Task-List></Task-List>
    </div>
    
</template>

<script>
import TaskHeader from '../components/Task/TaskHeader.vue'
import WeeklyCalender from '../components/Task/WeeklyCalendar.vue'
import TaskList from '../components/Task/TaskList.vue'

export default {
    components: {
        'Task-Header': TaskHeader,
        'Weekly-Calender': WeeklyCalender,
        'Task-List': TaskList
    },
}
</script>
<style scoped>
    .Task{
        margin: 0%;
    }
</style>