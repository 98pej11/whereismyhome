<template>
  <b-container class="bv-example-row mt-3 text-center">
    <transition name="fade">
      <router-view></router-view>
    </transition>
  </b-container>
</template>
<script>
export default {
  name: "AppHouse",
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
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
