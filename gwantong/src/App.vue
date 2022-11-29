<template>
  <div id="app">
    <b-row>
      <the-header-navbar class="padding-0"></the-header-navbar>
    </b-row>
    <b-row>
      <transition name="moveInUp">
        <router-view class="padding-0" />
      </transition>
    </b-row>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
  },
};
</script>

<style>
@font-face {
  font-family: "mfont";
  src: url("@/assets/NanumSquareNeo-bRg.ttf");
  /* src: url('C:\Users\multicampus\Downloads\gwantong07-15-master\src\assets\강원교육모두.ttf'); */

  font-weight: normal;
  font-style: normal;
}
.moveInUp-enter-active {
  opacity: 0;
  transition: opacity 1s ease-in;
}
.moveInUp-enter-active {
  animation: fadeIn 1s ease-in;
}
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}
/* .App {
  text-align: center;
  font-size: 50px;
  font-family: mfont;
  overflow: auto;
} */
#app {
  font-family: mfont;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  /* background: linear-gradient(to right, #000000 0%, #2c3e50 50%, #000000 100%); */
  color: white;
  background-color: #212529;
  width: auto;
  height: auto;
}
a.router-link-exact-active {
  color: #fff2a6;
}
a.router-link-exact-active:hover {
  text-decoration: none;
}

.butt {
  float: right;
}
.space {
  height: 2vw;
}
.py-5 {
  padding-top: 3rem !important;
  padding-bottom: 3rem !important;
}
.my-5 {
  margin-top: 3rem !important;
  margin-bottom: 3rem !important;
}

.container,
.container-fluid,
.container-xxl,
.container-xl,
.container-lg,
.container-md,
.container-sm {
  width: 100%;
  padding-right: var(--bs-gutter-x, 0.75rem);
  padding-left: var(--bs-gutter-x, 0.75rem);
  margin-right: 0;
  margin-left: 0;
}

.row {
  --bs-gutter-x: 1.5rem;
  --bs-gutter-y: 0;
  display: flex;
  flex-wrap: wrap;
  margin-top: calc(-1 * var(--bs-gutter-y));
  margin-right: calc(-0.5 * var(--bs-gutter-x));
  margin-left: calc(-0.5 * var(--bs-gutter-x));
  margin-right: 0;
  margin-left: 0;
}
.row > * {
  flex-shrink: 0;
  width: 100%;
  max-width: 100%;
  padding-right: calc(var(--bs-gutter-x) * 0.5);
  padding-left: calc(var(--bs-gutter-x) * 0.5);
  margin-top: var(--bs-gutter-y);
}

.col {
  flex: 1 0 0%;
}

.row-cols-auto > * {
  flex: 0 0 auto;
  width: auto;
}

.gx-5 {
  --bs-gutter-x: 3rem;
}
.col-lg-3 {
  flex: 0 0 auto;
  width: 25%;
}
.d-flex {
  display: flex !important;
}
.align-items-center {
  align-items: center !important;
}
.mt-lg-5 {
  margin-top: 3rem !important;
}
.mb-4 {
  margin-bottom: 1.5rem !important;
  color: #212529;
}
.img-fluid {
  max-width: 100%;
  height: auto;
}
.rounded-circle {
  border-radius: 50% !important;
}
.ms-3 {
  margin-left: 1rem !important;
}
.fw-bold {
  font-weight: 700 !important;
}
.text-muted {
  text-align: right;
  --bs-text-opacity: 1;
  color: black !important;
}
.col-lg-9 {
  flex: 0 0 auto;
  width: 75%;
  color: #212529;
}
.fw-bolder {
  font-weight: bolder !important;
}
.mb-1 {
  margin-bottom: 0.25rem !important;
}
.text-muted {
  --bs-text-opacity: 1;
  color: black !important;
}
.fst-italic {
  font-style: italic !important;
}
.mb-2 {
  font-size: 14px;
  margin-bottom: 0.5rem !important;
}
.badge {
  display: inline-block;
  padding: 0.35em 0.65em;
  font-size: 0.75em;
  font-weight: 700;
  line-height: 1;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: 0.25rem;
}
.bg-secondary {
  --bs-bg-opacity: 1;
  background-color: rgba(
    var(--bs-secondary-rgb),
    var(--bs-bg-opacity)
  ) !important;
}
.text-decoration-none {
  text-decoration: none !important;
}
.link-light {
  color: #f8f9fa;
}
.mb-5 {
  margin-bottom: 3rem !important;
}
.fs-5 {
  font-size: 1.25rem !important;
}
.card {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: 1px solid rgba(0, 0, 0, 0.125);
  border-radius: 0.25rem;
}
.bg-light {
  --bs-bg-opacity: 1;
  background-color: rgba(var(--bs-light-rgb), var(--bs-bg-opacity)) !important;
}
.card-body {
  flex: 1 1 auto;
  padding: 1rem 1rem;
}
.form-control {
  display: block;
  width: 100%;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.flex-shrink-0 {
  flex-shrink: 0 !important;
}
.mt-4 {
  margin-top: 1.5rem !important;
}
/* fade효과 */
.moveInUp-enter-active {
  opacity: 0;
  transition: opacity 1s ease-in;
}
.moveInUp-enter-active {
  animation: fadeIn 1s ease-in;
}
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}
</style>

<style scoped>
.padding-0{
  /* padding: 0px;
  width: 100vw; */
  overflow: hidden;
}
</style>