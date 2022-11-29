<template>
  <div class="about">
    <Bar
      :chart-data="chartDataSet"
    />
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import { mapState,mapMutations } from "vuex";


ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "HouseChart",
  components: { Bar },

  computed:{
    ...mapState(["chartDataSet","houses"]),
  },

  watch:{
    chartDataSet:"setBackgroundColor",
    houses: "setData",
  },

  created(){
    this.setData();
    this.setBackgroundColor();
  },

  updated(){    
    this.setData();
    this.setBackgroundColor();
  },
  data() {
    return {
      chartOptions: {
        responsive: true,
      },
    };
  },

  methods:{
    
    ...mapMutations(["SET_COLOR","SET_DATA","SET_LABEL"]),

    setData(){
      let data = [0,0,0,0,0,0,0,0,0,0,0];
      let labels = [0,0,0,0,0,0,0,0,0,0];
      let maxs = 0;
      let mins = 10000000;
      for (const h of this.houses) {
        let tmpValue = parseInt(h.recentPrice.replace(",",""));
        maxs = Math.max(maxs,tmpValue);
        mins = Math.min(mins,tmpValue);
      }
      for (const h of this.houses) {
        let tmpValue = parseInt(h.recentPrice.replace(",",""));
        let idx = parseInt(((tmpValue-mins)/(maxs-mins))*10);
        data[idx] += 1;
      }

      for (let i = 0; i < 10; i++) {
        console.log((((i*(maxs-mins)))+mins));
        let left = parseInt((((i*(maxs-mins)))+mins)/10000).toString();
        console.log(left);
        let right = parseInt(((((i+1)*(maxs-mins))+mins))/10000).toString();
        console.log(right);
        let lLabel = `${left.substr(0, left.length - 1)}.${left.substr(
            left.length - 1,
            1
          )}`;
        let rLabel = `${right.substr(0, right.length - 1)}.${right.substr(
          right.length - 1,
            1
          )}`;
        labels[i] = `${lLabel} ~ ${rLabel} 억원`;
      }

      this.SET_LABEL(labels);
      this.SET_DATA(data);
    },   

    setBackgroundColor(){
      let data = this.chartDataSet.datasets[0].data
      console.log(JSON.stringify(data));
      let color = []
      let maxs = 0;
      let mins = 1000000;
      for (const d of data) {
        maxs = Math.max(maxs,d);
        mins = Math.min(mins,d);
      }
      
      for (const d of data) {
        let v = Math.max(30,parseInt(((d-mins)/(maxs-mins))*255));
        v = v.toString(16);
        console.log(v);
        color.push(`#ff0000${v}`);
      }

      this.SET_COLOR(color);
    },
  }
};
</script>
