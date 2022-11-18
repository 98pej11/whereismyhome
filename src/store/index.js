import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
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
      return state.latlan;
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
      // console.log("Mutations", house);
      state.house = house;
    },

    GET_LAT_LNG(state){ 

      state.latlng = [];
      state.houses.forEach(function(item){
        state.latlng.push([item.lat,item.lng]);
      })

      },

    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    CREATE_TODO(state, todoItem) {
      // console.log('mutation CREATE_TODO Call');
      // console.log(state);
      // console.log(todoItem);
      state.todos.push(todoItem);
    },
    DELETE_TODO(state, todoItem) {
      const index = state.todos.indexOf(todoItem);
      state.todos.splice(index, 1);
    },
    UPDATE_TODO_STATUS(state, todoItem) {
      // console.log(state);
      // console.log(todoItem);
      state.todos = state.todos.map((todo) => {
        if (todo === todoItem) {
          return {
            // title: todoItem.title,
            ...todo,
            completed: !todoItem.completed,
          };
        }
        return todo;
      });
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
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
          // alert(data);
          console.log(commit, data);
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    // createTodo(context, todoItem) {
    //   console.log(context);
    //   console.log('actions createTodo Call');
    //   context.commit('CREATE_TODO', todoItem);
    // },
    // destructuring 활용
    createTodo({ commit }, todoItem) {
      commit("CREATE_TODO", todoItem);
    },
    deleteTodo({ commit }, todoItem) {
      // console.log(context);
      // console.log(todoItem);
      commit("DELETE_TODO", todoItem);
    },
    updateTodoStatus({ commit }, todoItem) {
      commit("UPDATE_TODO_STATUS", todoItem);
    },
    getLatLng({commit}){
      commit("GET_LAT_LNG");
    }
    //////////////////////////// Todo List end //////////////////////////////////
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
