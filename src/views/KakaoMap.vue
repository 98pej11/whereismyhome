<template>
  <div>
    <div id="map">
        <div class="filter-left">
            <button @click="showFilters">하이</button>
            <Transition>
            <div class="inner-filter" v-if="showfilter">
              <!-- <map-filters></map-filters> -->
              <house-search-bar></house-search-bar>
              <button @click="displayMarker">marker set 1</button>
            </div>
            </Transition>
        </div>
        <div class="filter-right" v-if="detailShow">
            <!-- <house-list></house-list> -->
            <HouseDetail></HouseDetail>
        </div>
    </div>
  </div>
</template>

<script>
// import mapFilters from '@/components/map/mapFilters.vue';
import HouseSearchBar from '@/components/house/HouseSearchBar.vue';
import HouseList from '@/components/house/HouseList.vue';
import { mapState,mapActions } from "vuex";
import HouseDetail from '@/components/house/HouseDetail.vue';
export default {
  components: {
    // mapFilters,
    HouseSearchBar,
    HouseList,
    HouseDetail
},
  name: "KakaoMap",

  data() {
    return {
    showfilter:false,
      overlays: [],
      infowindow: null,
      detailShow: false,
    };
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
    ...mapState(["latlng","houses"]),
  },

  // watch: {
  //   latlng: this.displayMarker(latlng),
  // },

  methods: {
    
    ...mapActions(["getLatLng","detailHouse"]),
    showFilters(){
        if (this.showfilter == true){
            this.showfilter = false;
        }
        else{
            this.showfilter = true;
        }
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker() {
      this.getLatLng();
      alert("display");
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

          var content = document.createElement("div");
          content.style="background-color: white;";
          var info = document.createElement("span");
          info.id = i;
          info.appendChild(document.createTextNode(this.houses[i].recentPrice));
          content.appendChild(info);
          info.addEventListener("click",(e) =>{
            console.log(e.target);
            console.log(e.target.id);
            this.detailHouse(this.houses[e.target.id]);
            this.detailShow = true;
          });
          

          var overlay = new kakao.maps.CustomOverlay({
          position: positions[i],
          content: content
          });
          overlay.setMap(this.map);
          this.overlays.push(overlay);

          bounds.extend(positions[i]);
           } 


      }
      this.map.setBounds(bounds);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 95vw;
  height: 70vw;
  margin-left: calc(-48vw + 50%);
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

.filter-left{
    position:absolute;
    left:100px;
    top:100px;
  padding:5px;
  width: 200px;
  z-index:10;
}

.filter-right{    
    position:absolute;
    left : 80vw;
    top : 0px;
  padding:5px;
  z-index:10;
  height: 100vw;
}


</style>
