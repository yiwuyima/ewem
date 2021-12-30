<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .anti_search {
    border-radius: 70px !important;
  }

</style>
<template>
  <div class="app-container home" style="background-color: #f2f6f9">
    <el-row :gutter="20">
      <el-carousel indicator-position="outside">
        <el-carousel-item v-for="item in productImgs" :key="item">
          <el-image :src="item" style="width: 100%; height: 100%"/>
        </el-carousel-item>
      </el-carousel>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-alert title="恭喜，您查询的是本公司的正品。" type="success" center show-icon :closable="false" effect="dark"/>
        <br>
        <div style="text-align: center;color: #129f5d;font-size: 17px">
          <span>本次为第<strong style="color: red">{{scanNum}}</strong>次查询</span><br>
          <span>首次查询时间：<strong>{{ parseTime(firstScanTime) }}</strong></span>
        </div>
      </el-col>
      <el-col :span="24">
        <br>
        <el-input placeholder="防伪码验证" class="anti_search" v-model="antiCode">
          <el-button slot="append" icon="el-icon-search" @click="antiCodeCheck"/>
          <br>
        </el-input>
        <br>
        <br>
        <el-alert title="验证成功" type="success" v-show="antiSuccess" show-icon center :closable="false"/>
        <el-alert title="验证失败" type="error" v-show="antiError" show-icon center :closable="false"/>
      </el-col>
    </el-row>
    <el-row>
      <el-tabs :stretch="true" v-model="activeName">
        <el-tab-pane label="产品详情" name="second">
          <el-descriptions class="margin-top" :column="1" border>
            <el-descriptions-item>
              <template slot="label">
                产品分类
              </template>
              {{categoryName}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                产品名称
              </template>
              {{productName}}
            </el-descriptions-item>
            <template v-for="(x,k) in productAttrs">
              <el-descriptions-item>
                <template slot="label">
                  {{x.k}}
                </template>
                {{x.v}}
              </el-descriptions-item>
            </template>
          </el-descriptions>

        </el-tab-pane>
        <el-tab-pane label="溯源信息" name="third">
          <div class="block">
            <el-timeline>
              <template v-for="(x,k) in links">
                <el-timeline-item placement="top" type="success">
                  <el-card>
                    <h3><strong>{{x.link.showName}}</strong></h3>
                    <template v-for="(a,b) in x.attrs">
                      <p>{{a.k}}：{{a.v}}</p>
                    </template>
                  </el-card>
                </el-timeline-item>
              </template>
            </el-timeline>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-row>
  </div>
</template>
<script>

  import { getTrace, antiCheck } from '@/api/ewem/trace'

  export default {
    name: 'Trace',
    data() {
      return {
        activeName: 'second',
        form: {},
        productName: undefined,
        categoryName: undefined,
        scanNum: undefined,
        antiCode: undefined,
        antiSuccess: false,
        antiError: false,
        firstScanTime: undefined,
        productAttrs: [],
        productImgs: [],
        links: []
      }
    },
    created() {
      const c = this.$route.query.c
      getTrace(c).then(response => {
        const code = response.code
        if (code === 200) {
          const data = response.data
          this.scanNum = data.scanNum
          this.firstScanTime = data.firstScanTime
          this.productName = data.product.name
          this.categoryName = data.product.category.name
          this.productAttrs = data.product.attrs
          this.links = data.links
          if (data.product.img !== '') {
            data.product.img.split(',').forEach((item, index) => {
              this.productImgs.push('http://localhost' + process.env.VUE_APP_BASE_API + item)
            })
          }
        }

      })
    },
    methods: {
      antiCodeCheck() {
        const c = this.$route.query.c
        const antiCode = this.antiCode
        if (antiCode == null || antiCode === '') {
          return
        }
        antiCheck(c, antiCode).then(res => {
          if (res.data) {
            this.antiSuccess= true
            this.antiError = false
            return
          }
          this.antiSuccess = false
          this.antiError = true
        })
      }
    }
  }
</script>

