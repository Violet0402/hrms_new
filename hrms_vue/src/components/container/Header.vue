<template>
    <div>
      <el-row>
        <el-col :span="5"><div>
          <el-image
            style="width: 200px; height: 60px"
            :src="require('../../assets/QQ图片20200927122122.jpg')"
            :fit="fit"></el-image>
        </div></el-col>
        <el-col :span="4" push="6">
            <div>
              <h1 style="color: #DCDFE6">人资资源管理后台</h1>
            </div>
        </el-col>
        <el-col :span="2" push="14"><div>
          <el-dropdown>
              <span class="el-dropdown-link">
                <div style="margin-top: 10px; margin-right: 10px">
                  <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
              </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div></el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data(){
            return{
                fit:"fill"
            }
        },
        methods:{
          logout(){
            console.log(this)
            window.localStorage.removeItem("token");
            this.$router.replace("/login");
          },
        },
    }
</script>

<style >

</style>
