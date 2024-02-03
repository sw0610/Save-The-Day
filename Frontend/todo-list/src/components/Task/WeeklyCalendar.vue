<template>
    <div id="weekly">
        <div >
            <button id="calendar">Feb 14</button>
            <button id="today">Today</button>
        </div>
        <div id="weeklyCalender">
            <div v-for="day in days" :key="day.id">{{ day }}</div>
        </div>
    </div>
</template>
<script>
import { ref } from 'vue';

export default {
    setup() {
        const days = ref(['SUN', 'MON', "TUE", "WED", "THU", "FRI", "SAT"]);

        return {
            days
        }
    }
}

</script>
<style scoped>
    #weekly{
        background-color: #FFBAB5;
        height: 110px;
        margin:0;
        padding-left: 24px;
        padding-right: 24px;
        
    }
    button{
        margin-top: 16px;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;

    }
    #calendar{
        font-size: 16px;
        color: #1B1C1F;
        border: 0;
        background-color: transparent;
    }
    #today{
        border: 0;
        background: #FFFFFF;
        border-radius: 16px;
        padding:3px 5px;
        font-size: 14px;
        color: #F19F9D;
        float: right;


    }
</style>
