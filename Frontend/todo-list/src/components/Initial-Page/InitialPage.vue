<template>
    <div id="logoPage">
        <div id="logo">
            <img src="@/assets/logo.svg"/>
        </div>
        <div id="buttons">
            <button id="signInBtn" class="btn" @click="goToTaskPage">시작하기</button>
        </div>
        <!-- <div id="buttons">
            <button id="signInBtn" class="btn" @click="goToSignUpPage">로그인</button>
            <button id="signUpBtn" class="btn">회원 가입</button>
        </div> -->
    </div>
</template>
<script>
    export default {
        methods: {
            // goToSignUpPage() {
            //     this.$router.push({
            //     path: '/sign-up',
            //     });        
            // }
            goToTaskPage(){
                this.$router.push({
                    path:'/task'
                });
            }
        }
    };
</script>
<style scoped>
    #logoPage{
        background-color: #F7F7F7;
        height: 100vh;
        padding: 32px 32px;

    }
    #logo{
        margin-top:240px;
        display: flex;
        justify-content: center; 
        align-items: center; 
    }

    #buttons{
        position: fixed;
        width: 100%;
        display: flex;
        justify-content: center;  /* 가로선 상에서 중앙 정렬 */
        flex-direction: column;  /* 요소들을 세로로 배치 */
        gap: 1em;  /* 버튼 간의 간격 설정 */
        box-sizing: border-box;        
        left: 50%; /* 가운데 정렬을 위해 왼쪽 여백을 50%로 설정 */
        transform: translateX(-50%); /* 가운데 정렬 */
        width: 85%; /* 버튼의 너비를 화면 너비의 90%로 설정 */        
        bottom:90px;
    }
    .btn{
        border-radius: 22.5px;
        height: 52px;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 14px;
    }
    #signInBtn{
        border: 1px solid #F19F9D;
        background-color: #F19F9D;
        color: #FFFFFF;
    }
    #signUpBtn{
        border: 1px solid #F19F9D;
        background-color: #F7F7F7;
    }
</style>