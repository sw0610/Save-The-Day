<template>
    <div id="form">
        <div>
            <div class="formComponent" id="taskTitle">
                <div class="title">Title</div>
                <div id="titleInputBox">
                    <input class="box" id="titleInput" v-model="formData.title" type="text" @focus="isTitleFocused=true" @blur="isTitleFocused=false">
                    <img id="inputIcon" :src= "require(isTitleFocused ? '@/assets/icon/check_selected.svg':'@/assets/icon/check.svg')">
                </div>
            </div>
            <div class="formComponent" id="date">
                <div class="title">Date</div>
                <button class="box"  @click="displayCalendar=true">{{ date.getFullYear() }}년 {{ date.getMonth()+1 }}월 {{ date.getDate() }}일</button>  
            </div>
            <MonthlyCalendar v-if="displayCalendar" :showDate=date :isForm="true" @closeCalendar="handleCloseCalendar" @selectedDate="dateSelect"/>
            <div  class="formComponent" id="importance">
                <div class="title">Priority</div>
                <div class="selectBox" :class="{ selectBoxOpen: isOpen }">
                    <button class="selectBoxLabel" :class="{ openLabel: isOpen }" @click="toggleDropdown">{{ selectedOption || '선택해주세요' }}</button>  
                    <ul v-show="isOpen" class="optionList">
                        <li class="optionItem" v-for="(importanceOpt, index) in importanceOpts" :key="index" @click="selectOption(importanceOpt)">{{ importanceOpt }}</li>
                    </ul>
                </div>
            </div>
            <div class="formComponent" id="status">
                <div class="title">Status</div>
                    <div class="selectBox" :class="{ selectStatusBoxOpen: isStatusOpen }">
                        <button class="selectBoxLabel" :class="{ openStatusLabel: isStatusOpen }" @click="toggleStatusDropdown">{{ selectedStatusOption || '선택해주세요' }}</button>  
                        <ul v-show="isStatusOpen" class="optionList">
                            <li class="optionItem" v-for="(statusOpt, index) in statusOpts" :key="index" @click="selectStatusOption(statusOpt)">{{ statusOpt }}</li>
                        </ul>
                </div> 
            </div>
            <div  class="formComponent" id="content">
                <div class="title" >Content</div>
                <textarea id="contentInput" class="box" type="text" v-model="formData.content" @focus="isContentSelected=true" @blur="isContentSelected=false"></textarea>
            </div>
            <div id="emotion" v-if="selectedStatusOption === 'Finished'">
                <div class="title">Emotion</div>
                    <div id="emojiContainer">
                        <div class="emoji" id="happy" @click="selectEmotion('happy')" :class="{ selectedEmotion: selectedEmotion === 'happy' }">
                            <img :src="require(selectedEmotion === 'happy' ? '@/assets/icon/emoji/happy_color.svg' : '@/assets/icon/emoji/happy_gray.svg')" />
                        </div>
                        <div class="emoji" id="lovely" @click="selectEmotion('lovely')" :class="{ selectedEmotion: selectedEmotion === 'lovely' }">
                            <img :src="require(selectedEmotion === 'lovely' ? '@/assets/icon/emoji/lovely_color.svg' : '@/assets/icon/emoji/lovely_gray.svg')" />
                        </div>
                        <div class="emoji" id="soso" @click="selectEmotion('soso')" :class="{ selectedEmotion: selectedEmotion === 'soso' }">
                            <img :src="require(selectedEmotion === 'soso' ? '@/assets/icon/emoji/soso_color.svg' : '@/assets/icon/emoji/soso_gray.svg')" />
                        </div>
                        <div class="emoji" id="sad" @click="selectEmotion('sad')" :class="{ selectedEmotion: selectedEmotion === 'sad' }">
                            <img :src="require(selectedEmotion === 'sad' ? '@/assets/icon/emoji/sad_color.svg' : '@/assets/icon/emoji/sad_gray.svg')" />
                        </div>
                        <div class="emoji" id="angry" @click="selectEmotion('angry')" :class="{ selectedEmotion: selectedEmotion === 'angry' }">
                            <img :src="require(selectedEmotion === 'angry' ? '@/assets/icon/emoji/angry_color.svg' : '@/assets/icon/emoji/angry_gray.svg')" />
                        </div>
                    </div>                
            </div>
        </div>
        <button id="submitBtn" :disabled="!isFormValid" @click="postTask">저장</button>
    </div>
