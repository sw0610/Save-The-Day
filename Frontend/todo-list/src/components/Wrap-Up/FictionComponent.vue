<template>
    <div id="quoteComponent" @click="goToMaker">
        <div  class="formComponent">
            <div class="title" >오늘의 소설</div>
            <div v-if="hasData===false" id="content"> 📖오늘의 소설을 생성해보세요📖 </div>
            <div v-else id="content"> {{ fiction }} </div>

        </div>
    </div>
    
</template>
<script>
import http from '@/util/http-common.js';

export default{

    // props: ['date'],
    data(){
        return{
            date:new Date(),
            fiction:null,
            dateString:null,
            hasData:false
        }
    },

    methods:{
        
        goToMaker(){
            const offset = new Date().getTimezoneOffset() * 60000;
            const krDate = new Date(this.date-offset)
            this.dateString = krDate.toISOString().slice(0, 10);
            console.log(this.date);
            this.$router.push({
            path: '/wrap-up/fiction',
            query: { date: this.dateString, hasData:this.hasData}
        });        
        }
    },
    mounted() {
        this.emitter.on('send-date', (selectedDate) => {
            this.date = new Date(selectedDate);
        });
        const offset = new Date().getTimezoneOffset() * 60000;
            const krDate = new Date(this.date-offset)
            this.dateString = krDate.toISOString().slice(0, 10);
        http.get(`/open-ai/get-answer?type=fiction&date=`+this.dateString).then(res=>{
            console.log(res);
            if(res.data==""){
                this.hasData=false;
                console.log("sfsdfsddddddf")

            }else{
                console.log("sfsdfsdf")

                console.log(res)
                console.log(res.data)

                this.hasData=true;
                this.fiction = res.data;
            }
        })

    
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