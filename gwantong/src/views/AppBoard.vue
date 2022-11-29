<template>
  <b-container class="bv-example-row">
    <b-row class="space">
    </b-row>
    <b-row>
      <h6 class="underline-hotpink" ><b-icon icon="journals"></b-icon> Board </h6>
    </b-row>
    <b-row class="space">
    </b-row>
    <b-row>
      <transition name="moveInUp">
        <router-view id="rv"/>
      </transition>
    </b-row>
    <!-- Footer-->
    <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto"><div class="small m-0 text-white">Copyright &copy; Your Website 2022</div></div>
                    <div class="col-auto">
                        <a class="link-light small" href="#!">Privacy</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Terms</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Contact</a>
                    </div>
                </div>
            </div>
        </footer>
  </b-container>
</template>
<script>
export default {
  name: "AppBoard",
};
</script>
<style scoped>
/* h6{
  text-align: right;
} */
#rv{
  /* background-color: #dfdfdf; */
  background-color: rgba(243, 240, 240, 0.9);
  border-radius: 30px;

}
.rounded-circle {
  border-radius: 50% !important;
}

.space{
  height: 2vw;
}

.bv-example-row{
  width: 70vw;
  height: 100vw;
}
.moveInUp-enter-active{
  opacity: 0;
  transition: opacity 1s ease-in;
}
.moveInUp-enter-active{
  animation: fadeIn 1s ease-in;
}
@keyframes fadeIn{
  0%{
    opacity: 0;
  }
  50%{
    opacity: 0.5;
  }
  100%{
    opacity: 1;
  }
}
@keyframes fadeIn{
  0%{
    opacity: 0;
  }
  50%{
    opacity: 0.5;
  }
  100%{
    opacity: 1;
  }
}
/* #view {
  width: 100vw;
  height: 100vw;
  margin-left: calc(-50vw + 50%);
} */
/* .underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
} */

</style>
