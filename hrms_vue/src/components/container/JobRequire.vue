<template>
    <div>
      <el-container>
        <el-header>Header</el-header>
        <el-main>
          <div>
            <el-table class="tableBox" border :data="tableData"><!-- :data="tableData" border style="width: 100%" ref="multipleTable" @select-change="handleSelectionChange"-->
              <el-table-column type="selection"  width="55"></el-table-column>
              <el-table-column prop="department" label="部门" width="120"></el-table-column>
              <el-table-column prop="proposer" label="申请人" width="120"></el-table-column>
            </el-table>
          </div>
        </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </div>
</template>

<script>
    export default {
        name: "JobRequire",
      data() {
        return {
          tableData:[],
        }
      }
    }
</script>

<style scoped>
  .el-header,footer{
    background-color: #DCDFE6;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 160px;
    height: 73vh;
  }
</style>
