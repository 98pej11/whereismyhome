<!-- <template>
  <div id="app">
    <KakaoMap />
  </div>
</template>

<script> -->
<template>
  <div id="app" style="width:100%">
    <the-header-navbar></the-header-navbar>
    <router-view style="background-color:rgba(0, 0, 0, 0)"></router-view>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
// import KakaoMap from "./views/KakaoMap";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
  },
};
</script>

<style>
@font-face {
    font-family: 'myfont';  
    src: url('./assets/OdFlutterGothicM.ttf') format('truetype');
    font-weight: normal;
    font-style: normal;
 }

#app {
  font-family: system, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* background: linear-gradient(to right, #000000 0%, #111111 40%,#222222 50%,#111111 60%, #000000 100%); */
  height: 100vw;  
}

a.router-link-exact-active {
  color: #89bfef;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