</template>
<script>
import MonthlyCalendar from "./MonthlyCalendar.vue";
import http from "@/util/http-common.js"

    export default{
        components: {
            MonthlyCalendar,
        },
        data(){
            return{
                isTitleFocused: false,
                isContentSelected:false,
                displayCalendar:false,
                importanceOpts:['High', 'Normal', 'Low'],
                isOpen: false,
                selectedOption: null,
                statusOpts: ['Not Started', 'In Progress', 'Finished'],
                isStatusOpen: false,
                selectedStatusOption: null,
                formData:{
                    title:'',
                    content:'',
                    dueDate:null,
                    priority:null,
                    processStatus:null,
                    emotion:null
                },
                selectedEmotion: null,
                date:new Date()
            };
        },
        methods: {
            dateSelect(data) {
                this.date = data;
                console.log(this.date);
                this.displayCalendar = false;
            },
            toggleDropdown() {
                this.isOpen = !this.isOpen;
                if(this.isOpen){
                    this.isStatusOpen=false;
                }
            },
            selectOption(option) {
                this.selectedOption = option;
                this.formData.priority = option;
                this.isOpen = false; // 선택한 후 드롭다운을 닫습니다.
            },
            toggleStatusDropdown() {
                this.isStatusOpen = !this.isStatusOpen;
                if(this.isStatusOpen){
                    this.isOpen=false;
                }
            },
            selectStatusOption(option) {
                this.selectedStatusOption = option;
                this.formData.processStatus = option;
                this.isStatusOpen = false; // 선택한 후 드롭다운을 닫습니다.
            },
            selectEmotion(id){
                this.selectedEmotion = id;
            },
            postTask(){
                const offset = new Date().getTimezoneOffset() * 60000;
                const krDate = new Date(this.date-offset)

                http.post(`/task/detail`,{
                    title:this.formData.title,
                    dueDate:krDate,
                    priority:this.formData.priority,
                    processStatus:this.formData.processStatus,
                    emotion:this.formData.emotion,
                    content:this.formData.content
                }).then(()=>this.$router.push(`/task`));
            }
        },
        computed:{
            isFormValid(){
                var data= this.formData;
                return data.title!==''
                    && data.priority!==null && data.processStatus!==null;
            }
        },
        mounted(){
            if(this.$route.params.taskId){
                http.get(`/task/detail/${this.$route.params.taskId}`)
                .then(res => {
                    console.clear();
                    console.log(res.data);
                    this.formData=res.data;
                    this.selectedOption = res.data.priority;
                    this.selectedStatusOption = res.data.processStatus;
                    this.date = new Date(res.data.dueDate);
                    // console.log(this)
                    // this.formData.title=res.title;
                });
            }

        },
        created() {
            console.log("created에서 확인합니다");
            if (this.$route.query.selectedDate) {
                console.log("??????!!!!?!?!");
                console.log(this.$route.query.selectedDate);
                this.date = new Date(this.$route.query.selectedDate);
            }
            console.log("끝났는지 확인합니다");

        }
    };
