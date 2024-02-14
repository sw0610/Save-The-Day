<template>
    <div id="wrapupApp">
        <WrapUpHeader></WrapUpHeader>
        <QuoteComponent :date="date"></QuoteComponent>
        <FictionComponent></FictionComponent>
        <ImageComponent></ImageComponent>
    </div>

</template>
<script>
import WrapUpHeader from '@/components/Wrap-Up/WrapUpHeader.vue'
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