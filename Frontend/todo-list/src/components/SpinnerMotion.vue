<template>
<div class="main">
  <div class="droplet_spinner">
    <div class="droplet"></div>
    <div class="droplet"></div>
    <div class="droplet"></div>
  </div>
</div>
</template>
<style>
.main{
  width: 90vw;
  margin: 0 auto;
  text-align: center;
}

.droplet_spinner {
  display: flex;
  justify-content: center;
  margin: 30px;
}

.droplet_spinner .droplet {
  width: 12px;
  height: 12px;
  margin: 0 5px;
  
  background-color: #F1867E;
  border-radius: 50%;
  transform-origin: center bottom;
  
  animation: bounce 1.2s cubic-bezier(0.3, 0.01, 0.4, 1) infinite;
}

.droplet_spinner .droplet:nth-child(1) {
  animation-delay: -0.4s;
}

.droplet_spinner .droplet:nth-child(2) {
  animation-delay: -0.2s;
}

.droplet_spinner .droplet:nth-child(3) {
  animation-delay: 0s;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-20px);
  }
}
</style>