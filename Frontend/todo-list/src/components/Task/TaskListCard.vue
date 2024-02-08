 <template>
    <div id="task" v-for="task in taskList" :key="task.id">
        <div v-if="task.processStatus==='not started'" class="status" id="notStarted"></div>
        <div v-if="task.processStatus==='in progress'" class="status" id="inProgress"></div>
        <div v-if="task.processStatus==='finished'" class="status" id="finished"></div>

        {{ task.title }}
    </div>
 </template>
 <script>
 export default{
    props:{
        taskList: Array
    }
 }
</script>
<style scoped>
    #task{
        border-style: solid;
        border-width: 0px;
        background-color: #FFFFFF; 
        /* width: 100%; */
        height: 52px;
        padding-left: 20px;
        border-radius: 22.5px;
        /* padding: 12px 17px; */
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #1B1C1F;
        font-family: 'Noto Sans kr', sans-serif;
        font-weight: 600;
        font-size: 16px;
        margin-bottom: 10px;;
    }
    .status{

        width: 16px;
        height: 16px;
        /* margin-left: ; */
        margin-right: 20px;
    }
    #notStarted{
        border: 1px dashed #F1867E;
        border-radius: 50%;
    }

    #inProgress{
        border: 1px solid #F1867E;
        border-radius: 50%;
        background-color: #FFBAB5;
    }

</style>