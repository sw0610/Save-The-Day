<template>
  <div id="monthlyCalendar">
  <div class="card">
    <div class="calendar-toolbar">
      <button class="prev month-btn" @click="changeMonth(-1)">
        <i class="fas fa-chevron-left"></i>
      </button>
      <div class="current-month">{{ currentMonth }}</div>
      <button class="next month-btn" @click="changeMonth(1)">
        <i class="fas fa-chevron-right"></i>
      </button>
    </div>
    <div class="calendar">
      <div class="weekdays">
        <div class="weekday-name" v-for="day in ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']" :key="day">
          {{ day }}
        </div>
      </div>                                                                                              
      <div class="calendar-days">
        <div v-for="day in days" :id="'day-' + day.id" 
        :class="[day.class, { 'selected-day': day.year === selectedDay.year&& day.month===selectedDay.month && day.date===selectDate.date }]" 
        :key="day.id" @click="selectDate(day)">
          {{ day.date }}
        </div>
        <!-- <div v-for="day in days" :id="'day-' + day.id" :class="[day.class, { 'selected-day': day === selectedDay }]" :key="day.id" @click="selectDate(day)">
        {{ day.date }}
      </div> -->
      </div>
    </div>
    <div class="goto-buttons">
      <button type="button" class="btn prev-year" @click="goToYear(-1)">Prev Year</button>
      <button type="button" class="btn today" @click="goToToday">Today</button>
      <button type="button" class="btn next-year" @click="goToYear(1)">Next Year</button>
    </div>
  </div>
</div>
</template>

<script>
export default {
  data() {
    return {
      today: new Date(),
      date: new Date(),
      days: [],
      selectedDate: {
        year:new Date().getFullYear(),
        month:new Date().getMonth(),
        date: new Date().getDate()        
      }
    };
  },
  computed: {
    currentMonth() {
      return this.date.toLocaleDateString("en-US", {month:'long', year:'numeric'});
    }
  },
  methods: {
    renderCalendar() {
      const firstDay = new Date(this.date.getFullYear(), this.date.getMonth(), 1);
      const lastDay = new Date(this.date.getFullYear(), this.date.getMonth() + 1, 0);

      const startWeekDay = firstDay.getDay();
      const totalMonthDay = lastDay.getDate();

      this.days = [];

      // Add empty slots for previous month
      for (let i = 0; i < startWeekDay; i++) {
        // let prevLastDay = new Date(this.date.getFullYear(), this.date.getMonth(), 0).getDate();
        this.days.push({ id: i, date: ' ', class: 'padding-day' });
      }

      // Add days of current month
      for (let i = 1; i <= totalMonthDay; i++) {
        let date = new Date(this.date.getFullYear(), this.date.getMonth(), i);
        let dayClass = this.today.getTime() === date.getTime() ? 'current-day' : 'month-day';
        this.days.push({ id: startWeekDay + i - 1, year:this.date.getFullYear(), month: this.date.getMonth(), date: i, class: dayClass });
      }
    },
    changeMonth(diff) {
      this.date.setMonth(this.date.getMonth() + diff);
      this.date = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate());
      this.currentMonth = this.date.toLocaleDateString("en-US", {month:'long', year:'numeric'});
      this.renderCalendar();

    },
    goToYear(diff) {
      this.date = new Date(this.date.getFullYear() + diff, this.date.getMonth(), this.date.getDate());
      this.renderCalendar();
    },
    goToToday() {
      this.date = new Date();
      this.renderCalendar();
    },
    selectDate(day) {
      console.log("day", day);
      console.log(this.selectedDate);
      // this.selectedDate = new Date(this.date.getFullYear(), this.date.getMonth(), day.date);
      this.selectDate.year=this.date.getFullYear();
      this.selectDate.month=this.date.getMonth();
      this.selectDate.date=day.date;
    }
//     selectDate(day) {
//   this.selectedDay = day;
//   this.selectedDate = new Date(this.date.getFullYear(), this.date.getMonth(), day.date);
// }

      },
      created() {
        this.renderCalendar();
        // Find today in the days array
        const today = this.days.find(day => day.date === this.today.getDate());
        console.log("today33", this.date);
        // Select today
        if (today) {
          this.selectDate.year=today.getFullYear();
          this.selectDate.month=today.getMonth();
          this.selectDate.date=today.getDate();
        }
        console.log("{{today}}", this.selectedDate);
      }
    };
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

#monthlyCalendar {
  height: 100vh;
  background: rgb(238,174,202);
  background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Noto Sans", sans-serif;
  user-select: none;
}

.card {
  width: 316px;
  height: fit-content;
  background-color: #FFFFFF;
  border-radius: 15px;
  box-shadow: 0px 0px 10px #efefef;
}

.calendar-toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #efefef;
}

.calendar-toolbar > .current-month {
  font-size: 20px;
  font-weight: bold;
  color: #19181a;
}

.calendar-toolbar > [class$="month-btn"] {
  width: 40px;
  aspect-ratio: 1;
  text-align: center;
  line-height: 40px;
  font-size: 14px;
  color: #1B1C1F;
  background: #f8f7fa;
  border: none;
  border-radius: 15px;
}

.weekdays,
.calendar-days {
  display: flex;
  flex-wrap: wrap;
  padding-inline: 18px;
}
.weekdays {
  padding-top: 12px;
}
.calendar-days {
  padding-bottom: 12px;
}

.weekday-name,
[class$="-day"] {
  width: 40px;
  height: 40px;
  color: #1B1C1F;
  text-align: center;
  line-height: 40px;
  font-weight: 500;
  font-size: 1rem;
}

.weekday-name {
  color: #1B1C1F;
  font-weight: 700;
}

.current-day {
  background-color: #F19F9D;
  color: #f8f7fa;
  border-radius: 15px;
  font-weight: 700;
  transition: 0.5s;
  cursor: pointer;
}

.padding-day {
  color: #a5a5a5;
  user-select: none;
}

.calendar-toolbar > [class$="month-btn"]:hover,
.month-day:hover,
.btn:hover {
  border-radius: 15px;
  background-color: #f8f7fa;
  color: #F19F9D;
  transition: 0.1s;
  cursor: pointer;
  box-shadow: inset 0px 0px 0px 1.5px #F19F9D;
  background-color: #F19F9D;
  color: #f8f7fa;
}

.calendar-toolbar > [class$="month-btn"]:focus,
.month-day:focus,
.btn:focus {
  border-radius: 15px;
  background-color: #F19F9D;
  color: #f8f7fa;
}

.goto-buttons {
  border-top: 1px solid #efefef;
  padding-block: 18px;
  display: flex;
  justify-content: space-evenly;
}

.btn {
  background: #f8f7fa;
  border: none;
  border-radius: 15px;
  padding: 11px 13px;
  color: #19181a;
  font-family: "Noto Sans", sans-serif;
  font-weight: 500;
  font-size: 0.9rem;
  margin-right: 1px;
  box-shadow: 0px 0px 0px #efefef;
}

.selected-day {
  background-color: #F19F9D; /* 원하는 색상으로 바꿔주세요 */
  color: #ffffff; /* 글자색도 원하시는 색상으로 변경하세요 */
}
/* https://www.codewithfaraz.com/content/97/learn-how-to-build-a-dynamic-calendar-with-html-css-and-javascript */
</style>