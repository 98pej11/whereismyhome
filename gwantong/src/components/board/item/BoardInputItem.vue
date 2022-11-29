<template>
  <b-container class="top">
    <div>
      <b-form @submit="onSubmit" @reset="onReset">
      <article>
        <header class="mb-4">
          <b-row class="space"> </b-row>
          <input
            type="text"
            v-model="article.subject"
            placeholder="Type Subject..."
            style="background-color: rgba(0, 0, 0, 0);"
          />
          <div></div>
          <a
            class="badge bg-secondary text-decoration-none link-light"
            href="#!"
            >{{ userInfo.userid }}
          </a>
        </header>
        <figure class="mb-4">
          <img
            class="img-fluid rounded"
            :src="imgurl"
            alt="..."
            id="article_img"
            v-if="imgurl != null"
          />
          <label for="img-input" v-if="imgurl == null">
            <img src="@/assets/pngwing.com.png" style="width: 30px;" />
          </label>

          <input
            id="img-input"
            type="file"
            @change="selectFile"
            accept="image/*"
            style="display: none"
          />
        </figure>

        <section class="mb-5">
          <textarea
            class="form-control"
            rows="10"
            type="text"
            v-model="article.content"
            placeholder="Type Subject..."
            style="background-color: rgba(0, 0, 0, 0.1);"
          >
          </textarea>
        </section>
        <div></div>
        <b-button type="submit" pill variant="outline-secondary" size="sm" class="butt" v-if="this.type === 'register'">글작성</b-button>
        <b-button type="submit" pill variant="outline-secondary" size="sm" class="butt" v-else>글수정</b-button>
      </article>
    </b-form>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import file from "@/api/file";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
      formData: new FormData(),
      filecnt: 0,
      imgurl: null,
    };
  },
  props: {
    type: { type: String },
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        this.article = data;
      });
      this.isUserid = true;
    }
  },

  mounted() {
    this.article.userid = this.userInfo.userid;
  },

  methods: {
    selectFile(e) {
      const fData = new FormData();
      let i = 0;
      for (const file of e.target.files) {
        fData.append("files", file);
        i += 1;
        if(this.imgurl ==null && i==1){
          this.imgurl = URL.createObjectURL(file);
        }
      }
      this.formData = fData;
      this.filecnt = i;
    },

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      this.formData.append(
        "key",
        new Blob([JSON.stringify(this.article)], { type: "application/json" })
      );

      if (this.filecnt != 0) {
        file
          .post("/board/file", this.formData)
          .then(() => {
            console.log("성공");
            this.moveList();
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        http
          .post(`/board`, {
            userid: this.article.userid,
            subject: this.article.subject,
            content: this.article.content,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.moveList();
          });
      }
    },
    modifyArticle() {
      http
        .put(`/board`, {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style scoped>
#wform {
  text-align: left;
  font-size: medium;
  color: rgb(65, 59, 59);
}
.butt {
  float: right;
}

input {
  border: none;
  border-bottom: 1px solid gray;
}
</style>
