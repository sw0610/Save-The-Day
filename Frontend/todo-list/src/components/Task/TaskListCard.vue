 <template>
    <div>
    <div v-for="task in taskList" :key="task.id">
        <router-link class="routerLink" :to="'/task/detail/'+task.taskId">
            <div id="task" >
                <div v-if="task.processStatus==='Not Started'" class="status" id="notStarted"></div>
                <div v-if="task.processStatus==='In Progress'" class="status" id="inProgress"></div>
                <div v-if="task.processStatus==='Finished'" class="status" id="finished"></div>

                {{ task.title }}
            </div>
        </router-link>
    </div>
</div>
 </template>
 <script>
    // import http from '@/util/http-common.js';

    export default{
        props:{
            taskList: Array
        },

    }
</script>
<style scoped>
    .routerLink{
        text-decoration: none;    
    }

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