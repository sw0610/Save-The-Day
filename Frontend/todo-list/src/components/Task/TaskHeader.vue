<template>

    <div class="header" id="header">
        <div id="titleBar">
            <img id="menu" src="@/assets/icon/menu_bar.svg">
            <div id="title">Todo List</div>

        </div>
    </div>
</template>
<script>


</script>
<style scoped>
    .header{
        background-color: #FFBAB5;
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
    color: #FFFFFF;
}


</style>