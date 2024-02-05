<template>
    <div id="form">
        <div>
            <div class="formComponent" id="taskTitle">
                <div class="title">Title</div>
                <input class="box">
            </div>
            <div class="formComponent" id="date">
                <div class="title">Date</div>
                <button class="box"></button>  
            </div>
            <div  class="formComponent" id="importance">
                <div class="title">Importance</div>
                <button class="box"></button>  
            </div>
            <div class="formComponent" id="status">
                <div class="title">Status</div>
                <button class="box"></button>  
            </div>
            <div  class="formComponent" id="content">
                <div class="title">Content</div>
                <input class="box">
            </div>
        </div>
        <button>저장</button>
    </div>
</template>
<script>
</script>
<style>
    #form{
        background-color: #F7F7F7;
        height: calc(100vh - 70px);
        padding: 20px 32px;
    }

    .formComponent{
        margin-top: 12px;
        margin-bottom: 12px;
    }
    .title{
        color: #F19F9D;
        font-size: 14px;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;
        margin-bottom: 2px
    }
    .box{
        border: 1px solid #7E869E;
        width: 100%;
        height: 52px;
        border-radius: 22.5px;
        /* padding: 12px 17px; */
        background-color: transparent;
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #7E869E;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;
        font-size: 14px;
    }

</style>