<template>
  <div class="navbar">
    <div class="navbar-left">
      <img src="/images/logo.png" alt="Avatar">
      <h1 class="logo-title">乡城县水利数字沙盘</h1>
    </div>
    <ul class="river-system">
      <li class="water-system-button" @click="layerDataLocation('水系', 27, 12)">许曲</li>
      <li class="water-system-button" @click="layerDataLocation('水系', 29, 12)">定曲</li>
      <li class="water-system-button" @click="layerDataLocation('水系', 28, 12)">玛曲</li>
    </ul>
    <div class="navbar-right">
      <img src="/images/avatar.jpg" alt="Avatar">
      <p>格桑梅朵</p>
    </div>
  </div>
  <div class="searchTab">
    <div class="right-top-close" @click="searchTabClose"><el-icon>
        <CloseBold />
      </el-icon></div>
    <SearchTab></SearchTab>
  </div>
  <div class="detailTab" @mousedown="moveBox">
    <div class="right-top-close" @click="detailTabClose">
      <el-icon>
        <CloseBold />
      </el-icon>
    </div>
    <DetailTab :attributes="attributes" :name="name"></DetailTab>

  </div>
  <div class="weatherTab">
    <div class="right-top-close" @click="weatherTabClose"><el-icon><CloseBold /></el-icon></div>
    <WeatherTab></WeatherTab>
    <div class="professional-data">
      <a target="_blank" href="https://map.qweather.com/index.html?lat=28.92&lon=99.71&level=8&layer=cloud">专业数据</a>
    </div>
  </div>
  <div ref="mapViewNode" style="height: calc(100vh - 40px);">
  </div>
  <div id="topbar">
    <button class="action-button esri-icon-measure-line" id="distanceButton" type="button" title="测量两点的距离"></button>
    <button class="action-button esri-icon-measure-area" id="areaButton" type="button" title="测量面积"></button>
    <button class="action-button esri-icon-printer" id="screenShotButton" type="button" title="下载图像"></button>
  </div>
  <div class="nine-function-div" id="river"><el-button type="primary" :icon="Edit" title="水系" @click="layerDisplayController('水系')" /></div>
  <div class="nine-function-div" id="safety"><el-button type="primary" :icon="Lock" title="水安全" @click="layerDisplayController('水安全')" /></div>
  <div class="nine-function-div" id="resource"><el-button type="primary" :icon="PieChart" title="水资源" @click="layerDisplayController('水资源')" /></div>
  <div class="nine-function-div" id="project"><el-button type="primary" :icon="Flag" title="水利工程" @click="layerDisplayController('水利工程')" /></div>
  <div class="nine-function-div" id="shoreline"><el-button type="primary" :icon="SortDown" title="岸线规划" @click="layerDisplayController('岸线规划功能分区')" /></div>
  <div class="nine-function-div" id="boundary"><el-button type="primary" :icon="SortUp" title="河湖划界" @click="layerDisplayController('河湖划界')" /></div>
  <div class="nine-function-div" id="health"><el-button type="primary" :icon="Umbrella" title="健康评价" @click="layerDisplayController('健康评价')" /></div>
  <div class="nine-function-div" id="disaster"><el-button type="primary" :icon="WarnTriangleFilled" title="灾害点" @click="layerDisplayController('灾害点')" /></div>
  <div class="nine-function-div" id="relocation"><el-button type="primary" :icon="UserFilled" title="移民搬迁" @click="layerDisplayController('移民搬迁')" /></div>
  <div class="nine-function-div" id="waternet"><el-button type="primary" :icon="Grid" title="水网规划" @click="layerDisplayController('水网规划')" /></div>

  <div class="else-function-div" id="searchPane"><el-button type="primary" :icon="TrendCharts" title="搜索数据" @click="searchPaneDisplay()" /></div>
  <div class="else-function-div" id="weatherPane"><el-button type="primary" :icon="PartlyCloudy" title="气象预警" @click="weatherPaneDisplay()" /></div>
  <div class="else-function-div" id="home"><el-button type="primary" :icon="House" title="正视角" @click="goHomeView(true)" /></div>

  <div id="sketchPanel" class="esri-widget">
    <div id="startbuttons">
      <button id="point" data-type="point" class="esri-button starttool">坐标标注</button>
      <button id="line" data-type="polyline" class="esri-button starttool">路线绘制</button>
      <button id="extrudedPolygon" data-type="polygon" class="esri-button starttool">建筑描绘</button>
      <button id="canoe" class="esri-button">模型加载</button>
    </div>
    <div id="actionbuttons">
      <button id="cancel" class="esri-button">取消</button>
      <button id="done" class="esri-button">完成</button>
    </div>
    <div id="edgeoperationbuttons">
      <br />选择边缘操作:
      <div class="update-options" id="edge">
        <button class="esri-widget--button edge-button" id="none-edge-button" value="none">无</button>
        <button class="esri-widget--button edge-button edge-button-selected" id="split-edge-button" value="split">分割</button>
        <button class="esri-widget--button edge-button" id="offset-edge-button" value="offset">位移</button>
      </div>
      选择移动操作:
      <div class="update-options" id="shape">
        <button class="esri-widget--button shape-button" id="none-shape-button" value="none">无</button>
        <button class="esri-widget--button shape-button shape-button-selected" id="move-shape-button" value="move">移动</button>
      </div>
    </div>
  </div>
  <div id="configurationDiv" class="esri-widget">
    <table id="configurationTable">
      <tbody>
        <tr>
          <td>
            <label><b>提示/标签 </b></label>
          </td>
        </tr>
        <tr>
          <td>
            <label for="tooltipOptionscheckbox" id="tooltipOptionscheckboxlabel">- 提示</label>
          </td>
          <td><input type="checkbox" id="tooltipOptionsheckbox" /></td>
        </tr>
        <tr>
          <td>
            <label for="labelOptionscheckbox" id="labelOptionscheckboxlabel">- 标签</label>
          </td>
          <td><input type="checkbox" id="labelOptionscheckbox" /></td>
        </tr>
        <tr>
          <td>
            <label for="enabledcheckbox" id="enabledcheckboxlabel"><b>捕捉开启 (<div id="snappingctrlkey">CTRL-键</div>)</b></label>
          </td>
          <td><input type="checkbox" id="enabledcheckbox" checked /></td>
        </tr>
        <tr>
          <td>
            <label for="selfsnappingcheckbox" id="selfsnappingcheckboxlabel">- 自动捕捉</label>
          </td>
          <td><input type="checkbox" id="selfsnappingcheckbox" /></td>
        </tr>
        <tr>
          <td>
            <label for="featuresnappingcheckbox" id="featuresnappingcheckboxlabel">- 特征捕捉</label>
          </td>
          <td><input type="checkbox" id="featuresnappingcheckbox" /></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div id="configurationInfoDiv" class="esri-widget" title="绘画参数设置">
  </div>
  <!--  洪水海平面控制图层-->
  <div id="menu" class="esri-widget" title="海平面模拟">
    <h4>水浪方向</h4>
    <div id="waveSlider"></div>
    <h4>水浪强度</h4>
    <input type="radio" name="waveStrengthRadio" value="calm" id="calm" /><label for="calm">平静</label><br />
    <input type="radio" name="waveStrengthRadio" value="rippled" id="rippled" /><label for="rippled">细微</label><br />
    <input type="radio" name="waveStrengthRadio" value="slight" id="slight" /><label for="slight">轻微</label><br />
    <input type="radio" name="waveStrengthRadio" value="moderate" id="moderate" checked /><label for="moderate">激流</label><br />
    <h4>水面颜色</h4>
    <button id="navy" class="color-btn"></button>
    <button id="green" class="color-btn"></button>
    <button id="turqoise" class="color-btn"></button>
  </div>
