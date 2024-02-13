<template>
    <div>
        <div v-for="task in taskList" :key="task.id">
            <router-link class="routerLink" :to="'/task/detail/'+task.taskId">
                <div class="task" :class="{ 'finished-task': task.processStatus === 'Finished' }">
                    <div v-if="task.processStatus==='Not Started'" class="status" id="notStarted"></div>
                    <div v-if="task.processStatus==='In Progress'" class="status" id="inProgress"></div>
                    <div v-if="task.processStatus==='Finished'">
                        <div v-if="task.emotion === null" class="status" id="finished">
                            <img id="emoji" :src="require('@/assets/icon/check_filled.svg')" />
                        </div>
                        <div v-else class="status" id="finishedWithEmotion">
                            <img id="emoji" :src="require('@/assets/icon/emoji/'+task.emotion+'_color.svg')" />
                        </div>
                    </div>
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

    .task{
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
        position: relative;

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
    #finished{

    }

    #emoji{
        width: 21px;
        height: 21px;
        margin-right: 20px;
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
    }

    .finished-task {
        background-color: #F7F7F7; 
        border:1px solid #EAEAED;
        color: #7E869E;
    }   

</style>