<template>
  <div id="monthlyCalendar">
    <img id="closeBtn" src="../../assets/icon/close.svg" alt="close Tab" @click="closeCalendar"/>
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
          <div
            class="weekday-name"
            v-for="day in ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']"
            :key="day"
          >
            {{ day }}
          </div>
        </div>
        <div class="calendar-days">
          <div v-for="(item, index) in days" :key="index">
          <div
          @click="selectDay(item)"

            v-if="item.date"
            :id="'day-' + item.id" 
            :class="[item.class, {
              'selected-day': item.date.getFullYear() === selectedDate.getFullYear() && item.date.getMonth() === selectedDate.getMonth() && item.date.getDate() === selectedDate.getDate(),
            }]"
          >
            {{ item.day }}
          </div>
            <div v-else-if="days.filter(day => day.date===null).length !== 7" class="padding-day">
              {{ item.day }}
            </div>
          </div>
        </div>
      </div>
      <div class="goto-buttons">
        <button type="button" class="btn prev-year" @click="navigateYear(-1)">
          Prev Year
        </button>
        <button type="button" class="btn today" @click="goToToday">
          Today
        </button>
        <button type="button" class="btn next-year" @click="navigateYear(1)">
          Next Year
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props:['showDate'],
  data() {
    return {
      selectedDate: new Date(this.showDate),
      date: new Date(this.showDate),
      today: new Date(),
      days: [],
    };
  },


  computed: {
    currentMonth() {
      return this.date.toLocaleDateString("en-US", {
        month: "long",
        year: "numeric",
      });
      
    },
  },
  methods: {   
    closeCalendar(){
      this.$emit('selectedDate', this.selectedDate);
    },

    changeMonth(num) {
      console.log("0",this.selectedDate);
      console.log("0",this.showDate);

      this.date.setMonth(this.date.getMonth() + num);
      console.log("1",this.selectedDate);
      console.log("0",this.showDate);

      this.currentMonth = this.date.toLocaleDateString("en-US", {month:'long', year:'numeric'});
      console.log("2",this.selectedDate);
      console.log("0",this.showDate);

      this.date = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate());
      console.log("3",this.selectedDate);
      console.log("0",this.showDate);

      this.renderCalendar();
      console.log("4",this.selectedDate);
      console.log("0",this.showDate);

    },
    navigateYear(diff) {
      this.date = new Date(this.date.getFullYear() + diff, this.date.getMonth(), this.date.getDate());
      this.renderCalendar();
    },
    navigateToday() {
      this.date = new Date();
      this.selectedDay = this.today;
      this.renderCalendar();
    },
    selectDay(day) {

      if (day && day.date) {
        this.selectedDate = day.date;
      }
    },

    renderCalendar() {
      const totalMonthDay = new Date(
        this.date.getFullYear(),
        this.date.getMonth() + 1,
        0
      ).getDate();
      const startWeekDay = new Date(
        this.date.getFullYear(),
        this.date.getMonth(),
        0
      ).getDay();

      this.days = [];
      let totalCalendarDay = 6 * 7;
      for (let i = 0; i < totalCalendarDay; i++) {
        let day = i - startWeekDay;
        if (i <= startWeekDay) {
          this.days.push({ day: ' ', date: null });
        } else if (i <= startWeekDay + totalMonthDay) {
          let date = new Date(this.date.getFullYear(), this.date.getMonth(), i);
          let dayClass = this.today.getTime() === date.getTime() ? 'current-day' : 'month-day';
          this.days.push({
            id: startWeekDay + i - 1,
            day,
            date: new Date(this.date.getFullYear(), this.date.getMonth(), day),
            class: dayClass
          });
        }
      }
    },
  },
  mounted() {
    this.renderCalendar();
  },
};

</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

#monthlyCalendar {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Noto Sans", sans-serif;
  user-select: none;
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.card {
  width: 316px;
  height: fit-content;
  background-color: #ffffff;
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
  color: #1b1c1f;
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
  color: #1b1c1f;
  text-align: center;
  line-height: 40px;
  font-weight: 500;
  font-size: 1rem;
}

.weekday-name {
  color: #1b1c1f;
  font-weight: 700;
}

.current-day {
  background-color: #f19f9d;
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
  background-color: #f19f9d;
  color: #FFFFFF;
  transition: 0.1s;
  cursor: pointer;
  box-shadow: inset 0px 0px 0px 1.5px #f19f9d;
  /* background-color: transparent; */
  /* color: #1b1c1f; */
}

.calendar-toolbar > [class$="month-btn"]:focus,
.month-day:focus,
.btn:focus {
  border-radius: 15px;
  background-color: #f19f9d;
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
  background-color: #f19f9d; /* 원하는 색상으로 바꿔주세요 */
  border-radius: 15px;
  color: #ffffff; /* 글자색도 원하시는 색상으로 변경하세요 */
}
/* https://www.codewithfaraz.com/content/97/learn-how-to-build-a-dynamic-calendar-with-html-css-and-javascript */

#closeBtn {
  position: absolute;
  right: 32px;
  top: 32px;
}
</style>
