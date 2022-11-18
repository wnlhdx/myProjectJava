<template>
  <el-row gutter="32">
    <el-col :span="8"></el-col>
    <el-col :span="16">
      <h1>
        您的用户名为 <span @click="Change">{{ user.name }} </span>
      </h1>
      <el-button type="primary" @click="ShowPass">修改密码</el-button>
      <el-form label-width="120px" v-if="show">
        <el-form-item label="新密码">
          <el-input v-model="user.password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="Change">修改密码</el-button>
          <el-button type="primary" @click="Delete">删除用户</el-button>
          <el-button @click="Login">注销</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="8"></el-col>
  </el-row>
</template>
<script>
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
  name: 'userinfo',
  data() {
    return {
      user: {
        name: '',
        password: '',
      },
      show: false,
    }
  },
  methods: {
    Login() {
      this.$router.push('/login')
    },
    ShowPass() {
      this.show = !this.show
    },
    Change() {
      axios
        .post('http://localhost:8888/SetPass', this.FormUser())
        .then((response) => {
          if (response.data == '设置成功') {
            ElMessageBox.alert(response.data, '密码修改成功')
            this.$router.push('/login')
          } else {
            ElMessageBox.alert(response.data, '密码修改失败')
          }
        })
    },
    Delete() {
      axios
        .post('http://localhost:8888/DelUser', this.FormUser())
        .then((response) => {
          if (response.data == '删除成功') {
            ElMessageBox.alert(response.data, '删除成功')
            this.$router.push('/login')
          } else {
            ElMessageBox.alert(response.data, '删除失败')
          }
        })
    },
    FormUser() {
      let userdata = new FormData()
      userdata.append('username', this.user.name)
      userdata.append('password', this.user.password)
      return userdata
    },
  },
  created() {
    this.user.name = this.$route.params.username
  },
}
</script>
