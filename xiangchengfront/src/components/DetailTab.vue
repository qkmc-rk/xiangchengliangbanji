<template>
  <div class="detailTabContainer">
    <!-- <el-dialog v-model="dialogVisible" @close="restore"> -->
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane class="basicInfo" label="基本信息" name="basic">
        <div class="propsTable">
          <el-descriptions :border="true" :column="2" size="large">
            <el-descriptions-item label="名称">
              <div class="river-name">
                <span>{{ watersystemsresult.riverName }}</span>
                <div class="buttons">
                  <el-button type="primary" size="25" link @click="gotoDetail('水系')">详情</el-button>
                  <el-button type="primary" size="25" link @click="layerLocation('水系')">跳转</el-button>
                </div>

              </div>

            </el-descriptions-item>
            <el-descriptions-item label="类型">{{ watersystemsresult.waterType }}</el-descriptions-item>
            <el-descriptions-item label="代码">{{ watersystemsresult.surveyCode }}</el-descriptions-item>
            <el-descriptions-item label="长度">{{ watersystemsresult.totalLength }}</el-descriptions-item>
            <el-descriptions-item label="起始位置">{{ watersystemsresult.startLocation }}</el-descriptions-item>
            <el-descriptions-item label="结束位置">{{ watersystemsresult.endLocation }}</el-descriptions-item>
            <el-descriptions-item label="级别">{{ watersystemsresult.riverLevel }}</el-descriptions-item>
            <el-descriptions-item label="河口流量">{{ watersystemsresult.estuaryFlow }}</el-descriptions-item>
            <el-descriptions-item label="流域总水量">{{ watersystemsresult.basinvol }}</el-descriptions-item>
            <el-descriptions-item label="径流">{{ watersystemsresult.runoff }}</el-descriptions-item>
            <el-descriptions-item label="可用水量">{{ watersystemsresult.available }}</el-descriptions-item>
            <el-descriptions-item label="已用水量">{{ watersystemsresult.consum }}</el-descriptions-item>
            <el-descriptions-item label="健康评价"><img class="charts-img" src="http://xiangoos.ruankun.xyz/%E5%81%A5%E5%BA%B7%E8%AF%84%E4%BB%B7.jpg"></el-descriptions-item>
          </el-descriptions>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="太阳能提灌站" name="solarirstation">
        <div class="propsTable">
          <!--solarirstationresult-->
          <el-table :data="solarirstationresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="comment" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('太阳能提灌站', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('太阳能提灌站', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="采砂" name="sandsite">
        <div class="propsTable">
          <el-table :data="sandsiteresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="intro" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('采砂点位', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('采砂点位', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="涉河建筑" name="riversidebuilding">
        <div class="propsTable">
          <el-table :data="riversidebuildingresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="type" label="类别" />
            <el-table-column prop="intro" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('涉河建筑物', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('涉河建筑物', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水网管线" name="waternetpipe">
        <div class="propsTable">
          <el-table :data="waternetpiperesult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="comment" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('水网管线', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('水网管线', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="灌区" name="irrigationarea">
        <div class="propsTable">
          <el-table :data="irrigationarearesult" style="width: 100%" :empty-text="'暂无数据'">
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
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="拟建水库" name="proposalreservoir">
        <div class="propsTable">
          <el-table :data="proposalreservoirresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="O_Comment" label="内容" />
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
      <el-tab-pane class="basicInfo" label="水安全" name="watersafety">
        <div class="propsTable">
          <el-table :data="watersafetyresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="type" label="类型" />
            <el-table-column prop="address" label="地址" />
            <el-table-column prop="length" label="长度" />
            <el-table-column prop="construction_year" label="建设时间" />
            <el-table-column prop="responsible_department" label="负责单位" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="height" label="高度" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />4 <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('水安全', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('水安全', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水资源" name="waterresources">
        <div class="propsTable">
          <el-table :data="waterresourcesresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="resourceType" label="类型" />
            <el-table-column prop="resourceVolume" label="总量" />
            <el-table-column prop="resourceUsage" label="用量" />
            <el-table-column prop="resourceQuality" label="质量" />
            <el-table-column prop="resourceStatus" label="状态" />
            <el-table-column prop="extractionRate" label="利用率" />
            <el-table-column prop="dataYear" label="年份" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('水资源', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('水资源', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水利工程" name="waterprojects">
        <div class="propsTable">
          <el-table :data="waterprojectsresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="type" label="类型" />
            <el-table-column prop="capacity" label="容量" />
            <el-table-column prop="status" label="状态" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('水利工程', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('水利工程', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <!--      <el-tab-pane class="basicInfo" label="健康评价" name="healthevaluation">healthevaluation</el-tab-pane> 放在基本信息中-->
      <!--      <el-tab-pane class="basicInfo" label="移民搬迁" name="relocation">relocation</el-tab-pane> 暂时没有数据-->
      <!--      <el-tab-pane class="basicInfo" label="河湖划界" name="riverlakeboudary">-->
      <!--        <div class="propsTable">-->
      <!--        </div>-->
      <!--      </el-tab-pane>-->
      <el-tab-pane class="basicInfo" label="岸线规划" name="shorelineplanning">
        <div class="propsTable">
          <el-table :data="shorelineplanningresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="planningName" label="名称" />
            <el-table-column prop="shorelineType" label="类型" />
            <el-table-column prop="planningLength" label="长度" />
            <!--            <el-table-column prop="planningDescription" label="描述"/>-->
            <el-table-column prop="approvalStatus" label="是否审批" />
            <el-table-column fixed="right" label="更多">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="gotoDetail('岸线规划', scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="layerLocation('岸线规划', scope.row)">跳转</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- </el-dialog> -->
  </div>

</template>
<script>
import { reactive, toRefs, ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import bus from '../utils/bus.js'
import GradientStackedAreaChart from './GradientStackedAreaChart.vue'

export default {
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
  components: { GradientStackedAreaChart },
  data() {
    return {}
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  },
  computed: {
    Search() {
      return Search
    }
  },
  mounted() {},
  setup(props) {
    const state = reactive({
      watersystemsresult: [],
      solarirstationresult: [],
      sandsiteresult: [],
      riversidebuildingresult: [],
      waternetpiperesult: [],
      irrigationarearesult: [],
      proposalreservoirresult: [],
      watersafetyresult: [],
      waterresourcesresult: [],
      waterprojectsresult: [],
      shorelineplanningresult: [],
      alldataresults: [],
      activeName: ref('basic'),
      dialogVisible: false
    })
    bus.on('shuixiCellClick', result => {
      //watersystemsresult, solarirstationresult,sandsiteresult, riversidebuildingresult, waternetpiperesult, irrigationarearesult, proposalreservoirresult, watersafetyresult, waterresourcesresult, shorelineplanningresult
      console.log('所有的数据都传过来了：', result)
      state.alldataresults = result

      state.watersystemsresult = result[0]

      state.solarirstationresult = result[1]

      state.sandsiteresult = result[2]
      state.riversidebuildingresult = result[3]
      state.waternetpiperesult = result[4]
      state.irrigationarearesult = result[5]
      state.proposalreservoirresult = result[6]
      state.watersafetyresult = result[7]
      state.waterresourcesresult = result[8]
      state.waterprojectsresult = result[9]
      state.shorelineplanningresult = result[10]

      state.dialogVisible = true
    })
    const restore = () => {
      // 重置Tab当前所在信息栏
      state.activeName = 'basic'
    }
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
      console.log('定位')
      if (name === '水系') {
        bus.emit('layerLocation', [name, state.watersystemsresult])
      } else if (name === '水安全') {
        if (rowData.type === '堤防') {
          bus.emit('layerLocation', ['堤防', rowData])
        } else {
          //如果是护岸的话extraJson是ID
          rowData.id = Number(rowData.extraJson)
          bus.emit('layerLocation', ['护岸', rowData])
        }
      } else if (name === '水利工程') {
        if (rowData.type === '水电站') {
          bus.emit('layerLocation', ['水电站', rowData])
        } else {
          //将水文站的id存到extraJson中
          rowData.id = Number(rowData.extraJson)
          bus.emit('layerLocation', ['水文站', rowData])
        }
      } else {
        bus.emit('layerLocation', [name, rowData])
        console.log(rowData)
      }
    }
    return {
      ...toRefs(state),
      restore,
      gotoDetail,
      layerLocation
    }
  }
}
</script>
<style lang="less" scoped>
.detailTabContainer {
  // overflow: auto;
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  padding: 15px;
  height: 100%;
}
.charts {
  width: 100%;
}
.charts-img {
  width: 100%;
}
.basicInfo {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.el-tabs__content {
  height: 80%;
}
.propsTable {
  width: 100%;
  height: 400px;
  overflow: auto;
  // margin-top: 50px;
}

// 背景色调制透明
/deep/.el-descriptions__body {
  background-color: rgba(255, 255, 255, 0.3);
}
/deep/.el-descriptions__label.el-descriptions__cell.is-bordered-label {
  background-color: rgba(255, 255, 255, 0);
}

//设置table透明
.propsTable /deep/ .el-table,
.el-table__expanded-cell {
  background-color: transparent;
}
.propsTable /deep/ .el-table tr {
  background-color: transparent !important;
}
.propsTable /deep/ .el-table--enable-row-transition .el-table__body td,
.el-table .cell {
  background-color: transparent;
}
.el-table::before {
  //去除底部白线
  left: 0;
  bottom: 0;
  width: 100%;
  height: 0px;
}
.river-name {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.el-button + .el-button {
  margin: 0;
}
// .el-table__footer-wrapper,
// /deep/.el-table__header-wrapper {
//   position: fixed;
//   z-index: 10;
// }
// /deep/.el-table__body-wrapper {
//   top: 50px;
//   bottom: 50px;
// }
</style>