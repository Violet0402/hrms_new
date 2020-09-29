<template>
    <div>
      <el-dialog title="编辑申请" :visible.sync="editVisible">
        <el-form :model="editData" ref="editData">
          <el-form-item
            label="岗位"
            label-width="120px"
            prop="post"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.post"></el-input>
          </el-form-item>
          <el-form-item
            label="需求人数"
            label-width="120px"
            prop="nums"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.nums"></el-input>
          </el-form-item>
          <el-form-item
            label="岗位需求"
            label-width="120px"
            prop="requirement"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.requirement"></el-input>
          </el-form-item>
          <el-form-item
            label="学历"
            label-width="120px"
            prop="education"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.education"></el-input>
          </el-form-item>
          <el-form-item
            label="专业"
            label-width="120px"
            prop="major"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.major"></el-input>
          </el-form-item>
          <el-form-item
            label="希望到岗日期"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="editData.hopeTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
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
                v-model="addForm.hopeTime"
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
            <el-col :span="3"><div class="grid-content bg-purple"><el-button type="danger" plain round @click="deletePatch">批量删除</el-button></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"><el-button @click="addRequirement = true" type="primary" plain round>新增需求</el-button></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="6"><div class="grid-content bg-purple">
              <div class="block">
                <el-date-picker
                  v-model="value1"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="申请日期搜索"
                  end-placeholder="申请日期搜索">
                </el-date-picker>
              </div>
            </div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple"><el-input v-model="searchInput" placeholder="搜索岗位名称"></el-input></div></el-col>
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
              prop="interviewTime"
              label="面试日期"
              width="110">
            </el-table-column>
            <el-table-column
              prop="interviewee"
              label="面试者"
              width="140">
            </el-table-column>
            <el-table-column
              prop="gender"
              label="性别"
              width="85">
            </el-table-column>
            <el-table-column
              prop="age"
              label="年龄"
              width="160">
            </el-table-column>
            <el-table-column
              prop="post"
              label="面试岗位"
              width="50">
            </el-table-column>
            <el-table-column
              prop="department"
              label="部门"
              width="200">
            </el-table-column>
            <el-table-column
              prop="stage"
              label="阶段"
              width="55">
            </el-table-column>
            <el-table-column
              prop="result"
              label="结果"
              width="130">
            </el-table-column>
            <el-table-column
              prop="isTalent"
              label="是否纳入人才储备"
              width="110">
            </el-table-column>
            <el-table-column
              prop="conclusion"
              label="评价"
              width="110">
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
        name: "InterviewM",
        data() {
            return {
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
                    post:"",nums:'',requirement:'', education:'',major:'',hopeTime:''
                }
            }
        },
        methods: {
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
                this.$message("已取消");
                this.editVisible = false;
            },
            editRequirement(){
                let _this = this;
                this.$http.put("http://localhost:9999/requirement", this.editData).then(res =>{
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
                this.$http.get("http://localhost:9999/requirement/findList",{
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
                this.$http.post("http://localhost:9999/requirement",this.$qs.stringify(this.addForm)).then((res)=>{
                    if (res.data.code == 200){
                        _this.$message({
                            type:"success",
                            message:"增加成功"
                        });
                        this.addRequirement = false;
                        this.addForm.post = '';
                        this.addForm.nums = '';
                        this.addForm.requirement = '';
                        this.addForm.education = '';
                        this.addForm.major = '';
                        this.hopeTime = '';
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
                this.addForm.post = '';
                this.addForm.nums = '';
                this.addForm.requirement = '';
                this.addForm.education = '';
                this.addForm.major = '';
                this.hopeTime = '';
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
