<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="文章标题" prop="titile">
      <el-input v-model="dataForm.titile" placeholder="文章标题"></el-input>
    </el-form-item>
    <el-form-item label="文章作者" prop="owner">
      <el-input v-model="dataForm.owner" placeholder="文章作者"></el-input>
    </el-form-item>
    <el-form-item label="文章链接地址" prop="linkAddr">
      <el-input v-model="dataForm.linkAddr" placeholder="文章链接地址"></el-input>
    </el-form-item>
    <el-form-item label="文章发表时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="文章发表时间"></el-input>
    </el-form-item>
    <el-form-item label="文章更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="文章更新时间"></el-input>
    </el-form-item>
    <el-form-item label="文章点赞量" prop="likeNum">
      <el-input v-model="dataForm.likeNum" placeholder="文章点赞量"></el-input>
    </el-form-item>
    <el-form-item label="文章评论量" prop="commentNum">
      <el-input v-model="dataForm.commentNum" placeholder="文章评论量"></el-input>
    </el-form-item>
    <el-form-item label="文章转发量" prop="mailNum">
      <el-input v-model="dataForm.mailNum" placeholder="文章转发量"></el-input>
    </el-form-item>
    <el-form-item label="文章阅读量" prop="readNum">
      <el-input v-model="dataForm.readNum" placeholder="文章阅读量"></el-input>
    </el-form-item>
    <el-form-item label="文章大类别" prop="bigSubject">
      <el-input v-model="dataForm.bigSubject" placeholder="文章大类别"></el-input>
    </el-form-item>
    <el-form-item label="文章小类别" prop="smallSubject">
      <el-input v-model="dataForm.smallSubject" placeholder="文章小类别"></el-input>
    </el-form-item>
    <el-form-item label="文章详细类别" prop="detailSubject">
      <el-input v-model="dataForm.detailSubject" placeholder="文章详细类别"></el-input>
    </el-form-item>
    <el-form-item label="文章总字数" prop="totalChars">
      <el-input v-model="dataForm.totalChars" placeholder="文章总字数"></el-input>
    </el-form-item>
    <el-form-item label="文章创作类型（0：原创 1：引用）" prop="createType">
      <el-input v-model="dataForm.createType" placeholder="文章创作类型（0：原创 1：引用）"></el-input>
    </el-form-item>
    <el-form-item label="文章所属详细归类（关联了smallSubject，smallSubject又关联了subject）" prop="detailSubjectId">
      <el-input v-model="dataForm.detailSubjectId" placeholder="文章所属详细归类（关联了smallSubject，smallSubject又关联了subject）"></el-input>
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
          titile: '',
          owner: '',
          linkAddr: '',
          createTime: '',
          updateTime: '',
          likeNum: '',
          commentNum: '',
          mailNum: '',
          readNum: '',
          bigSubject: '',
          smallSubject: '',
          detailSubject: '',
          totalChars: '',
          createType: '',
          detailSubjectId: ''
        },
        dataRule: {
          titile: [
            { required: true, message: '文章标题不能为空', trigger: 'blur' }
          ],
          owner: [
            { required: true, message: '文章作者不能为空', trigger: 'blur' }
          ],
          linkAddr: [
            { required: true, message: '文章链接地址不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '文章发表时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '文章更新时间不能为空', trigger: 'blur' }
          ],
          likeNum: [
            { required: true, message: '文章点赞量不能为空', trigger: 'blur' }
          ],
          commentNum: [
            { required: true, message: '文章评论量不能为空', trigger: 'blur' }
          ],
          mailNum: [
            { required: true, message: '文章转发量不能为空', trigger: 'blur' }
          ],
          readNum: [
            { required: true, message: '文章阅读量不能为空', trigger: 'blur' }
          ],
          bigSubject: [
            { required: true, message: '文章大类别不能为空', trigger: 'blur' }
          ],
          smallSubject: [
            { required: true, message: '文章小类别不能为空', trigger: 'blur' }
          ],
          detailSubject: [
            { required: true, message: '文章详细类别不能为空', trigger: 'blur' }
          ],
          totalChars: [
            { required: true, message: '文章总字数不能为空', trigger: 'blur' }
          ],
          createType: [
            { required: true, message: '文章创作类型（0：原创 1：引用）不能为空', trigger: 'blur' }
          ],
          detailSubjectId: [
            { required: true, message: '文章所属详细归类（关联了smallSubject，smallSubject又关联了subject）不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/blog/article/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.titile = data.article.titile
                this.dataForm.owner = data.article.owner
                this.dataForm.linkAddr = data.article.linkAddr
                this.dataForm.createTime = data.article.createTime
                this.dataForm.updateTime = data.article.updateTime
                this.dataForm.likeNum = data.article.likeNum
                this.dataForm.commentNum = data.article.commentNum
                this.dataForm.mailNum = data.article.mailNum
                this.dataForm.readNum = data.article.readNum
                this.dataForm.bigSubject = data.article.bigSubject
                this.dataForm.smallSubject = data.article.smallSubject
                this.dataForm.detailSubject = data.article.detailSubject
                this.dataForm.totalChars = data.article.totalChars
                this.dataForm.createType = data.article.createType
                this.dataForm.detailSubjectId = data.article.detailSubjectId
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
              url: this.$http.adornUrl(`/blog/article/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'titile': this.dataForm.titile,
                'owner': this.dataForm.owner,
                'linkAddr': this.dataForm.linkAddr,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'likeNum': this.dataForm.likeNum,
                'commentNum': this.dataForm.commentNum,
                'mailNum': this.dataForm.mailNum,
                'readNum': this.dataForm.readNum,
                'bigSubject': this.dataForm.bigSubject,
                'smallSubject': this.dataForm.smallSubject,
                'detailSubject': this.dataForm.detailSubject,
                'totalChars': this.dataForm.totalChars,
                'createType': this.dataForm.createType,
                'detailSubjectId': this.dataForm.detailSubjectId
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
