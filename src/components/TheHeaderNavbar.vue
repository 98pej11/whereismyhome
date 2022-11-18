<template>
  <div>
    <b-navbar toggleable="lg" type="dark">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }">
          <b-img
            :src="require('@/assets/ssafy_logo.png')"
            id="logo"
            class="d-inline-block align-top"
            alt="logo"
          ></b-img>
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link :to="{ name: 'main' }" class="link">
              <b-icon icon="house-door" animation="fade" font-scale="2"></b-icon>
              Main
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              <b-icon icon="journal" animation="fade" font-scale="2"></b-icon>
              Announcement
            </router-link>
            <router-link :to="{ name: 'house' }" class="m-2 link">
              <b-icon icon="house-fill" animation="fade" font-scale="2"></b-icon>
              Apart Info
            </router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'join' }" class="link">
                <b-icon icon="person-circle"></b-icon> 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'login' }" class="link"> <b-icon icon="key"></b-icon> 로그인 </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}

/* .navbar.navbar-dark.bg-dark{
    background-color: #BDD0E7!important;
 } */
 /* .navbar.navbar-dark.type-dark{
    background-color: #BDD0E7!important;
 } */
</style>
