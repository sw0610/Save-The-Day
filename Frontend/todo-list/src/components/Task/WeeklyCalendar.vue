<template>
  <div id="weekly">
    <div id="buttons">
      <MonthlyCalendar
        v-if="displayCalendar"
        :showDate=selectedDate
        :isWeekly="true"
        @closeCalendar="handleCloseCalendar"
        @selectedDate="dateSelect"
      />
      <button id="calendar" @click="displayCalendar=true">
        {{ getMonthName(selectedDate.getMonth()) }} {{ selectedDate.getDate() }}
        <img src="../../assets/icon/calendar_drop.svg" id="calendarBtn" />
      </button>
      <button id="today" @click="setToday">Today</button>
    </div>
    <div id="weeklyCalender">
      <!-- <div class="day" v-for="day in days" :key="day.id"  >{{ day }}</div>
            <div class="date" v-for="date in dates" :key="date.id" >{{ date }}</div> -->
      <!-- <div id="calendarItem" v-for="item in zippedData" :key="item.day"
      :class="{ 'selected-date': item.date == selectedDate.getDate() }"
      @click="selectDate(item.date)"> -->
      <div 
        id="calendarItem" 
        v-for="item in zippedData" 
        :key="item.day"
        :class="{ 'selected-date': item.date.getDate() == selectedDate.getDate() }"
        @click="selectDate(item.date)">
        <div class="calendarRow">
          <div class="day"
          :style="{ color: item.date.getDate() == selectedDate.getDate() ? '#FFFFFF' : '' }">
            {{ item.day }}
        </div>
        </div>
        <div class="calendarRow">
    <div 
      class="date"
      :style="{ color: item.date.getDate() == selectedDate.getDate() ? '#FFFFFF' : '' }"
    >
      {{ item.date.getDate() }}
        </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MonthlyCalendar from "./MonthlyCalendar.vue";

export default {
  components: {
    MonthlyCalendar,
  },
  data() {
    return {
      displayCalendar: false,
      selectedDate: new Date(),
      dates : ["4", "5", "6", "7", "8", "9", "10"]

    };
  },
//   setup() {
//     const days = ref(["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"]);
//     const zippedData = ref(
//       days.value.map((day, index) => ({ day, date: this.dates.value[index] }))
//     );

//     return {
//       zippedData,
//     };
//   },
  methods: {
    dateSelect(data) {
        
      this.selectedDate = data;
      this.displayCalendar = false;
      this.emitter.emit('send-date', this.selectedDate);
      this.updateWeek();

    },
    setToday() {
      this.selectedDate = new Date();
      this.updateWeek();
    },
    getMonthName(month) {
        const monthNames = ["January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December"
                        ];
        return monthNames[month];
    },
    getWeekRange(date) {
        const start = date.getDate() - date.getDay(); // 주의 시작 (일요일)
        const end = start + 6; // 주의 끝 (토요일)

        const weekStart = new Date(date.getFullYear(), date.getMonth(), start);
        const weekEnd = new Date(date.getFullYear(), date.getMonth(), end);

        return { start: weekStart, end: weekEnd };
    },
    
    // updateWeek() {
    //     const { start, end } = this.getWeekRange(this.selectedDate);
    //     this.dates = [];
    //     for (let date = new Date(start); date <= end; date.setDate(date.getDate() + 1)) {
    //         this.dates.push(date.getDate().toString());
    //     }
    // },
    updateWeek() {
    const { start, end } = this.getWeekRange(this.selectedDate);
    this.dates = [];
    for (let date = new Date(start); date <= end; date.setDate(date.getDate() + 1)) {
      this.dates.push(new Date(date)); // Date 객체를 dates 배열에 추가
    }
    
  },
  selectDate(date) {
    this.selectedDate = date; // date 파라미터가 Date 객체이므로 직접 설정
    this.emitter.emit('send-date', this.selectedDate);
  },

  },
  created() {
    this.updateWeek();
    if (this.$route.params.selectedDate) {
      // this.selectedDate = this.$route.params.selectedDate;
    }
  },
  computed: {
    zippedData() {
        const days = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
        return days.map((day, index) => ({ day, date: this.dates[index] }));
    },

    },
    mounted() {
        this.updateWeek();
        this.emitter.emit('send-date', this.selectedDate);
    },

};
</script>
<style scoped>
#weekly {
  background-color: #ffbab5;
  height: 136px;
  margin: 0;
  padding: 0 24px;
  position: relative;
}
#buttons {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

button {
  margin-top: 16px;
  font-family: "Noto Sans", sans-serif;
  font-weight: 500;
}
#calendar {
  font-size: 16px;
  color: #1b1c1f;
  padding-top: 3px;
  border: 0;
  background-color: transparent;
  display: flex; /* Add this line */
  align-items: center;
}
#today {
  border: 0;
  background: #ffffff;
  border-radius: 16px;
  padding: 3px 5px;
  font-size: 14px;
  color: #f19f9d;
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
#calendarItem {
  flex-grow: 1;
  flex-basis: 0;
  /* border: 1px solid blue; */
  padding-top: 8px;
  padding-bottom: 8px;
}
.day {
  font-family: "Noto Sans", sans-serif;
  font-size: 12px;
  font-weight: 400;
  color: #767e8c;
  flex-grow: 1;
  /* border: 1px solid red; */
  flex: 1;
  text-align: center;
  margin-bottom: 5px;

  /* padding: 0 10px; */
}
.date {
  font-family: "Noto Sans", sans-serif;
  font-size: 16px;
  font-weight: 500;
  color: #1b1c1f;
  flex-grow: 1;
  /* border: 1px solid yellow; */
  flex: 1;
  text-align: center;
  /* padding: 0 10px; */
}
#calendar img {
  margin-left: 5px;
  vertical-align: middle; /* Add this line */
}

.selected-date {
  background-color: #F1867E;
  border-radius: 8px;
}
</style>
