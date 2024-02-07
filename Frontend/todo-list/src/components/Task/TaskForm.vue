<template>
    <div id="form">
        <div>
            <div class="formComponent" id="taskTitle">
                <div class="title">Title</div>
                <div id="titleInputBox">
                    <input class="box" id="titleInput" v-model="formData.title" type="text" @focus="isTitleFocused=true" @blur="isTitleFocused=false">
                    <img id="inputIcon" :src= "require(isTitleFocused ? '../../assets/icon/check_selected.svg':'../../assets/icon/check.svg')">
                </div>
            </div>
            <div class="formComponent" id="date">
                <div class="title">Date</div>
                <button class="box"></button>  
            </div>
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
                <textarea id="contentInput" class="box" type="text" @focus="isContentSelected=true" @blur="isContentSelected=false"></textarea>
            </div>
        </div>
        <button id="submitBtn" :disabled="!isFormValid">저장</button>
    </div>
</template>
<script>
    export default{
        data(){
            return{
                isTitleFocused: false,
                isContentSelected:false,
                importanceOpts:['High', 'Normal', 'Low'],
                isOpen: false,
                selectedOption: null,
                statusOpts: ['Not Started', 'In Progress', 'Not Started'],
                isStatusOpen: false,
                selectedStatusOption: null,
                formData:{
                    title:'',
                    content:'',
                    priority:null,
                    status:null,
                }
            };
        },
        methods: {
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
                this.formData.status = option;
                this.isStatusOpen = false; // 선택한 후 드롭다운을 닫습니다.
            }
        },
        computed:{
            isFormValid(){
                var data= this.formData;
                return data.title!==''
                    && data.priority!==null && data.status!==null;
            }
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

    </style>
