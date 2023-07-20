<template>
  <el-dialog v-model="dialogVisible" :title="name" width="50%" @close="restore">
    <div class="disasterInfo" v-if="name === '灾害点'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="ID">{{ disasterInfo.Id }}</el-descriptions-item>
        <el-descriptions-item label="代码">{{ disasterInfo.code }}</el-descriptions-item>
        <el-descriptions-item label="危险颜色划分">{{ disasterInfo.color }}</el-descriptions-item>
        <el-descriptions-item label="县">{{ disasterInfo.county }}</el-descriptions-item>
        <el-descriptions-item label="危险程度">{{ disasterInfo.danger }}</el-descriptions-item>
        <el-descriptions-item label="沟名">{{ disasterInfo.goudaoming }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="人数">{{ disasterInfo.people }}</el-descriptions-item>
        <el-descriptions-item label="建议">{{ disasterInfo.suggestion }}</el-descriptions-item>
        <el-descriptions-item label="提示">{{ disasterInfo.tip }}</el-descriptions-item>
        <el-descriptions-item label="镇">{{ disasterInfo.town }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '护岸'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="ID">{{ disasterInfo.id }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '堤防'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="长度">{{ disasterInfo.length }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '水资源'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type || disasterInfo.resourceType }}</el-descriptions-item>
        <el-descriptions-item label="ID">{{ disasterInfo.id }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '水电站'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{  disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '水文站'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{  disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '许曲岸线规划功能分区' || name === '定曲岸线规划功能分区'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="面积">{{ disasterInfo.area }}</el-descriptions-item>
        <el-descriptions-item label="基本需求">{{ disasterInfo.basis }}</el-descriptions-item>
        <el-descriptions-item label="所属市州">{{ disasterInfo.city }}</el-descriptions-item>
        <el-descriptions-item label="所属县">{{ disasterInfo.county }}</el-descriptions-item>
        <el-descriptions-item label="GID">{{ disasterInfo.gid }}</el-descriptions-item>
        <el-descriptions-item label="长度">{{ disasterInfo.len }}</el-descriptions-item>
        <el-descriptions-item label="区域ID">{{ disasterInfo.regionid }}</el-descriptions-item>
        <el-descriptions-item label="限制">{{ disasterInfo.restraints }}</el-descriptions-item>
        <el-descriptions-item label="河流名称">{{ disasterInfo.rname }}</el-descriptions-item>
        <el-descriptions-item label="岸别">{{ disasterInfo.side === 1?"右":"左" }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '灌区'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="面积">{{ disasterInfo.BM_Area }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.BM_Name }}</el-descriptions-item>
        <el-descriptions-item label="BM_Perimet">{{ disasterInfo.BM_Perimet }}</el-descriptions-item>
        <el-descriptions-item label="BM_Time">{{ disasterInfo.BM_Time }}</el-descriptions-item>
        <el-descriptions-item label="县">{{ disasterInfo.Layer }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.Name }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '拟建水库'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.O_Name || disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
        <el-descriptions-item label="详情">{{ disasterInfo.O_Comment }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '水系'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="支流名称">{{ disasterInfo.riverName || disasterInfo.rname }}</el-descriptions-item>
        <el-descriptions-item label="干流名称">{{ disasterInfo.affiliatedBasin || disasterInfo.srname}}</el-descriptions-item>
        <el-descriptions-item label="流域总水量">{{ disasterInfo.basinvol }}</el-descriptions-item>
        <el-descriptions-item label="径流">{{ disasterInfo.runoff }}</el-descriptions-item>
        <el-descriptions-item label="已用水量">{{ disasterInfo.consum }}</el-descriptions-item>
        <el-descriptions-item label="可用水量">{{ disasterInfo.available }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '河湖划界'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="干流名称">{{ disasterInfo.rname }}</el-descriptions-item>
        <el-descriptions-item label="支流名称">{{ disasterInfo.srname }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '地质灾害发生实际区域和转移路线'">
      <img class="disasterEscapeInfo-img" src="http://xiangoos.ruankun.xyz/%E4%B9%A1%E5%9F%8E%E5%8E%BF%E9%A6%99%E5%B7%B4%E6%8B%89%E9%95%87%E5%86%B7%E9%98%BF%E5%A4%AE%E4%BB%B2%E6%9D%91.jpg">
    </div>
    <div class="disasterInfo" v-if="name === '引水工程'">
      <img class="disasterEscapeInfo-img" src="http://xiangoos.ruankun.xyz/%E6%AD%A3%E6%96%97%E4%B9%A1%E5%BC%95%E6%B0%B4%E7%94%A8%E6%B0%B4%E5%8C%BA%E5%9F%9F.jpg">
    </div>
    <div class="disasterInfo" v-if="name === '水网管线'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.O_Name ||  disasterInfo.name}}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '涉河建筑物'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID || 0}}</el-descriptions-item>
        <el-descriptions-item label="干流">{{ disasterInfo.river }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ disasterInfo.type }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="介绍">{{ disasterInfo.intro }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '采砂点位'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="介绍">{{ disasterInfo.intro }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="disasterInfo" v-if="name === '太阳能提灌站'">
      <el-descriptions :border="true" :column="2">
        <el-descriptions-item label="FID">{{ disasterInfo.id || disasterInfo.FID || 0}}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ disasterInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="介绍">{{ disasterInfo.comment }}</el-descriptions-item>
        <el-descriptions-item label="所属行政区划">{{ disasterInfo.region }}</el-descriptions-item>
      </el-descriptions>
    </div>
  </el-dialog>
</template>
<script>
import { reactive, toRefs, watchEffect, watch, toRaw, onMounted } from 'vue'
import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
import axios from '../api/request'
import Radar from './GradientStackedAreaChart.vue'
import Search from './Search.vue'
import bus from '../utils/bus.js'

export default {
  components: { ElDialog, ElTabs, ElTabPane, Radar, Search },

  methods: {},
  props: {
    attributes: {
      type: Object,
      required: true
    },
    name: {
      type: String,
      required: true
    }
  },
  setup(props) {
    console.log(props)
    const state = reactive({
      dialogVisible: false,
      activeTab: 'disaster',
      disasterInfo: {},
      name: ''
    })

    const fetchData = async () => {
      //监听 fid 的变化,因为父传过来的props可能产生异步,所以要监听props的变化
      watch(
        () => props.attributes,
        async newattributes => {
          console.log(props.attributes, newattributes)
          try {
            // const disasterInfoRes = await axios({ url: `/api/water-systems/${newattributes.FID}`, method: 'get' }) //水系基本信息
            // state.disasterInfo = disasterInfoRes.data

            state.disasterInfo = props.attributes
            state.name = props.name
            console.log('在这里请求数据', state.disasterInfo)
          } catch (error) {
            console.error(error)
          }
        },
        { immediate: true }
      ) // 立即执行一次
    }

    const openDialog = () => {
      console.log('hello world')
      state.dialogVisible = true
      fetchData()
    }

    // 关闭当前河流窗口后触发
    const restore = () => {
      // 重置Tab当前所在信息栏
      state.activeTab = 'disaster'
    }

    // 当监听到location定位的时候,关闭弹窗
    bus.on('closePop', data => {
      state.dialogVisible = data
    })
    return {
      ...toRefs(state),
      openDialog,
      restore
    }
  }
}
</script>
<style scoped>
.disasterEscapeInfo-img {
  width: 100%;
  height: 100%;
}
</style>