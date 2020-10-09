<template>
    <div>
      <el-dialog title="编辑申请" :visible.sync="editVisible">
        <el-form :model="editData" ref="editData">
          <el-form-item
            label="面试时间"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="editData.interviewTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item
            label="名字"
            label-width="120px"
            prop="name"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.name"></el-input>
          </el-form-item>
          <el-form-item
            label="性别"
            label-width="120px"
            prop="gender"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="editData.gender" placeholder="请选择">
              <el-option
                v-for="item in genders"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="年龄"
            label-width="120px"
            prop="age"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.age"></el-input>
          </el-form-item>
          <el-form-item
            label="学历"
            label-width="120px"
            prop="education"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="editData.education" placeholder="请选择">
              <el-option
                v-for="item in educations"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
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
            label="面试岗位"
            label-width="120px"
            prop="job"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select @focus="selectJob" v-model="editData.job" filterable placeholder="请选择">
              <el-option
                v-for="item in jobs"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="阶段"
            label-width="120px"
            prop="stage"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="editData.stage" placeholder="请选择">
              <el-option
                v-for="item in stages"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="结果"
            label-width="120px"
            prop="result"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.result"></el-input>
          </el-form-item>
          <el-form-item
            label="是否纳入人才储备库"
            label-width="120px"
            prop="isStore"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="editData.isStore" placeholder="请选择">
              <el-option
                v-for="item in isStores"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="评价"
            label-width="120px"
            prop="assess"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="editData.assess"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editConcel">取 消</el-button>
          <el-button type="primary" @click="editRequirement">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="新增管理" :visible.sync="addRequirement">
        <el-form :model="addForm" ref="addForm">
          <el-form-item
            label="面试时间"
            label-width="120px">
            <div class="block">
              <el-date-picker
                v-model="addForm.interviewTime"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item
            label="名字"
            label-width="120px"
            prop="name"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
          <el-form-item
            label="性别"
            label-width="120px"
            prop="gender"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.gender" placeholder="请选择">
              <el-option
                v-for="item in genders"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="年龄"
            label-width="120px"
            prop="age"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.age"></el-input>
          </el-form-item>
          <el-form-item
            label="学历"
            label-width="120px"
            prop="education"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.education" placeholder="请选择">
              <el-option
                v-for="item in educations"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
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
            label="面试岗位"
            label-width="120px"
            prop="job"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select @focus="selectJob" v-model="addForm.job" filterable placeholder="请选择">
              <el-option
                v-for="item in jobs"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="阶段"
            label-width="120px"
            prop="stage"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.stage" placeholder="请选择">
              <el-option
                v-for="item in stages"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="结果"
            label-width="120px"
            prop="result"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.result"></el-input>
          </el-form-item>
          <el-form-item
            label="是否纳入人才储备库"
            label-width="120px"
            prop="isStore"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-select v-model="addForm.isStore" placeholder="请选择">
              <el-option
                v-for="item in isStores"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="评价"
            label-width="120px"
            prop="assess"
            :rules="[
            {required:true, message:'请输入内容', trigger:'blur'}
          ]">
            <el-input v-model="addForm.assess"></el-input>
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
            <el-col :span="3"><div class="grid-content bg-purple"><el-button @click="addRequirement = true" type="primary" plain round>新增管理</el-button></div></el-col>
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
            <el-col :span="3"><div class="grid-content bg-purple"><el-input v-model="searchInput" placeholder="搜索面试者"></el-input></div></el-col>
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
              prop="name"
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
              prop="education"
              label="学历"
              width="70">
            </el-table-column>
            <el-table-column
              prop="major"
              label="专业"
              width="160">
            </el-table-column>
            <el-table-column
              prop="job"
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
              prop="isStore"
              label="是否纳入人才储备"
              width="110">
            </el-table-column>
            <el-table-column
              prop="assess"
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
                isStores:[{
                    label:'是',
                    value:'是'
                },
                    {
                        label: '否',
                        value: '否'
                    }],
                stages:[{
                    label:'初试',
                    value:'初试'
                },
                    {
                        label: '复试',
                        value: '复试'
                    }],
                jobs:'',
                educations:[{
                    label:"大专以下",
                    value:"大专以下"
                },
                    {
                        label:"专科",
                        value:"专科"
                    },
                    {
                        label:"本科",
                        value:"本科"
                    },
                    {
                        label:"硕士",
                        value:"硕士"
                    },
                    {
                        label:"博士",
                        value:"博士"
                    }],
                genders:[{
                   label:'男',
                   value:'男'
                  },
                    {
                        label: '女',
                        value: '女'
                    }
                ],
                select:'',
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
                    interviewTime:'', name:'',gender:'',age:'',education:'',major:'',job:'',department:'',stage:'',result:'',isStore:'',assess:''
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
                    this.$http.post("http://localhost:9999/interview/deleteAll",this.multipleSelection).then(res =>{
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
                this.$http.put("http://localhost:9999/interview", this.editData).then(res =>{
                    if (res.data.code == 200){
                        _this.$message.success("编辑成功");
                        _this.editVisible = false;
                        _this.getList();
                    }else{
                        _this.$message.error("编辑失败");
                    }
                })
            },
            selectJob(){
                let _this = this;
                console.log(this.jobs)
                this.$http.get("http://localhost:9999/interview/getJob").then(res =>{
                    _this.jobs = res.data.data
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
                this.$http.get("http://localhost:9999/interview/findList",{
                    params:{
                        currentPage:_this.currentPage,
                        size:_this.size,
                        startTime:_this.value1[0],
                        endTime: _this.value1[1],
                        name:_this.searchInput
                    }
                }).then((res) =>{
                    _this.total = res.data.data.total
                    _this.tableData = res.data.data.records;
                })
            },
            add(){
                let _this = this;
                this.$http.post("http://localhost:9999/interview",this.$qs.stringify(this.addForm)).then((res)=>{
                    if (res.data.code == 200){
                        _this.$message({
                            type:"success",
                            message:"增加成功"
                        });
                        this.addRequirement = false;
                        this.addForm.interviewTime='';
                        this.addForm.name='';
                        this.addForm.gender='';
                        this.addForm.age='';
                        this.addForm.education='';
                        this.addForm.major='';
                        this.addForm.job='';
                        this.addForm.department='';
                        this.addForm.stage='';
                        this.addForm.result='';
                        this.addForm.isStore='';
                        this.addForm.assess='';
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
                /*addForm:{
                    interviewTime:'', name:'',gender:'',age:'',education:'',major:'',
                    job:'',department:'',stage:'',result:'',isStore:'',assess:''
                }*/
                this.addForm.interviewTime='';
                this.addForm.name='';
                this.addForm.gender='';
                this.addForm.age='';
                this.addForm.education='';
                this.addForm.major='';
                this.addForm.jobs='';
                this.addForm.department='';
                this.addForm.stage='';
                this.addForm.result='';
                this.addForm.isStore='';
                this.addForm.assess='';
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
                console.log(this.editData)
            },
            handleDelete(index, row) {
                this.$confirm('此操作将删除该申请, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    this.$http.delete("http://localhost:9999/interview/"+row.id).then(res =>{
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
            this.$http.get("http://localhost:9999/interview/findList").then((res) =>{
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
