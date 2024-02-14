<template>
    <div id="quoteComponent">
        <div  class="formComponent">
            <div class="title" >📖 오늘의 소설 📖</div>
            <div id="content"> 
                <a v-if="isLoading===false" id="fiction"> {{ this.fictionContent }} </a>
                <div v-else id="loadingContainer">
                <a id="loading">생성 중입니다<br>잠시만 기다려주세요</a>
                <SpinnerMotion></SpinnerMotion>
                </div>
            </div>
        </div>
        <div id="buttons">
                <button class="btn" id="reGenerateBtn" @click="generateFiction">다시 만들기</button>
                <button class="btn" id="saveBtn" @click="deleteTask">저장</button>
        </div>
    </div>
    
</template>
<script>
import http from '@/util/http-common.js';
import SpinnerMotion from '@/components/SpinnerMotion.vue';

export default{
    components:{
        SpinnerMotion
    },
    data(){
        return{
            fictionContent:'',
            isLoading: true
        };
    },
    methods:{
        generateFiction(){
            this.isLoading= true;
            console.log(this.$route.query.date);

            http.get(`/open-ai/answer`, {
                params: {
                    type:"fiction",
                    date: this.$route.query.date
                },
            }).then(res => { 
                this.isLoading= false;
                this.fictionContent = res.data;
            });
        }
    },

    mounted(){
        this.generateFiction();
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
        text-align: center;
        font-size: 20px;
        font-family: 'Noto Sans kr', sans-serif;
        font-weight: 500;
        margin-top:60px;
        margin-bottom: 20px
    }
    #content{
        box-sizing: border-box;
        border: 1px solid #F19F9D;
        background-color: #FFFFFF;
        width: 100%;
        border-radius: 15px;
        /* padding: 12px 17px; */
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #1B1C1F;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 16px;
        padding: 12px;
        justify-content: center; 
        overflow-x: hidden;
        max-height: 450px;
    }

    #buttons{
        position: fixed;
        box-sizing:border-box;
        bottom: 0;
        left: 0;
        right: 0;
        margin-top: 36px;
        display: flex;
        width: 100%;
        gap: 10px;
        justify-content: space-between;
        padding-left: 32px;
        padding-right: 32px;
        background-color: #F7F7F7;
        margin-bottom: 40px;

    }
    #loadingContainer{
    text-align:center;

    }
.btn{
    /* width: 122px; */
    height: 48px;
    flex: 1;
    border-radius: 8px;
    border-style: none;
    font-size: 16px;
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 500;
    
}
#reGenerateBtn{
 color: #1B1C1F;
 background-color: transparent;

 border: 1px solid #F19F9D;
}

#saveBtn{
    background-color: #F19F9D;
    color: #FFFFFF;
}
#loading{
    text-align:center;
    color: #A3A3B9;
}

</style>