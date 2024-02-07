<template>
    <div id="taskList">
        <div class="list">
            <div class="statusText" id="todoLine">Todo</div>
            <div>
                <div class="tasks">
                    <!-- <div class="statusCircle"></div> -->
                    <!-- 음악 듣기 -->
                    <TaskList-Card></TaskList-Card>
                </div>
            </div>
        </div>
        <div>
            <div class="statusText" id="inProgressLine">In Progress</div>
            <div>
                <div class="tasks">
                    <TaskList-Card></TaskList-Card>

                </div>
            </div>
        </div>
        <div>
            <div class="statusText" id="finishedLine">Finished</div>
            <div>
                <div class="tasks">
                    <TaskList-Card></TaskList-Card>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import TaskListCard from './TaskListCard.vue'
import axios from 'axios';

// axios.get('http://localhost:8080/task/daily?date=2024-02-01').then((res)=>{
//         console.log(res.data);
//     })

export default {
    // props:{
    //     dateData: Date
    // },
    components: {
        'TaskList-Card': TaskListCard,

    },
    // setup(){
    //     const dateData=this.emitter.on('send-date', data);
    // },
    mounted() {
  this.emitter.on('send-date', (date) => {
    const dateString = date.toISOString().slice(0, 10);
    axios.get('http://localhost:8080/task/daily', {
      params: {
        date: dateString,
      },
    }).then((res) => {
      console.log(res.data);
      // 필요한 작업을 수행합니다.
    });
  });
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

</style>
