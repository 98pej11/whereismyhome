<template>
  <b-container>
    <section class="py-5">
       <!-- 리스트-->
        <div class="row gx-5">
          <Transition name="moveInUp">
          <b-col v-if="isShow" style="width: 10%" id="left-list">
              <b-row
                  v-for="(article, index) in articles"
                  :key="index"
                  :index="index" @click="viewArticle(article)">
                  <board-list-item :article="article"></board-list-item>
                  <div>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ</div>
              </b-row>
          </b-col>
        </Transition>
          <!--  -->

          <!-- 게시판 테이블목록(디뽈트) -->
          <Transition name="moveInUp">
          <b-col v-if="!isShow" id="default-list">
                <b-col>
                  <b-row class="mb-1">
                    <b-col>
                      <b-button 
                      pill variant="outline-secondary" @click="moveWrite()" id="butt" size="sm"
                      >
                      new-regist
                    </b-button>
                    </b-col>
                  </b-row>
                  <b-row class="space">
                  </b-row>
                  <b-row>
                    <b-col>
                      <b-table
                        id="my-table"
                        hover
                        :items="articles"
                        :fields="fields"
                        @row-clicked="viewArticle"
                        :per-page="perPage"
                        :current-page="currentPage"
                      >
                        <template #cell(subject)="data">
                          {{ data.item.subject }}
                        </template>
                      </b-table>

                      <b-pagination
                        v-model="currentPage"
                        pills
                        :total-rows="rows"
                        :per-page="perPage"
                        aria-controls="my-table"
                        align="center"
                        size="sm"
                      ></b-pagination>
                    </b-col>
                  </b-row>
                </b-col>
          </b-col>
          </Transition>
          <!--  -->

          <!-- 상세보기 -->
          <Transition name="moveInUp">
          <b-col v-if="isShow" style="width: 90%" id="right-detail">
              <BoardView :article="article" :imgurl="article.fileInfos[0].saveFile"></BoardView>
          </b-col>
        </Transition>
          <!--  -->
        </div>
    </section>
  </b-container>
</template>


<script>
import http from "@/api/http";
import BoardView from "./BoardView.vue";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapState,mapMutations,mapActions } from "vuex";

const commentStore = "commentStore";

export default {
    name: "BoardList",
    data() {
        return {
            isShow: false,
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
            article: null,
        };
    },
    created() {
        http.get(`/board`).then(({ data }) => {
            this.articles = data;
        });

        if(this.global_isShow){
          this.CLEAN_GLOBAL_ISSHOW();
          this.viewArticle(this.global_article);
        }
    },
    computed: {
        rows() {
            return this.articles.length;
        },

        ...mapState(["global_article","global_isShow"]),
    },
    methods: {

        ...mapActions(commentStore, ["getComments"]),
        ...mapMutations(["CLEAN_GLOBAL_ISSHOW"]),
        
        moveWrite() {
            this.$router.push({ name: "boardwrite" });
        },
        viewArticle(article) {
          if(this.article == null || this.article.articleno != article.articleno){

            http.get(`/board/${article.articleno}`)
            .then(({data})=>{
              this.article = data;
            }).then(() =>{
              this.isShow = true;
            if(this.article.fileInfos.length == 0)
              this.article.fileInfos = [{saveFile: "ssafy_logo.png"}];
            this.getComments(this.article.articleno);
            }) ;
          }
          else{
            this.isShow = false;
            this.article = null;            
          }

        },
    },
    components: {
       BoardView,
       BoardListItem,
    },
};
</script>

<style scoped>
#butt{
  float: right;
}
.space{
  height: 2vw;
}
/* .fields{
  font-size: 0.15;
} */
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
.py-5 {
  padding-top: 3rem !important;
  padding-bottom: 3rem !important;
}
.my-5 {
  margin-top: 3rem !important;
  margin-bottom: 3rem !important;
}
.px-5 {
  padding-right: 3rem !important;
  padding-left: 3rem !important;
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
  margin-right: auto;
  margin-left: auto;
}
.row {
  --bs-gutter-x: 1.5rem;
  --bs-gutter-y: 0;
  display: flex;
  flex-wrap: wrap;
  margin-top: calc(-1 * var(--bs-gutter-y));
  margin-right: calc(-0.5 * var(--bs-gutter-x));
  margin-left: calc(-0.5 * var(--bs-gutter-x));
}
.row > * {
  flex-shrink: 0;
  /* width: 100%; */
  /* max-width: 100%; */
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
  background-color: #212529;
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

.container{
  width: 100%;
}

a{
  width: 100%;
}
</style>
