<template>
    <div>
      <el-dialog title="编辑申请" :visible.sync="editVisible">
        <el-form :model="editData" ref="editData">
          <el-form-item
            label="离职日期"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="editData.endTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item
            label="姓名"
            label-width="120px"
            prop="name"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.name"></el-input>
          </el-form-item>
          <el-form-item
            label="是否自动离职"
            label-width="120px"
            prop="isAutoEnd"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.isAutoEnd"></el-input>
          </el-form-item>
          <el-form-item
            label="学历"
            label-width="120px"
            prop="isDispute"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.isDispute"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editConcel">取 消</el-button>
          <el-button type="primary" @click="editRequirement">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="新增需求" :visible.sync="addRequirement">
        <el-form :model="addForm" ref="addForm">
          <el-form-item
            label="离职日期"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="addForm.endTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item
            label="姓名"
            label-width="120px"
            prop="name"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select @focus="selectNames" v-model="addForm.name" filterable placeholder="请选择">
              <el-option
                v-for="item in aNames"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="入职时间"
            label-width="120px"
            prop="interviewTime"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select @focus="selectJoinTime" v-model="addForm.joinTime" filterable placeholder="请选择">
              <el-option
                v-for="item in aJoinTimes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="是否自动离职"
            label-width="120px"
            prop="isAutoEnd"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.isAutoEnd" placeholder="请选择">
              <el-option
                v-for="item in aIsOfficials"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="是否有人事纠纷"
            label-width="120px"
            prop="isDispute"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.isDispute" placeholder="请选择">
              <el-option
                v-for="item in aIsOfficials"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="经济补偿金"
            label-width="120px"
            prop="compensation"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.compensation"></el-input>
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
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"><el-button @click="addRequirement = true" type="primary" plain round>新增需求</el-button></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="6"><div class="grid-content bg-purple">
              <div class="block">
                <el-date-picker
                  v-model="value1"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="离职日期搜索"
                  end-placeholder="离职日期搜索">
                </el-date-picker>
              </div>
            </div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"><el-input v-model="searchInput" placeholder="搜索名字"></el-input></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple">
              <el-button type="primary" round @click="getList">搜索</el-button>
            </div></el-col>
          </el-row>
        </el-header>
        <el-main>
          <el-table
            height="650px"
            stripe
            border
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
            type="selection"
            width="35">
          </el-table-column>
            <el-table-column
            type="index"
            width="35">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="110">
            </el-table-column>
            <el-table-column
              prop="post"
              label="岗位"
            width="140">
            </el-table-column>
            <el-table-column
              prop="department"
              label="部门"
            width="150">
            </el-table-column>
            <el-table-column
              prop="isAutoEnd"
              label="是否自动离职"
            width="80">
            </el-table-column>
            <el-table-column
              prop="isDispute"
              label="是否有人事纠纷"
            width="80">
            </el-table-column>
            <el-table-column
              prop="compensation"
              label="经济补偿金"
            width="100">
            </el-table-column>
            <el-table-column
              prop="endTime"
              label="离职时间"
              width="100">
            </el-table-column>
            <el-table-column
              label="操作"
              width="100"
              show-overflow-tooltip>
              <template slot-scope="scope">
                <el-button
                  size="medium"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
              aIsOfficials:[{
                  label:'是',
                  value:"是"
              },
                  {
                      label: "否",
                      value: "否"
                  }],
              aJoinTimes:'',
              total:"",
              size:"",
              currentPage:"1",
              addRequirement:false,
              editVisible:false,

              searchInput:'',
              editData:'',

              value1:'',
              tableData:[{

              }],
              addForm:{
                  post:"",nums:'',requirement:'', education:'',major:'',hopeTime:'',name:'',joinTime:''
                  ,isAutoEnd:'', isDispute:'', compensation:'',endTime:''
              },
              aNames:''
          }
        },
        methods: {
            selectJoinTime(){
                let _this = this;
                this.$http.get("http://localhost:9999/employee/getJoinTimes",{
                    params:{
                        name:_this.addForm.name
                    }
                }).then(res =>{
                    _this.aJoinTimes = res.data.data
                })
            },
            selectNames(){
                let _this = this;
                this.$http.get("http://localhost:9999/employee/getNames").then(res =>{
                    _this.aNames = res.data.data
                })
            },
            deletePatch(){
                this.$confirm('此操作将删除所选, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    this.$http.post("http://localhost:9999/requirement/deleteAll",this.multipleSelection).then(res =>{
                        if(res.data.code == 200){
                            _this.$message.success("删除成功");
                        }else {
                            _this.$message.error("删除失败")
                        }
                        _this.getList();
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            editConcel(){
                this.getList();
                this.$message("已取消");
                this.editVisible = false;
            },
            editRequirement(){
                let _this = this;
                this.$http.put("http://localhost:9999/end", this.editData).then(res =>{
                    if (res.data.code == 200){
                        _this.$message.success("编辑成功");
                        _this.editVisible = false;
                        _this.getList();
                    }else{
                        _this.$message.error("编辑失败");
                    }
                })
            },
            getList(){
                let _this = this;
                if (this.value1 == null){
                    this.value1 = ''
                }
                if (this.searchInput == null){
                    this.search = '';
                }
                this.$http.get("http://localhost:9999/end/findList",{
                    params:{
                        currentPage:_this.currentPage,
                        size:_this.size,
                        startTime:_this.value1[0],
                        endTime: _this.value1[1],
                        post:_this.searchInput
                    }
                }).then((res) =>{
                    _this.total = res.data.data.total
                    _this.tableData = res.data.data.records;
                })
            },
            add(){
                let _this = this;
                this.$http.post("http://localhost:9999/end",this.$qs.stringify(this.addForm)).then((res)=>{
                    if (res.data.code == 200){
                        _this.$message({
                            type:"success",
                            message:"增加成功"
                        });
                        this.addRequirement = false;
                        this.addForm.name = '';
                        this.addForm.joinTime = '';
                        this.addForm.isAutoEnd = '';
                        this.addForm.isDispute = '';
                        this.addForm.compensation = '';
                        _this.getList();
                    }else{
                        _this.$message({
                            type:'error',
                            message:"增加失败"
                        })
                    }
                })
            },
            addConcel(){
                this.$message("已取消");
                this.addRequirement = false;
                this.addForm.name = '';
                this.addForm.joinTime = '';
                this.addForm.isAutoEnd = '';
                this.addForm.isDispute = '';
                this.addForm.compensation = '';
            },
            handleSizeChange(val) {
                this.size = val;
                this.getList();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getList()
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
                this.editVisible = true;
                this.editData = row;
            },
            handleDelete(index, row) {
                this.$confirm('此操作将删除该申请, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    this.$http.delete("http://localhost:9999/requirement/"+row.id).then(res =>{
                        _this.$message({
                        type: 'success',
                        message: '删除成功!'
                        });
                        _this.getList();
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        created() {
            let _this = this;
            this.$http.get("http://localhost:9999/end/findList").then((res) =>{
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
    line-height: 50px;
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
