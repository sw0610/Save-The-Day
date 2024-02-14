<template>
    <div id="quoteComponent" @click="goToMaker">
        <div  class="formComponent">
            <div class="title" >오늘의 소설</div>
            <div id="content"> 📖오늘의 소설을 생성해보세요📖 </div>
        </div>
    </div>
    
</template>
<script>
export default{
    // props: ['date'],
    data(){
        return{
            date:new Date()
        }
    },

    methods:{
        
        goToMaker(){
            const offset = new Date().getTimezoneOffset() * 60000;
            const krDate = new Date(this.date-offset)
            const dateString = krDate.toISOString().slice(0, 10);
            console.log(this.date);
            this.$router.push({
            path: '/wrap-up/fiction',
            query: { date: dateString}
        });        
        }
    },
    mounted() {
        this.emitter.on('send-date', (selectedDate) => {
            this.date = new Date(selectedDate);
        });
    
    }
}
</script>
<style scoped>
    #quoteComponent{
        background-color: #F7F7F7;
        padding-left: 32px;
        padding-right: 32px;
        padding-top: 32px;
     }
    .title{
        color: #1B1C1F;
        font-size: 16px;
        font-family: 'Noto Sans kr', sans-serif;
        font-weight: 500;
        margin-bottom: 8px
    }
    #content{
        box-sizing: border-box;
        border: 1px solid #F19F9D;
        background-color: #FFFFFF;
        width: 100%;
        height: 52px;
        border-radius: 15px;
        /* padding: 12px 17px; */
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #1B1C1F;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 14px;
        padding-left: 12px;
        justify-content: center; 

    }
</style>