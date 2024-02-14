<template>
    <div id="quoteComponent">
        <div  class="formComponent">
            <div class="title" >🎨 오늘의 그림 🎨</div>
            <div id="content"> 
                <img v-if="isLoading===false" id="image" :src="this.imgUrl"/>
            <div v-else id="loadingContainer">
                <a id="loading">생성 중입니다<br>잠시만 기다려주세요</a>
                <SpinnerMotion></SpinnerMotion>
                </div>
            </div>
            
            <div  v-show="isLoading===false" id="description">
                        <div class="emoji" v-if="this.emotion === 'happy'">
                            <img src="@/assets/icon/emoji/happy_color.svg" />
                        </div>
                        <div class="emoji" v-if="this.emotion === 'lovely'">
                            <img src="@/assets/icon/emoji/lovely_color.svg" />
                        </div>
                        <div class="emoji" v-if="this.emotion === 'soso'">
                            <img src="@/assets/icon/emoji/soso_color.svg" />
                        </div>
                        <div class="emoji" v-if="this.emotion === 'sad'">
                            <img src="@/assets/icon/emoji/sad_color.svg" />
                        </div>
                        <div class="emoji" v-if="this.emotion === 'angry'">
                            <img src="@/assets/icon/emoji/angry_color.svg" />
                        </div>
                <div id="taskTitle">
                     {{ this.task }}
                </div>
            </div>
        </div>
        <div id="buttons">
                <button class="btn" id="reGenerateBtn" @click="generateImage">다시 만들기</button>
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
            imgUrl:'',
            emotion:'happy',
            task:'',
            isLoading: true
        };
    },
    methods:{
        generateImage(){
            this.isLoading= true;

            http.get(`/open-ai/answer`, {
                params: {
                    type:"image",
                    date: this.$route.query.date
                },
            }).then(res => { 
                this.isLoading= false;
                console.log(res);
                this.imgUrl = res.data.imgUrl;
                this.emotion  =res.data.imgEmotion;
                this.task = res.data.imgTask;
                console.log(this.imgUrl);

            });
    
        },
        saveImage(){
            http.post(`/open-ai/answer`,{
                date:this.$route.query.date,
                type:"image",
                imgUrl:this.imgUrl,
                title:this.imgTask,
                emotion:this.emotion
            }).then(()=>this.$router.push(`/task`));
        }
    },

    mounted(){
        this.generateImage();
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
        margin-top: 60px;
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
#loadingContainer{
    text-align:center;

}

#saveBtn{
    background-color: #F19F9D;
    color: #FFFFFF;
}
#loading{
    text-align:center;
    color: #A3A3B9;
}
#image{
    height:300px;
    width:300px;
}

#description{
    margin-top: 16px;
    display: flex;
    flex-direction: row;
    gap: 1em;
}
/* #emojiContainer{
    border-radius: 15px;
    width: 60px;
    height: 60px;


    box-sizing: border-box;

        
    } */
    
    .emoji{
        width: 60px;
        height: 60px;
        border: 1px solid #F19F9D;
        background-color: #FFFFFF;
        border-radius: 15px;
        display: flex;  
        justify-content: center;
        align-items: center;
    }
    #taskTitle{
        border: 1px solid #F19F9D;
        background-color: #FFFFFF;
        padding: 12px;
        border-radius: 15px;
        align-items: center; 
        justify-content: flex-start; 
        display: flex;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 16px;
        flex-grow: 1;
    }

</style>