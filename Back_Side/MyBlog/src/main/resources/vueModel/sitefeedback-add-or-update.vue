<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="反馈类型(0：bug修复  1：需求增添)" prop="type">
      <el-input v-model="dataForm.type" placeholder="反馈类型(0：bug修复  1：需求增添)"></el-input>
    </el-form-item>
    <el-form-item label="联系邮箱" prop="connectionEmail">
      <el-input v-model="dataForm.connectionEmail" placeholder="联系邮箱"></el-input>
    </el-form-item>
    <el-form-item label="反馈标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="反馈标题"></el-input>
    </el-form-item>
    <el-form-item label="反馈详细描述" prop="detail">
      <el-input v-model="dataForm.detail" placeholder="反馈详细描述"></el-input>
    </el-form-item>
    <el-form-item label="反馈提交时间" prop="publishTime">
      <el-input v-model="dataForm.publishTime" placeholder="反馈提交时间"></el-input>
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
          type: '',
          connectionEmail: '',
          title: '',
          detail: '',
          publishTime: ''
        },
        dataRule: {
          type: [
            { required: true, message: '反馈类型(0：bug修复  1：需求增添)不能为空', trigger: 'blur' }
          ],
          connectionEmail: [
            { required: true, message: '联系邮箱不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '反馈标题不能为空', trigger: 'blur' }
          ],
          detail: [
            { required: true, message: '反馈详细描述不能为空', trigger: 'blur' }
          ],
          publishTime: [
            { required: true, message: '反馈提交时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/blog/sitefeedback/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.type = data.siteFeedback.type
                this.dataForm.connectionEmail = data.siteFeedback.connectionEmail
                this.dataForm.title = data.siteFeedback.title
                this.dataForm.detail = data.siteFeedback.detail
                this.dataForm.publishTime = data.siteFeedback.publishTime
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
              url: this.$http.adornUrl(`/blog/sitefeedback/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'type': this.dataForm.type,
                'connectionEmail': this.dataForm.connectionEmail,
                'title': this.dataForm.title,
                'detail': this.dataForm.detail,
                'publishTime': this.dataForm.publishTime
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
