<template>
  <div class="card h-100 shadow border-0 top">
    <div class="top">
      <b-col class="text-center second">
        <b-row class="sm-3">
            <p>시 / 도</p>
            <b-form-select
              v-model="sidoCode"
              :options="sidos"
              @change="gugunList"
            >
        </b-form-select>
      </b-row>
        <b-row class="sm-3">
          <p>구 / 군</p>
            <b-form-select
              v-model="gugunCode"
              :options="guguns"
            >
          </b-form-select>
        </b-row>
        <b-row class="sm-3">
          <p>매매 가격</p>
          <b-input
            v-model="priceStart"
            placeholder="최소가격을 입력하세요 ."
          ></b-input>
        </b-row>
        <b-row class="sm-3">
          <b-input
            v-model="priceEnd"
            placeholder="최대가격을 입력하세요 ."
          ></b-input>
        </b-row>
        <b-row class="sm-3">
          <p>매매 년월</p>
          <b-input
            v-model="dealYear"
            placeholder="매매년도을 입력하세요 ."
          ></b-input>
        </b-row>
        <b-row class="sm-3">
          <b-input
            v-model="dealMonth"
            placeholder="매매월을 입력하세요 ."
          ></b-input>
        </b-row>
        <b-row class="sm-3 searchbtn">
          <b-button @click="this.searchApt">검색</b-button>
        </b-row>
      </b-col>
    </div>
    <div class="card-body p-4 child">
      <HouseList></HouseList>
    </div>
  </div>
</template>

<script>
import HouseList from "@/components/house/HouseList.vue";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      priceStart: 10000,
      priceEnd: 100000000,
      dealMonth: 3,
      dealYear: 2019,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {

    ...mapActions([
      "getSido",
      "getGugun",
      "getHouseList2",
    ]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_APT_LIST"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {



      console.log(this.gugunCode);
      var params = {
        dongCode: this.gugunCode,
        priceStart: this.priceStart,
        priceEnd: this.priceEnd,
        dealYear: this.dealYear,
        dealMonth: this.dealMonth,
      };
      this.getHouseList2(params);
    },
  },
  components: {
    HouseList,
    // HouseChart
},
};
</script>

<style scoped>
p{
  color: black;
  text-align: left;
  margin-left: 12px;
  font-size: 15px;
  margin-top: 13px;
  margin-bottom: 0;
}
.top {
  background-color: rgba(243, 240, 240, 0.9);
  /* width: 600px; */
}

.top > * {
  background-color: rgba(0, 0, 0, 0);
}

.second {
  margin: 0px;
}
.child{
  width: inherit;
}

input{
  margin-top: 5px;
  width: 90%;
  margin-left: 15px;
}

button {
  margin: 0 3px;
  margin-top: 10px;
  background-color: rgba(139, 137, 137, 0.9);
  width:60px;
  height:35px;
}
.searchbtn{
  margin-left: 110px;
  margin-top: 10px;
}
select{
  width: 285px;
  height: 38px;
  margin-left: 20px;
  margin-top: 3px;
  padding: 0;
}

</style>