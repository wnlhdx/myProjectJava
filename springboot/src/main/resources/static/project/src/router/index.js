import {createRouter,createWebHistory} from 'vue-router' 
import login from '../components/index.vue'
import user from '../components/user.vue'


const routes=[
  {path: '/index', component:index},
  {path: '/user/:username', component:user},
  {path:'/:pathMatch(.*)',redirect:'/index'}
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router