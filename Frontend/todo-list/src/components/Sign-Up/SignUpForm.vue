<template>
    <div id="SignUpPage">
        <div id="welcomText">
            <div>Save The Day에 오신 것을<br>환영합니다😊</div>
        </div>
        <div id="signUpForm">
            <div>
                <div class="formTitle">닉네임</div>
                <input type="text" class="formInput" />
            </div>
            <div>
                <div class="formTitle">이메일</div>
                <input type="email" class="formInput"/>
            </div>
            <div>
                <div class="formTitle">비밀번호</div>
                <input type="password" class="formInput"/>
            </div>
            <div>
                <div class="formTitle">비밀번호 확인</div>
                <input type="password" class="formInput"/>
            </div>
        </div>
    </div>
    
</template>
<script>

</script>
<style scoped>
    #SignUpPage{
        background-color: #F7F7F7;
        height: 100vh;
        padding:32px 32px;
    }
    #welcomText{
        margin-top: 60px;
        margin-bottom: 60px;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        font-size: 24px;
        color: #1B1C1F;
    }
    #signUpForm{

    }
    .formTitle{
        color: #F19F9D;
        font-size: 14px;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: 500;
        margin-top: 10px;
        margin-bottom: 5px
    }
    .formInput{
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
</style>