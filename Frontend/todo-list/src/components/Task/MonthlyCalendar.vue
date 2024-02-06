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
        <div v-for="day in days" :class="day.class" :key="day.id">
          {{ day.date }}
        </div>
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
      days: []
    };
  },
  computed: {
    currentMonth() {
      return this.date.toLocaleDateString("en-US", {month:'long', year:'numeric'});
    }
  },
  methods: {
    renderCalendar() {
      const prevLastDay = new Date(this.date.getFullYear(), this.date.getMonth(), 0).getDate();
      const totalMonthDay = new Date(this.date.getFullYear(), this.date.getMonth() + 1, 0).getDate();
      const startWeekDay = new Date(this.date.getFullYear(), this.date.getMonth(), 1).getDay();
      let totalCalendarDay = 42; // 6 weeks

      this.days = [];

      for (let i = 0; i < totalCalendarDay; i++) {
        let day = i - startWeekDay + 1;
        let date = new Date(this.date.getFullYear(), this.date.getMonth(), day);
        if (i < startWeekDay) {
          // previous month
          date = new Date(this.date.getFullYear(), this.date.getMonth(), day);
          this.days.push({ id: i, date: prevLastDay - startWeekDay + i + 1, class: 'padding-day' });
        } else if (i >= startWeekDay + totalMonthDay) {
          // next month
          date = new Date(this.date.getFullYear(), this.date.getMonth() + 1, day - totalMonthDay);
          this.days.push({ id: i, date: day - totalMonthDay, class: 'padding-day' });
        } else {
          // current month
          let dayClass = this.today.getTime() === date.getTime() ? 'current-day' : 'month-day';
          this.days.push({ id: i, date: day, class: dayClass });
        }
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
    }

      },
      created() {
        this.renderCalendar();
      }
    };
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;600;700&display=swap");

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
  font-family: "Quicksand", sans-serif;
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
  color: #19181a;
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
  color: #19181a;
  text-align: center;
  line-height: 40px;
  font-weight: 500;
  font-size: 1rem;
}

.weekday-name {
  color: #19181a;
  font-weight: 700;
}

.current-day {
  background-color: rgb(112, 71, 235);
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
  color: rgb(112, 71, 235);
  transition: 0.1s;
  cursor: pointer;
  box-shadow: inset 0px 0px 0px 1.5px rgb(112, 71, 235);
}

.calendar-toolbar > [class$="month-btn"]:focus,
.month-day:focus,
.btn:focus {
  border-radius: 15px;
  background-color: rgb(112, 71, 235);
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
  font-family: "Quicksand", sans-serif;
  font-weight: 600;
  font-size: 0.9rem;
  margin-right: 1px;
  box-shadow: 0px 0px 0px #efefef;
}
</style>