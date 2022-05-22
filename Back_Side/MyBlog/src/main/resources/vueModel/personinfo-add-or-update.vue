<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="微信公众号名" prop="wxAccountName">
      <el-input v-model="dataForm.wxAccountName" placeholder="微信公众号名"></el-input>
    </el-form-item>
    <el-form-item label="微信公众号二维码" prop="wxAccountQrCode">
      <el-input v-model="dataForm.wxAccountQrCode" placeholder="微信公众号二维码"></el-input>
    </el-form-item>
    <el-form-item label="csdn博客用户id" prop="csdnUserId">
      <el-input v-model="dataForm.csdnUserId" placeholder="csdn博客用户id"></el-input>
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
          wxAccountName: '',
          wxAccountQrCode: '',
          csdnUserId: ''
        },
        dataRule: {
          wxAccountName: [
            { required: true, message: '微信公众号名不能为空', trigger: 'blur' }
          ],
          wxAccountQrCode: [
            { required: true, message: '微信公众号二维码不能为空', trigger: 'blur' }
          ],
          csdnUserId: [
            { required: true, message: 'csdn博客用户id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/blog/personinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.wxAccountName = data.personInfo.wxAccountName
                this.dataForm.wxAccountQrCode = data.personInfo.wxAccountQrCode
                this.dataForm.csdnUserId = data.personInfo.csdnUserId
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
              url: this.$http.adornUrl(`/blog/personinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'wxAccountName': this.dataForm.wxAccountName,
                'wxAccountQrCode': this.dataForm.wxAccountQrCode,
                'csdnUserId': this.dataForm.csdnUserId
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
