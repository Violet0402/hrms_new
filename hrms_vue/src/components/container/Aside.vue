<template>
    <div>
      <el-row class="tac">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">

            <el-menu-item :index="(index+1+'')" @click="change(index+1)" v-for="(item,index) in menuList">
              <i class="el-icon-menu"></i>
              <span slot="title">{{item.menuName}}</span>
            </el-menu-item>
          </el-menu>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        methods: {
          change(index){
              this.$router.push("/home/"+index);
          }
        },
        data(){
            return{
                menuList:[{id:"", menuName:""}],
            }
        },
        created() {
            let _this = this;
            this.$http.get("http://localhost:9999/menu/findAll").then((res) =>{
                _this.menuList = res.data.data;
            })
        }
    }
</script>

<style scoped>

</style>
