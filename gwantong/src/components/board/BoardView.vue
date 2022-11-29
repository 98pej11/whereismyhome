<template>
  <b-container class="top">
    <transition name="moveInUp">
      <div>
        <article>
          <header class="mb-4">
            <h5 class="fw-bolder mb-1">{{ article.subject }}</h5>
            <div></div>
            <a
              class="badge bg-secondary text-decoration-none link-light"
              href="#!"
              >{{ article.userid }}
            </a>
            <span>ㅤ</span>
            <a
              class="badge bg-secondary text-decoration-none link-light"
              href="#!"
              >{{ article.registerTime }}
            </a>
          </header>
          <figure class="mb-4">
            <img
              class="img-fluid rounded"
              :src="imgurl"
              alt="..."
              id="article_img"
            />
          </figure>

          <section class="mb-5">
            <h6>
              {{ article.content }}
            </h6>
          </section>

                    <BoardComment
                      :articleno="article.articleno"
                    ></BoardComment>
          <!-- -->
          <!-- 수정또는삭제 -->
          <div></div>
          <b-col class="text-right">
            <b-button
              pill
              variant="outline-secondary"
              size="sm"
              @click="moveModifyArticle"
              class="m-1 butt"
              >correct</b-button
            >
            <b-button
              pill
              variant="outline-secondary"
              size="sm"
              @click="deleteArticle"
              class="m-1 butt"
              >delete</b-button
            >
          </b-col>
        </article>
      </div>
    </transition>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import BoardComment from "./BoardComment.vue";
import { mapState, mapActions } from "vuex";

const commentStore = "commentStore";

export default {
  name: "BoardDetail",
  props: {
    article: Object,
    imgurl: String,
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },

    ...mapState(commentStore, ["comments"]),
  },

  mounted() {    
    this.imgurl = require(`@/assets/221122/${this.imgurl}`);
  },

  updated() {
    this.imgurl = require(`@/assets/221122/${this.imgurl}`);
  },
  methods: {
    ...mapActions(commentStore, ["deleteAllComment"]),

    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.deleteAllComment(this.article2.articleno);
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  components: { BoardComment },
};
</script>

<style scoped>

.top{
  padding: 0px;
  padding-right : calc(var(--bs-gutter-x) * 0.5);
  color: black;
}
</style>
