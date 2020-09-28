<template>
    <div>
      <el-container>
        <el-header><a href="javascript:;" @click="test">Header</a> </el-header>
        <el-main>
          <el-table
            border
            stripe
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
            prop="department"
            label="部门"
            width="100">
            </el-table-column>
            <el-table-column
              prop="proposer"
              label="申请人"
            width="100">
            </el-table-column>
            <el-table-column
              prop="post"
              label="岗位">
            </el-table-column>
            <el-table-column
              prop="nums"
              label="需求人数"
            width="55">
            </el-table-column>
            <el-table-column
              prop="requirement"
              label="岗位需求">
            </el-table-column>
            <el-table-column
            prop="education"
            label="学历"
            width="100">
            </el-table-column>
            <el-table-column
            prop="major"
            label="专业">
            </el-table-column>
            <el-table-column
              label="希望到岗时间"
              prop="date"
              show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.date }}</template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
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
            tableData:[

            ],
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
          test(){
              console.log(this.multipleSelection[0].department)
          },
            handleEdit(index, row) {
              console.log(index, row);
            },
            handleDelete(index, row) {
              console.log(index, row);
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
