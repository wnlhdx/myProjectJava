import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: './',
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 3333,
    open: true,
    host: '0.0.0.0',
    proxy: {
       "/": {
         target: "http://localhost:8888",
         changeOrigin: true,
         rewrite: (path) => path.replace(/^\/api/, ""),
       },
    }
  }
})

