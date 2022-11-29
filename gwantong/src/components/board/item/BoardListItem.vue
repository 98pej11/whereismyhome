<template>
  <b-container class="top">
    <b-row>
      <b-col md="auto">
        <img
          class="img-fluid"
          :src="imgs"
          alt="..."
        />
      </b-col>
      <b-col>
          <b-row class="fw-bold">{{ article.subject }}</b-row>
          <b-row class="text-muted">{{ article.userid }}</b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    article: Object,
    index: Number,
  },

  data() {
    return {
      imgs:null,
    }
  },

  mounted() {

    if(this.article.fileInfos.length != 0)
      this.imgs = require(`@/assets/221122/${this.article.fileInfos[0].saveFile}`);
    else      
      this.imgs = require(`@/assets/221122/ssafy_logo.png`);

  },


};
</script>

<style scoped>

col{
  width: auto;
}
.img-fluid {
  max-width: 80px;
  width: auto;
  height: auto;
  margin-left: 100px;
}
.ms-3 {
  margin-left: 1rem !important;
}
.fw-bold {
  margin-top: 20px;
  font-weight: 700 !important;
  color: black;
}
.text-muted {
  --bs-text-opacity: 1;
  color: black !important;
}

.top{
  width: auto;
  height: auto;
}
</style>
