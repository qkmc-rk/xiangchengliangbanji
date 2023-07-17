<template>
  <el-tabs v-model="activeName" class="search-tab">
    <el-tab-pane label="水系" name="shuiXi">
      <div class="input-size">
        <el-input v-model="shuixiInput" class="w-50 m-2" size="large" placeholder="请输入水系名称" :suffix-icon="Search" @input="shuixiInputChange" />
      </div>
      <div class="data-static">
        干流3条，支流27条，湖泊7个
      </div>
      <div class="data-table">
        <el-table :data="shuixiTableData" style=" background-color: rgba(255,255,255,0)" row-key="id" @cell-click="shuixiCellClick">
          <el-table-column prop="riverName" label="河流名称"></el-table-column>
        </el-table>
      </div>
    </el-tab-pane>
    <el-tab-pane label="水系相关资源" name="safe">
      <p style="font-size: 16px">1.选择水利工程类型</p>
      <el-select v-model="typevalue" class="m-2" placeholder="选择水利工程类型" size="large">
        <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
      </el-select>
      <p style="font-size: 16px">2.选择行政区域并输入关键词</p>
      <div class="input-size">
        <div class="mt-4">
          <el-input v-model="input3" placeholder="搜索空即为搜索所有" class="input-with-select" size="large">
            <template #prepend>
              <el-select v-model="select" placeholder="行政区" style="width: 115px">
                <el-option label="尼斯乡" value="尼斯乡" />
                <el-option label="青德乡" value="青德乡" />
                <el-option label="正斗乡" value="正斗乡" />
                <el-option label="香巴拉" value="香巴拉" />
                <el-option label="定波乡" value="定波乡" />
                <el-option label="水洼乡" value="水洼乡" />
                <el-option label="沙贡乡" value="沙贡乡" />
                <el-option label="洞松乡" value="洞松乡" />
                <el-option label="然乌乡" value="然乌乡" />
                <el-option label="热打乡" value="热打乡" />
                <el-option label="青麦乡" value="青麦乡" />
                <el-option label="白依乡" value="白依乡" />
              </el-select>
            </template>
            <template #append>
              <el-button :icon="Search" @click="searchWithRegion" />
            </template>
          </el-input>
        </div>
      </div>
      <div class="data-table">
        <el-table v-if="watersafetyresult.length !== 0" :data="watersafetyresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="堤防ID" />
          <el-table-column prop="extraJson" label="护岸ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="type" label="类型" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail(scope.row.type, scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation(scope.row.type, scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="waterresourcesresult.length !== 0" :data="waterresourcesresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="resourceType" label="类型" />
          <el-table-column prop="extractionRate" label="利用率" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('水资源', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('水资源', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="waterprojectsresult.length !== 0" :data="waterprojectsresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="type" label="类型" />
          <el-table-column prop="status" label="状态" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('水利工程', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('水利工程', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="disasterpreventionpointresult.length !== 0" :data="disasterpreventionpointresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="type" label="类型" />
          <el-table-column prop="description" label="描述" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('灾害点', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('灾害点', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="solarirstationresult.length !== 0" :data="solarirstationresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('太阳能提灌站', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('太阳能提灌站', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="sandsiteresult.length !== 0" :data="sandsiteresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('采砂点', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('采砂点', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="riversidebuildingresult.length !== 0" :data="riversidebuildingresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('涉河建筑物', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('涉河建筑物', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="waternetpiperesult.length !== 0" :data="waternetpiperesult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('水网管线', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('水网管线', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="irrigationarearesult.length !== 0" :data="irrigationarearesult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="perimet" label="周长" />
          <el-table-column prop="area" label="面积" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('灌区', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('灌区', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-table v-if="proposalreservoirresult.length !== 0" :data="proposalreservoirresult" style=" background-color: rgba(255,255,255,0)" row-key="id">
          <el-table-column prop="id" label="ID" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="river" label="流域" />
          <el-table-column fixed="right" label="更多">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="gotoDetail('拟建水库', scope.row)">详情</el-button>
              <el-button link type="primary" size="small" @click="layerLocation('拟建水库', scope.row)">跳转</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import { reactive, toRefs, ref } from 'vue'
import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import axios from '../api/request.js'
import bus from '../utils/bus.js'

export default {
  components: { ElDialog, ElTabs, ElTabPane },
  data() {
    return {
      shuixiInput: '',
      shuixiTableData: [],
      select: '',
      input3: '',
      typevalue: '',
      initialData: [
        {
          id: 27,
          riverName: '许曲',
          children: [
            {
              id: 17,
              riverName: '色坝沟'
            },
            {
              id: 10,
              riverName: '过绒沟'
            },
            {
              id: 7,
              riverName: '马熊沟'
            },
            {
              id: 20,
              riverName: '日翁卓沟'
            },
            {
              id: 23,
              riverName: '百另八条沟'
            },
            {
              id: 9,
              riverName: '浪充拥沟'
            },
            {
              id: 21,
              riverName: '热勒拥沟'
            },
            {
              id: 11,
              riverName: '冷龙沟'
            },
            {
              id: 22,
              riverName: '登尕冈休沟'
            },
            {
              id: 0,
              riverName: '仁堆沟'
            },
            {
              id: 26,
              riverName: '然乌沟'
            },
            {
              id: 19,
              riverName: '青达沟'
            },
            {
              id: 13,
              riverName: '牛龙沟'
            }
          ]
        },
        {
          id: 29,
          riverName: '定曲',
          children: [
            {
              id: 15,
              riverName: '结绒溪右支河'
            },
            {
              id: 25,
              riverName: '结绒溪'
            },
            {
              id: 8,
              riverName: '曾斗溪'
            },
            {
              id: 14,
              riverName: '麻拉沟'
            }
          ]
        },
        {
          id: 28,
          riverName: '玛曲',
          children: [
            {
              id: 3,
              riverName: '弄仰溪'
            },
            {
              id: 1,
              riverName: '扎衣溪'
            },
            {
              id: 24,
              riverName: '毕斗溪'
            },
            {
              id: 2,
              riverName: '水浇沟'
            },
            {
              id: 12,
              riverName: '松协通沟'
            },
            {
              id: 16,
              riverName: '莫让溪'
            },
            {
              id: 18,
              riverName: '纳力拥沟'
            },
            {
              id: 4,
              riverName: '曲刀拥沟'
            },
            {
              id: 5,
              riverName: '布吉沟'
            },
            {
              id: 6,
              riverName: '清多沟'
            }
          ]
        },
        {
          id: -1,
          riverName: '湖泊',
          children: [
            {
              id: 30,
              riverName: '次拥措'
            },
            {
              id: 31,
              riverName: '沙绕措'
            },
            {
              id: 32,
              riverName: '吾年措'
            },
            {
              id: 33,
              riverName: '吾年措'
            },
            {
              id: 34,
              riverName: '吾年措'
            },
            {
              id: 35,
              riverName: '泽仁措'
            },
            {
              id: 36,
              riverName: '巴姆七湖'
            },
            {
              id: 37,
              riverName: '松措'
            },
            {
              id: 38,
              riverName: '查呈措'
            }
          ]
        }
      ],
      typeOptions: [
        {
          value: '水安全',
          label: '水安全'
        },
        {
          value: '水资源',
          label: '水资源'
        },
        {
          value: '水利工程',
          label: '水利工程'
        },
        {
          value: '灾害转移',
          label: '灾害转移'
        },
        {
          value: '太阳能提灌站',
          label: '太阳能提灌站'
        },
        {
          value: '采砂点位',
          label: '采砂点位'
        },
        {
          value: '涉河建筑物',
          label: '涉河建筑物'
        },
        {
          value: '水网管线',
          label: '水网管线'
        },
        {
          value: '灌区',
          label: '灌区'
        },
        {
          value: '拟建水库',
          label: '拟建水库'
        }
      ],
      watersafetyresult: [],
      waterresourcesresult: [],
      waterprojectsresult: [],
      disasterpreventionpointresult: [],
      solarirstationresult: [],
      sandsiteresult: [],
      riversidebuildingresult: [],
      waternetpiperesult: [],
      irrigationarearesult: [],
      proposalreservoirresult: []
    }
  },
  methods: {
    async searchWithRegion() {
      this.watersafetyresult= []
      this.waterresourcesresult= []
      this.waterprojectsresult= []
      this.disasterpreventionpointresult= []
      this.solarirstationresult= []
      this.sandsiteresult= []
      this.riversidebuildingresult= []
      this.waternetpiperesult= []
      this.irrigationarearesult= []
      this.proposalreservoirresult= []
      console.log('搜索开始')
      console.log(this.input3, this.select, this.typevalue)
      const type = this.typevalue //水利工程类型
      const region = this.select //行政区域
      // const value = this.input3 //具体内容  暂时不通过这个字段搜索，接口还没写出来

      if (type === '水安全') {
        const watersafetyresult = await axios({ url: `/api/generic/watersafety/byregion`, method: 'get', data: { region: region } })
        this.watersafetyresult = watersafetyresult.data
      } else if (type === '水资源') {
        const waterresourcesresult = await axios({ url: `/api/generic/waterresources/byregion`, method: 'get', data: { region: region } })
        this.waterresourcesresult = waterresourcesresult.data
      } else if (type === '水利工程') {
        const waterprojectsresult = await axios({ url: `/api/generic/waterproject/byregion`, method: 'get', data: { region: region } })
        this.waterprojectsresult = waterprojectsresult.data
      } else if (type === '灾害转移') {
        //还没考虑
        const disasterpreventionpointresult = await axios({ url: `/api/generic/disasterpoint/byaddress`, method: 'get', data: { address: region } })
        this.disasterpreventionpointresult = disasterpreventionpointresult.data
      } else if (type === '太阳能提灌站') {
        const solarirstationresult = await axios({ url: `/api/generic/solarirstation/byregion`, method: 'get', data: { region: region } })
        this.solarirstationresult = solarirstationresult.data
      } else if (type === '采砂点位') {
        const sandsiteresult = await axios({ url: `/api/generic/sandsite/byregion`, method: 'get', data: { region: region } })
        this.sandsiteresult = sandsiteresult.data
      } else if (type === '涉河建筑物') {
        const riversidebuildingresult = await axios({ url: `/api/generic/riversidebuilding/byregion`, method: 'get', data: { region: region } })
        this.riversidebuildingresult = riversidebuildingresult.data
      } else if (type === '水网管线') {
        const waternetpiperesult = await axios({ url: `/api/generic/waternetpipe/byregion`, method: 'get', data: { region: region } })
        this.waternetpiperesult = waternetpiperesult.data
      } else if (type === '灌区') {
        const irrigationarearesult = await axios({ url: `/api/generic/irrigationarea/byregion`, method: 'get', data: { region: region } })
        this.irrigationarearesult = irrigationarearesult.data
      } else if (type === '拟建水库') {
        const proposalreservoirresult = await axios({ url: `/api/generic/proposalreservoir/byregion`, method: 'get', data: { region: region } })
        this.proposalreservoirresult = proposalreservoirresult.data
      }
    },
    async shuixiInputChange(element) {
      if (element === '') {
        this.shuixiTableData = this.initialData
      } else {
        const riverInfoRes = await axios({ url: `/api/water-systems/by-keyword`, method: 'get', data: { keyword: element } }) //水系基本信息
        console.log(riverInfoRes.data)
        this.shuixiTableData = riverInfoRes.data
      }
    },
    async shuixiDataFetchAll() {
      const riverInfoRes = await axios({ url: `/api/water-systems`, method: 'get' }) //水系基本信息
      console.log(riverInfoRes.data)
      this.shuixiTableData = riverInfoRes.data
    },
    async shuixiCellClick(row) {
      console.log('点击这一行：', row)
      const watersystemsresult = await axios({ url: `/api/water-systems/by-keyword`, method: 'get', data: { keyword: row.riverName } }) //水系基本信息
      //要查询很多数据
      const solarirstationresult = await axios({ url: `/api/generic/solarirstation/byriver`, method: 'get', data: { river: row.riverName } })
      const sandsiteresult = await axios({ url: `/api/generic/sandsite/byriver`, method: 'get', data: { river: row.riverName } })
      const riversidebuildingresult = await axios({ url: `/api/generic/riversidebuilding/byriver`, method: 'get', data: { river: row.riverName } })
      const waternetpiperesult = await axios({ url: `/api/generic/waternetpipe/byriver`, method: 'get', data: { river: row.riverName } })
      const irrigationarearesult = await axios({ url: `/api/generic/irrigationarea/byriver`, method: 'get', data: { river: row.riverName } })
      const proposalreservoirresult = await axios({ url: `/api/generic/proposalreservoir/byriver`, method: 'get', data: { river: row.riverName } })
      const watersafetyresult = await axios({ url: `/api/generic/watersafety/byriver`, method: 'get', data: { river: row.riverName } })
      const waterresourcesresult = await axios({ url: `/api/generic/waterresources/byriver`, method: 'get', data: { river: row.riverName } })
      const waterprojectsresult = await axios({ url: `/api/water-projects`, method: 'get' })
      const shorelineplanningresult = await axios({ url: `/api/shoreline-planning/by-water-systems-id`, method: 'get', data: { waterSystemsId: row.id } })
      console.log('太阳能提灌站', solarirstationresult)
      bus.emit('shuixiCellClick', [
        watersystemsresult.data[0],
        solarirstationresult.data,
        sandsiteresult.data,
        riversidebuildingresult.data,
        waternetpiperesult.data,
        irrigationarearesult.data,
        proposalreservoirresult.data,
        watersafetyresult.data,
        waterresourcesresult.data,
        waterprojectsresult.data,
        shorelineplanningresult.data
      ])
    }
  },
  computed: {
    Search() {
      return Search
    }
  },
  props: {},
  mounted() {
    //加载时候把所有数据获取一遍
    // this.shuixiDataFetchAll()
    //设置一下
    this.shuixiTableData = this.initialData
  },
  setup(props) {
    const state = reactive({
      activeName: ref('shuiXi')
      // select: '',
      // input3: '',
      // typevalue: ''
    })
    const gotoDetail = (name, rowData) => {
      console.log('跳转到详情Tab')
      if (name === '水系') {
        bus.emit('loadMapData', [name, state.watersystemsresult])
      } else if (name === '水安全') {
        if (rowData.name.split('-')[4] === '堤防') {
          bus.emit('loadMapData', ['堤防', rowData])
        } else {
          bus.emit('loadMapData', ['护岸', rowData])
        }
      } else if (name === '水利工程') {
        if (rowData.type === '水电站') {
          bus.emit('loadMapData', ['水电站', rowData])
        } else {
          bus.emit('loadMapData', ['水文站', rowData])
        }
      } else {
        bus.emit('loadMapData', [name, rowData])
        console.log(rowData)
      }
    }
    const layerLocation = (name, rowData) => {
      bus.emit('layerLocation', [name, rowData])
    }

    return {
      ...toRefs(state),
      gotoDetail,
      layerLocation
    }
  }
}
</script>
<style scoped lang="less">
.search-tab > .el-tabs__content {
  /* padding: 5px; */
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
  // height: 80%;
  /* max-height: 820px; */
}
.search-tab {
  padding: 5%;
  overflow: auto;
}
.data-static {
  margin: 10px 0;
}
.data-table {
  height: 60%;
  overflow: auto;
}
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
// 背景色调制透明
/deep/.el-input--large .el-input__wrapper {
  background-color: rgba(255, 255, 255, 0.6);
}
/deep/.el-table tr,
/deep/.el-table td.el-table__cell,
/deep/.el-table th.el-table__cell.is-leaf {
  background-color: rgba(255, 255, 255, 0.3);
}
</style>