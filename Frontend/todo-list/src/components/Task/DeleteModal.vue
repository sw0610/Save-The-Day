<template>
    <div id="modalView">
        <div id="modal">
            <img id="deleteIcon" src="@/assets/icon/delete_circle.svg">
            <div id="textContainer">
                <div id="bigText">할 일 삭제</div>
                <div id="smallText">정말로 삭제하시겠습니까?<br>
                    삭제 후에는 되돌릴 수 없습니다.
                </div>
            </div>
            <div id="buttons">
                <button class="btn" id="cancleBtn" @click="cancleDelete">취소</button>
                <button class="btn" id="deleteBtn" @click="deleteTask">삭제</button>
            </div>
        </div>
    </div>

</template>
<script>
import http from '@/util/http-common.js';

export default{
    methods:{
        deleteTask(){
                http.delete(`/task/detail/${this.$route.params.taskId}`)
                .then(()=>this.$router.push(`/task`));

        },
        cancleDelete(){
            this.$emit('close');
        }
    }
}
</script>
<style scoped>
#modalView{
    margin: 0;
    padding: 0;
    z-index: 2;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: "Noto Sans", sans-serif;
    user-select: none;
    background-color: rgba(0, 0, 0, 0.5);
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}
#modal{
    height: 288px;
    width: 337px;
    background-color: #FFFFFF;
    border-radius: 22.5px;
    padding: 20px 40px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    flex-direction: column;
}
#deleteIcon{
    margin-top: 5px;
}
#textContainer{
    text-align: center;
}
#bigText{
    margin-top: 14px;
    font-size: 24px;
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 500;
}
#smallText{
    margin-top: 14px;
    font-size: 14px;
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 400;
}

#buttons{
    margin-top: 36px;
    display: flex;
    gap: 10px;
}
.btn{
    width: 122px;
    height: 48px;
    border-radius: 8px;
    border-style: none;
    font-size: 14px;
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 500;
}
#cancleBtn{
 color: #1B1C1F;
}

#deleteBtn{
    background-color: #F95959;
    color: #FFFFFF;
}
</style>