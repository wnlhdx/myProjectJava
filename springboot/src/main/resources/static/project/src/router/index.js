import {createRouter,createWebHistory} from 'vue-router' 
import login from '../components/login.vue'
import user from '../components/user.vue'


const routes=[
  {path: '/login', component:login},
  {path: '/user/:username', component:user},
  {path:'/:pathMatch(.*)',redirect:'/login'}
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router