</template>
<script>
import SceneView from '@arcgis/core/views/SceneView'
import WebScene from '@arcgis/core/WebScene'

import Expand from '@arcgis/core/widgets/Expand'
import Weather from '@arcgis/core/widgets/Weather'
import Daylight from '@arcgis/core/widgets/Daylight'
import ElevationProfile from '@arcgis/core/widgets/ElevationProfile'

import esriConfig from '@arcgis/core/config'
import Legend from '@arcgis/core/widgets/Legend'

import BasemapGallery from '@arcgis/core/widgets/BasemapGallery'
import Camera from '@arcgis/core/Camera'
import SpatialReference from '@arcgis/core/geometry/SpatialReference'
import FeatureLayer from '@arcgis/core/layers/FeatureLayer'
import LayerList from '@arcgis/core/widgets/LayerList'

import Search from '@arcgis/core/widgets/Search'

import GraphicsLayer from '@arcgis/core/layers/GraphicsLayer'
import Point from '@arcgis/core/geometry/Point'
import { Check, Close, CloseBold, Edit, Flag, House, Lock, PieChart, SortDown, SortUp, TrendCharts, PartlyCloudy, Umbrella, UserFilled, WarnTriangleFilled, Grid } from '@element-plus/icons-vue'

import bus from '../utils/bus.js'
import SearchView from './SearchView.vue'
import Sketch from '@arcgis/core/widgets/Sketch.js'
import DirectLineMeasurement3D from '@arcgis/core/widgets/DirectLineMeasurement3D'
import AreaMeasurement3D from '@arcgis/core/widgets/AreaMeasurement3D'
import SketchViewModel from '@arcgis/core/widgets/Sketch/SketchViewModel.js'
import Basemap from '@arcgis/core/Basemap.js'
import SearchTab from './SearchTab.vue'
import DetailTab from './DetailTab.vue'
import WeatherTab from './WeatherTab.vue'
import axios from '../api/request.js'
import CoordinateConversion from '@arcgis/core/widgets/CoordinateConversion'
import Slider from '@arcgis/core/widgets/Slider.js'
import SceneLayer from "@arcgis/core/layers/SceneLayer.js";
import Graphic from "@arcgis/core/Graphic";

