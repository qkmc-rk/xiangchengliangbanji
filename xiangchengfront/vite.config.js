import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import viteCompression from 'vite-plugin-compression'
import CompressionPlugin from "compression-webpack-plugin"

// https://vitejs.dev/config/
export default defineConfig({
  cacheDir: 'node_modules/.vite-cache',
  publicPath:'./',
//在plugins配置数组里添加gzip插件
 plugins: [vue(),viteCompression({
  verbose: true,
  disable: false,
  threshold: 10240,
  algorithm: 'gzip',
  ext: '.gz',
})],
  define: {
    'process.env':{}
  },
  optimizeDeps: {
    include: ['axios'],
  },
  server: {
    host:'0.0.0.0',
    cors: true,
    open: true,
    port: 80,
    proxy: {
      '/api': {
        target: 'http://xiangcheng.ruankun.xyz:8081',   //代理接口
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')  // 重定向.
      }
    },
    //尝试解决页面自动刷新
    hmr: {
      clientPort: 80
    }
  },
  build: {
    terserOptions: {
      compress: {
        drop_console: true,
        drop_debugger: true,
      },
    },
    rollupOptions: {
      external: ['/api/captcha?type=math'],
      
      output: {
        chunkFileNames: 'static/js/[name]-[hash].js',
        entryFileNames: 'static/js/[name]-[hash].js',
        assetFileNames: 'static/[ext]/[name]-[hash].[ext]',
        manualChunks(id) {
          if (id.includes('node_modules')) {
            return id.toString().split('node_modules/')[1].split('/')[0].toString();
          }
        }
      }

    }
  },

  chainWebpack: (config) => {
    // 生产环境，开启js\css压缩
    if (process.env.NODE_ENV === 'production') {
      config.plugin('compressionPlugin').use(new CompressionPlugin({
        test: /\.(js|css|less|map)$/, // 匹配文件名
        threshold: 1024, // 对超过10k的数据压缩
        minRatio: 0.8,
      }))
    }
 
  },

})
