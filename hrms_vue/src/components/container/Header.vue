<template>
    <div>
      <el-row>
        <el-col :span="4"><div>
          <el-image
            style="width: 200px; height: 60px"
            :src="require('../../assets/AEB12CB42E9D5727296460A3A3A5B5B5.jpg')"
            :fit="fit"></el-image>
        </div></el-col>
        <el-col :span="2" push="18"><div>
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
            window.localStorage.clear();
            this.$router.push("/login");
          },
        },
    }
</script>

<style scoped>

</style>
