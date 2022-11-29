<template>
  <div>
    <div id="map">
      <b-col
        >>
        <div class="filter-left">
          <b-button v-b-toggle.collapse-1 class="filterbutton"
            >🔍︎ 검색 필터 ▼</b-button
          >
          <b-collapse id="collapse-1" class="mt-2" style="width: 300px">
            <house-search-bar></house-search-bar>
          </b-collapse>
        </div>
      </b-col>
      <b-col>
        <div class="filter-right">
          <HouseDetail></HouseDetail>
        </div>
      </b-col>
    </div>
  </div>
</template>

<script>
// import mapFilters from '@/components/map/mapFilters.vue';
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import HouseDetail from "@/components/house/HouseDetail.vue";
import AppMain from "./AppMain.vue";
export default {
  components: {
    // mapFilters,
    HouseSearchBar,
    HouseList,
    HouseDetail,
    AppMain,
  },
  name: "KakaoMap",

  data() {
    return {
      showfilter: false,
      overlays: [],
      infowindow: null,
      detailShow: false,
      isHovered: false,
      redhouseImg: require("@/assets/빨간색.png"),
      blackhouseImg: require("@/assets/검은색.png"),
      whitehouseImg: require("@/assets/흰색.png"),
    };
  },

  watch: {
    latlng: "displayMarker",
    house: "moveMap",
    hoverIdx: "highlightHome",
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9f834d40afbaa1365ee3dcd44caba575";
      document.head.appendChild(script);
    }
  },

  computed: {
    ...mapState(["latlng", "houses", "house", "hoverIdx"]),
  },

  // watch: {
  //   latlng: this.displayMarker(latlng),
  // },

  methods: {
    ...mapMutations(["SET_DETAIL_HOUSE"]),

    matchDetail(el) {
      alert(JSON.stringify(this.houses[el.targer.id]));
      this.detailHouse(this.houses[el.target.id]);
    },

    handleHover(hovered) {
      this.isHovered = hovered;
    },
    ...mapActions(["getLatLng", "detailHouse"]),
    showFilters() {
      if (this.showfilter == true) {
        this.showfilter = false;
      } else {
        this.showfilter = true;
      }
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 6,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker() {
      let markerPositions = this.latlng;
      if (this.overlays.length > 0) {
        for (let i = 0; i < this.overlays.length; i++) {
          this.overlays[i].setMap(null);
        }
        this.overlays = [];
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      var bounds = new kakao.maps.LatLngBounds();
      if (positions.length > 0) {
        for (let i = 0; i < positions.length; i++) {

          let redhouse = document.createElement("img");
          let blackhouse = document.createElement("img");
          let whitehouse = document.createElement("img");
          let label = document.createElement("label");
          let content = document.createElement("div");
          let sPrice = this.houses[i].recentPrice.replace(",", "") * 1;
          let price = sPrice * 1;
          let value = price / 100000;
          redhouse.src = `${this.redhouseImg}`;
          redhouse.style.width = "80px";
          redhouse.style.position = "absolute";
          redhouse.style.transform = "translate(-40px, -40px)";
          redhouse.style.zIndex = 100;
          redhouse.id = `${i}`;

          blackhouse.src = `${this.blackhouseImg}`;
          blackhouse.style.width = "80px";
          blackhouse.style.position = "absolute";
          blackhouse.style.transform = "translate(-40px, -40px)";
          blackhouse.style.zIndex = 200;

          console.log(value);
          if (value < 1) {
            redhouse.style.opacity = 0.3;
            blackhouse.style.opacity = Math.max(0, Math.min(0.6, value));
          } else if (value < 2) {
            redhouse.style.opacity = Math.min(
              1,
              Math.max(0.3, ((value * 10) % 10) / 10)
            );
            blackhouse.style.opacity = 0.4;
          } else {
            redhouse.style.opacity = 1;
            blackhouse.style.opacity = 0.2;
          }

          whitehouse.src = `${this.whitehouseImg}`;
          whitehouse.style.width = "80px";
          whitehouse.style.position = "absolute";
          whitehouse.style.transform = "translate(-40px, -40px)";

          sPrice = parseInt(sPrice / 1000).toString();
          let fPrice = `${sPrice.substr(0, sPrice.length - 1)}.${sPrice.substr(
            sPrice.length - 1,
            1
          )}억원`;
          label.textContent = `${fPrice}`;
          label.style.color = "white";
          label.style.position = "absolute";
          label.style.zIndex = 300;
          label.style.top = "50%";
          label.style.left = "50%";
          label.style.transform = "translate(-50%, -50%)";
          label.style.fontWeight = "bold";
          label.style.fontSize = "13px";

          content.appendChild(whitehouse);
          content.appendChild(redhouse);
          content.appendChild(blackhouse);
          content.appendChild(label);
          blackhouse.addEventListener('click', () => {
            this.SET_DETAIL_HOUSE(this.houses[i]);
          });

          var overlay = new kakao.maps.CustomOverlay({
            position: positions[i],
            content: content,
          });

          overlay.setMap(this.map);
          this.overlays.push(overlay);

          bounds.extend(positions[i]);
        }
      }
      this.map.setBounds(bounds);
      this.map.setLevel(this.map.getLevel() + 1);
    },

    moveMap() {
      const location = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      console.log(JSON.stringify(location));
      this.map.setCenter(location);
      this.map.setLevel(5);
    },
    highlightHome() {
      let element = document.getElementById(`${hoverIdx}`);
      element.className = "hovered";
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 90vh;
  /* margin-left: calc(-50vw + 50%, 10%); */
  overflow: hidden;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
  background-color: rgba(141, 138, 138, 0.9);
  border-radius: 30px;
}

.filter-left {
  position: relative;
  left: 50px;
  top: 50px;
  padding: 5px;
  width: 300px;
  z-index: 100;
}

.filter-right {
  position: absolute;
  left: 60vw;
  top: 5px;
  padding: 5px;
  z-index: 100;
}

.overay {
  background-color: white;
  color: rgb(11, 11, 11);
}
.filterbutton {
  margin: 0;
  /* background-color: (243, 240, 240, 0.9); */
}
</style>
