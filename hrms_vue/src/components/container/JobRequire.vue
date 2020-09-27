<template>
    <div>
      <el-container>
        <el-header>Header</el-header>
        <el-main>
          <el-table
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column
              label="日期"
              width="120">
              <template slot-scope="scope">{{ scope.row.date }}</template>
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="address"
              label="地址"
              show-overflow-tooltip>
            </el-table-column>
          </el-table>
          <!--<el-table  border :data="tableData">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="department" label="部门"></el-table-column>
            <el-table-column prop="proposer" label="申请人"></el-table-column>
            <el-table-column prop="post" label="岗位"></el-table-column>
            <el-table-column prop="nums" label="需求人数"></el-table-column>
            <el-table-column prop="requirement" label="岗位需求"></el-table-column>
            <el-table-column prop="education" label="学历"></el-table-column>
            <el-table-column prop="major" label="专业"></el-table-column>
            <el-table-column prop="date" label="希望到岗时间" type=""></el-table-column>
            <el-table-column label="操作" width="180">

            </el-table-column>
          </el-table>-->
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
        },
        methods: {
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
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
