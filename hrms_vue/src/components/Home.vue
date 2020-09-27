<template>
    <div>
      <el-container>
        <el-header><Header></Header></el-header>
        <el-container>
          <el-aside width="200px"><Aside></Aside></el-aside>
          <el-main><router-view></router-view></el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
  import Header from "./container/Header";
  import Aside from "./container/Aside";
    export default {
        name: "Home",
        components: {Header, Aside},
        data() {

        }
    }
</script>

<style scoped>
  .el-header {
    background-color: #545c64;
    color: #333;
    line-height: 23px;
    padding: 0;
  }

  .el-aside {
    background-color: #545c64;
    color: #333;
    text-align: center;
    line-height: 200px;
    height:90vh;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
</style>
