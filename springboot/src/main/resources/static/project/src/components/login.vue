<template>
  <el-row gutter="32">
    <el-col :span="8"></el-col>
    <el-col :span="16">
      <el-form label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="user.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="Login">登陆</el-button>
          <el-button type="primary" @click="Register">注册</el-button>
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
    }
  },
  methods: {
    Login() {
      axios
        .post('http://localhost:8888/Login', this.FormUser())
        .then((response) => {
          if (response.data == this.user.name) {
            this.$router.push('/user/' + this.user.name)
          } else {
            ElMessageBox.alert(response.data, '登陆失败')
          }
        })
    },
    Register() {
      axios
        .post('http://localhost:8888/AddUser', this.FormUser())
        .then(function (response) {
          if (response.data == '注册成功') {
            ElMessageBox.alert(response.data, '注册成功')
            this.$router.push('/login')
          } else {
            ElMessageBox.alert(response.data, '注册失败')
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
}
</script>
