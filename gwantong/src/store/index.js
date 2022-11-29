import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import createPersistedState from "vuex-persistedstate";
import memberStore from "@/store/modules/memberStore";
import commentStore from "@/store/modules/commentStore";

Vue.use(Vuex);

export default new Vuex.Store({

  modules:{
    memberStore,
    commentStore,
  },

  
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    todos: [
      // { title: '할 일1', completed: false },
      // { title: '할 일2', completed: false },
    ],
    latlng :[],
    comments: [],
    hoverIdx: -1,
    chartDataSet: {
      labels: [],
      datasets: [{ 
        data: [],
        backgroundColor: [],
        label: "매매 가격",
       }],
    },
    global_isShow: false,
    global_article:{},
  },
  getters: {
    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },

    allLatLng(state){
      return state.latlng;
    },
  },
  mutations: {
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      // alert("hi")
      state.house = house;
    },

    GET_LAT_LNG(state){ 

      state.latlng = [];
      state.houses.forEach(function(item){
        state.latlng.push([item.lat,item.lng]);
      })
    },


    SET_COLOR(state, param){
      state.chartDataSet.datasets[0].backgroundColor = param;
    },

    SET_DATA(state, param){
      state.chartDataSet.datasets[0].data = param;
    },

    SET_LABEL(state, param){
      state.chartDataSet.labels = param;
    },

    SET_GLOBAL_ISSHOW(state){
      state.global_isShow = true;
    },

    CLEAN_GLOBAL_ISSHOW(state){
      state.global_isShow=false;
    },

    SET_GLOBAL_ARTICLE(state, param){
      state.global_article = param;
    },
    
    GET_GLOBAL_ARTICLE(state, param){
      state.global_article = {};
    },

  },

    /////////////////////////////// House end /////////////////////////////////////
  actions: {
    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getHouseList({ commit }, gugunCode) {

      // const params = {
      //   dongcode: gugunCode,
      //   year_month: "202207",
      // };

      const params = { dongcode : gugunCode};

      http
        .get(`/map/aptlist/${gugunCode}`)
        .then(({ data }) => {
          console.log(commit, data);
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      commit("SET_DETAIL_HOUSE", house);
    },
    getLatLng({commit}){
      commit("GET_LAT_LNG");
    },
    
    getHouseList2({commit}, params){
      http
      .post(`/map/aptlist`,params)
      .then(({ data }) => {
        console.log(commit, data);
        commit("SET_HOUSE_LIST", data);
        commit("GET_LAT_LNG");
      })
      .catch((error) => {
        console.log(error);
      });
    }
    /////////////////////////////// House end /////////////////////////////////////
  },
});