export default {
  name: 'SceneView',
  computed: {
    Grid() {
      return Grid
    },
    Edit() {
      return Edit
    },
    Lock() {
      return Lock
    },
    PieChart() {
      return PieChart
    },
    Flag() {
      return Flag
    },
    SortDown() {
      return SortDown
    },
    SortUp() {
      return SortUp
    },
    Umbrella() {
      return Umbrella
    },
    WarnTriangleFilled() {
      return WarnTriangleFilled
    },
    UserFilled() {
      return UserFilled
    },
    TrendCharts() {
      return TrendCharts
    },
    PartlyCloudy() {
      return PartlyCloudy
    },
    House() {
      return House
    },
    CloseBold() {
      return CloseBold
    }
  },
  components: { WeatherTab, DetailTab, SearchTab, SearchView },
  data() {
    return {
      sceneView: null,
      map: null,
      Check,
      Close,
      searchTabVisible: false,
      weatherTabVisible: false,
      detailTabVisible: false,
      highlight: null,
      attributes: {},
      name: ''
    }
  },
  created() {
    document.body.style.marginTop = '32px'
    document.body.style.height = '100%'
    document.body.style.overflow = 'hidden'
  },
  mounted() {
    this.initializeMap()
  },
  methods: {
    layerDataLocation(layerName, FID, zoom) {
      //点击许曲后定位到许曲的相应位置
      const featureLayer = window.view.map.layers.find(layer => layer.title === layerName)
      // 构造查询条件
      const query = {
        where: 'FID = ' + (FID + 1), //我不明白为什么要+1
        returnGeometry: true
      }
      featureLayer.queryFeatures(query).then(result => {
        const feature = result.features[0]
        // 高亮显示查询结果
        if (this.highlight) {
          this.highlight.remove()
        }
        view.whenLayerView(featureLayer).then(layerView => {
          this.highlight = layerView.highlight([FID + 1]) //妈的原来这里是要传入FID，老子搞了半天总算搞明白了
        })
        //跳转位置
        if (zoom === 12) {
          let center = feature.geometry.extent.center
          let camera = new Camera({
            position: new Point({
              x: center.x,
              y: center.y - 0.5,
              z: 20000,
              spatialReference: new SpatialReference({ wkid: 4326 })
            }),
            tilt: 70
          })
          view.goTo(camera)
        } else {
          view.goTo({ target: feature.geometry, zoom: zoom })
        }
      })
    },
    async getCurrentWeather() {
      let url = '/api/weather?lat=99.78&lon=29.00'
      const weatherInfo = await axios({ url: url, method: 'get' })
      console.log(weatherInfo)
      //weatherInfo.data.server_time   //服务器时间
      //weatherInfo.data.result.forecast_keypoint  预报话
      return weatherInfo.data.result.realtime
    },
    convertSkycon(skycon) {
      //晴天:CLEAR_DAY CLEAR_NIGHT
      //多云 PARTLY_CLOUDY_DAY PARTLY_CLOUDY_NIGHT CLOUDY
      //雾天 LIGHT_HAZE MODERATE_HAZE HEAVY_HAZE FOG DUST SAND WIND
      //雨水 LIGHT_RAIN MODERATE_RAIN HEAVY_RAIN STORM_RAIN
      //雪天 LIGHT_SNOW  MODERATE_SNOW HEAVY_SNOW STORM_SNOW

      //('sunny', 'cloudy', 'rainy', 'snowy', 'foggy')
      switch (skycon) {
        case 'CLEAR_DAY':
        case 'CLEAR_NIGHT':
          return 'sunny'
        case 'PARTLY_CLOUDY_DAY':
        case 'PARTLY_CLOUDY_NIGHT':
        case 'CLOUDY':
          return 'cloudy'
        case 'LIGHT_HAZE':
        case 'MODERATE_HAZE':
        case 'HEAVY_HAZE':
        case 'FOG':
        case 'DUST':
        case 'SAND':
        case 'WIND':
          return 'foggy'
        case 'LIGHT_RAIN':
        case 'MODERATE_RAIN':
        case 'HEAVY_RAIN':
        case 'STORM_RAIN':
          return 'rainy'
        case 'LIGHT_SNOW':
        case 'MODERATE_SNOW':
        case 'HEAVY_SNOW':
        case 'STORM_SNOW':
          return 'snowy'
        default:
          return 'unknown'
      }
    },
    layerDisplayController(element) {
      //凡是点击了功能按钮就先清除一下高亮
      if (this.highlight) {
        this.highlight.remove()
      }
      let map = view.map
      let layers = map.layers
      layers.forEach(function (layer) {
        layer.visible = false
      })
      //但是模型加载的图层和海平面的图层必须一直开启
      let haipingmian = map.layers.find(function (layer) {
        return layer.title === '洪水海平面'
      })
      haipingmian.visible = true
      let moxing = map.layers.find(function (layer) {
        return layer.title === '模型加载'
      })
      moxing.visible = true

      // 乡镇名称
      let xiangzhenLayer = map.layers.find(function (layer) {
        return layer.title === '乡镇分区图'
      })
      xiangzhenLayer.visible = true
      if (element === '水系') {
        // 接着显示水系
        let shuixiLayer = map.layers.find(function (layer) {
          return layer.title === '水系'
        })
        shuixiLayer.visible = true
      } else if (element === '水安全') {
        let shuixiLayer = map.layers.find(function (layer) {
          return layer.title === '水系'
        })
        shuixiLayer.visible = true
        let huan = map.layers.find(function (layer) {
          return layer.title === '护岸'
        })
        huan.visible = true
        let difang = map.layers.find(function (layer) {
          return layer.title === '堤防'
        })
        difang.visible = true
      } else if (element === '水资源') {
        let shuiziyuan = map.layers.find(function (layer) {
          return layer.title === '水资源'
        })
        shuiziyuan.visible = true
        let guanqu = map.layers.find(function (layer) {
          return layer.title === '灌区'
        })
        guanqu.visible = true
      } else if (element === '水利工程') {
        let shuidianzhan = map.layers.find(function (layer) {
          return layer.title === '水电站'
        })
        shuidianzhan.visible = true
        let shuiwenzhan = map.layers.find(function (layer) {
          return layer.title === '水文站'
        })
        shuiwenzhan.visible = true
        let nijianshuiku = map.layers.find(function (layer) {
          return layer.title === '拟建水库'
        })
        nijianshuiku.visible = true
        let yinshuigongcheng = map.layers.find(function (layer) {
          return layer.title === '引水工程'
        })
        yinshuigongcheng.visible = true
      } else if (element === '岸线规划功能分区') {
        let xuquanxianguihua = map.layers.find(function (layer) {
          return layer.title === '许曲岸线规划功能分区'
        })
        xuquanxianguihua.visible = true
        let dingquanxianguihua = map.layers.find(function (layer) {
          return layer.title === '定曲岸线规划功能分区'
        })
        dingquanxianguihua.visible = true
      } else if (element === '河湖划界') {
        let hehuhuajie = map.layers.find(function (layer) {
          return layer.title === '河湖划界'
        })
        hehuhuajie.visible = true
      } else if (element === '健康评价') {
        // 接着显示水系
        let shuixiLayer = map.layers.find(function (layer) {
          return layer.title === '水系'
        })
        shuixiLayer.visible = true
      } else if (element === '灾害点') {
        let zaihaidian = map.layers.find(function (layer) {
          return layer.title === '灾害点'
        })
        zaihaidian.visible = true
        // 接着显示水系
        let shuixiLayer = map.layers.find(function (layer) {
          return layer.title === '水系'
        })
        shuixiLayer.visible = true
        let shuidianzhan = map.layers.find(function (layer) {
          return layer.title === '水电站'
        })
        shuidianzhan.visible = true
        let shuiwenzhan = map.layers.find(function (layer) {
          return layer.title === '水文站'
        })
        shuiwenzhan.visible = true
        let zhuanyiluxianLayer = map.layers.find(function (layer) {
          return layer.title === '地质灾害发生实际区域和转移路线'
        })
        zhuanyiluxianLayer.visible = true
      } else if (element === '移民搬迁') {
        alert('数据正在搜集中...')
      } else if (element === '水网规划') {
        // 水系 (8) 拟建水库 (25) 护岸 (0) 堤防 (1) 水资源 (2) 水电站 (4) 水文站 (5) 灌区 (16)  地质灾害发生实际区域和转移路线 (20) 引水工程 (21) 水网管线 (24) 涉河建筑物 (26)
        let shuixi = map.layers.find(function (layer) {
          return layer.title === '水系'
        })
        shuixi.visible = true
        let shuiku = map.layers.find(function (layer) {
          return layer.title === '拟建水库'
        })
        shuiku.visible = true
        let yinshui = map.layers.find(function (layer) {
          return layer.title === '引水工程'
        })
        yinshui.visible = true
        let shuiwang = map.layers.find(function (layer) {
          return layer.title === '水网管线'
        })
        shuiwang.visible = true
        let shehe = map.layers.find(function (layer) {
          return layer.title === '涉河建筑物'
        })
        shehe.visible = true
        let dizhizaihai = map.layers.find(function (layer) {
          return layer.title === '地质灾害发生实际区域和转移路线'
        })
        dizhizaihai.visible = true
        let caisha = map.layers.find(function (layer) {
          return layer.title === '采砂点位'
        })
        caisha.visible = true
        let taiyangneng = map.layers.find(function (layer) {
          return layer.title === '太阳能提灌站'
        })
        taiyangneng.visible = true
      }
      //回到主视图
      this.goHomeView()
    },
    async initializeMap() {
      let now = new Date()
      let weather = await this.getCurrentWeather()
      try {
        esriConfig.apiKey = 'AAPKfcfab4769ecd4082a0983c91ddb91a10qY1wTLqICXuld4YQGysCEGlH46-8nmNBS517S_kHqDUwYvk9P02AdG8B_gtG2UcR'
        let map = new WebScene({
          portalItem: {
            id: '5a392557cffb485f8fe004e668e9edc0'
          }
        })
        let camera = new Camera({
          position: new Point(
            //     {
            //   x: 99.82975225369145,
            //   y: 28.802280667091217,
            //   z: 3613.927701551467,
            //   spatialReference: new SpatialReference({ wkid: 4326 })
            // }
            {
              x: 99.74587169702623,
              y: 29.074594554358285,
              z: 234185.00685059093,
              spatialReference: new SpatialReference({ wkid: 4326 })
            }
          ),
          // heading: 1.962013105645539,
          heading: 0,
          // tilt: 84.3710848755085
          tilt: 0
        })
        // Create the SceneView
        let view = new SceneView({
          map: map,
          container: 'app',
          qualityProfile: 'high',
          camera: camera,
          timeExtent: {
            start: now,
            end: now
          },
          environment: {
            weather: {
              type: this.convertSkycon(weather.skycon),
              cloudCover: weather.cloudrate
            }
          },
          zoom: 10
        })

        await this.getCurrentWeather(view)

        //加载map service
        let apiUrl = import.meta.env.VITE_MAP_SERVER_URL
        let layer0 = new FeatureLayer({
          url: apiUrl + '/0',
          outFields: ['*'],
          visible: false,
          title: '护岸',
          popupTemplate: {
            content: element => {
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer1 = new FeatureLayer({
          url: apiUrl + '/1',
          outFields: ['*'],
          visible: false,
          title: '堤防',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer2 = new FeatureLayer({
          url: apiUrl + '/2',
          outFields: ['*'],
          visible: false,
          title: '水资源',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer3 = new FeatureLayer({
          url: apiUrl + '/3',
          outFields: ['*'],
          visible: false,
          title: '灾害点',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer4 = new FeatureLayer({
          url: apiUrl + '/4',
          outFields: ['*'],
          visible: false,
          title: '水电站',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer5 = new FeatureLayer({
          url: apiUrl + '/5',
          outFields: ['*'],
          visible: false,
          title: '水文站',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer6 = new FeatureLayer({
          url: apiUrl + '/6',
          outFields: ['*'],
          visible: false,
          title: '定曲岸线规划功能分区',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer7 = new FeatureLayer({
          url: apiUrl + '/7',
          outFields: ['*'],
          visible: false,
          title: '许曲岸线规划功能分区',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer8 = new FeatureLayer({
          url: apiUrl + '/8',
          outFields: ['*'],
          visible: true,
          title: '水系',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer9 = new FeatureLayer({
          url: apiUrl + '/9',
          outFields: ['*'],
          visible: true,
          title: '县（区）界',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer10 = new FeatureLayer({
          url: apiUrl + '/10',
          outFields: ['*'],
          visible: true,
          title: '乡（镇）界',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer12 = new FeatureLayer({
          url: apiUrl + '/12',
          outFields: ['*'],
          visible: true,
          title: '晕线'
        })
        let layer13 = new FeatureLayer({
          url: apiUrl + '/13',
          outFields: ['*'],
          visible: true,
          title: '晕线'
        })
        let layer14 = new FeatureLayer({
          url: apiUrl + '/14',
          outFields: ['*'],
          visible: true,
          title: '乡镇分区图',
          opacity: 0.5
        })
        let layer15 = new FeatureLayer({
          url: apiUrl + '/15',
          outFields: ['*'],
          visible: false,
          title: '河湖划界',
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer16 = new FeatureLayer({
          url: apiUrl + '/16',
          outFields: ['*'],
          visible: false,
          title: '灌区',
          opacity: 0.7,
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer25 = new FeatureLayer({
          url: apiUrl + '/25',
          outFields: ['*'],
          visible: false,
          title: '拟建水库',
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })

        let waterLayer = new FeatureLayer({
          url: apiUrl + '/18',
          title: '洪水海平面',
          opacity: 0.9,
          elevationInfo: {
            mode: 'absolute-height',
            offset: 0
          },
          renderer: {
            type: 'simple',
            symbol: {
              type: 'polygon-3d',
              symbolLayers: [
                {
                  type: 'water',
                  waveDirection: 260,
                  color: '#25427c',
                  waveStrength: 'moderate',
                  waterbodySize: 'medium'
                }
              ]
            }
          }
        })
        let layer20 = new FeatureLayer({
          url: apiUrl + '/20',
          outFields: ['*'],
          visible: false,
          opacity: 0.8,
          title: '地质灾害发生实际区域和转移路线',
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer21 = new FeatureLayer({
          url: apiUrl + '/21',
          outFields: ['*'],
          visible: false,
          title: '引水工程',
          opacity: 0.7,
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })
        let layer24 = new FeatureLayer({
          url: apiUrl + '/24',
          outFields: ['*'],
          visible: false,
          title: '水网管线',
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })

        let layer26 = new FeatureLayer({
          url: apiUrl + '/26',
          outFields: ['*'],
          visible: false,
          title: '涉河建筑物',
          popupTemplate: {
            content: element => {
              // console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })

        let layer17 = new FeatureLayer({
          url: apiUrl + '/17',
          outFields: ['*'],
          visible: false,
          title: '采砂点位',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })

        let layer27 = new FeatureLayer({
          url: apiUrl + '/27',
          outFields: ['*'],
          visible: false,
          title: '太阳能提灌站',
          popupTemplate: {
            content: element => {
              console.log(element.graphic.layer.title, element.graphic.attributes)
              this.$emit('setAttributes', element.graphic.layer.title, element.graphic.attributes)
            }
          }
        })

        //用于作图的图层
        const graphicsLayer = new GraphicsLayer()
        graphicsLayer.title = '模型加载'
        map.add(waterLayer)
        map.add(layer21)
        map.add(layer15)
        map.add(layer14)
        map.add(layer13)
        map.add(layer12)
        map.add(layer10)
        map.add(layer9)
        map.add(layer8)
        map.add(layer7)
        map.add(layer17)
        map.add(layer16)
        map.add(layer20) //灾害区域
        map.add(layer6)
        map.add(layer5)
        map.add(layer4)
        map.add(layer3)
        map.add(layer2)
        map.add(layer1)
        map.add(layer0)
        map.add(layer17) //采砂点位
        map.add(layer26) //涉河建筑物
        map.add(layer24) //水网管线
        map.add(layer25) //拟建水库
        map.add(layer27) //太阳能提灌站

        map.add(graphicsLayer) // 这是绘画图层

        let weatherExpand = new Expand({
          view: view,
          content: new Weather({
            view: view
          }),
          group: 'top-right',
          expanded: false
        })

        let daylightExpand = new Expand({
          view: view,
          content: new Daylight({
            view: view
          }),
          group: 'top-right'
        })

        let elevationProfileExpand = new Expand({
          view: view,
          content: new ElevationProfile({
            view: view,
            profiles: [{ type: 'ground' }, { type: 'view' }],
            visibleElements: {
              selectButton: true
            }
          }),
          group: 'top-right'
        })

        let basemapGalleryExpand = new Expand({
          view: view,
          content: new BasemapGallery({
            view: view,
            source: [Basemap.fromId('hybrid'), Basemap.fromId('streets'), Basemap.fromId('satellite')]
          }),
          group: 'top-right'
        })
        const legendExpand = new Expand({
          content: new Legend({
            view: view
          }),
          view: view,
          expanded: false
        })
        const dizhizaihai = new FeatureLayer({
          url: 'https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/3',
          popupTemplate: {
            title: '地质灾害点: {town}{county}{goudaoming}{name} </br>{type}, ({suggestion})',
            overwriteActions: true
          }
        })
        const shuidianzhan = new FeatureLayer({
          url: 'https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/4'
        })
        const shuiwenzhan = new FeatureLayer({
          url: 'https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/5'
        })
        const shuixi = new FeatureLayer({
          url: 'https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/8'
        })

        let searchExpand = new Expand({
          view: view,
          content: new Search({
            view: view,
            allPlaceholder: '定位搜索,下拉选择搜索图层',
            includeDefaultSources: false,
            sources: [
              {
                layer: shuidianzhan,
                searchFields: ['name'],
                displayField: 'name',
                exactMatch: false,
                outFields: ['*'],
                name: '水电站',
                placeholder: '水电站名称'
              },
              {
                layer: shuiwenzhan,
                searchFields: ['stnm'],
                displayField: 'stnm',
                exactMatch: false,
                outFields: ['*'],
                name: '水文站',
                placeholder: '水文站名称'
              },
              {
                layer: shuixi,
                searchFields: ['rname'],
                displayField: 'rname',
                exactMatch: false,
                outFields: ['*'],
                name: '水系',
                placeholder: '水系名称'
              },
              {
                layer: dizhizaihai,
                searchFields: ['town', 'county', 'name', 'code', 'type', 'people', 'goudaoming', 'danger', 'tip'],
                displayField: 'name',
                exactMatch: false,
                outFields: ['*'],
                name: '地质灾害点位',
                placeholder: '地质灾害相关信息'
              }
            ]
          }),
          group: 'top-right'
        })

        let layerListExpand = new Expand({
          view: view,
          content: new LayerList({ view: view }),
          group: 'top-right'
        })

        let sketchExpand = new Expand({
          view: view,
          content: new Sketch({
            view,
            layer: graphicsLayer,
            creationMode: 'update'
          }),
          group: 'top-right'
        })
        let floodExpand = new Expand({
          view: view,
          expandTooltip: '洪水海平面设置面板展开',
          content: document.getElementById('menu'),
          group: 'top-right'
        })

        view.ui.add([legendExpand], 'bottom-left')
        view.ui.add([weatherExpand, daylightExpand, elevationProfileExpand, basemapGalleryExpand, searchExpand, layerListExpand, sketchExpand], 'top-right')
        view.ui.add('topbar', 'top-right')

        view.ui.add('searchPane', 'top-left')
        view.ui.add('weatherPane', 'top-right')
        view.ui.add('home', 'top-left')

        view.ui.add('river', 'top-left')
        view.ui.add('safety', 'top-left')
        view.ui.add('resource', 'top-left')
        view.ui.add('project', 'top-left')
        view.ui.add('shoreline', 'top-left')
        view.ui.add('boundary', 'top-left')
        view.ui.add('health', 'top-left')
        view.ui.add('disaster', 'top-left')
        view.ui.add('relocation', 'top-left')
        view.ui.add('waternet', 'top-left')

        let ccWidget = new CoordinateConversion({
          view: view
        })

        // 坐标显示插件
        view.ui.add(ccWidget, 'bottom-left')

        // 控制洪水得
        view.ui.add(floodExpand, 'bottom-right')

        //以下是设置睡眠调整得东西
        const slider = new Slider({
          container: 'waveSlider',
          min: 2000,
          max: 4000,
          visibleElements: {
            labels: true
          },
          precision: 0,
          values: [2000]
        })

        slider.on('thumb-drag', event => {
          const value = parseInt(event.value)
          const renderer = waterLayer.renderer.clone()
          renderer.symbol.symbolLayers.getItemAt(0).waveDirection = value
          waterLayer.elevationInfo.offset = value
          waterLayer.renderer = renderer
        })

        const waveStrengthRadio = document.getElementsByName('waveStrengthRadio')

        for (let i = 0; i < waveStrengthRadio.length; i++) {
          const element = waveStrengthRadio[i]
          element.addEventListener('change', event => {
            const renderer = waterLayer.renderer.clone()
            renderer.symbol.symbolLayers.getItemAt(0).waveStrength = event.target.value
            waterLayer.renderer = renderer
          })
        }

        function setWaterColor(color) {
          const renderer = waterLayer.renderer.clone()
          renderer.symbol.symbolLayers.getItemAt(0).color = color
          waterLayer.renderer = renderer
        }
        document.getElementById('navy').addEventListener('click', () => {
          setWaterColor('#25427c')
        })
        document.getElementById('green').addEventListener('click', () => {
          setWaterColor('#039962')
        })
        document.getElementById('turqoise').addEventListener('click', () => {
          setWaterColor('rgb(119,89,37)')
        })

        //3D绘画技术
        const extrudedPolygon = {
          type: 'polygon-3d',
          symbolLayers: [
            {
              type: 'extrude',
              size: 50, // extrude by 10 meters
              material: {
                color: 'gray'
              },
              edges: {
                type: 'solid',
                size: '3px',
                color: 'blue'
              }
            }
          ]
        }

        // polyline symbol used for sketching routes
        const route = {
          type: 'line-3d',
          symbolLayers: [
            {
              type: 'line',
              size: '10px',
              material: {
                color: 'white'
              }
            },
            {
              type: 'line',
              size: '3px',
              material: {
                color: 'blue'
              }
            }
          ]
        }

        // point symbol used for sketching points of interest
        const point = {
          type: 'text', // autocasts as new TextSymbol()
          color: '#fff',
          text: '\ue61e', // esri-icon-key
          font: {
            size: 10,
            family: 'CalciteWebCoreIcons'
          },
          horizontalAlignment: 'left',
          verticalAlignment: 'bottom'
        }
        // Set-up event handlers for buttons and click events
        const enabledcheckbox = document.getElementById('enabledcheckbox')
        const startbuttons = document.getElementById('startbuttons')
        const actionbuttons = document.getElementById('actionbuttons')
        const edgeoperationbuttons = document.getElementById('edgeoperationbuttons')
        const tooltipOptionsheckbox = document.getElementById('tooltipOptionsheckbox')
        const configurationInfoDiv = document.getElementById('configurationInfoDiv')
        const labelOptionscheckbox = document.getElementById('labelOptionscheckbox')
        const selfsnappingcheckbox = document.getElementById('selfsnappingcheckbox')
        const snappingctrlkey = document.getElementById('snappingctrlkey')
        const featuresnappingcheckbox = document.getElementById('featuresnappingcheckbox')
        // load the default value from the snapping checkbox
        let snappingcheckboxsavedstate = enabledcheckbox.checked ? true : false

        // define the SketchViewModel and pass in the symbols for each geometry type
        // set the snappingOptions.selfEnabled to the default state
        const sketchViewModel = new SketchViewModel({
          layer: graphicsLayer,
          view: view,
          pointSymbol: point,
          polygonSymbol: extrudedPolygon,
          polylineSymbol: route,
          snappingOptions: {
            enabled: snappingcheckboxsavedstate,
            featureSources: [{ layer: graphicsLayer }]
          },
          tooltipOptions: { enabled: true },
          labelOptions: { enabled: true },
          defaultUpdateOptions: {
            tool: 'reshape'
          }
        })
        // after drawing the geometry, enter the update mode to update the geometry
        // and the deactivate the buttons
        sketchViewModel.on('create', event => {
          if (event.state === 'complete') {
            startbuttons.style.display = 'inline'
            actionbuttons.style.display = 'none'
            sketchViewModel.update(event.graphic)
          }
          if (event.state === 'cancel') {
            startbuttons.style.display = 'inline'
            actionbuttons.style.display = 'none'
          }
          //以上是设置按钮的样式
          //接下来我们需要设置绘制后的一些处理工作
          if (event.state === 'complete' && event.tool === 'point') {
            const point = event.graphic.geometry
            event.graphic.symbol.text = point.longitude.toFixed(4) + ',' + point.latitude.toFixed(4) + ',' + point.z.toFixed(2)
          }
        })

        sketchViewModel.on('update', event => {
          if (event.state === 'start') {
            startbuttons.style.display = 'none'
            actionbuttons.style.display = 'inline'
            if (event.graphics[0].geometry.type === 'polygon' || event.graphics[0].geometry.type === 'polyline') {
              edgeoperationbuttons.style.display = 'inline'
            }
          }
          if (event.state === 'complete') {
            startbuttons.style.display = 'inline'
            actionbuttons.style.display = 'none'
            edgeoperationbuttons.style.display = 'none'
          }
        })

        /**********************************************
         * Drawing UI with configuration
         *********************************************/

        const drawButtons = Array.prototype.slice.call(document.getElementsByClassName('starttool'))
        const cancelBtn = document.getElementById('cancel')
        const doneBtn = document.getElementById('done')

        // set event listeners to activate sketching graphics
        drawButtons.forEach(btn => {
          btn.addEventListener('click', event => {
            // to activate sketching the create method is called passing in the geometry type
            // from the data-type attribute of the html element
            sketchViewModel.create(event.target.getAttribute('data-type'))
            startbuttons.style.display = 'none'
            actionbuttons.style.display = 'inline'
          })
        })

        cancelBtn.addEventListener('click', event => {
          sketchViewModel.cancel()
        })
        doneBtn.addEventListener('click', event => {
          if (sketchViewModel.updateGraphics.length !== 0) {
            sketchViewModel.complete()
          } else {
            sketchViewModel.cancel()
          }
        })

        view.ui.add('sketchPanel', 'bottom-right')

        // default values for edge/move operations
        let edgeType = 'split'
        let shapeType = 'move'

        // Handling the configuration for edge operation
        const noneEdgeBtn = document.getElementById('none-edge-button')
        const splitEdgeBtn = document.getElementById('split-edge-button')
        const offsetEdgeBtn = document.getElementById('offset-edge-button')
        noneEdgeBtn.onclick = edgeChangedClickHandler
        splitEdgeBtn.onclick = edgeChangedClickHandler
        offsetEdgeBtn.onclick = edgeChangedClickHandler

        function edgeChangedClickHandler(event) {
          edgeType = event.target.value

          // handle the buttons
          const buttons = document.getElementsByClassName('edge-button')
          for (const button of buttons) {
            button.classList.remove('edge-button-selected')
          }
          this.classList.add('edge-button-selected')
          restartUpdateMode({
            reshapeOptions: {
              edgeOperation: edgeType,
              shapeOperation: shapeType
            }
          })
        }

        // Handling the configuration for move operation
        const noneShapeButton = document.getElementById('none-shape-button')
        const moveShapeButton = document.getElementById('move-shape-button')
        noneShapeButton.onclick = shapeChangedClickHandler
        moveShapeButton.onclick = shapeChangedClickHandler

        function shapeChangedClickHandler(event) {
          shapeType = event.target.value

          // handle the buttons
          const buttons = document.getElementsByClassName('shape-button')
          for (const button of buttons) {
            button.classList.remove('shape-button-selected')
          }
          this.classList.add('shape-button-selected')
          restartUpdateMode({
            reshapeOptions: {
              edgeOperation: edgeType,
              shapeOperation: shapeType
            }
          })
        }

        function restartUpdateMode(updateOptions) {
          sketchViewModel.defaultUpdateOptions = {
            ...sketchViewModel.defaultUpdateOptions,
            ...updateOptions
          }

          if (sketchViewModel.activeTool) {
            if (sketchViewModel.activeTool === 'transform' || sketchViewModel.activeTool === 'move' || sketchViewModel.activeTool === 'reshape') {
              updateOptions.tool = sketchViewModel.activeTool
              sketchViewModel.update(sketchViewModel.updateGraphics.toArray(), updateOptions)
            }
          }
        }

        /**********************************************
         * Configuration UI for snapping
         *********************************************/

        sketchViewModel.watch('snappingOptions.enabled', newValue => {
          enabledcheckbox.checked = newValue
        })

        enabledcheckbox.checked = sketchViewModel.snappingOptions.enabled
        enabledcheckbox.addEventListener('change', event => {
          sketchViewModel.snappingOptions.enabled = event.target.checked ? true : false
        })

        tooltipOptionsheckbox.checked = sketchViewModel.tooltipOptions.enabled
        tooltipOptionsheckbox.addEventListener('change', event => {
          sketchViewModel.tooltipOptions.enabled = event.target.checked ? true : false
        })

        labelOptionscheckbox.checked = sketchViewModel.labelOptions.enabled
        labelOptionscheckbox.addEventListener('change', event => {
          sketchViewModel.labelOptions.enabled = event.target.checked ? true : false
        })

        selfsnappingcheckbox.checked = sketchViewModel.snappingOptions.selfEnabled
        selfsnappingcheckbox.addEventListener('change', event => {
          sketchViewModel.snappingOptions.selfEnabled = event.target.checked ? true : false
        })

        featuresnappingcheckbox.checked = sketchViewModel.snappingOptions.featureEnabled
        featuresnappingcheckbox.addEventListener('change', event => {
          sketchViewModel.snappingOptions.featureEnabled = event.target.checked ? true : false
        })

        const configurationExpand = new Expand({
          expandIcon: 'gear',
          expandTooltip: '模型绘制参数设置',
          expanded: false,
          view: view,
          content: document.getElementById('configurationDiv')
        })

        // observe the if the CTRL-key got pressed to give the user a visual feedback
        // the logic itself for toggling snapping is in the SketchViewModel
        view.on(['key-down'], ev => {
          if (ev.key === 'Control') {
            snappingctrlkey.style.fontWeight = 'bold'
            snappingctrlkey.style.color = 'royalblue'
          }
        })
        view.on(['key-up'], ev => {
          if (ev.key === 'Control') {
            snappingctrlkey.style.fontWeight = 'normal'
            snappingctrlkey.style.color = 'black'
          }
        })

        view.ui.add(configurationExpand, 'bottom-right')

        configurationInfoDiv.addEventListener('click', event => {
          configurationExpand.expand()
        })
        view.ui.add('configurationInfoDiv', 'bottom-right')

        //距离面积测量
        let activeWidget = null
        document.getElementById('distanceButton').addEventListener('click', event => {
          setActiveWidget(null)
          if (!event.target.classList.contains('active')) {
            setActiveWidget('distance')
          } else {
            setActiveButton(null)
          }
        })

        document.getElementById('areaButton').addEventListener('click', event => {
          setActiveWidget(null)
          if (!event.target.classList.contains('active')) {
            setActiveWidget('area')
          } else {
            setActiveButton(null)
          }
        })
        document.getElementById('screenShotButton').addEventListener('click', event => {
          view
            .takeScreenshot({
              format: 'png',
              quality: '98',
              width: 1980,
              height: 1080
            })
            .then(screenshoot => {
              const link = document.createElement('a')
              link.download = 'screenshot.png' // Set the download filename
              link.href = screenshoot.dataUrl // Set the base64 data URL as the href
              link.click() // Trigger the download
              setTimeout(() => {
                link.remove() // Remove the link element
              }, 1000)
            })
        })

        function setActiveWidget(type) {
          switch (type) {
            case 'distance':
              activeWidget = new DirectLineMeasurement3D({
                view: view
              })

              // skip the initial 'new measurement' button
              activeWidget.viewModel.start()

              view.ui.add(activeWidget, 'top-right')
              setActiveButton(document.getElementById('distanceButton'))
              break
            case 'area':
              activeWidget = new AreaMeasurement3D({
                view: view
              })

              // skip the initial 'new measurement' button
              activeWidget.viewModel.start()

              view.ui.add(activeWidget, 'top-right')
              setActiveButton(document.getElementById('areaButton'))
              break
            case null:
              if (activeWidget) {
                view.ui.remove(activeWidget)
                activeWidget.destroy()
                activeWidget = null
              }
              break
          }
        }

        function setActiveButton(selectedButton) {
          // focus the view to activate keyboard shortcuts for sketching
          view.focus()
          const elements = document.getElementsByClassName('active')
          for (let i = 0; i < elements.length; i++) {
            elements[i].classList.remove('active')
          }
          if (selectedButton) {
            selectedButton.classList.add('active')
          }
        }
        function deactivateButtons() {
          const elements = Array.prototype.slice.call(document.getElementsByClassName('esri-button'))
          elements.forEach(element => {
            element.classList.remove('esri-button--secondary')
          })
        }

        //放置glb模型的代码
        const canoeBtn = document.getElementById('canoe')
        view
          .when(() => {
            // This sample uses the SketchViewModel to add points to a
            // GraphicsLayer. The points have 3D glTF models as symbols.
            const sketchVM = new SketchViewModel({
              layer: graphicsLayer,
              view: view
            })
            canoeBtn.addEventListener('click', () => {
              // reference the relative path to the glTF model
              // in the resource of an ObjectSymbol3DLayer
              sketchVM.pointSymbol = {
                type: 'point-3d',
                symbolLayers: [
                  {
                    type: 'object',
                    resource: {
                      href: '/glb/sjg.glb'
                    }
                  }
                ]
              }
              deactivateButtons()
              sketchVM.create('point')
              canoeBtn.classList.add('esri-button--secondary')
            })

            sketchVM.on('create', event => {
              if (event.state === 'complete') {
                sketchVM.update(event.graphic)
                deactivateButtons()
              }
            })
          })
          .catch(console.error)

        //左下角添加当前经纬度显示
        // const ccWidget = new CoordinateConversion({
        //   view: view
        // })
        // view.ui.add(ccWidget, 'bottom-left')

        // 将 SceneView 对象保存到组件的 data 中
        this.sceneView = view
        this.map = map
        // 触发 "map-ready" 事件
        this.$emit('map-ready', this.view)

        //将对象添加进入window 调试使用
        window.view = view

        bus.on('shuixiCellClick', data => {
          this.detailPaneDisplay()
        })
        bus.on('layerLocation', data => {
          if (data[0] === '护岸'){
            //护岸使用extrajson存储id
            this.layerDataLocation(data[0], Number(data[1].extraJson) - 1, 14)
          }else{
            //还要进行图层的定位
            this.layerDataLocation(data[0], data[1].id - 1, 14)
          }
        })

        const modelSymbol = {
          type: "point-3d",
          symbolLayers: [
            {
              type: "object",
              resource: {
                href: "/glb/irstation.glb"
              },
              height: 20,
              heading: 0,
              tilt: 0,
              scale: 1,
              anchor: "relative",
              anchorPosition: {
                x: 0,
                y: 0,
                z: 0
              }
            }
          ]
        };

        const modelGraphic = new Graphic({
          geometry: {
            type: "point",
            x: 99.71,
            y: 28.92,
            z: 4000
          },
          symbol: modelSymbol
        });
        graphicsLayer.add(modelGraphic);

      } catch (error) {
        console.error('地图初始化失败：', error)
      }
    },
    searchPaneDisplay() {
      if (this.searchTabVisible) document.getElementsByClassName('searchTab')[0].style.display = 'none'
      else if (!this.searchTabVisible) document.getElementsByClassName('searchTab')[0].style.display = 'block'
      this.searchTabVisible = !this.searchTabVisible
    },
    weatherPaneDisplay() {
      if (this.weatherTabVisible) document.getElementsByClassName('weatherTab')[0].style.display = 'none'
      else if (!this.searchTabVisible) document.getElementsByClassName('weatherTab')[0].style.display = 'block'
      this.weatherTabVisible = !this.weatherTabVisible
    },
    detailPaneDisplay() {
      document.getElementsByClassName('detailTab')[0].style.display = 'block'
    },
    goHomeView(layerDis = false) {
      if (layerDis) {
        //凡是点击了功能按钮就先清除一下高亮
        if (this.highlight) {
          this.highlight.remove()
        }
        let map = view.map
        let layers = map.layers
        layers.forEach(function (layer) {
          layer.visible = true
        })
      }
      //回到主视图
      let camera = new Camera({
        position: new Point({
          x: 99.74587169702623,
          y: 29.074594554358285,
          z: 234185.00685059093,
          spatialReference: new SpatialReference({ wkid: 4326 })
        }),
        heading: 0,
        tilt: 0
      })
      window.view.goTo(camera)
    },
    searchTabClose() {
      // console.log(e.value)
      document.getElementsByClassName('searchTab')[0].style.display = 'none'
      this.searchTabVisible = !this.searchTabVisible
    },
    detailTabClose() {
      document.getElementsByClassName('detailTab')[0].style.display = 'none'
    },
    weatherTabClose() {
      document.getElementsByClassName('weatherTab')[0].style.display = 'none'
    },
    // 移动盒子
    moveBox(e) {
      console.log('按下鼠标')
      let box = document.querySelector('.detailTab')
      let x = e.pageX - box.offsetLeft //    鼠标距离页面位置 - 盒子距离左边的位置 =   鼠标点击位置到盒子边缘的距离
      let y = e.pageY - box.offsetTop
      document.addEventListener('mousemove', moveS)
      function moveS(e) {
        box.style.left = e.pageX - x + 'px' //鼠标所在位置  -   鼠标到盒子边缘的距离 =  盒子距离左边的距离
        box.style.top = e.pageY - y + 'px'
      }
      document.addEventListener('mouseup', function (e) {
        document.removeEventListener('mousemove', moveS)
      })
    }
  }
}
</script>
<style lang="less">
body {
  margin-bottom: 0;
  // margin-top: 50px;
}
ul {
  list-style: none;
}
/* 控制popupTemplate显示 */
.esri-popup__main-container {
  display: none !important;
}
/*图层按钮的样式*/
.map-FigureLayer {
  position: fixed;
  width: 60px;
  height: 40px;
  right: 22px;
  top: 200px;
  background-color: #ffffff;
  text-align: center;
  font-size: 1em;
  border-radius: 10px;
  border: 2px solid skyblue;
}
.map-FigureLayer p {
  margin: 0 auto;
  line-height: 40px;
}
.map-FigureLayer-Font :hover {
  cursor: pointer;
  user-select: none;
}

.map-FigureLayer-TF {
  position: fixed;
  right: 22px;
  top: 260px;
  background-color: #4f8ee1;
  border-radius: 10px;
  height: 400px;
  color: white;
  font-weight: bold;
  width: 225px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  overflow-y: scroll;
  opacity: 0.88;
  user-select: none;
}
.map-FigureLayer-TF p {
  width: 180px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.map-FigureLayer-TFdiv {
  display: flex;
}
.map-FigureLayer-TFdiv > div {
  line-height: 40px;
  flex-wrap: wrap;
  right: 20px;
  height: 40px;
  align-items: center;
}
/* 设置导航 */
.navbar {
  background-color: #4f8ee1 !important;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 1; /* 设置比地图视图层级高 */
}
.navbar-left img {
  display: inline;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-left {
  margin-left: 20px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.navbar-left h1 {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
  margin: 0;
  // 不换行
  white-space: nowrap;
  width: 70%;
}
.river-system {
  display: flex;
  justify-content: space-around;
  // justify-content: ;
  width: 20%;
}

.water-system-button {
  color: white;
  background-color: #79bbff;
  border-radius: 5px;
  height: 30px;
  line-height: 30px;
  width: 20%;
  text-align: center;
  // 超出盒子显示省略号
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.water-system-button:hover {
  animation-duration: 0.5s;
  background-color: #0171dc;
  cursor: pointer;
}

.navbar-right {
  margin-right: 20px;
  display: flex;
  align-items: center;
}

.navbar-right img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-right p {
  font-size: 14px;
  color: #ffffff;
  margin: 0;
  width: 80%;
  // 超出盒子显示省略号
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.navbar-right p:hover {
  cursor: pointer;
  color: yellowgreen;
}
.navbar-right img:hover {
  cursor: pointer;
  width: 35px;
  height: 35px;
  transition-duration: 0.4s;
}

.measure-tools-icon:hover {
  cursor: pointer;
  background-color: rgb(243, 243, 243);
}
/* 搜索功能 */
.searchTab {
  overflow: auto;
  width: 24%;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.6);
  left: 80px;
  top: 60px;
  border-radius: 5px;
  box-shadow: #6e6e6e;
  display: none;
  height: 80%;
  // overflow: auto;
  min-width: 300px;
}
/* 河流信息 */
.detailTab {
  //width: 50%;
  max-width: 1452px;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.6);
  left: 60%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 5px;
  box-shadow: #6e6e6e;
  display: none;
  min-width: 990px;
  min-height: 480px;
}

/* 天气预警 */
.weatherTab {
  // height: 20%;
  //width: 14%;
  min-height: 250px;
  min-width: 200px;
  // width: 230px;
  // height: 270px;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.6);
  right: 100px;
  top: 50px;
  border-radius: 10px;
  box-shadow: #6e6e6e;
  display: none;
  padding: 0.8%;
}

/*测量工具*/
#topbar {
  background: rgba(0, 0, 0, 0);
  display: flex;
  flex-direction: column;
}

.action-button {
  font-size: 16px;
  color: #6e6e6e;
  height: 32px;
  width: 32px;
  text-align: center;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
  margin-bottom: 10px;
}

.action-button:hover,
.action-button:focus {
  cursor: pointer;
}

.active {
  background: #0079c1;
  color: #e4e4e4;
}

/*3D绘画工具*/
#sketchPanel {
  padding: 10px;
  background-color: rgba(255, 255, 255, 0.8);
}

.esri-button {
  margin: 2px;
}

#configurationDiv {
  padding: 10px;
  text-align: left;
  width: 250px;
}

#configurationInfoDiv {
  padding: 7px;
  text-align: left;
}

#snappingctrlkey {
  display: inline;
}

#actionbuttons,
#edgeoperationbuttons {
  display: none;
}

.update-options {
  display: flex;
  flex-direction: row;
}

.edge-button,
.shape-button {
  flex: 1;
  border-style: solid;
  border-width: 1px;
  border-image: none;
}

.edge-button-selected,
.shape-button-selected {
  background: #4c4c4c;
  color: #fff;
}

// .logo-title {
//   padding-right: 100px;
// }
.nine-function-div {
  width: 32px;
  height: 32px;
}
.else-function-div .el-button {
  background-color: #79bbff;
}
/*防止左边按钮变形*/
.el-button {
  padding: 0 9px !important;
  background-color: white;
  color: #6e6e6e;
}
.el-button:hover {
  background-color: #f3f3f3;
  color: #2e2e2e;
}
/* tab关闭的按钮div*/
.right-top-close {
  z-index: 999;
  width: 15px;
  height: 15px;
  position: absolute;
  right: 5px;
  top: 5px;
  color: #6e6e6e;
}
.right-top-close:hover {
  cursor: pointer;
}

/*洪水海平面控制*/
#menu {
  min-width: 200px !important;
  padding: 1em;
}

#waveSlider {
  height: 50px;
  margin-top: 2em;
}

.color-btn {
  border: 1px solid rgb(173, 172, 172);
  width: 40px;
  height: 20px;
  cursor: pointer;
}

#navy {
  background-color: #25427c;
}

#green {
  background-color: #039962;
}

#turqoise {
  background-color: rgb(119, 89, 37);
}
/*洪水海平面控制↑*/

.professional-data {
  text-align: center;
  background-color: rgb(60, 123, 206);
  padding: 5px 10px 5px 10px;
  border-radius: 5px;
  margin-top: 10px;
}
.professional-data a{
  text-decoration: none;
  color: white;
}
</style>