</script>
<style>
    #form{
        position: relative;
        background-color: #F7F7F7;
        height: calc(100vh - 100px);
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
        margin-bottom: 5px
    }
    .box{
        box-sizing: border-box;
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
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 14px;
        padding-left: 12px;
    }

    #titleInputBox{
        position: relative;
    }
    #inputIcon{
        position: absolute;
        top:17px;
        left: 17px;
    }

    #titleInput{
        padding-left: 48px;
    }
    #titleInput:focus-within, #contentInput:focus-within{
        outline: none !important;
        border-color: #F19F9D;
        color: #1B1C1F;
    }

    #contentInput{
        padding: 24px 24px !important;
        height: 100px;
    }

    .selectBox * { box-sizing: border-box; }
    .selectBox {
        position: relative;
        height: 52px;
        border-radius: 22.5px;
        border: 1px solid #7E869E;
        background-color: transparent;
        background-size: 20px;
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #7E869E;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 14px;
        padding-left: 12px;
    /* cursor: pointer; */

        /* padding: 12px 17px; */
    }


    .selectBox .selectBoxLabel {
        display: flex;
        width: 100%;
        align-items: center;
        /* width: inherit; */
        height: inherit;
        border: 0 none;
        outline: 0 none;
        padding-left: 15px;
        background: transparent;
        position: static;
        cursor: pointer;
        color: #7E869E;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;
        font-size: 14px;
    }
    .selectBox .optionList {
        position: absolute; 
        border: 1px solid #F19F9D;
        top: 40px;
        left: 0;
        width: 100%;
        color: #1B1C1F;
        list-style-type: none;
        padding: 0px;
        border-radius: 6px;
        overflow: hidden;
        background-color: #F7F7F7;
        /* max-height: 0; */
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;
        font-size: 14px;
        transition: .3s ease-in;
        z-index: 1;
    }

    .selectBox.active .optionList {
        /* max-height: 500px; */
    }

    .selectBox .optionItem {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    text-align: left;
    border-bottom: 1px dashed #F19F9D;
    padding: 5px 15px 5px;
    transition: .1s;
    height: 42px;
    }

    .optionItem:hover {
        background: #F19F9D;
        color: #7E869E;
    }

    .selectBox .optionItem:last-child {
        border-bottom: 0 none;
    }

    .selectBoxOpen, .selectStatusBoxOpen{
        border-color: #F19F9D;
    }
    .openLabel, .openStatusLabel {
        color: #1B1C1F !important;
    }
    

    .selectBox .optionList::-webkit-scrollbar {width: 6px;}
    .selectBox .optionList::-webkit-scrollbar-track {background: transparent; }
    .selectBox .optionList::-webkit-scrollbar-thumb {background: #303030; border-radius: 45px;}
    .selectBox .optionList::-webkit-scrollbar-thumb:hover {background: #303030;}


    #submitBtn{
        position: fixed;
        bottom: 32px; /* 하단 여백 조정 */
        left: 50%; /* 가운데 정렬을 위해 왼쪽 여백을 50%로 설정 */
        transform: translateX(-50%); /* 가운데 정렬 */
        width: 90%; /* 버튼의 너비를 화면 너비의 90%로 설정 */
        max-width: 400px; /* 버튼의 최대 너비 설정 */
        border: 1px solid #F19F9D;
        background-color: #F19F9D;
        color: #FFFFFF;
        height: 52px;
        border-radius: 22.5px;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 14px;
    }

    #submitBtn:disabled {
        /* 유효하지 않은 경우의 스타일 */
        border: 1px solid #A3A3B9;
        background-color: #A3A3B9;
        color: #FFFFFF;
    }
    #emojiContainer{
        width: 100%;
        display: flex;
        flex-direction: row;
        box-sizing: border-box;
        justify-content: space-between; /* 추가된 부분 */
        
    }
    
    .emoji{
        width: 60px;
        height: 60px;
        border: 1px solid #7E869E;
        border-radius: 15px;
        display: flex;  
        justify-content: center;
        align-items: center;
    }

    .selectedEmotion{
        border: 1px solid #F19F9D;
        background-color: #F19F9D;
    }

    </style>
