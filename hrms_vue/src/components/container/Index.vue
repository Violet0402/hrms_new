<template>
    <div>
      <el-container>
        <el-header>
          <el-row>
            <el-col :span="14"><div class="grid-content bg-purple"><span style="font-size: 20px">年度入职/离职率统计图</span></div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple"><span style="float: right">搜索数据：</span></div></el-col>
            <el-col :span="5"><div class="grid-content bg-purple">
              <el-date-picker
                style="width: 300px"
              v-model="year"
              type="year"
                value-format="yyyy"
              placeholder="选择年">
            </el-date-picker></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"><el-button type="primary" round @click="getList">搜索</el-button></div></el-col>
          </el-row>
        </el-header>
        <el-main><div class="line" id="line" :style="{width: '1000px', height: '640px'}"></div></el-main>
      </el-container>

      <!--<el-button type="primary" round @click="getList">搜索</el-button>-->

    </div>
</template>

<script>
    export default {
        name: "Index",
        data(){
            return{
                year:'',
                category:['一月', '二月', '三月', '四月',"五月",'六月','七月','八月','九月','十月','十一月','十二月'],
                data:''
            }
        },
        created(){
            let _this = this;
            this.$http.get("http://localhost:9999/monitor/time").then(res =>{
                _this.year = res.data.data;
            })
        },
        mounted(){
            if (this.year == null){
                this.year = ''
            }
            let _this = this;
            this.$http.get("http://localhost:9999/monitor/data", {
                params:{
                    year:this.year
                }
            }).then(res =>{
               _this.data = res.data.data;
                this.drawLine(_this.category,_this.data);
            });
            // 调用方法绘制图表

        },
        methods:{
            getList(){
                let _this = this;
                if (this.year == null){
                  this.year = ''
                };
                this.$http.get("http://localhost:9999/monitor/data",{
                  params:{
                    year:this.year
                  }
                }).then(res =>{
                  _this.data = res.data.data;
                  this.drawLine(_this.category,_this.data);
                })
            },
            drawLine(category,data){

                // 为charts选定父容器并初始化charts画布
                let myChart = this.$echarts.init(document.getElementById('line'));

                app.config = {
                    rotate: 90,
                    align: 'left',
                    verticalAlign: 'middle',
                    position: 'insideBottom',
                    distance: 15,
                    onChange: function () {
                        var labelOption = {
                            normal: {
                                rotate: app.config.rotate,
                                align: app.config.align,
                                verticalAlign: app.config.verticalAlign,
                                position: app.config.position,
                                distance: app.config.distance
                            }
                        };
                        myChart.setOption({
                            series: [{
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }]
                        });
                    }
                };

                var labelOption = {
                    show: true,
                    position: app.config.position,
                    distance: app.config.distance,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    rotate: app.config.rotate,
                    formatter: '{c}  {name|{a}}',
                    fontSize: 16,
                    rich: {
                        name: {
                            textBorderColor: '#fff'
                        }
                    }
                };



                // 为图表添加数据
                myChart.setOption({
                    color: ['#003366', '#006699'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['入职率', '离职率']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: category/*['2012', '2013', '2014', '2015', '2016']*/
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '入职率',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            data: data[0].data
                        },
                        {
                            name: '离职率',
                            type: 'bar',
                            label: labelOption,
                            data: data[1].data
                        },
                    ]
                })
            }
        },
    }
</script>

<style scoped>
  .el-header, .el-footer {
    padding: 0 !important;
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 50px;
    height: 73vh;
  }

  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }

</style>
