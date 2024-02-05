<template>
        <TaskDetail-Header></TaskDetail-Header>
        <Task-Form></Task-Form>
</template>
<script>
import TaskDetailHeader from '../components/Task/TaskDetailHeader.vue'
import TaskForm from '../components/Task/TaskForm.vue'


export default {
    components: {
        'TaskDetail-Header': TaskDetailHeader,
        'Task-Form':TaskForm
    },
}
</script>
<style>

</style>