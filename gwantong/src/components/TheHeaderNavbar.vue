<template>
  <div class="ttop">
    <b-navbar toggleable="lg" type="dark">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }">
          <b-img
            :src="require('@/assets/밤하늘의호옴.png')"
            id="lg"
            class="d-inline-block align-top"
            alt="lg"
          ></b-img>
        </router-link>
      </b-navbar-brand>

        <b-navbar-nav>
          <b-nav-item>
            <router-link :to="{ name: 'main' }" class="link">
              메인페이지
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              게시판
            </router-link>
            <router-link :to="{ name: 'house' }" class="m-2 link">
              아파트정보
            </router-link>
          </b-nav-item>
        </b-navbar-nav>



        <b-navbar-nav class="ml-auto marginleft" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <b-avatar variant="primary" v-text="userInfo.userid.charAt(0).toUpperCase()"></b-avatar>
            {{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.
          </b-nav-item>
          <b-nav-item class="align-self-center">
            <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
          </b-nav-item>
          <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto marginleft" v-if="!userInfo">
          <b-nav-item right>
            <router-link :to="{ name: 'login' }" class="link">
              <b-icon icon="person-circle"> </b-icon> Member
            </router-link>
          </b-nav-item>
        </b-navbar-nav>
    </b-navbar>
  </div>
</template>


<script>
import { mapState, mapGetters, mapActions } from "vuex";
import HouseChart from "./house/HouseChart.vue";

const memberStore = "memberStore";

export default {
    name: "TheHeaderNavbar",
    data() {
        return {};
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        ...mapGetters(["checkUserInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),
        // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
        onClickLogout() {
            // this.SET_IS_LOGIN(false);
            // this.SET_USER_INFO(null);
            // sessionStorage.removeItem("access-token");
            // if (this.$route.path != "/") this.$router.push({ name: "main" });
            console.log(this.userInfo.userid);
            //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
            //+ satate에 isLogin, userInfo 정보 변경)
            // this.$store.dispatch("userLogout", this.userInfo.userid);
            this.userLogout(this.userInfo.userid);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/")
                this.$router.push({ name: "main" });
        },
    },
    components: { HouseChart }
};
</script>


<style scoped>
.right{
  float: right;
  margin-left: 200px;
}
#lg {
  width: 100px;
  height: 50px;
  margin-left: 30px;
  border-radius: 30%;
}

.link {
  text-decoration: none;
  font-weight: bold;
  padding: 23px;
}

.navbar.navbar-dark.bg-dark{
    background-color: rgba(0, 0, 0, 0);
 }

 a{
  text-decoration: none;
  color: aliceblue;
 }

 .marginleft{
  margin-left: auto;
 }

 .ttop{
  padding-top: 10px;
 }
</style>