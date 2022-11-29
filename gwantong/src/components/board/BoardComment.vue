<template>
  <b-container class="tops">
    <div class="d-flex mb-4" v-for="(c, index) in comments" :key="index">
      <div class="flex-shrink-0">
        <img
          class="rounded-circle"
          src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
          alt="..."
        />
      </div>
      <div class="ms-3">
        <div class="fw-bold">{{ c.userId }}</div>
        {{ c.comment }}
      </div>
      <div v-if="isUpdate && memono === c.memono ">
        <div class="card-body">
          <textarea
            class="form-control"
            rows="3"
            placeholder="Join the discussion and leave a comment!"
            v-model="update_comment"
          ></textarea>
        </div>        
        <b-button @click="uComment(c)">댓글 수정</b-button>
      </div>

      <div style="float: right" v-if=" userInfo != null && userInfo.userid === c.userId">
        <a @click.prevent="dComment(e,c)">삭제 </a>
        <a @click.prevent="showUpdate(e,c)"> 수정</a>
      </div>
    </div>
    <div class="card-body">
      <textarea
        class="form-control"
        rows="3"
        placeholder="Join the discussion and leave a comment!"
        v-model="comment"
      ></textarea>
    </div>

    <b-button @click="wComment" class="write-comment">댓글 작성</b-button>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const commentStore = "commentStore";
const memberStore = "memberStore";

export default {
  name: "BoardDelete",

  data() {
    return {
      update_comment: null,
      comment: null,
      isUpdate: false,
      memono: -1,

    };
  },

  props: {
    articleno: String,
  },
  computed: {
    ...mapState(commentStore, ["comments"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(commentStore, [
      "getComments",
      "writeComment",
      "deleteComment",
      "updateComment",
    ]),

    wComment() {

      if(this.userInfo==null){
        alert("로그인 후 다시 시도해주세요");
        return;
      }

      var params = {
        comment: this.comment,
        userId: this.userInfo.userid,
        articleNo: this.articleno,
      };

      this.writeComment(params);
      this.comment = null;
    },

    dComment(e,c) {
      this.deleteComment(c);
      
    },

    showUpdate(e,c) {
      this.memono = c.memono;
      if (this.isUpdate === true) {
        this.isUpdate = false;
      } else {
        this.isUpdate = true;
        this.update_comment = c.comment;
      }
    },

    uComment(c){
      var params = c;
      params.comment = this.update_comment;
      this.updateComment(params);
      this.isUpdate = false;
      this.update_comment = null;
      this.memono = -1;
    },
  },
};
</script>

<style scoped>
.tops {
  color: black;
  padding: 0;
}

textarea{
  resize: none;
  background-color: rgba(0, 0, 0, 0.1);
}

.write-comment{
  width: 100px;
}
</style>
