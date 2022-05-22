// const {defineConfig} = require('@vue/cli-service');

module.exports = {
    lintOnSave: false, // 关闭eslint的语法检查
    devServer:{
        // 关闭eslint语法检查
        overlay:{
            warning:false,
            errors:false
        }
    },
}
