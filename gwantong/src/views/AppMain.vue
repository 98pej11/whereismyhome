<template>
  <b-container class="bv-example-row mt-3 text-center">
    <section class="py-5 intro">
      <div class="container px-5">
        <!-- <h1 class="fw-bolder fs-5 mb-4 twhite">~소개글 부분~</h1> -->
        <div class="row gx-0">
          <div class="col-lg-6 col-xl-5 py-lg-5">
            <div class="p-4 p-md-5">
              <div class="badge bg-primary bg-gradient rounded-pill mb-2">
                Introduce
              </div>
              <div class="h2 fw-bolder">밤하늘의 호옴</div>
              <p></p>
              <div>원하시는 아파트를 못 찾아서 고민하고 계신가요?</div>
              <div>밤하늘의 호옴과 함께 하세요!</div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Blog preview section-->
    <section class="py-5">
      <div class="px-5">
        <h2 class="fw-bolder fs-5 mb-4 twhite">~바로가기~</h2>
        <div class="row gx-5 csize">
          <div class="col-lg-4 mb-5 colflex">
            <b-card :img-src="imgboard" img-height="250">
              <div class="badge bg-primary bg-gradient rounded-pill mb-2">
                Board
              </div>
              <b-table
                id="my-table"
                hover
                :items="articles"
                :fields="fields"
                :per-page="perPage"
                :current-page="currentPage"
                @row-clicked="goBoardView"
              >
                <template #cell(subject)="data">
                  {{ data.item.subject }}
                </template>
              </b-table>

              <template #footer>
                <!-- <small class="text-muted">Footer Text</small> -->
                <b-card-text>
                  <router-link :to="{ name: 'board' }" class="tblack"
                    >게시판 바로가기</router-link
                  >
                </b-card-text>
              </template>
            </b-card>
          </div>
          <div class="col-lg-4 mb-5 colflex">
            <b-card :img-src="imgapart" img-height="250">
              <div class="badge bg-primary bg-gradient rounded-pill mb-2">
                Apart
              </div>
                <b-row v-for="(house, index) in local_houses" :key="index" @click="showModal(house)">
                <house-list-item :house="house" />
              </b-row>
              <template #footer>
                <b-card-text>
                  <router-link :to="{ name: 'house' }" class="tblack"
                    >아파트정보 바로가기</router-link
                  >
                </b-card-text>
              </template>
            </b-card>
          </div>
          <div class="col-lg-4 mb-5 colflex">
            <b-card :img-src="imggraph" img-height="250">
              <div class="badge bg-primary bg-gradient rounded-pill mb-2">
                More
              </div>
              <b-card-text>
                <router-link :to="{ name: 'house' }" class="tblack"
                  >아파트정보 바로가기</router-link
                >
              </b-card-text>
              <template #footer>
                <small class="text-muted">Footer Text</small>
              </template>
            </b-card>
          </div>
          <div class="text-end mb-5 mb-xl-0">
            <a class="text-decoration-none" href="#!">
              More stories
              <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>
    </section>
    <!-- Footer-->
    <footer class="bg-dark py-4 mt-auto">
      <div class="container px-5">
        <div
          class="row align-items-center justify-content-between flex-column flex-sm-row"
        >
          <div class="col-auto">
            <div class="small m-0 text-white">
              Copyright © Your Website 2022
            </div>
          </div>
          <div class="col-auto">
            <a class="link-ligxht small" href="#!">Privacy</a>
            <span class="text-white mx-1">·</span>
            <a class="link-light small" href="#!">Terms</a>
            <span class="text-white mx-1">·</span>
            <a class="link-light small" href="#!">Contact</a>
          </div>
        </div>
      </div>
    </footer>

    <div class="modal" @click="showModal(null)">
      <HouseDetail style="width: 600px;"></HouseDetail>
    </div>
  </b-container>
</template>

<script>
import BoardList from "@/components/board/BoardList.vue";
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem.vue";
import { mapActions, mapMutations } from "vuex";
import HouseListItem from "@/components/house/HouseListItem.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "AppMain",
  data() {
    return {
      imgboard: null,
      imgapart: null,
      imggraph: null,
      perPage: 10,
      currentPage: 1,
      articles: [],
      fields: [
        { key: "file", label: "", tdClass: "tdClass" },
        // { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      local_houses: [],
      isModal: false,
    };
  },

  
  props: {
    msg: String,
  },
  
  created() {
    this.imgapart = require("@/assets/아파트2.png");
    this.imgboard = require("@/assets/게시판.png");
    this.imggraph = require("@/assets/그래프.png");
    http.get(`/board`).then(({ data }) => {
      for (let i = 0; i < Math.min(data.length, 4); i++) {
        this.articles.push(data[i]);
      }
    });

    http.get(`/map/aptlist/sample`).then(({ data }) => {
      this.local_houses = data;
    });
  },
  methods: {
    ...mapMutations(["SET_GLOBAL_ISSHOW", "SET_GLOBAL_ARTICLE"]),
    ...mapActions(["getHouseList"]),
    movePage() {
      this.$router.push({ name: "board" });
    },

    goBoardView(article) {
      this.SET_GLOBAL_ISSHOW();
      window.scrollTo({ top: 0, behavior: "smooth" });
      this.SET_GLOBAL_ARTICLE(article);
      this.$router.push({ name: "board" });
    },

    showModal(house){
      let modal = document.querySelector('.modal');
      if(this.isModal){
        this.isModal = false;
        modal.style.display = 'none';
      }
      else{  
        this.getHouseList(house.dongCode);
        this.isModal = true;
        modal.style.display = 'block';
      }
    },
  },
  components: { BoardList, BoardListItem, HouseListItem, HouseDetail },
};
</script>

<style scoped>
.colflex {
  flex: 1 1 1%;
}
.px-5 {
  margin-left: 10%;
  margin-right: 10%;
}

.twhite {
  color: white;
}
.tblack {
  color: black;
}
.intro {
  color: white;
  background-color: rgba(255, 255, 255, 0);
  background-size: cover;
  overflow: hidden;
  background-image: url("@/assets/밤하늘배경.jpg");
  background-size: no-repeat;
  background-position: center;
  opacity: 0.8;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.modal {
  position: absolute;
  top: 50vh;
  left: calc(-50%-50vw);

  width: 100%;
  height: 100%;

  display: none;

  background-color: rgba(0, 0, 0, 0.4);
}
</style>
