<template>
    <div id="quoteComponent" @click="goToMaker">
        <div  class="formComponent">
            <div class="title" >오늘의 소설</div>
            <div id="content"> 📖오늘의 소설을 생성해보세요📖 </div>
            <!-- <div v-else id="content"> {{ fiction }} </div> -->

        </div>
    </div>
    
</template>
<script>
// import http from '@/util/http-common.js';

export default{
    props: ['date'],
    methods:{
        goToMaker(){
            console.log(this.date);
            this.$router.push({
            path: '/wrap-up/fiction',
            query: { date: this.date}
        });        
        }
    }
}
    // methods:{
        
        // goToMaker(){
        //     const offset = new Date().getTimezoneOffset() * 60000;
        //     const krDate = new Date(this.date-offset)
        //     this.dateString = krDate.toISOString().slice(0, 10);
        //     console.log(this.date);
        //     this.$router.push({
        //     path: '/wrap-up/fiction',
        //     query: { date: this.date, hasData:this.hasData}
        // });        
        // },
        // getFiction(date){
        //     http.get(`/open-ai/get-answer?type=fiction&date=`+date).then(res=>{
        //         console.log("res",res);
        //         if(res.data==""){
        //             this.hasData=false;
        //             console.log("sfsdfsddddddf")

        //         }else{
        //             console.log("sfsdfsdf")

        //             console.log(res)
        //             console.log(res.data)

        //             this.hasData=true;
        //             this.fiction = res.data;
        //         }
        //     });

        // },
        // toDateString(date){
        //     date = new Date(date);
        //     let year = date.getFullYear();
        //     let month = (1 + date.getMonth()).toString().padStart(2, '0');
        //     let day = date.getDate().toString().padStart(2, '0');

        //     return `${year}-${month}-${day}`;
        // }
    // },
    // mounted() {
    //     // const offset = new Date().getTimezoneOffset() * 60000;
    //     // const krDate = new Date(this.date-offset);


    //     this.emitter.on('send-date', (selectedDate) => {
    //         console.log("aa",selectedDate);
    //         // const krDate = new Date(selectedDate-offset);
    //         // this.dateString = krDate.toISOString().slice(0, 10);
        
    //         console.log("ax", this.toDateString(selectedDate))
    //         this.dateString = this.toDateString(selectedDate);
    //         this.getFiction(this.getFiction(this.dateString));
    //     });

    //     if(this.hasData===false){
    //         this.dateString = this.toDateString(new Date());
    //         console.log("mounted", this.dateString);
    //     }


    
    // },
    // mounted(){
    //     console.log("str",this.dateString);
    //     this.getFiction(this.getFiction(this.dateString));

    // }
// }
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