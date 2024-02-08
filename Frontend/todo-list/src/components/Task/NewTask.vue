<template>
    <div id="newTask">
        <div>
            <button id="newTaskBtn"  @click="goToNewTaskPage">
                <img src="@/assets/icon/plus_icon.svg" id="btnImage" alt="button image">
                Add New Task
            </button>
        </div>
    </div>
</template>
<script>
export default {
    methods: {
        goToNewTaskPage() {
            this.$router.push('/detail');
        }
    }
};
</script>
<style scoped>
    #newTask{
        background-color: #F7F7F7;
        padding: 24px 34px;
        /* display: flex; */
    }
    #btnImage{
        margin-right: 5px;
    }
    #newTaskBtn{
        border: 1px solid #7E869E;
        width: 100%;
        height: 52px;
        border-radius: 22.5px;
        padding: 12px 17px;
        background-color: transparent;
        display: flex;
        align-items: center; 
        justify-content: flex-start; 
        color: #7E869E;
        font-family: 'Noto Sans', sans-serif;
        font-weight: 500;
        font-size: 14px;
    }
</style>