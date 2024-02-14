<template>
    <div id="wrapupApp">
        <WrapUpHeader></WrapUpHeader>
        <QuoteMaker></QuoteMaker>
    </div>

</template>
<script>
import WrapUpHeader from '@/components/Wrap-Up/WrapUpHeader.vue'
import QuoteMaker from '@/components/Wrap-Up/QuoteMaker.vue'

export default {
    components: {
        WrapUpHeader,
        QuoteMaker

    },
}
</script>
<style scoped>
#wrapupApp{
    background-color: #F7F7F7;
    height: 100vh;
}
</style>