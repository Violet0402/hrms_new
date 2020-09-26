<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          prop="department"
          label="部门"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="申请人"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="需求岗位">
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
    export default {
        name: "JobRequire",
      data() {
        return {
          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
        }
      }
    }
</script>

<style scoped>

</style>
