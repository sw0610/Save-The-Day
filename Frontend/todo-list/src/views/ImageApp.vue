<template>
    <div id="wrapupApp">
        <WrapUpHeader></WrapUpHeader>
        <ImageMaker></ImageMaker>
    </div>

</template>
<script>
import WrapUpHeader from '@/components/Wrap-Up/WrapUpHeader.vue'
import ImageMaker from '@/components/Wrap-Up/ImageMaker.vue'

export default {
    components: {
        WrapUpHeader,
        ImageMaker

    },
}
</script>
<style scoped>
#wrapupApp{
    background-color: #F7F7F7;
    height: 100vh;
}
</style>