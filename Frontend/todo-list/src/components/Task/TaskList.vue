<template>
    <div id="taskList">
      <div v-if="hasTasks">
        <div class="list">
          <div v-if="this.notStartedList.length>0" class="statusText" id="todoLine">Todo</div>
          <div>
            <div class="tasks">
              <TaskList-Card :taskList="notStartedList"></TaskList-Card>
            </div>
          </div>
        </div>
        <div>
          <div v-if="this.inProgressList.length>0" class="statusText" id="inProgressLine">In Progress</div>
          <div>
            <div class="tasks">
              <TaskList-Card :taskList="inProgressList"></TaskList-Card>
            </div>
          </div>
        </div>
        <div>
          <div  v-if="this.finishedList.length>0" class="statusText" id="finishedLine">Finished</div>
          <div>
            <div class="tasks">
              <TaskList-Card :taskList="finishedList"></TaskList-Card>
            </div>
          </div>
        </div>
      </div>
      <div v-else class="noTask">✨ 할 일이 없어요 ✨</div>
    </div>
  </template>
  
<script>
import TaskListCard from './TaskListCard.vue'
import http from '@/util/http-common.js'


export default {
    data() {
        return {
            notStartedList:[],
            inProgressList:[],
            finishedList:[],
            hasTasks: false,
            date: new Date()
        }
    },
    components: {
        'TaskList-Card': TaskListCard,

    },
    // setup(){
    //     const dateData=this.emitter.on('send-date', data);
    // },
    mounted() {
        this.emitter.on('send-date', (date) => {
            this.date = date;
        });

        this.getTaskList(new Date());
    },
    watch:{
      date: function(newDate){
        this.getTaskList(newDate);
      }
    },
    methods:{
      getTaskList(date){
        const offset = new Date().getTimezoneOffset() * 60000;
        const krDate = new Date(date-offset)
        const dateString = krDate.toISOString().slice(0, 10);

        http.get('http://localhost:8080/task/daily', {
            params: {
            date: dateString,
            },
        }).then((res) => {
            const datas = res.data;

            if(datas.notStartedList.length==0&&datas.inProgressList.length==0&&datas.finishedList==0){
                this.hasTasks=false;
            }else{
                this.hasTasks=true;
            }
            this.notStartedList=datas.notStartedList;
            this.inProgressList=datas.inProgressList;
            this.finishedList=datas.finishedList;
        });
      }
    
    },


}
</script>
<style scoped>
    #taskList{
        background-color: #F7F7F7;
        height: 100vh;
        padding-top: 5px;
        padding-left: 34px;
        padding-right: 34px;
    }
    #list{
        margin-bottom: 5px;
        margin-top: 5px;

    }
    .statusText{
        color: #F2A296;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 600;
        font-size: 16px;
        display: flex;
        align-items: center;
        flex-basis: 100%;
        margin-bottom: 10px;
    }
    .statusText::after {
        content: "";
        display: inline-block;
        height: 0.5px;  /* 선의 높이를 조절합니다. */
        width: 100px;  /* 선의 너비를 조절합니다. */
        background-color: #F2A296;  /* 선의 색상을 조절합니다. */
        margin-left: 5px;  /* 텍스트와 선 사이의 간격을 조절합니다. */
        vertical-align: middle;  /* 선이 텍스트와 같은 높이에 위치하도록 조절합니다. */
        flex-grow: 1;
    }
/* 
    .tasks{
        border: 0;
        width: 100%;
        background-color: #FFFFFF;
        height: 52px;
        border-radius: 22.5px;
        margin-top: 5px;
        margin-bottom: 5px;
        padding: 12px 17px;
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        font-family: 'Noto Sans', sans-serif;
        font-weight: 400;
        font-size: 14px;
    } */
    /* .tasks{
        border: 1px solid #7E869E;
        width: 100%;
        height: 52px;
        border-radius: 22.5px;
        padding: 12px 17px;
        background-color: transparent;
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #1B1C1F;
        font-family: 'Noto Sans kr', sans-serif;
        font-weight: 500;
        font-size: 14px;
    } */

    .noTask {
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: "Noto Sans kr", sans-serif;
        font-weight: 500;
        font-size: 18px;
    }


</style>
