<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="文章总量" prop="articleNum">
      <el-input v-model="dataForm.articleNum" placeholder="文章总量"></el-input>
    </el-form-item>
    <el-form-item label="分类总量" prop="bigsubjectNum">
      <el-input v-model="dataForm.bigsubjectNum" placeholder="分类总量"></el-input>
    </el-form-item>
    <el-form-item label="标签总量(导航项)" prop="labeNum">
      <el-input v-model="dataForm.labeNum" placeholder="标签总量(导航项)"></el-input>
    </el-form-item>
    <el-form-item label="总访问量" prop="accessNum">
      <el-input v-model="dataForm.accessNum" placeholder="总访问量"></el-input>
    </el-form-item>
    <el-form-item label="建站时间" prop="buildSiteTime">
      <el-input v-model="dataForm.buildSiteTime" placeholder="建站时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          articleNum: '',
          bigsubjectNum: '',
          labeNum: '',
          accessNum: '',
          buildSiteTime: '',
          updateTime: ''
        },
        dataRule: {
          articleNum: [
            { required: true, message: '文章总量不能为空', trigger: 'blur' }
          ],
          bigsubjectNum: [
            { required: true, message: '分类总量不能为空', trigger: 'blur' }
          ],
          labeNum: [
            { required: true, message: '标签总量(导航项)不能为空', trigger: 'blur' }
          ],
          accessNum: [
            { required: true, message: '总访问量不能为空', trigger: 'blur' }
          ],
          buildSiteTime: [
            { required: true, message: '建站时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/blog/siteinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleNum = data.siteInfo.articleNum
                this.dataForm.bigsubjectNum = data.siteInfo.bigsubjectNum
                this.dataForm.labeNum = data.siteInfo.labeNum
                this.dataForm.accessNum = data.siteInfo.accessNum
                this.dataForm.buildSiteTime = data.siteInfo.buildSiteTime
                this.dataForm.updateTime = data.siteInfo.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/blog/siteinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'articleNum': this.dataForm.articleNum,
                'bigsubjectNum': this.dataForm.bigsubjectNum,
                'labeNum': this.dataForm.labeNum,
                'accessNum': this.dataForm.accessNum,
                'buildSiteTime': this.dataForm.buildSiteTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
