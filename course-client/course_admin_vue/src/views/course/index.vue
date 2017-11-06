<template>
  <div class="app-container">
    <el-tag type="primary">mounted times ：{{createdTimes}}</el-tag>
    <el-tabs style='margin-top:15px;' v-model="activeName" type="border-card" @tab-click="test">
      <el-tab-pane label="课程管理" key='courseList' name="courseList">
        <keep-alive>
          <course-list v-if="activeName == 'courseList'" @create='showCreatedTimes'></course-list>
        </keep-alive>
      </el-tab-pane>
      <el-tab-pane label="课程推荐" key='courseRecommend' name="courseRecommend">
        <keep-alive>
          <course-recommend v-if="activeName == 'courseRecommend'" @create='showCreatedTimes'></course-recommend>
        </keep-alive>
      </el-tab-pane>
      <el-tab-pane label="课程统计" key='courseStat' name="courseStat">
        <keep-alive>
          <course-stat v-if="activeName == 'courseStat'" @create='showCreatedTimes'></course-stat>
        </keep-alive>
      </el-tab-pane>
      <el-tab-pane label="图表分析" key='courseChart' name="courseChart">
        <keep-alive>
          <course-chart v-if="activeName == 'courseChart'" @create='showCreatedTimes'></course-chart>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>

  </div>

</template>

<script>
  import courseList from './courseList'
  import courseRecommend from './courseRecommend'
  import courseStat from './courseStat'
  import courseChart from './courseChart'

  export default {
    components: { courseList, courseRecommend, courseStat, courseChart },
    data() {
      return {
        activeName: 'courseList',
        createdTimes: 0
      }
    },
    methods: {
      showCreatedTimes() {
        this.createdTimes = this.createdTimes + 1
      },
      test(e) {
        console.log(this.createdTimes)
      }
    }
  }
</script>
<style>

</style>
