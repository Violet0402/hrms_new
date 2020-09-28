<template>
    <div>
      <el-dialog title="新增需求" :visible.sync="addRequirement">
        <el-form :model="addForm" ref="addForm">
          <el-form-item
            label="岗位"
          label-width="120px"
          prop="post"
          :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.post"></el-input>
          </el-form-item>
          <el-form-item
            label="需求人数"
            label-width="120px"
            prop="nums"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.nums"></el-input>
          </el-form-item>
          <el-form-item
            label="岗位需求"
            label-width="120px"
          prop="requirement"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.requirement"></el-input>
          </el-form-item>
          <el-form-item
            label="学历"
            label-width="120px"
          prop="education"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.education"></el-input>
          </el-form-item>
          <el-form-item
            label="专业"
            label-width="120px"
          prop="major"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.major"></el-input>
          </el-form-item>
          <el-form-item
            label="希望到岗日期"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="addForm.homeTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addConcel">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>

      <el-container>
        <el-header>
          <el-row>
            <el-col :span="3"><div class="grid-content bg-purple"><el-button type="danger" plain round>批量删除</el-button></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple-light"><el-button @click="addRequirement = true" type="primary" plain round>新增需求</el-button></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple-light"></div></el-col>
          </el-row>
        </el-header>
        <el-main>
          <el-table
            stripe
            border
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
            type="index"
            width="50">
            </el-table-column>
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column
              prop="department"
              label="部门"
            width="150">
            </el-table-column>
            <el-table-column
              prop="proposer"
              label="申请人"
            width="100">
            </el-table-column>
            <el-table-column
              prop="post"
              label="岗位"
            width="200">
            </el-table-column>
            <el-table-column
              prop="nums"
              label="需求人数"
            width="50">
            </el-table-column>
            <el-table-column
              prop="requirement"
              label="岗位需求"
            width="200">
            </el-table-column>
            <el-table-column
              prop="education"
              label="学历"
            width="100">
            </el-table-column>
            <el-table-column
              prop="major"
              label="专业"
            width="150">
            </el-table-column>
            <el-table-column
              prop="hopeTime"
              label="希望到岗日期"
            width="150">
            </el-table-column>
            <el-table-column
              label="操作"
              width="200"
              show-overflow-tooltip>
              <template slot-scope="scope">
                <el-button
                  size="medium"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                  size="medium"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
        <el-footer>
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="100"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
            </el-pagination>
          </div>
        </el-footer>
      </el-container>
    </div>
</template>

<script>
    export default {
        name: "JobRequire",
        data() {
          return {
              total:"",
              size:"",
              currentPage:"1",
              addRequirement:false,
              tableData:[{

              }],
              addForm:{
                  post:"",nums:'',requirement:'', education:'',major:'',homeTime:''
              }
          }
        },
        methods: {
            getList(){
                let _this = this;
                this.$http.get("http://localhost:9999/requirement/findList").then((res) =>{
                    _this.total = res.data.data.total
                    _this.tableData = res.data.data.records;
                })
            },
            add(){
                let _this = this;
                this.$http.post("http://localhost:9999/requirement",this.$qs.stringify(this.addForm)).then((res)=>{
                    _this.addRequirement = false;
                    _this.getList();
                })
            },
            addConcel(){
                  this.addRequirement = false;
                  this.addForm.post = '';
                this.addForm.nums = '';
                this.addForm.requirement = '';
                this.addForm.education = '';
                this.addForm.major = '';
                this.homeTime = '';
                console.log(this.addForm)
            },
            handleSizeChange(val) {
                this.size = val;
                let _this = this;
                this.$http.get("http://localhost:9999/requirement/findList",{
                    params:{
                        size:_this.size
                    }
                }).then((res) =>{
                    _this.total = res.data.data.total
                    _this.tableData = res.data.data.records;
                });
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                let _this = this;
                this.$http.get("http://localhost:9999/requirement/findList",{
                    params:{
                        currentPage:this.currentPage
                    }
                }).then((res) =>{
                    _this.total = res.data.data.total
                    _this.tableData = res.data.data.records;
                })
            },
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
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            }
        },
        created() {
            let _this = this;
            this.$http.get("http://localhost:9999/requirement/findList").then((res) =>{
                _this.total = res.data.data.total
                _this.tableData = res.data.data.records;
            })
        }
    }
</script>

<style scoped>
  .el-header,footer{
    padding: 0 !important;
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

  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
