<template>
  <!-- <div class="weather-container">
    <div class="weather-date">
      {{ weatherDate }}
    </div>
    <div class="weather-content">
      <div class="weather-content-row">预 报：{{ weatherKeyPoint }}</div>
      <div class="weather-content-row">云 量：{{ cloudrate }}</div>
      <div class="weather-content-row">湿 度：{{ humidity }}</div>
      <div class="weather-content-row">温 度：{{ temperature }}</div>
      <div class="weather-content-row">能见度：{{ visibility }}</div>
    </div>
  </div> -->
  <div id="he-plugin-standard">
  </div>
</template>
<script>
// import { reactive, toRefs, ref } from 'vue'
// import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
// import axios from '../api/request.js'

import { onMounted } from 'vue'

// export default {
//   components: { ElDialog, ElTabs, ElTabPane },
//   data() {
//     return {}
//   },
//   methods: {},
//   computed: {},
//   props: {},
//   mounted() {},
//   setup(props) {
//     const state = reactive({
//       weatherDate: ref(''),
//       weatherKeyPoint: ref(''),
//       cloudrate: ref(''),
//       humidity: ref(''),
//       temperature: ref(''),
//       visibility: ref('')
//     })

//     let url = '/api/weather?lat=99.78&lon=29.00'
//     const weatherInfo = axios({ url: url, method: 'get' }).then(weatherInfo => {
//       console.log('嘿嘿', weatherInfo)
//       //weatherInfo.data.server_time   //服务器时间
//       //weatherInfo.data.result.forecast_keypoint  预报话
//       let date = new Date()
//       state.weatherDate = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDay() + ' ' + date.getHours() + ':' + date.getMinutes()
//       state.weatherKeyPoint = weatherInfo.data.result.forecast_keypoint
//       state.cloudrate = weatherInfo.data.result.realtime.cloudrate
//       state.humidity = weatherInfo.data.result.realtime.humidity
//       state.temperature = weatherInfo.data.result.realtime.temperature
//       state.visibility = weatherInfo.data.result.realtime.visibility
//       //weatherInfo.data.result.realtime.cloudrate
//       //weatherInfo.data.result.realtime.humidity
//       //weatherInfo.data.result.realtime.temperature
//       //weatherInfo.data.result.realtime.visibility

//       console.log(state.weatherDate)
//       console.log(state.weatherKeyPoint)
//     })
//     return {
//       ...toRefs(state)
//     }
//   }
// }
//https://map.qweather.com/index.html?lat=28.92&lon=99.71&level=8&layer=cloud   乡城县的热力图
export default {
  setup() {
    onMounted(() => {
      window.WIDGET = {
        CONFIG: {
          layout: '2',
          width: 230,
          height: 270,
          background: '1',
          dataColor: 'FFFFFF',
          language: 'zh',
          borderRadius: '10',
          key: '32e91b858a734585a1948daa59381138',
          horizontal: 'left',
          city: '乡城县'
        }
      }
      // let script = document.createElement('script')
      // script.type = 'text/javascript'
      // script.src = 'https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0'
      // document.getElementsByTagName('head')[0].appendChild(script)
      ;(function (d) {
        var c = d.createElement('link')
        c.rel = 'stylesheet'
        c.href = '../../static/css/he-standard.css'
        // c.href = 'https://widget.qweather.net/standard/static/css/he-standard.css?v=1.4.0'
        var s = d.createElement('script')
        s.src = '../../static/js/he-standard.js'
        s.type = 'text/javascript'
        // s.src = 'https://widget.qweather.net/standard/static/js/he-standard.js?v=1.4.0'
        var sn = d.getElementsByTagName('script')[0]
        sn.parentNode.insertBefore(c, sn)
        sn.parentNode.insertBefore(s, sn)

      })(document)
    })
    window.onload = function () {
      // 在这里放置你的代码
      document.getElementById('he-plugin-standard').style.width = '100%'
      document.getElementById('he-plugin-standard').style.heigth = '100%'
      document.getElementById('he-plugin-standard').style.backgroundSize = 'auto'
    }
  }
}
</script>
<!-- <script src="https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0"></script> -->
<style scoped lang="less">
/* .weather-container {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: space-around;
}
.weather-content {
  font-size: 15px;
  color: blue;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: left;
} */
#he-plugin-standard {
  margin: 0;
  padding: 0;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>