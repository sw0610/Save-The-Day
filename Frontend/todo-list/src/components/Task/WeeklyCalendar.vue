<template>
    <div id="weekly">
        <div id="buttons">
            <button id="calendar" @click="goToCalendar">Feb 14</button>
            <button id="today">Today</button>
        </div>
        <div id="weeklyCalender">
            <!-- <div class="day" v-for="day in days" :key="day.id"  >{{ day }}</div>
            <div class="date" v-for="date in dates" :key="date.id" >{{ date }}</div> -->
            <div id="calendarItem" v-for="item in zippedData" :key="item.day">
                <div class="calendarRow">
                    <div class="day" >{{ item.day }}</div>
                </div>
                <div class="calendarRow">
                    <div class="date">{{ item.date }}</div>
                </div>
            </div>

        </div>
    </div>
</template>
<script>
import { ref } from 'vue';

export default {
    setup() {
        const days = ref(['SUN', 'MON', "TUE", "WED", "THU", "FRI", "SAT"]);
        const dates = ref(['4', '5', "6", "7", "8", "9", "10"]);

        const zippedData = ref(days.value.map((day, index) => ({ day, date: dates.value[index] })));

        return {
            zippedData
        }
        },
        methods: {
        goToCalendar() {
            this.$router.push('/calendar');
        }
    }
}



</script>
<style scoped>

    #weekly{
        background-color: #FFBAB5;
        height: 136px;
        margin:0;
        padding: 0 24px; /* 이 줄을 추가하세요. */

        position: relative; 
        
    }
    /* #buttons{
        margin-left: 24px;
        margin-right: 24px;
    } */

    button{
        margin-top: 16px;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;

    }
    #calendar{
        font-size: 16px;
        color: #1B1C1F;
        padding-top:3px;
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
    #weeklyCalender {
        /* display: flex; */
        /* justify-content: space-between; */
        /* position: absolute; */
        box-sizing: border-box;
        margin-top: 16px;
        /* border: 1px solid black; */
        /* border-color: #1B1C1F; */
        /* margin-left: 5px;
        margin-right: 8px; */
        width: 100%; 
        display: flex;
        /* padding: 8px 16px !important; */

    }


    .calendarRow {
        display: flex;
        justify-content: space-between;

    }
    #calendarItem{
        flex-grow: 1;
        flex-basis: 0;
        /* border: 1px solid blue; */
        padding-top: 8px;
        padding-bottom: 8px;
        
    }
    .day{
        font-family: 'Noto Sans', sans-serif;
        font-size: 12px;
        font-weight: 400;
        color: #767E8C;
        flex-grow: 1;
        /* border: 1px solid red; */
        flex: 1;
        text-align: center;
        margin-bottom: 5px;

        /* padding: 0 10px; */
    }
    .date {
        font-family: 'Noto Sans', sans-serif;
        font-size: 16px;
        font-weight: 500;
        color: #1B1C1F;
        flex-grow: 1;
        /* border: 1px solid yellow; */
        flex: 1;
        text-align: center;
        /* padding: 0 10px; */
    }


</style>
