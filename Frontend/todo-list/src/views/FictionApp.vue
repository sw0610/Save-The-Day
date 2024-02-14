<template>
    <div id="wrapupApp">
        <WrapUpHeader></WrapUpHeader>
        <FictionMaker></FictionMaker>
    </div>

</template>
<script>
import WrapUpHeader from '@/components/Wrap-Up/WrapUpHeader.vue'
import FictionMaker from '@/components/Wrap-Up/FictionMaker.vue'

export default {
    components: {
        WrapUpHeader,
        FictionMaker

    },
}
</script>
<style scoped>
#wrapupApp{
    background-color: #F7F7F7;
    height: 100vh;
}
</style>