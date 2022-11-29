<template>
  <b-container>
    <article>
      <board-input-item type="register" />
    </article>
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
  props:{
    article: Object,
  },
};
</script>

<style scoped>
.space{
  height: 2vw;
}
</style>
