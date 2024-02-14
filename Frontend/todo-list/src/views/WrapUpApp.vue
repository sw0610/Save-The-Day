<template>
    <div id="wrapupApp">
        <WrapUpHeader></WrapUpHeader>
        <WeeklyCalender></WeeklyCalender>
        <QuoteComponent :date="date"></QuoteComponent>
        <FictionComponent :date="date"></FictionComponent>
        <ImageComponent :date="date"></ImageComponent>
    </div>

</template>
<script>
import WrapUpHeader from '@/components/Wrap-Up/WrapUpHeader.vue'
import WeeklyCalender from '../components/Task/WeeklyCalendar.vue'
import QuoteComponent from '@/components/Wrap-Up/QuoteComponent.vue'
import FictionComponent from '@/components/Wrap-Up/FictionComponent.vue'
import ImageComponent from '@/components/Wrap-Up/ImageComponent.vue'


export default {
    data() {
        return {
            date: this.$route.query.date,
        };
    },
    components: {
        WrapUpHeader,
        WeeklyCalender,
        QuoteComponent,
        FictionComponent,
        ImageComponent
    },
}
</script>
<style scoped>
#wrapupApp{
    background-color: #F7F7F7;
    height: 100vh;
}
</style>