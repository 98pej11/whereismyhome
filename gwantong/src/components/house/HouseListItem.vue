<template>
  <!-- <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img thumbnail src="https://picsum.photos/250/250/?image=58" alt="Image 1"></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center"> {{ house.lat }} {{ house.lng }} </b-col>
  </b-row> -->
  <b-container class="m-2"
  @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }">
    <b-row>
      <b-col>
          <b-row class="fw-bold">{{ house.apartmentName }}</b-row>
          <b-row class="text-muted">{{ house.sidoName }} {{ house.gugunName}} {{ house.dong }}</b-row>
          <b-row class="text-muted">{{ house.recentPrice }}</b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    idx: Number,
  },

  methods: {
    // ...mapMutations(["SET_HOVER_IDX"]),
    ...mapActions(["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      // if(flag == true){
      //   this.SET_HOVER_IDX(idx);
      // }
      // else{
      //   this.SET_HOVER_IDX(-1);
      // }
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}

.fw-bold{
  color: black;
}
</style>
