<template>
    <div class="header" id="header">
        <div id="titleBar">
            <img id="menu" src="@/assets/icon/left_icon.svg" @click="goBack">
            <div id="title">{{ $route.query.date }}</div>

        </div>
    </div>
</template>
<script>
export default {
    methods: {
        goBack() {
            this.$router.go(-1);      
        }
    }
};

</script>
<style scoped>
    .header{
        background-color: #F7F7F7;
        /* padding-left: 32px;        
        padding-right: 32px; */
        display: grid;
        justify-items: center;
        align-items: center;

    }

    #header{
        height: 70px;
    }


    /* #menu{ */
        /* position: absolute;
        padding-top: 30px; */
        /* padding-top: 30; */
        /* justify-self: start;


    } */
    /* #title {
    text-align: center;
    font-size: 18px;
    font-family: 'Noto Sans', sans-serif;
    font-weight: 400;
    color: #FFFFFF;
    } */

    #titleBar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    
}

#menu {
    position: absolute;
    left: 32px;
}

#title {
    position: absolute;
    left: 50%;
    transform: translate(-50%);
    font-size: 18px;
    font-family: 'Noto Sans', sans-serif;
    font-weight: 400;
    color: #1B1C1F;
}


</style>