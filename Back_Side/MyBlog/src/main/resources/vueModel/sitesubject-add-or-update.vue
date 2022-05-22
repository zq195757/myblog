<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="分类名" prop="name">
      <el-input v-model="dataForm.name" placeholder="分类名"></el-input>
    </el-form-item>
    <el-form-item label="分类跳转地址" prop="linkAddr">
      <el-input v-model="dataForm.linkAddr" placeholder="分类跳转地址"></el-input>
    </el-form-item>
    <el-form-item label="外键labelID" prop="labelId">
      <el-input v-model="dataForm.labelId" placeholder="外键labelID"></el-input>
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
          name: '',
          linkAddr: '',
          labelId: ''
        },
        dataRule: {
          name: [
            { required: true, message: '分类名不能为空', trigger: 'blur' }
          ],
          linkAddr: [
            { required: true, message: '分类跳转地址不能为空', trigger: 'blur' }
          ],
          labelId: [
            { required: true, message: '外键labelID不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/blog/sitesubject/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.siteSubject.name
                this.dataForm.linkAddr = data.siteSubject.linkAddr
                this.dataForm.labelId = data.siteSubject.labelId
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
              url: this.$http.adornUrl(`/blog/sitesubject/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'linkAddr': this.dataForm.linkAddr,
                'labelId': this.dataForm.labelId
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
