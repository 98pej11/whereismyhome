<template>
  <b-container v-if="house">
    <div class="card h-100 shadow border-0 top">
      <b-row>
        <b-col
          ><b-alert show class="title"
            >{{ house.apartmentName }}
          </b-alert></b-col
        >
      </b-row>
      <b-row class="mb-2 mt-1">
        <b-col>
          <HouseChart style="width: 600px"></HouseChart>
        </b-col>
      </b-row>
      <b-row>
        <b-col sm="4">
          <p>일련 번호 :</p>
          <p>아파트 이름 :</p>
          <p>법정동 :</p>
          <p>층수 :</p>
          <p>거래 금액 :</p>
        </b-col>
        <b-col sm="8">
          <p show class="val">{{ house.aptCode }}</p>
          <p show class="val">{{ house.apartmentName }}</p>
          <p show class="val">{{ house.dong }}</p>
          <p show class="val">{{ house.floor }}층</p>
          <p show class="val">
            {{
              (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
            }}원
          </p>
        </b-col>
        <b-col></b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import HouseChart from "./HouseChart.vue";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  components: { HouseChart },
};
</script>

<style scoped>
.title {
  color: rgb(54, 54, 54);
  background-color: rgba(243, 240, 240, 0.9);
  border-color: rgba(243, 240, 240, 0.9);
  font-weight: bold;
  margin-bottom: 0;
}
.val {
  text-align: left;
}
p {
  color: black;
  text-align: center;
  margin-left: 12px;
  font-size: 15px;
  margin-top: 13px;
  margin-bottom: 0;
}
/* .list{
  background-color:rgba(192, 191, 189,0.9);
  border-color: bisque;
  text-align: center;
  width: 200px;
  height: 40px;
  float: right;
} */
.top {
  background-color: rgba(243, 240, 240, 0.9);
  /* width: 600px; */
}

.top > * {
  background-color: rgba(0, 0, 0, 0);
}
</style>
