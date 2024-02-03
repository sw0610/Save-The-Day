<template>

    <div class="header" id="header">
        <div id="titleBar">
            <div id="menu">button</div>
            <div id="title">Todo List</div>
        </div>
    </div>
</template>
<script>


</script>
<style scoped>
    .header{
        background-color: #FFBAB5;
        padding-left: 32px;        
        padding-right: 32px;
    }

    #header{
        height: 120px;
    }
    #titleBar{
        position: relative;
    }

    #menu{
        /* position: absolute;
        padding-top: 30px; */
        /* padding-top: 30; */
    

    }

    #title{
        /* position: absolute; */
        text-align: center;
        padding-top: 30px;
        font-size: 20px;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 400;
        color: #FFFFFF;
        /* display: inline-block; */
    }

</style>