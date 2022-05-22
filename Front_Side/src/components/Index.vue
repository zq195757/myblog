<template>
  <div class="index-all">
    <!-- 利用<快速生成.vue文件的框架内容 -->
    <!-- 首部 -->
    <header class="index-header" id="header-part">
      <!-- 首页头部导航栏 -->
      <ul class="header-part">
        <li class="label-li">
          <a href="#">
            <img src="../assets/images/zqlogo.png" alt="首页Header背景图" />
          </a>
        </li>
        <li class="label-li"><i class="iconfont icon-shouye"></i>首页</li>
        <li class="label-li studyLearn-li">
          <i class="iconfont icon-bijiben"></i>学习心得
          <ul class="belowDiv">
            <li v-for="(info, index) in headerInfo.studyObtain" :key="index">
              <a :href="info.linkAddr">{{ info.subject }}</a>
            </li>
          </ul>
        </li>
        <li class="label-li resource-li">
          <i class="iconfont icon-ziyuan"></i>资源宝藏
          <ul class="belowDiv">
            <li
              v-for="(info, index) in headerInfo.resourceTreasure"
              :key="index"
            >
              <a :href="info.linkAddr">{{ info.subject }}</a>
            </li>
          </ul>
        </li>
        <li class="label-li mianshi-li">
          <i class="iconfont icon-mianshianpai"></i>面试准备
          <ul class="belowDiv">
            <li v-for="(info, index) in headerInfo.invertiewReady" :key="index">
              <a :href="info.linkAddr">{{ info.subject }}</a>
            </li>
          </ul>
        </li>
        <li class="label-li yule-li">
          <i class="iconfont icon-yuleAPP"></i>生活娱乐
          <ul class="belowDiv">
            <li v-for="(info, index) in headerInfo.lifeFun" :key="index">
              <a :href="info.linkAddr">{{ info.subject }}</a>
            </li>
          </ul>
        </li>
        <!-- 搜索Li -->
        <li class="label-li" @click="dialogVisible = true">
          <div class="index-searchDiv">
            <i class="iconfont icon-sousuoxiao"></i>
          </div>
        </li>
      </ul>
      <!-- 首部装饰 -->
      <div class="decorate">
        <div class="decorate1">
          <div class="person"><span>个人</span></div>
          <div class="blog"><span>博客</span></div>
        </div>
        <div class="decorate2">
          <span
            >做人最重要的是开心嘛<strong
              id="stressFlag"
              v-if="stressInOrOut === 1"
              >！</strong
            ></span
          >
        </div>
      </div>
      <!-- 搜索弹出框 -->
      <el-dialog
        v-if="dialogVisible"
        v-model="dialogVisible"
        class="search-dialog"
        title="搜索区"
        width="30%"
      >
        <span class="desp-head">大类别：</span
        ><el-input
          v-model="searchObj.bigSubject"
          type="text"
          name="bigSubject"
          id="search-bigSubject-input"
          autofocus="true"
          placeholder="后端|前端|音乐等..."
        /><span class="input_xing">*</span><br />
        <span class="desp-head">小类别：</span
        ><el-input
          v-model="searchObj.smallSubject"
          type="text"
          name="smallSubject"
          id="search-smallSubject-input"
          placeholder="Java|HTML|Linux等..."
        /><span class="input_xing">*</span><br />
        <span class="desp-head">详细类别：</span
        ><el-input
          v-model="searchObj.detailSubject"
          type="text"
          name="detailSubject"
          id="search-detailSubject-input"
          placeholder="微服务|表单|基本命令等..."
        /><span class="input_xing">*</span><br />
        <span class="desp-head">文章标题：</span
        ><el-input
          v-model="searchObj.title"
          type="text"
          name="title"
          id="search-title-input"
          placeholder="请输入标题..."
        /><span class="input_xing">可填项</span><br />
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">关闭</el-button>
            <el-button type="primary" @click="searchArticles">搜索</el-button>
          </span>
        </template>
      </el-dialog>
    </header>

    <!-- 正文 -->
    <div class="index-content">
      <div class="slider-part-title">
        <i class="iconfont icon-remen"></i>
        <span>热门推荐</span>
      </div>
      <div class="slider-part">
        <div class="slider-content">
          <div class="head">
            <div class="subject">
              <span>{{
                articleList.articleHotList[sliderCurIndex].subject
              }}</span
              ><i class="iconfont icon-jiantou"></i>
              <span>{{
                articleList.articleHotList[sliderCurIndex].smallSubject
              }}</span
              ><i class="iconfont icon-jiantou"></i>
              <span>{{
                articleList.articleHotList[sliderCurIndex].detailSubject
              }}</span>
            </div>
            <div class="pubtime">
              <i class="iconfont icon-shijian"></i
              ><span>{{
                articleList.articleHotList[sliderCurIndex].pubTime
              }}</span>
            </div>
          </div>
          <div class="content">
            <div class="title">
              {{ articleList.articleHotList[sliderCurIndex].title }}
            </div>
            <div class="desp">
              {{ articleList.articleHotList[sliderCurIndex].desp }}
            </div>
            <div class="detail-link">
              <a :href="articleList.articleHotList[sliderCurIndex].linkAddr"
                >READ MORE</a
              >
            </div>
          </div>
          <div class="footer">
            <i class="iconfont icon-yuedu"></i
            ><span>{{
              articleList.articleHotList[sliderCurIndex].readNum
            }}</span>
          </div>
        </div>
        <div class="slider-footer">
          <ul>
            <li class="slider-circle" value="0"></li>
            <li class="slider-circle" value="1"></li>
            <li class="slider-circle" value="2"></li>
          </ul>
        </div>
        <div class="slider-left-arrow">
          <i
            id="slider-left-i"
            @click="sliderSwitchByClick('left')"
            class="iconfont icon-jiantouzuo"
          ></i>
        </div>
        <div class="slider-right-arrow">
          <i
            id="slider-right-i"
            @click="sliderSwitchByClick('right')"
            class="iconfont icon-jiantouyou"
          ></i>
        </div>
      </div>
      <div class="content-part">
        <div class="article-part">
          <div class="article-part-title">
            <i class="iconfont icon-zuixin"></i>
            <span>最新推荐</span>
          </div>
          <div
            class="article-part-content"
            v-for="(article, index) in articleList.articleNewList"
            :key="article.id"
            :value="index"
          >
            <div class="img-part">
              <a :href="article.linkAddr">
                <img :src="article.imgAddr" alt="文章背景图" />
              </a>
            </div>
            <div class="content-part">
              <div class="subject">
                <span>{{ article.subject }}</span
                ><i class="iconfont icon-jiantou"></i>
                <span>{{ article.smallSubject }}</span
                ><i class="iconfont icon-jiantou"></i>
                <span>{{ article.detailSubject }}</span>
              </div>
              <div class="content">
                <div class="title">{{ article.title }}</div>
                <div class="desp">
                  {{ article.desp }}
                </div>
              </div>
              <div class="footer">
                <i class="iconfont shijian"></i>
                发布时间：<span>{{ article.pubTime }}</span>
              </div>
            </div>
          </div>
          <div class="content-footer">
            <div id="reach-bottom-reminder">
              <span style="color: red" class="text">已</span>
              <span style="color: #ffb452" class="text">经</span>
              <span style="color: blue" class="text">到</span>
              <span style="color: purple" class="text">底</span>
              <span style="color: #52dfff" class="text">啦</span>
              <span style="color: #ff52e2" class="text">~</span>
            </div>
          </div>
        </div>
        <div class="site-desp-part">
          <div class="site-part-title">
            <i class="iconfont icon-IE"></i>
            <span>站点信息</span>
          </div>
          <div class="article-info">
            <div class="title">版块划分信息</div>
            <div id="site-partition-echarts"></div>
          </div>
          <div class="person-info">
            <div class="star-title">
              <i class="iconfont icon-star"></i>
              <span>一起Star</span>
              <i class="iconfont icon-star"></i>
              <hr id="label-line" />
            </div>
            <div id="csdn-userinfo">
              <i class="iconfont icon-csdn"></i>
              <span>{{ siteInfo.personInfo.csdnUserName }}</span>
            </div>
            <div id="gitee-userinfo">
              <i class="iconfont icon-gitee"></i>
              <span>{{ siteInfo.personInfo.giteeUserName }}</span>
            </div>
            <div id="wx-office-account">
              <i class="iconfont icon-weixingongzhonghao"></i>
              <span>{{ siteInfo.personInfo.wxPublicAccountName }}</span>
            </div>
            <div id="qt-code" value="微信二维码">
              <div class="title">
                <i class="iconfont icon-weixin"></i>
                <span>微信扫一扫</span>
              </div>
              <div class="img-part">
                <img
                  class="wx-office-account"
                  :src="siteInfo.personInfo.qtCodeImgAddr.wxPublicAccount"
                  :alt="siteInfo.personInfo.wxPublicAccountName"
                />
                <img
                  class="wx-office-small-program"
                  :src="siteInfo.personInfo.qtCodeImgAddr.wxSmallProgram"
                  alt="键盘与头发的故事"
                />
              </div>
            </div>
          </div>
          <div class="site-Info">
            <div class="title">
              <i class="iconfont icon-tongji"></i>
              <span>网站统计信息</span>
              <hr id="label-line" />
            </div>
            <div class="info-statics" id="runtime">
              <span>运行时间：</span>
              <span>{{ runTime }}</span>
            </div>
            <div class="info-statics" id="accessnum">
              <span>总访问量：</span>
              <span>{{ siteInfo.accessnum }}</span>
            </div>
            <div class="info-statics" id="onlinenum">
              <span>当前在线人数：</span>
              <span>{{ siteInfo.onlinenum }}</span>
            </div>
          </div>
          <div class="label-wall-info">
            <div class="title">
              <i class="iconfont icon-biaoqian"></i>
              <span>标签墙</span>
              <hr id="label-line" />
            </div>
            <div class="content">
              <a
                v-for="(val, index) in labelList"
                :key="val.labelId"
                :value="index"
                :href="val.linkAddr"
                class="label-addr"
              >
                <span class="label-text">{{ val.text }}</span>
                <span class="label-count">({{ val.count }})</span>
              </a>
            </div>
            <div class="footer">
              <span class="ellipsis">
                <span></span>
                <span></span>
                <span></span>
              </span>
            </div>
          </div>
          <div class="dailySendWord-info">
            <div class="title">
              <i class="iconfont icon-mingshijiyu"></i>
              <span>每日寄语</span>
              <hr id="label-line" />
            </div>
            <div id="text">
              <span>{{ siteInfo.dailySendWord }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 尾部 -->
    <div class="index-footer">
      <div class="site-desp">
        <span id="title">关于本站</span>
        <span
          >本站仅作为个人博客使用，记录和分享知识<i
            class="iconfont icon-zhishi"
          ></i
          >，知识及网络环境靠你我他共同维护！</span
        >
        <span
          >CopyRight<i class="iconfont icon-rs-copy"></i> 2022-<span>{{
            curTime.getFullYear()
          }}</span>
          归
          <span style="color: #fbfb23; font-weight: bold">{{
            siteInfo.personInfo.buildSiteUserName
          }}</span>
          版权所有</span
        >
      </div>
    </div>

    <!-- 音乐工具箱 -->
    <div class="music-tool" @click="musicBallClickHandle">
      <div class="content">
        <i class="iconfont icon-yinfu"></i>
      </div>
    </div>
    <div class="music-panel" v-if="isShowForMusic">
      <div class="top">
        <i class="iconfont icon-guanbi" @click="isShowForMusic = false"></i>
      </div>
      <div class="content">
        <div class="albumImg">
          <img
            :src="
              musicList[musicCurPlayingIndex]
                ? musicList[musicCurPlayingIndex].imgUrl
                : ''
            "
            :alt="
              musicList[musicCurPlayingIndex]
                ? musicList[musicCurPlayingIndex].albumName
                : ''
            "
          />
        </div>
        <div class="music-part">
          <div class="title">
            <span class="music-name"
              >{{
                musicList[musicCurPlayingIndex]
                  ? musicList[musicCurPlayingIndex].musicName
                  : ""
              }}<i class="iconfont icon-yinle"></i
            ></span>
            <span class="singer-name"
              >歌手：{{
                musicList[musicCurPlayingIndex]
                  ? musicList[musicCurPlayingIndex].singerName
                  : ""
              }}</span
            >
            <span class="album-name"
              >专辑：{{
                musicList[musicCurPlayingIndex]
                  ? musicList[musicCurPlayingIndex].albumName
                  : ""
              }}</span
            >
          </div>
          <div class="lyrics">
            <div class="show-content">
              <span
                class="lyric-line-cotent"
                v-for="(lineObj, index) in musicList[musicCurPlayingIndex]
                  ? musicList[musicCurPlayingIndex].lyrics
                  : ''"
                :key="index"
                :value="index"
              >
                {{ lineObj.lineContent }}
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="footer">
        <div class="process-bar">
          <span class="real-show-part"></span>
          <span class="process-small-ball"></span>
        </div>
        <div class="music-player-part">
          <div class="play-time">
            <span class="now" id="musicPlayTime">{{ curPlayTime }}</span>
            <strong> / </strong>
            <span class="total" id="curMusic-play-totalTime">
              <!-- 仅展示 分:秒 舍弃毫秒部分 -->
              {{
                musicTotalPlayTime ? musicTotalPlayTime.slice(0, 5) : "00:00"
              }}
            </span>
          </div>
          <div class="middle-play-function">
            <span class="func-item" @click="switchToLeftSong"
              ><i class="iconfont icon-shangyishou"></i
            ></span>
            <span class="func-item" @click="playOrStopMusic"
              ><i class="iconfont icon-zanting" id="play-or-stop"></i
            ></span>
            <span class="func-item" @click="switchToRightSong"
              ><i class="iconfont icon-xiayishou"></i
            ></span>
          </div>
          <div class="play-voice-size">
            <div class="voice-control-show" id="voice-control-all">
              <span id="voice-show-size">40%</span>
              <div class="show-bar" id="voice-show-bar">
                <span class="no-show-part"></span>
                <span class="control-ball-part" id="voice-control-ball" draggable="true"></span>
              </div>
            </div>
            <span class="voice-icon"
              ><i class="iconfont icon-shengyin"></i
            ></span>
          </div>
        </div>
      </div>
    </div>
    <!-- autoplay：自动播放 preload：预加载，若有autoplay属性则忽略该属性  controls：显示控制面板  loop：重复循环播放 -->
    <video
      class="music-video"
      controls
      id="music-video-element"
      :src="
        musicList[musicCurPlayingIndex]
          ? musicList[musicCurPlayingIndex].musicUrl
          : ''
      "
      muted
      autoplay
      loop
    ></video>
    <!-- 右侧设置栏工具箱 -->
    <div class="set-tool" id="set-tool-part">
      <div
        class="tool tool-switch-skin"
        id="footer-huanfu-op"
        v-on:click="switchSkin"
      >
        <i class="iconfont icon-huanfu"></i>
        <div class="msg-remind">
          <span>点击换肤</span>
        </div>
      </div>
      <div
        class="tool tool-comment"
        id="footer-comment-op"
        @click="dialogVisible2 = true"
      >
        <i class="iconfont icon-wentifankui"></i>
        <div class="msg-remind" @click="dialogVisible2 = true">
          <span>提交反馈</span>
        </div>
      </div>
      <div class="tool tool-up" id="footer-up-op" @click="toUP">
        <i class="iconfont icon-arrowTop-fill"></i>
        <div class="msg-remind">
          <span>返回顶部</span>
        </div>
      </div>
      <!-- 反馈提交弹出框 -->
      <el-dialog
        v-if="dialogVisible2"
        v-model="dialogVisible2"
        class="feedback-dialog"
        title="反馈登记"
        width="35%"
      >
        <el-form label-width="11px" :model="feedback_form">
          <span class="input_xing">*</span
          ><span class="desp-head">反馈类型</span>
          <el-form-item>
            <!-- 给el-radio增添:label="number"的属性，则第一个el-radio会被默认选中 -->
            <el-radio-group v-model="feedback_form.type">
              <el-radio :label="0" value="0">需求增添</el-radio>
              <el-radio :label="1" value="1">BUG修复</el-radio>
            </el-radio-group>
          </el-form-item>
          <br />

          <span class="input_xing">*</span
          ><label for="email" class="desp-head">联系邮箱</label>
          <el-form-item>
            <el-input
              v-model="feedback_form.email"
              type="text"
              name="email"
              id="feedback-email-input"
              placeholder="请填写您的联系邮箱"
            /> </el-form-item
          ><br />

          <span class="input_xing">*</span
          ><span class="desp-head">反馈主题</span>
          <el-form-item>
            <el-input
              v-model="feedback_form.subject"
              type="text"
              name="subject"
              id="feedback-subject-input"
              placeholder="请简要描述您的反馈主题"
            /> </el-form-item
          ><br />

          <span class="input_xing">*</span
          ><span class="desp-head">反馈详情</span>
          <el-form-item>
            <el-input
              :autosize="{ minRows: 8, maxRows: 25 }"
              type="textarea"
              placeholder="请在此详细描述您的反馈情况"
              v-model="feedback_form.detail"
              name="detail"
              id="feedback-detail-input"
            /> </el-form-item
          ><br />

          <span class="input_xing">可选项</span
          ><span class="desp-head">信息截图</span>
          <el-form-item>
            <el-input
              v-model="feedback_form.photo"
              type="file"
              name="photo"
              id="feedback-photo-input"
              style="margin-top: 9px; margin-left: -11px; margin-bottom: 5px"
            /> </el-form-item
          ><br />

          <el-form-item>
            <span class="dialog-footer">
              <el-button @click="dialogVisible2 = false">关闭</el-button>
              <el-button type="primary" @click="doComment">提交</el-button>
            </span>
          </el-form-item>
        </el-form>

        <!-- ><el-input
          v-model="feedbackObj.type"
          type="radio"
          name="type"
          id="feedback-type-input"
          autofocus="true"
          value="需求增添"
        /><el-input
          v-model="feedbackObj.type"
          type="radio"
          name="type"
          id="feedback-type-input"
          autofocus="true"
          value="BUG修复"
        /><br />
        <span class="input_xing">*</span><span class="desp-head">联系邮箱</span
        ><el-input
          v-model="feedbackObj.email"
          type="text"
          name="email"
          id="feedback-email-input"
          placeholder="请填写您的联系邮箱"
        /><br />
        <span class="input_xing">*</span><span class="desp-head">反馈主题</span
        ><el-input
          v-model="feedbackObj.subject"
          type="text"
          name="subject"
          id="feedback-subject-input"
          placeholder="请简要描述您的反馈主题"
        /><br />
        <span class="input_xing">*</span><span class="desp-head">反馈详情</span
        ><el-input
          v-model="feedbackObj.detail"
          type="text"
          name="detail"
          id="feedback-detail-input"
          value="请在此详细描述您的反馈情况"
        /><br />
        <span class="input_xing">可选项</span
        ><span class="desp-head">信息截图</span>
        <el-input
          v-model="feedbackObj.photo"
          type="file"
          name="photo"
          id="feedback-photo-input"
        /><br /> -->
      </el-dialog>
    </div>
  </div>
</template>

<script>
// import部分
//#region
import { ref } from "vue";
// eslint-disable-next-line
// 引入Vue3提供的用于组合进setup的生命周期钩子函数
import {
  onBeforeMount,
  onMounted,
  onBeforeUpdate,
  onUpdated,
  onBeforeUnmount,
  onUnmounted,
} from "vue";
// 引入echarts库
import * as echarts from "echarts";
// 引入Promise异步封装组件
import { axiosReqByPromised } from "./PromiseHandle.js";

//#endregion

export default {
  name: "Index",
  props: {
    msg: String,
  },
  data() {
    return {
      // 模态框显示/不显示Flag
      dialogVisible: false,
      dialogVisible2: false,
      // 感叹号显示/不显示变量
      stressInOrOut: 1,
      // 轮播图 图像列表中当前图像的index
      sliderCurIndex: 0,
      // 切换定时器ID
      switchTimeID: 0,
      // 当前时间
      curTime: new Date(),
      // 网站运行时间
      runTime: "",
      // 换肤背景色数组
      bgColors: [
        "linear-gradient(to right,rgb(255, 219, 241),white,rgb(216, 244, 255))",
        "linear-gradient(to left,rgb(255, 238, 211),rgb(213, 238, 255),rgb(199, 255, 231))",
        "linear-gradient(to right,rgb(213, 255, 250),rgb(246, 222, 222),rgb(234, 208, 255))",
      ],
      // 换肤背景色对应的取值下标（存储到localStorage中）
      switchBgIndex: localStorage.getItem("switchBgIndex"),
      // 是否展示音乐界面
      isShowForMusic: false,

      // 文章搜索功能对应的封装对象
      searchObj: {
        bigSubject: "", // 大类别
        smallSubject: "", // 小类别
        detailSubject: "", // 详细类别
        title: "", // 文章标题
      },
      // 反馈信息登记功能对应的封装对象
      feedback_form: {
        type: 0, // 0：表示需求增添 1：表示BUG修复
        email: "", // 联系邮箱
        subject: "", // 反馈主题
        detail: "", // 反馈详情
        photo: "", // 信息截图
      },

      // 导航栏信息
      headerInfo: {
        studyObtain: [
          { linkAddr: "#", subject: "前 端" },
          { linkAddr: "#", subject: "后 端" },
          { linkAddr: "#", subject: "算 法" },
          { linkAddr: "#", subject: "运 维" },
          { linkAddr: "#", subject: "云原生" },
          { linkAddr: "#", subject: "大数据" },
          { linkAddr: "#", subject: "计算机基础" },
        ],
        resourceTreasure: [
          { linkAddr: "#", subject: "宝藏网站" },
          { linkAddr: "#", subject: "技术文档" },
          { linkAddr: "#", subject: "方向图谱" },
          { linkAddr: "#", subject: "书籍推荐" },
          { linkAddr: "#", subject: "工具插件" },
        ],
        invertiewReady: [
          { linkAddr: "#", subject: "前 端" },
          { linkAddr: "#", subject: "后 端" },
          { linkAddr: "#", subject: "算 法" },
          { linkAddr: "#", subject: "云原生" },
          { linkAddr: "#", subject: "大数据" },
          { linkAddr: "#", subject: "计算机基础" },
          { linkAddr: "#", subject: "简历与外在" },
        ],
        lifeFun: [
          { linkAddr: "#", subject: "音 乐" },
          { linkAddr: "#", subject: "影 视" },
          { linkAddr: "#", subject: "素 材" },
          { linkAddr: "#", subject: "游 戏" },
          { linkAddr: "#", subject: "表情包" },
          { linkAddr: "#", subject: "段子与热词" },
          { linkAddr: "#", subject: "其 他" },
        ],
      },
      // 文章列表信息（最新推荐、热门推荐）
      articleList: {
        // 轮播图 图像列表（按照文章阅读量取前3的文章）
        articleHotList: [
          {
            imgAddr: "./images/index-slider-1.jpg",
            linkAddr: "#",
            readNum: "12345",
            pubTime: "2022-05-08",
            subject: "后端",
            smallSubject: "Java",
            detailSubject: "SpringBoot",
            title: "SpringBoot的简介与好处",
            desp: "SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
          {
            imgAddr: "./images/index-slider-2.jpg",
            linkAddr: "#",
            readNum: "23456",
            pubTime: "2022-06-08",
            subject: "前端",
            smallSubject: "框架",
            detailSubject: "Vue",
            title: "Vue的简介与好处",
            desp: "Vue作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
          {
            imgAddr: "./images/index-slider-3.jpg",
            linkAddr: "#",
            readNum: "34567",
            pubTime: "2022-07-08",
            subject: "运维",
            smallSubject: "Linux",
            detailSubject: "基础命令",
            title: "Linux的简介与好处",
            desp: "Linux作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
        ],
        // 最新文章推荐列表
        articleNewList: [
          {
            imgAddr: "./images/index-slider-1.jpg",
            linkAddr: "#",
            pubTime: "2022-05-08",
            subject: "后端",
            smallSubject: "Java",
            detailSubject: "SpringBoot",
            title: "SpringBoot的简介与好处",
            desp: "SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
          {
            imgAddr: "./images/index-slider-2.jpg",
            linkAddr: "#",
            pubTime: "2022-06-08",
            subject: "前端",
            smallSubject: "框架",
            detailSubject: "Vue",
            title: "Vue的简介与好处",
            desp: "Vue作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
          {
            imgAddr: "./images/index-slider-3.jpg",
            linkAddr: "#",
            pubTime: "2022-07-08",
            subject: "运维",
            smallSubject: "Linux",
            detailSubject: "基础命令",
            title: "Linux的简介与好处",
            desp: "Linux作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
          {
            imgAddr: "./images/index-slider-4.jpg",
            linkAddr: "#",
            pubTime: "2022-08-08",
            subject: "大数据",
            smallSubject: "框架",
            detailSubject: "Hadoop",
            title: "Hadoop的简介与好处",
            desp: "Hadoop作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。SpringBoot作为一款Java急速开发框架，它的出现解决了先前存在的SSM框架配置麻烦的痛点，让开发人员以更精简的配置实现对整个项目的管理。",
          },
        ],
      },
      // 标签墙列表
      labelList: [
        { id: 1, text: "JavaSE", linkAddr: "#", count: 14 },
        { id: 2, text: "JavaWeb", linkAddr: "#", count: 26 },
        { id: 3, text: "MySQL", linkAddr: "#", count: 12 },
        { id: 4, text: "JDBC", linkAddr: "#", count: 9 },
        { id: 5, text: "SSM", linkAddr: "#", count: 14 },
        { id: 6, text: "分布式", linkAddr: "#", count: 24 },
        { id: 7, text: "微服务", linkAddr: "#", count: 34 },
        { id: 8, text: "高并发", linkAddr: "#", count: 54 },
        { id: 9, text: "源码分析", linkAddr: "#", count: 47 },
        { id: 10, text: "开发经验", linkAddr: "#", count: 61 },
        { id: 11, text: "设计模式", linkAddr: "#", count: 68 },
        { id: 12, text: "数据结构+算法", linkAddr: "#", count: 95 },
        { id: 13, text: "中间件", linkAddr: "#", count: 45 },
        { id: 14, text: "Linux", linkAddr: "#", count: 39 },
        { id: 15, text: "容器化部署", linkAddr: "#", count: 72 },
        { id: 16, text: "HTML", linkAddr: "#", count: 14 },
        { id: 17, text: "CSS", linkAddr: "#", count: 26 },
        { id: 18, text: "JS", linkAddr: "#", count: 12 },
        { id: 19, text: "ES6-11", linkAddr: "#", count: 9 },
        { id: 20, text: "AJAX+Jquery", linkAddr: "#", count: 14 },
        { id: 21, text: "Promise+Axios", linkAddr: "#", count: 24 },
        { id: 22, text: "UI框架", linkAddr: "#", count: 34 },
        { id: 23, text: "JS框架", linkAddr: "#", count: 54 },
        { id: 24, text: "Webpack", linkAddr: "#", count: 47 },
        { id: 25, text: "数据可视化", linkAddr: "#", count: 57 },
        { id: 26, text: "微信小程序", linkAddr: "#", count: 67 },
        { id: 27, text: "NodeJS", linkAddr: "#", count: 72 },
        { id: 28, text: "计算机基础", linkAddr: "#", count: 61 },
        { id: 29, text: "软件工程", linkAddr: "#", count: 68 },
        { id: 30, text: "微机与编译原理", linkAddr: "#", count: 72 },
      ],
      // 网站首页信息
      siteInfo: {
        // 网站内容板块划分信息
        platePartitionInfo: [
          { value: 4, name: "标签" },
          { value: 19, name: "分类" },
          { value: 6, name: "文章" },
        ],
        personInfo: {
          buildSiteUserName: "zq",
          csdnUserName: "回忆与向往",
          giteeUserName: "zqweb-mygit",
          wxPublicAccountName: "小赵学IT",
          qtCodeImgAddr: {
            wxPublicAccount: "./images/wx-account-qtcode.jpg",
            wxSmallProgram: "./images/wx-smallprogram-qtcode.jpg",
          },
        },
        // 年 月 日 时 分 秒 毫秒   注意：月以0开始，其他都是正常的
        buildSiteTime: new Date(2022, 4, 6, 21, 15, 7, 0).getTime(), // "2022-05-06 21:15:07"
        accessnum: "47",
        onlinenum: "2",
        dailySendWord:
          "成功路上要注意：埋头苦干是作风，不是成绩，不要一味苦劳，要谋求功劳；不要怀才不遇，无须耿耿于怀，瞅准机遇，一飞冲天；一步登天是梦想，一夜暴富难成真；不要以待遇来衡量工作，钱不是唯一的目标；效仿别人只能亦步亦趋，勇于挑战才能有所超越；有错马上认，立即改，这绝不是一件丢脸的事。愿你能成就自己。",
        // "热情和欲望可以突破一切难关。",
      },

      // 音乐id数组（这里只预选了10首）
      musicPreChooseIds: [
        488249475, 86360, 1913296313, 185919, 1934970069, 473873431, 1335819187,
        5366306, 486188224, 1897356046,
      ],
      musicCurIndex: 0, // 当前遍历获取音乐的index（针对musicPreChooseIds数组）
      // 当前播放的音乐index（针对musicList数组），默认展示第一首歌
      musicCurPlayingIndex: -1,
      lyricCurIndex: 0, // 当前播放的歌词行index
      curPlayTime: "00:00", // 当前音乐已播放的时间
      lastPlayedTime: 0, // 上一次已播过的时间
      musicList: [], // 音乐列表信息
      musicTimeComputeId: 0, // 音乐计时器id
      musicIsPlayingFlag: false, // 音乐是否正在播放中的标识符
      topLyricIndex: 0, // 当前歌词展示界面的第一行歌词的index
      musicTotalPlayTime: "00:00:000", // 当前音乐的总时长
    };
  },
  watch: {},
  methods: {
    // 文章搜索
    searchArticles() {},

    // !号若隐若现效果实现（定时器 + 递归）
    showOrHidden() {
      let _this = this;
      let showOrHiddenTimeID = setTimeout(() => {
        clearTimeout(showOrHiddenTimeID);
        if (_this.stressInOrOut === 1) {
          _this.stressInOrOut = 0;
        } else {
          _this.stressInOrOut = 1;
        }
        _this.showOrHidden(); // 自身调自身，也需要通过this指针来调用
      }, 800);
    },

    // -------------- 轮播图行为 --------------
    // 轮播图的样式修改
    sliderStyleModify(sliderIndex) {
      let sliderObj = $(".slider-circle").eq(sliderIndex);
      // #effdff
      let sliderImg = this.articleList.articleHotList[sliderIndex].imgAddr;
      // console.log("sliderImg = " + `url("${sliderImg}") no-repeat`);
      // 注意：css是将样式以style的方式嵌入到html界面中，要着重考虑url路径问题
      $(".slider-part").css({ background: `url(${sliderImg}) no-repeat` });
      sliderObj.css({ "background-color": "rgb(89, 154, 244)" });
      sliderObj.siblings().css({ "background-color": "#effdff" });
    },
    // 轮播图的自动切换（每隔3s）
    sliderAutoSwitch() {
      let _this = this;
      let sliderImgListLen = _this.articleList.articleHotList.length;
      _this.switchTimeID = setTimeout(() => {
        clearTimeout(_this.switchTimeID);
        _this.sliderCurIndex = ++_this.sliderCurIndex % sliderImgListLen;
        // 样式修改（图片url和下方轮播圆点背景颜色）
        _this.sliderStyleModify(_this.sliderCurIndex);
        this.sliderAutoSwitch(); // 递归调用
      }, 3500); // 每隔3s就切换到下一张图片
    },
    // 轮播图的左、右箭头切换
    sliderSwitchByClick(flag) {
      // 先清除自动切换遗留的定时器，左、右箭头切换后再继续新的自动切换
      clearTimeout(this.switchTimeID);
      let sliderImgsLen = this.articleList.articleHotList.length;
      if (flag === "left") {
        // 如果是第一张图片，向左切换时需要切换至最后一张
        this.sliderCurIndex =
          this.sliderCurIndex == 0
            ? sliderImgsLen - 1
            : --this.sliderCurIndex % sliderImgsLen;
      } else {
        this.sliderCurIndex = ++this.sliderCurIndex % sliderImgsLen;
      }
      this.sliderStyleModify(this.sliderCurIndex);
      this.sliderAutoSwitch();
    },

    // 网站内容板块划分信息（echarts图表处理）
    platePartitionInfoHandleByEcharts() {
      let chartDom = document.querySelector("#site-partition-echarts");
      let myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: "item",
        },
        legend: {
          bottom: "5%",
          left: "center",
        },
        series: [
          {
            name: "统计信息",
            type: "pie",
            radius: ["40%", "70%"], // 内圈、外圈半径
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "40",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: this.siteInfo.platePartitionInfo,
          },
        ],
      };
      // option图表配置参数存在才设置到指定的DOM
      option && myChart.setOption(option);
    },

    // 获取网站的运行时长
    getSiteRunTime() {
      // 获取当前时间与建站时间的差值时间戳
      let curTime = new Date().getTime();
      let diffTime = curTime - this.siteInfo.buildSiteTime;
      // let diffYear = diffTime.getFullYear();
      // let diffMonth = diffTime.getMonth();

      let diffDate = new Date(diffTime);

      let diffDay = diffDate.getDate();
      let diffHour = diffDate.getHours();
      let diffMinute = diffDate.getMinutes();
      let diffSecond = diffDate.getSeconds();

      this.runTime =
        diffDay +
        "天" +
        diffHour +
        "时" +
        diffMinute +
        "分" +
        diffSecond +
        "秒";
      // 关闭之前残留的定时器
      clearTimeout(modifyRunTimeId);

      // Vue实例上挂载的函数的this指向vm本身，回调函数的this会向外层寻找this，仍指向vm
      let modifyRunTimeId = setTimeout(() => {
        // 重新更新curTime
        this.curTime = new Date(curTime);
        this.getSiteRunTime();
      }, 1000);
    },

    // 点击换肤
    switchSkin() {
      console.log("触发switchSkin方法...");
      let switchBgIndex = this.switchBgIndex;
      this.switchBgIndex = ++switchBgIndex % this.bgColors.length;
      // 存储到localStorage中
      localStorage.setItem("switchBgIndex", this.switchBgIndex);
      // console.log("bgColor = " + this.bgColors[this.switchBgIndex ] );
      $(".index-content").css(
        "background-image",
        this.bgColors[this.switchBgIndex]
      );
    },
    // 提交反馈
    doComment() {
      $(".feedback-dialog").modal("show");
    },
    // 返回顶部
    toUP() {
      console.log("触发返回顶部事件...");
      // window.scrollTo(0,0);// 瞬间弹回顶部
      // 返回弹回顶部
      $("html,body").animate({ scrollTop: 0 }, 700, "swing");
    },

    // 音乐部分功能处理
    // 首页初始加载时，登录到网易云音乐，并获取第一首背景音乐数据信息
    async getMusicInfoBySongId(songId) {
      let _this = this;
      // 先根据songId在musicList中是否存在，若不存在才push
      let oldMusicList = _this.musicList || [];
      let isExisted = false;
      if (oldMusicList.length > 0) {
        for (let index in oldMusicList) {
          // forEach方法的循环无法中断，会产生不必要的遍历，降低效率
          let { id } = oldMusicList[index];
          if (songId === id) {
            console.log("发现已存在的歌曲，无需重复添加...");
            isExisted = true;
            _this.musicCurPlayingIndex = index;
            break; // 发现重复歌曲后，停止继续扫描
          }
        }
      }
      if (isExisted) return;

      console.log("没有重复的歌曲，可进行添加...");
      await axiosReqByPromised({
        config: {
          url: "/song/detail",
          params: { ids: songId },
        },
      })
        .then((res) => {
          const { songs } = res.data;
          let musicInfo = {
            id: songId,
            musicName: songs[0].name,
            singerName: songs[0].ar[0].name,
            albumName: songs[0].al.name,
            imgUrl: songs[0].al.picUrl,
            musicUrl: "",
            lyrics: [],
          };
          return musicInfo; // then方法向下传值，若传递的是引用数据类型，则会自动封装为Promise对象，传递给下一个then方法
        })
        .then(async (musicInfo) => {
          await axiosReqByPromised({
            config: {
              url: "/song/url",
              params: {
                id: songId,
              },
            },
          }).then((res) => {
            let musicUrl = res.data.data[0].url;
            musicInfo["musicUrl"] = musicUrl;
          });
          return musicInfo;
        })
        .then(async (musicInfo) => {
          // then方法参数对于所有下级方法都可用（类似于全局概念）
          await axiosReqByPromised({
            config: {
              method: "GET",
              url: "/lyric",
              params: {
                id: musicInfo.id,
              },
            },
          }).then((res) => {
            // 歌词封装处理
            let { lyric } = res.data.lrc;
            let lyricInfos = lyric.split(/[\n\r]/g); // 根据换行符或回车符全局寻找切割
            for (let elem of lyricInfos) {
              let splitElemInfos = elem.split("]");
              let lyricInfo = {
                startPlayTime: splitElemInfos[0].slice(1), // 左闭右开
                lineContent: splitElemInfos[1],
              };
              if (elem) {
                // 有实际内容才进行push
                musicInfo["lyrics"].push(lyricInfo);
              }
            }
            _this.musicList.push(musicInfo);
            _this.musicCurPlayingIndex = _this.musicList.length - 1;
            console.log(_this.musicList);
          });
        });
    },
    async loginAndGetFirstBGMInfo() {
      let _this = this; // 保存this指针，防止箭头函数this指针指向window问题
      await axiosReqByPromised({
        config: {
          method: "POST",
          /* url: "http://180.76.51.217:12345/login/cellphone",
          data: {
            phone: "13694807031",
            password: "1957570798zq", // 注意：密码问题
          }, */
          url: "http://180.76.51.217:12345/search",
          data: {
            keywords: "哪里都是你",
          },
        },
      })
        .then((res) => {
          if (res.status === 200 || res.status === 250) {
            let songId = _this.musicPreChooseIds[_this.musicCurIndex];
            /*  myAxios.get("/song/detail",{
            params: {
              ids: songId
            }
          }) */
            console.log("songId = " + songId);
            // 获取指定songId相关的musicInfo，并设置到data域中
            _this.getMusicInfoBySongId(songId);
          }
        })
        .catch((e) => {
          console.error(e);
          alert("网络缓慢，请重新点击~");
        });
    },

    // 音乐球点击函数事件
    // 将格式为04:07:020或04:08的时间字符换转为new Date().getTime()时间戳整数类型
    convertStringToDateTime(dateStr) {
      // dateStr格式： 02:05   01:14.030
      dateStr = dateStr.toString();
      let handle1Arr = dateStr.split(":");
      let minutePart = Number.parseInt(handle1Arr[0]);
      let resDate = new Date();
      if (dateStr.indexOf(".") != -1 && handle1Arr[1]) {
        let handle2Arr = handle1Arr[1].split(".");
        let secondPart = Number.parseInt(handle2Arr[0]);
        let millSecondPart = Number.parseInt(handle2Arr[1]);
        resDate.setMinutes(minutePart, secondPart, millSecondPart);
      } else {
        let secondPart = Number.parseInt(handle1Arr[1]);
        resDate.setMinutes(minutePart, secondPart, 0);
      }
      return resDate.getTime();
    },
    // 将格式为123.45678xxx的时间浮点数转为指定格式02:08的时间字符串
    convertFloatToDateStr(floatNum) {
      let dateArr = floatNum.toString().split(".");
      let minutePart = Math.floor(Number.parseInt(dateArr[0]) / 60); // 向下取整
      let secondPart = Number.parseInt(dateArr[0]) % 60;
      let milliSecondPart = dateArr[1].slice(0, 3); // 截取3位
      if (minutePart < 10) {
        minutePart = "0" + minutePart;
      }
      if (secondPart < 10) {
        secondPart = "0" + secondPart;
      }
      let convertedDateStr =
        minutePart + ":" + secondPart + "." + milliSecondPart;
      // console.log("convertedDateStr = ",convertedDateStr);
      return convertedDateStr;
    },
    // 歌词滑动样式设置
    lyricStyleSet() {
      let _this = this;
      let moveDistance = _this.topLyricIndex * -15;
      // 距离挪动
      // console.log("moveDistance = ", moveDistance);
      $(".show-content").css("top", moveDistance + "%");
      // 对当前播放歌词行设置粉色字体
      $(".show-content span").each((index, domElem) => {
        if (_this.topLyricIndex === index) {
          $(domElem).css("color", "pink");
          $(domElem).siblings().css("color", "white");
        }
      });
    },
    // 根据dateTime计算毫秒数
    getMilliSecondsOfDateTime(dateTime) {
      let res =
        dateTime.getMinutes() * 60 * 1000 +
        dateTime.getSeconds() * 1000 +
        dateTime.getMilliseconds();
      return res;
    },

    // 进度条样式设置（当前已播放过的时长、当前播放歌曲的总时长）
    processBarStyleSet() {
      let _this = this;
      let clickTime = 0;
      let curPlayDate = new Date(
        _this.convertStringToDateTime(_this.curPlayTime)
      );
      let musicTotalTime = _this.musicTotalPlayTime
        ? _this.musicTotalPlayTime
        : "00:00:000";
      let totalPlayDate = new Date(
        _this.convertStringToDateTime(musicTotalTime)
      );
      // 根据毫秒数计算播放的时长与总时长的比例
      let curPlayMillSeconds = _this.getMilliSecondsOfDateTime(curPlayDate);
      let totalPlayMillSeconds = _this.getMilliSecondsOfDateTime(totalPlayDate);
      // console.log("curPlayMillSeconds = ",curPlayMillSeconds,",totalPlayMillSeconds = ",totalPlayMillSeconds);

      // 精度处理，这里是保留小数点后3位
      let widthTimeStr = (
        (curPlayMillSeconds * 100) /
        totalPlayMillSeconds
      ).toString();
      // console.log("widthTimeStr = ",widthTimeStr.slice(0,6) + "%");
      let showWitdhOfProcessBar = widthTimeStr.slice(0, 6) + "%"; // 左闭右开
      let showWidthOfProcessBarDouble = Number.parseFloat(
        showWitdhOfProcessBar.slice(0, showWitdhOfProcessBar.length)
      );
      // console.log("showWitdhOfProcessBar = ",showWitdhOfProcessBar);
      if (showWidthOfProcessBarDouble >= 98.0) {
        showWitdhOfProcessBar = 0;
        _this.curPlayTime = "00:00";
        clickTime = new Date().getTime(); // 记录播放完音乐的时间点以实现当前音乐的循环播放
        $(".show-content").css("top", 0);
        _this.topLyricIndex = 0;
      }
      $(".real-show-part").css("width", showWitdhOfProcessBar);
      return clickTime;
    },
    // 音乐计时器
    musicTimeCompute(clickTime, computeIndex) {
      // time：开始点击的事件或暂停点的时间
      let _this = this;
      // console.log("进入计时器方法...")
      // 清空之前残留的数据状态（初始化）
      clearTimeout(_this.musicTimeComputeId);

      _this.musicTimeComputeId = setTimeout(() => {
        computeIndex++; // 计时index++
        // 计算开启计时后经过的时间
        // 由video的currentTime属性可获得
        // 获取当前播过的时间与下一行歌词的开头歌唱时间点
        let curMusicInfo = _this.musicList[_this.musicCurPlayingIndex];
        let curLyrics = curMusicInfo ? curMusicInfo.lyrics : [];
        let nextLinePlayTime = this.convertStringToDateTime(
          curLyrics[_this.topLyricIndex + 1]
            ? curLyrics[_this.topLyricIndex + 1].startPlayTime
            : "00:00:000"
        );
        // 注意：new Date().getTime()的基数问题
        let nextPlayMilliSeconds = _this.getMilliSecondsOfDateTime(
          new Date(nextLinePlayTime)
        );
        let curPlayMilliSeconds =
          document.getElementById("music-video-element").currentTime * 1000;
        if (curPlayMilliSeconds >= nextPlayMilliSeconds) {
          // console.log("curPlayMilliSeconds = ", curPlayMilliSeconds);
          _this.topLyricIndex++;
          // console.log("topLyricIndex = ",_this.topLyricIndex)
          // 将歌词top的span上移一个基本单位
          _this.lyricStyleSet();
        }
        // 每隔1s对curPlayTime作特定格式xx:xx的处理
        if (computeIndex % 100 == 0) {
          computeIndex = 0; // 每到1s后就将计时index重新置为0
          let curPlayTimes = _this.curPlayTime.split(":");
          let minutePart = Number.parseInt(curPlayTimes[0]);
          let secondPart = Number.parseInt(curPlayTimes[1]);
          // 进位处理
          if (secondPart + 1 === 60) {
            minutePart += 1;
            secondPart = 0;
          } else {
            secondPart++;
          }
          // 格式处理（如 04:08）
          if (minutePart < 10) {
            minutePart = "0" + minutePart;
          }
          if (secondPart < 10) {
            secondPart = "0" + secondPart;
          }
          let modifyTime = minutePart + ":" + secondPart;
          _this.curPlayTime = modifyTime;
          // 修改音乐播放面板的已播放进度条宽度
          clickTime = _this.processBarStyleSet();
        }
        _this.musicTimeCompute(clickTime, computeIndex);
      }, 10);
    },
    // 声音控件与进度条的拖动与点击功能实现
    musicVoiceAndProcessBar_DragAndClickChange() {
      // hover音乐控件时将其展示，注意DOM渲染延时问题
      let delayDomLoadHandleTimeId1 = setTimeout(() => {
        $(".play-voice-size").hover(
          () => {
            // console.log("触发hoverIN事件...");
            $(".voice-control-show").css("display", "flex");
            // 声音控件的鼠标挪动与点击功能实现


            let ctlBallDom = document.getElementById("voice-control-ball");
            let voiceShowBarDom = document.getElementById("voice-show-bar");
            let widthOfVoiceCtlBall = ctlBallDom.offsetWidth; // 以像素px为单位
            let widthOfVoiceProcessBar = voiceShowBarDom.offsetWidth;
            let realTotalWidth = widthOfVoiceProcessBar - widthOfVoiceCtlBall;

            console.log(
              "widthOfVoiceCtlBall = ",
              widthOfVoiceCtlBall,
              ",widthOfVoiceProcessBar = ",
              widthOfVoiceProcessBar,
              ",realTotalWidth = ",
              realTotalWidth
            );
            let startClientX = 0;
            let moveClientX = 0;
            let endClientX = 0;
            let showRatio = 0;

            // 鼠标挪动（点击、移动、放下）
            ctlBallDom.onmousedown = function (ev) {
              console.log("onmousedown事件触发...");
              ev = ev || window.event;
              startClientX = ev.clientX;
              let _this = this;
              console.log("this对象：",_this);
              _this.onmousemove = function (ev) {
                console.log("onmousemove事件触发...");
                ev = ev || window.event;
                moveClientX = ev.clientX;
                // ctlBallDom.offsetLeft
                let offsetXFromEvSource = moveClientX - startClientX;
                // 保留3位精度
                let offsetXRatio = Number.parseFloat(
                  ((offsetXFromEvSource / realTotalWidth) * 100).toFixed(3)
                );
                let sourceXRatio =
                  (
                    Number.parseFloat($(ctlBallDom).css("left").slice(0, 7)) /
                    widthOfVoiceProcessBar
                  ).toFixed(5) * 100;
                if (sourceXRatio < 0) {
                  sourceXRatio = 0;
                } else if (sourceXRatio >= 100) {
                  sourceXRatio = 100;
                }
                let changedXRatio = sourceXRatio + offsetXRatio + "%";
                let realRatio = Number.parseFloat(changedXRatio.split("%")[0]);
                if (realRatio < 0) {
                  realRatio = 0;
                } else if (realRatio >= 100) {
                  realRatio = 100;
                }
                // 修改显示比例
                console.log(
                  ",offsetXFromEvSource = ",
                  offsetXFromEvSource,
                  ",offsetXRatio = ",
                  offsetXRatio,
                  ",sourceXRatio = ",
                  sourceXRatio,
                  ",changedXRatio = ",
                  changedXRatio,
                  ",realRatio = ",
                  realRatio
                );
                $(".no-show-part").css("width", realRatio + "%");
                $(".control-ball-part").css("left", realRatio + "%");
                let voiceShow = Math.round(
                  Number.parseFloat(realRatio.toString().slice(0))
                ); // 四舍五入取音量值
                console.log("voiceShow = ", voiceShow);
                $("#voice-show-size").text((100 - voiceShow) + "%");
                return false; // 禁用默认行为
              };
              window.onmouseup = function () {
                console.log("触发onmouseup方法，this对象：",_this);
                _this.onmousemove = false; //解绑移动事件  
                return false; // 禁用默认行为
              };
              return false;
            };

            
            // 鼠标点击
            voiceShowBarDom.onclick = function (ev) {
              console.log("onclick事件触发...");
              ev = ev || window.event;
              // ctlBallDom.offsetLeft
              console.log("this = ",this);
              let offsetXFromEvSource = this.offsetLeft;
              // 保留3位精度
              let offsetXRatio = Number.parseFloat(
                ((offsetXFromEvSource / realTotalWidth) * 100).toFixed(3)
              );
              let sourceXRatio =
                (
                  Number.parseFloat($(ctlBallDom).css("left").slice(0, 7)) /
                  widthOfVoiceProcessBar
                ).toFixed(5) * 100;
              let changedXRatio = sourceXRatio + offsetXRatio + "%";
              let realRatio = Number.parseFloat(changedXRatio.split("%")[0]);
              if (realRatio < 0) {
                realRatio = 0;
              } else if (realRatio >= 100) {
                realRatio = 100;
              }
              // 修改显示比例
              console.log(
                ",offsetXFromEvSource = ",
                offsetXFromEvSource,
                ",offsetXRatio = ",
                offsetXRatio,
                ",sourceXRatio = ",
                sourceXRatio,
                ",changedXRatio = ",
                changedXRatio,
                ",realRatio = ",
                realRatio
              );
              $(".no-show-part").css("width", realRatio + "%");
              $(".control-ball-part").css("left", realRatio + "%");
              let voiceShow = Math.round(
                Number.parseFloat(realRatio.toString().slice(0))
              ); // 四舍五入取音量值
              console.log("voiceShow = ", voiceShow);
              $("#voice-show-size").text(voiceShow + "%");
              return false;
            };
          },
          () => {
            // console.log("触发hoverOUT事件...");
            $(".voice-control-show").css("display", "flex");
          }
        );

        clearTimeout(delayDomLoadHandleTimeId1);
      }, 0);
    },
    musicBallClickHandle() {
      let _this = this;

      // 展示音乐面板
      _this.isShowForMusic = true;
      document.getElementById("music-video-element").muted = false; // 解静音
      // 渲染当前歌曲的总时长
      _this.musicTotalPlayTime = _this.convertFloatToDateStr(
        document.getElementById("music-video-element").duration
      );
      // 声音控件与进度条的拖动与点击
      _this.musicVoiceAndProcessBar_DragAndClickChange();

      let startTime = new Date().getTime();
      // 避免在已经开始音乐计时的情况下点击音乐球重新调用计时程序
      // console.log("curPlayTime = ",_this.curPlayTime,",isPlaying = ",_this.musicIsPlayingFlag);
      if (_this.curPlayTime == "00:00") {
        document.getElementById("music-video-element").play();
        _this.musicTimeCompute(startTime, 0);
        _this.musicIsPlayingFlag = true; // 播放中
      } else {
        // 之前已经播放了一段时间，然后关闭了，因而要重新加载之前的播放界面
        if (!_this.musicIsPlayingFlag) {
          // 只修改暂停中的图标样式
          // console.log("进入样式修改环节...");
          document.getElementById("music-video-element").pause();
        }
        let delayDomLoadHandleTimeId2 = setTimeout(() => {
          // 1、根据时长设置进度条样式 2、设置播放图标样式 3、歌词滑动分布位置
          // console.log("进入else逻辑...");
          _this.processBarStyleSet();
          if (!_this.musicIsPlayingFlag) {
            $("#play-or-stop").removeClass("iconfont icon-zanting");
            $("#play-or-stop").addClass("iconfont icon-jixu");
          }
          // TODO 歌词滑动分布位置
          _this.lyricStyleSet();

          // 关闭定时器
          clearTimeout(delayDomLoadHandleTimeId2);
        }, 0);
      }
    },

    // 左、右箭头之上一首、下一首背景音乐的切换
    async switchToLeftSong() {
      let _this = this;
      console.log("musicCurIndex1 = ", _this.musicCurIndex);
      // 关停之前的计时程序及清空之前残留的数据状态、将可能为暂停的icon图标改为播放态、
      _this.curPlayTime = "00:00";
      $(".real-show-part").css("width", 0);
      if (!_this.musicIsPlayingFlag) {
        $("#play-or-stop").removeClass("iconfont icon-jixu");
        $("#play-or-stop").addClass("iconfont icon-zanting");
        _this.musicIsPlayingFlag = true;
      }

      if (_this.musicCurIndex === 0) {
        _this.musicCurIndex = _this.musicPreChooseIds.length - 1;
      } else {
        _this.musicCurIndex =
          (_this.musicCurIndex - 1) % _this.musicPreChooseIds.length;
      }
      await _this.getMusicInfoBySongId(
        _this.musicPreChooseIds[_this.musicCurIndex]
      );

      // 将歌词div的top值置为14%初值、歌词当前行样式调整并启动新的计时
      _this.topLyricIndex = 0;
      $(".show-content").css("top", "14%");
      let firstLyricSpanJq = $(".show-content span").eq(0);
      firstLyricSpanJq.css("color", "pink");
      firstLyricSpanJq.siblings().css("color", "white");
      // 由于video根据src自动播放--autoplay延时长，防止进度条过了很长一段才开始放出声音，这里手动触发下播放，尽量保证声音和进度条同步化
      document.getElementById("music-video-element").oncanplay = function () {
        _this.musicTotalPlayTime = _this.convertFloatToDateStr(this.duration);
        document.getElementById("music-video-element").play();
        _this.musicTimeCompute(new Date().getTime(), 0); // 获取音乐数据后，才开始计时
      };
    },
    async switchToRightSong() {
      let _this = this;
      console.log("musicCurIndex3 = ", _this.musicCurIndex);
      // 关停之前的计时程序及清空之前残留的数据状态、将可能为暂停的icon图标改为播放态
      _this.curPlayTime = "00:00";
      $(".real-show-part").css("width", 0);
      if (!_this.musicIsPlayingFlag) {
        $("#play-or-stop").removeClass("iconfont icon-jixu");
        $("#play-or-stop").addClass("iconfont icon-zanting");
        _this.musicIsPlayingFlag = true;
      }

      if (_this.musicCurIndex === _this.musicPreChooseIds.length - 1) {
        _this.musicCurIndex = 0;
      } else {
        _this.musicCurIndex =
          (_this.musicCurIndex + 1) % _this.musicPreChooseIds.length;
      }
      await _this.getMusicInfoBySongId(
        _this.musicPreChooseIds[_this.musicCurIndex]
      );

      // 将歌词div的top值置为14%初值、歌词当前行样式调整并启动新的计时
      _this.topLyricIndex = 0;
      $(".show-content").css("top", "14%");
      let firstLyricSpanJq = $(".show-content span").eq(0);
      firstLyricSpanJq.css("color", "pink");
      firstLyricSpanJq.siblings().css("color", "white");
      // 由于video根据src自动播放--autoplay延时长，防止进度条过了很长一段才开始放出声音，这里手动触发下播放，尽量保证声音和进度条同步化
      document.getElementById("music-video-element").oncanplay = function () {
        _this.musicTotalPlayTime = _this.convertFloatToDateStr(this.duration);
        document.getElementById("music-video-element").play();
        _this.musicTimeCompute(new Date().getTime(), 0); // 获取音乐数据后，才开始计时
      };
    },
    // 音乐播放之暂停与继续
    playOrStopMusic() {
      let _this = this;
      let flag = $("#play-or-stop").hasClass("icon-zanting");
      // let targetDom = targetJq.get(0);// Jquery对象转为Dom对象，但转换后的DOM对象不具有媒体类操作行为
      let targetDom = document.getElementById("music-video-element");
      if (!flag) {
        // 暂停状态则继续播放
        // 修改图标样式、记录当前播放的时间点重新开始音乐计时并继续播放音乐
        $("#play-or-stop").removeClass("iconfont icon-jixu");
        $("#play-or-stop").addClass("iconfont icon-zanting");
        let continuePlayTime = new Date().getTime();
        _this.musicTimeCompute(continuePlayTime, 0);
        targetDom.play();
        _this.musicIsPlayingFlag = true;
      } else {
        // 继续状态则暂停播放
        // 修改图标样式，关停音乐，记录当前已播放的时长并关闭开着的定时器
        $("#play-or-stop").removeClass("iconfont icon-zanting");
        $("#play-or-stop").addClass("iconfont icon-jixu");
        targetDom.pause();
        _this.lastPlayedTime = _this.curPlayTime;
        clearTimeout(_this.musicTimeComputeId);
        _this.musicIsPlayingFlag = false;
      }
    },
    // 进度条滚动实现
  },
  // 生命周期函数需要放入setup中执行(使用setup，this指针为空)
  beforeMount() {
    let _this = this;
  },
  mounted() {
    let _this = this;
    this.showOrHidden();
    this.sliderAutoSwitch();

    // 获取网站的运行时长
    this.getSiteRunTime();

    // 设置index-content部分的背景色
    $(".index-content").css(
      "background-image",
      this.bgColors[this.switchBgIndex]
    );

    // 轮播图的hover切换
    $(".slider-circle").hover(
      function () {
        // 第1个函数：hoverIn回调 第2个函数：hoverOut回调
        let sliderIndex = Number.parseInt($(this).val());
        _this.sliderStyleModify(sliderIndex);
        _this.sliderCurIndex = sliderIndex;
        // 先关闭之前自动切换遗留的定时器，然后再开启，即重新计时
        clearTimeout(_this.switchTimeID);
        _this.sliderAutoSwitch();
      },
      () => {}
    );
    // 网站内容板块划分信息（ECharts处理）
    this.platePartitionInfoHandleByEcharts();

    // window对象绑定浏览器滚动条滚动事件
    window.addEventListener("scroll", (e) => {
      // console.log(e);// e：event对象
      // 获取滚动条距离浏览器顶部的距离
      let scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop;
      // 获取指定DOM的高度
      let headerHeight = $("#header-part").height();
      // console.log("scrollTop = " + scrollTop);
      // console.log("headerHeight = " + headerHeight);
      // 当超出一定距离后就显示侧边工具栏，在该距离值内就将其隐藏，124是测试值
      if (scrollTop >= headerHeight - 124) {
        $("#set-tool-part").css("opacity", 1);
      } else {
        $("#set-tool-part").css("opacity", 0);
      }
    });

    // 音乐处理相关
    _this.loginAndGetFirstBGMInfo();
  },
};
</script>

<style lang="less">
.index-all {
  position: relative;

  /* 首页区域 */
  .index-header {
    /* 背景图片部分 */
    background: url("../assets/images/index_bg3.jpg") no-repeat;
    background-size: 100% auto; /* 参数1：宽度，占100%  参数2：高度，auto */
    height: 39.6875rem;

    /* 标题栏部分 */
    .header-part {
      display: flex;
      justify-content: space-around;
      //   height: .7813rem;
      padding: 1rem 12.5rem 1rem 12.5rem; /* 响应式单位 */
      i {
        margin-right: 0.3125rem;
        font-size: 1rem;
      }
      li.label-li {
        cursor: pointer;
      }
      li.label-li:first-child img {
        width: 1.6875rem;
      }
      li.label-li:not(:first-child) {
        position: relative;
        font-size: 1.1563rem;
        color: #dcf677;
      }
      li.label-li:nth-child(2) {
        /* 定位的是同class名的第2个li */
        margin-left: 15rem;
        // width: 7.8125rem;
        height: 1.75rem;
        line-height: 1.75rem;
        text-align: center;
        // background-color: rgb(128, 191, 215);
        // border-radius: 0.4375rem;
        color: pink;
      }
      li.label-li:last-child {
        margin-left: 15.3125rem;
        margin-top: 0.0625rem;
        color: black;
        .index-searchDiv {
          background-color: rgb(255, 244, 244);
          padding: 0.5rem;
          border: 0.125rem solid rgba(0, 0, 0, 0);
          border-radius: 50%;
          box-shadow: 0 0 0.6875rem rgb(214, 216, 195);
          //   text-align: center;
          align-items: center;
          // 不停旋转
          animation: search-rotate 5s linear infinite;
          @keyframes search-rotate {
            from {
              transform: rotate(0deg);
            }
            to {
              transform: rotate(360deg);
            }
          }
          i {
            margin-left: 0.25rem;
          }
        }
      }
    }

    /* hover显示下拉div */
    //#region
    .belowDiv {
      // display: none;/* 该属性无法使用transition实现过渡 */
      visibility: hidden;
      opacity: 0; // 0：完全透明 1：完全呈现
      position: absolute;
      top: 1.75rem;
      width: 110%;
      overflow: hidden;
      transition: all 0.5s ease;
      background-color: pink;
      border-radius: 0.1875rem;
      z-index: 9999;
      // cursor: pointer;
      li {
        width: 100%;
        height: 1.625rem;
        line-height: 1.625rem;
        text-align: center;
        a {
          font-size: 1.0938rem;
          color: black;
        }
      }
    }
    .studyLearn-li:hover,
    .resource-li:hover,
    .mianshi-li:hover,
    .yule-li:hover {
      color: pink;
      .belowDiv {
        visibility: visible;
        opacity: 1;
        box-shadow: 0 0 0.625rem rgba(0, 0, 0, 0.6);
        li:hover {
          background-color: rgb(128, 191, 215);
        }
      }
    }
    //#endregion

    /* 首部装饰部分 */
    .decorate1 {
      width: 11rem;
      height: 6rem;
      // background-color: red;
      position: absolute;
      top: 8.4375rem;
      left: 45.625rem;
      .person {
        float: left;
        width: 5rem;
        height: 2.6875rem;
        border-top: 0.25rem solid #11c5fc;
        border-left: 0.25rem solid #11c5fc;
        border-radius: 0.375rem;
        text-align: left;
        span {
          font-size: 2.1875rem;
          color: #11c5fc;
          margin-left: 0.3125rem;
          display: inline-block;
          margin-top: -0.1875rem;
        }
      }
      .blog {
        float: right;
        margin-top: 3.0625rem;
        width: 5rem;
        height: 2.6875rem;
        border-bottom: 0.25rem solid #f00691;
        border-right: 0.25rem solid #f00691;
        border-radius: 0.375rem;
        text-align: right;
        span {
          font-size: 2.1875rem;
          color: #f00691;
          display: inline-block;
          padding-bottom: 0.625rem;
          margin-right: 0.3125rem;
          margin-top: -0.3125rem;
        }
      }
    }
    .decorate2 {
      position: absolute;
      top: 35.625rem;
      left: 10rem;
      span {
        font-size: 2.125rem;
        color: rgb(226, 97, 97);
        font-family: "华文行楷";
        font-weight: bold;
      }
    }

    /* dialog搜索框 */
    .el-dialog {
      border-radius: 0.4375rem;
      .el-dialog__header {
        .el-dialog__title {
          font-size: 1.125rem;
        }
      }
      /* .el-dialog__headerbtn{
      svg{
        width: 3.125rem;
        height: 3.125rem;
      }
    } */
      .desp-head {
        font-size: 1.0625rem;
      }
      .input_xing {
        margin-left: 0.3125rem;
        color: red;
        font-size: 0.4375rem;
      }
      .el-input {
        margin: 0.4375rem 0;
        width: 88%;
      }
    }
  }

  /* 正文区域 */
  .index-content {
    padding: 0 9.375rem 2.5rem;
    /* 渐变色 */
    background-image: linear-gradient(
      to right,
      rgb(213, 255, 250),
      rgb(246, 222, 222),
      rgb(213, 238, 255)
    );
    overflow: hidden; /* 解决子元素margin带来的父元素高度坍塌问题BFC */

    /* 轮播图 */
    .slider-part-title {
      margin-top: 1.25rem;
      /* 子元素垂直居中 */
      display: flex;
      align-items: center;
      i {
        font-size: 2.5rem;
        color: red;
      }
      span {
        font-size: 1.9375rem;
        margin-left: 0.9375rem;
        color: rgb(51, 228, 255);
        font-weight: bold;
      }
    }
    .slider-part {
      width: 100%;
      height: 20rem;
      position: relative;
      background: url("../../public/images/index-slider-1.jpg") no-repeat;
      background-size: 100% auto;
      border-radius: 0.25rem;
      border: 0.25rem solid rgb(255, 236, 198);
      box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
      margin-top: 2%;
      padding-top: 3%;
      /* 内容区 */
      .slider-content {
        width: 60%;
        height: 76.5%;
        background-color: rgba(255, 255, 255, 0.625);
        border-radius: 0.4375rem;
        padding: 1rem 1.875rem 0;
        margin: 0 auto 0.3125rem;
        .head {
          display: flex;
          justify-content: space-between;
          .subject {
            font-size: 1rem;
            color: rgba(205, 30, 59, 0.945);
          }
          .pubtime {
            font-size: 0.875rem;
            i {
              margin-right: 0.25rem;
            }
          }
        }
        .content {
          width: 100%;
          margin: 0.625rem auto;
          text-align: center;
          .title {
            font-size: 1.4375rem;
            color: black;
          }
          .desp {
            margin: 0.625rem 0;
            height: 4.9375rem;
            line-height: 1.25rem;
            // 文字溢出处理
            display: -webkit-box; /* 弹性伸缩盒子 */
            -webkit-box-orient: vertical; /* 盒子内的元素垂直分布 */
            -webkit-line-clamp: 4; /* 限制的block行数 */
            overflow: hidden;
            text-overflow: ellipsis;
            font-size: 0.9603rem;
            color: rgba(0, 0, 0, 0.65);
          }
          .detail-link {
            margin: 1.25rem auto 0;
            width: 8.75rem;
            height: 2.5rem;
            line-height: 2.5rem;
            background-color: #37beeb;
            text-align: center;
            // vertical-align: center;
            font-size: 1.0625rem;
            border-radius: 0.4375rem;
            a {
              color: rgb(173, 252, 246);
            }
          }
        }
        .detail-link a:hover {
          color: rgb(255, 255, 255);
        }
        .footer {
          width: 100%;
          text-align: right;
          margin-top: -0.4375rem;
          span {
            margin-left: 0.25rem;
          }
        }
      }
      /* 轮播圆点 */
      .slider-footer {
        width: 100%;
        height: 16%;
        ul {
          width: 7%;
          height: 100%;
          margin: 10px auto 5px;
          // background-color: red;
          display: flex;
          flex-direction: row; /* 水平排列 */
          .slider-circle {
            // flex: 1;/* 水平等分排列 */
            width: 0.625rem;
            height: 0.625rem;
            border-radius: 50%;
            background-color: #effdff;
            margin: 24% 0.3125rem 0;
          }
          .slider-circle:first-child {
            margin-left: 19%;
            background-color: rgb(89, 154, 244);
          }
        }
      }
      /* 轮播左、右箭头 */
      .slider-left-arrow {
        position: absolute;
        top: 40%;
        left: 1%;
        i {
          font-size: 3.75rem;
          font-weight: bold;
          cursor: pointer;
          color: rgb(223, 132, 72);
        }
      }
      .slider-right-arrow {
        position: absolute;
        top: 40%;
        right: 1%;
        i {
          font-size: 3.75rem;
          font-weight: bold;
          cursor: pointer;
          color: rgb(223, 132, 72);
        }
      }
    }

    /* 下方正文区域 */
    .content-part {
      width: 100.8%;
      display: flex;
      padding-top: 1.25rem;

      .article-part {
        flex: 7;
        .article-part-title {
          /* 子元素垂直居中 */
          display: flex;
          align-items: center;
          i {
            font-size: 2.5rem;
            color: red;
          }

          span {
            font-size: 1.9375rem;
            margin-left: 0.9375rem;
            color: rgb(51, 228, 255);
            font-weight: bold;
          }
        }

        .article-part-content {
          width: 100%;
          box-sizing: border-box;
          border-radius: 0.25rem;
          margin-top: 1.5625rem;
          // height: 28.75rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          transition: box-shadow 1s ease;
          overflow: hidden;
          .img-part {
            height: 16.25rem;
            overflow: hidden;
            img {
              width: 100%;
              transition: width 1s ease;
            }
          }
          .content-part {
            width: 100%;
            box-sizing: border-box; /* 盒子模型的width以border为准 */
            padding: 0.9375rem 0.9375rem 0.4375rem;
            background-color: #fff9fa;
            display: block;
            .subject {
              margin-bottom: 0.625rem;
              font-size: 0.9375rem;
              color: rgba(205, 30, 59, 0.945);
            }
            .content {
              .title {
                font-size: 1.4375rem;
                color: black;
              }
              .desp {
                margin: 0.625rem 0;
                height: 5rem;
                line-height: 1.25rem;
                // 文字溢出处理
                display: -webkit-box;
                /* 弹性伸缩盒子 */
                -webkit-box-orient: vertical;
                /* 盒子内的元素垂直分布 */
                -webkit-line-clamp: 4;
                /* 限制的block行数 */
                overflow: hidden;
                text-overflow: ellipsis;
                font-size: 0.9603rem;
                color: rgba(0, 0, 0, 0.65);
              }
            }
            .footer {
              width: 100%;
              text-align: right;
              margin-top: 0.5rem;
              font-size: 0.9375rem;
              color: #01c1fc;
              i {
                margin-right: 0.125rem;
              }
              span {
                margin-left: 0.125rem;
              }
            }
          }
        }
        .article-part-content:hover {
          box-shadow: 0 0 1.8125rem rgba(61, 61, 61, 0.4);
          .img-part {
            img {
              width: 101.2%;
            }
          }
        }
        .content-footer {
          width: 100%;
          box-sizing: border-box; /* 盒子模型的width以border为准 */
          // background-color: rgba(235, 219, 219, 0.668);
          background-color: #fff9fa;
          height: 2.8125rem;
          text-align: center;
          display: flex;
          align-items: center;
          margin-top: 1.5625rem;
          border-radius: 0.3125rem;
          // border:.25rem solid rgb(255, 236, 198);
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          transition: box-shadow 1s ease;
          vertical-align: middle;
          overflow: hidden;
          #reach-bottom-reminder {
            width: 100%;
            animation: textCircleMove 12s linear infinite;
            .text {
              font-size: 1.375rem;
              margin-right: 0.8125rem;
            }
            @keyframes textCircleMove {
              from {
                transform: translateX(-60%);
              }
              to {
                transform: translateX(60%);
              }
            }
          }
        }
        .content-footer:hover {
          box-shadow: 0 0 1.8125rem rgba(61, 61, 61, 0.4);
          color: #52dfff;
        }
      }

      .site-desp-part {
        flex: 3;
        margin-left: 1.375rem;
        .site-part-title {
          /* 子元素垂直居中 */
          display: flex;
          align-items: center;
          i {
            font-size: 2.5rem;
            color: red;
          }

          span {
            font-size: 1.9375rem;
            margin-left: 0.9375rem;
            color: rgb(51, 228, 255);
            font-weight: bold;
          }
        }

        .article-info {
          width: 100%;
          background-color: #fff9fa;
          transition: box-shadow 1s ease;
          border-radius: 0.3125rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          box-sizing: border-box;
          padding: 0.75rem 1.25rem 0.3125rem;
          margin-top: 1.5625rem;

          .title {
            text-align: center;
            font-size: 1.875rem;
            font-weight: bold;
            height: 3.125rem;
            line-height: 3.125rem;
            padding-bottom: 0.625rem;
            border-bottom: 0.1875rem solid red;
          }

          #site-partition-echarts {
            width: 100%;
            height: 25rem;
          }
        }

        .person-info {
          box-sizing: border-box;
          width: 100%;
          background-color: #fff9fa;
          transition: box-shadow 1s ease;
          border-radius: 0.3125rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          padding: 1.5625rem 1.25rem;
          margin-top: 1.25rem;

          .star-title {
            font-size: 1.25rem;
            color: black;
            margin-bottom: 1.25rem;
            i {
              font-size: 1.625rem;
            }
            span {
              margin: 0 0.625rem;
            }
            #label-line {
              background-color: rgb(175, 175, 249);
              width: 30%;
              height: 0.1563rem;
              margin-top: 0.625rem;
              transition: width 1s ease;
            }
          }

          #csdn-userinfo,
          #gitee-userinfo,
          #wx-office-account,
          #qt-code {
            width: 100%;
            border-radius: 0.25rem;
            border: 0.0625rem solid rgb(226, 196, 196);
            padding-left: 0.3125rem;
            margin-top: 0.625rem;
            font-size: 0.9375rem;
            height: 2.25rem;
            line-height: 2.25rem;
            span {
              margin-left: 0.625rem;
            }
            i {
              font-size: 1.0625rem;
            }
          }

          #qt-code {
            height: 10.625rem;
            // padding-right: 0;
            .title {
              font-size: 1.0625rem;
            }
            .img-part {
              display: flex;
              justify-content: center;
              // margin: 0 auto;
              width: 100%;
              img {
                width: 40%;
                padding: 0 0.8125rem;
              }
            }
          }

          // 图标颜色样式修改
          .icon-star {
            color: #ff9d4d;
            font-weight: bold;
          }
          .icon-csdn {
            color: red;
          }
          .icon-gitee {
            color: red;
          }
          .icon-weixingongzhonghao {
            color: #6ece46;
          }
          .icon-weixin {
            color: #6ece46;
          }
        }

        .site-Info {
          width: 100%;
          background-color: #fff9fa;
          transition: box-shadow 1s ease;
          border-radius: 0.3125rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          box-sizing: border-box;
          padding: 1.5625rem 1.25rem 0.9375rem;
          font-size: 1.25rem;
          margin-top: 1.25rem;
          .title {
            margin-bottom: 0.9375rem;
            i {
              font-size: 1.625rem;
              color: skyblue;
            }
            span {
              margin-left: 0.625rem;
            }
            #label-line {
              background-color: rgb(175, 175, 249);
              width: 30%;
              height: 0.1563rem;
              margin-top: 0.625rem;
              transition: width 1s ease;
            }
          }
          .info-statics {
            width: 100%;
            display: flex;
            justify-content: space-between;
            height: 1.875rem;
            line-height: 1.875rem;
            font-size: 0.9375rem;
          }
        }

        .label-wall-info {
          width: 100%;
          background-color: #fff9fa;
          transition: box-shadow 1s ease;
          border-radius: 0.3125rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          box-sizing: border-box;
          padding: 1.25rem 1.25rem 0.9375rem;
          margin-top: 1.25rem;
          .title {
            width: 100%;
            font-size: 1.3125rem;
            margin-bottom: 0.9375rem;
            span {
              margin-left: 0.625rem;
            }
            i {
              font-size: 1.625rem;
              color: #f980f1;
            }
            #label-line {
              background-color: rgb(175, 175, 249);
              width: 30%;
              height: 0.1563rem;
              margin-top: 0.625rem;
              transition: width 1s ease;
            }
          }

          .content {
            width: 100%;
            a.label-addr {
              display: inline-block;
              padding: 0.625rem;
              background-color: pink;
              color: black;
              font-size: 0.8125rem;
              text-align: center;
              height: 1rem;
              line-height: 1rem;
              margin: 0.625rem 0.625rem 0 0;
            }
          }
          .footer {
            text-align: center;
            margin-top: 0.625rem;
            .ellipsis {
              span {
                display: inline-block;
                width: 0.5938rem;
                height: 0.5938rem;
                border-radius: 50%;
                margin-right: 0.375rem;
                background-color: #01c1fc;
              }
            }
          }
        }
        .article-info:hover,
        .person-info:hover,
        .site-Info:hover,
        .label-wall-info:hover {
          box-shadow: 0 0 1.8125rem rgba(61, 61, 61, 0.4);
          overflow: hidden;
          #label-line {
            width: 42%;
          }
        }
        .label-wall-info .content a.label-addr:hover {
          background-color: #01c1fc;
          border-radius: 0.1875rem;
        }

        .dailySendWord-info {
          width: 100%;
          height: 9.375rem;
          background-color: #fff9fa;
          transition: box-shadow 1s ease;
          border-radius: 0.3125rem;
          box-shadow: 0 0 0.75rem rgba(61, 61, 61, 0.4);
          box-sizing: border-box;
          padding: 1.25rem 1.25rem 0.9375rem;
          margin-top: 1.25rem;
          // 设置滚动条，内容过多时height不至于过大，影响布局
          overflow-y: auto; // y轴溢出指定高度时触发滚动条
          .title {
            width: 100%;
            font-size: 1.3125rem;
            margin-bottom: 0.9375rem;
            span {
              margin-left: 0.625rem;
            }
            i {
              font-size: 1.625rem;
              color: rgb(84, 245, 84);
            }
            #label-line {
              background-color: rgb(175, 175, 249);
              width: 30%;
              height: 0.1563rem;
              margin-top: 0.625rem;
              transition: width 1s ease;
            }
          }
        }
        .dailySendWord-info:hover {
          box-shadow: 0 0 1.8125rem rgba(61, 61, 61, 0.4);
          #label-line {
            width: 42%;
          }
        }
        // 滚动条样式设置
        .dailySendWord-info::-webkit-scrollbar {
          width: 0.625rem;
          border-radius: 4px;
          background-color: #01c1fc;
        }
        .dailySendWord-info::-webkit-scrollbar-thumb {
          background: rgba(0, 0, 0, 0.2);
          border-radius: 4px;
          transition: background-color 0.4s;
        }
        .dailySendWord-info::-webkit-scrollbar-thumb:hover {
          background: #bcb5b5;
        }
      }
    }
  }

  /* 底部区域 */
  .index-footer {
    background-color: rgba(227, 93, 227, 0.914);
    width: 100%;
    .site-desp {
      margin: 0 auto;
      height: 6.25rem;
      display: flex;
      flex-direction: column;
      text-align: center;
      padding: 1.25rem 0 0.3125rem 0;
      i {
        margin-left: 0.3125rem;
        color: rgba(153, 255, 197, 0.895);
      }
      span {
        flex: 1;
        font-size: 1rem;
        color: white;
      }
      span:last-child {
        font-size: 0.875rem;
      }
      #title {
        font-size: 1.25rem;
      }
    }
  }

  /* 工具区域 */
  .music-tool {
    display: inline-block;
    position: fixed;
    left: -1%;
    bottom: 19%;
    transition: left 0.6s ease;
    width: 3.125rem;
    height: 3.125rem;
    border-radius: 50%;
    padding: 0.9375rem;
    background-color: rgb(160, 255, 125);
    box-shadow: 0 0 0.625rem rgba(195, 177, 177, 0.948);
    cursor: pointer;
    animation: music-rotate 4s linear infinite;
    @keyframes music-rotate {
      from {
        transform: rotate(0deg);
      }
      to {
        transform: rotate(360deg);
      }
    }
    .content {
      width: 100%;
      height: 100%;
      display: flex;
      border-radius: 50%;
      background-color: #f5dd42;
      align-items: center;

      i {
        margin: 0 auto;
        font-size: 1.875rem;
        color: #0cb972;
      }
    }
  }
  .music-tool:hover {
    left: 1%;
  }
  .music-panel {
    position: fixed;
    left: 24%;
    top: 18%;
    background-color: #6c6c6cf0;
    border-radius: 0.25rem;
    width: 50%;
    padding: 0.9375rem 0.9375rem 0.3125rem;
    color: white;
    .top {
      width: 100%;
      text-align: right;
      i {
        font-size: 1.125rem;
        color: rgb(171, 157, 157);
        cursor: pointer;
      }
    }
    .content {
      height: 100%;
      // box-sizing: content-box;
      padding: 1.5625rem 2.1875rem 3.125rem;
      display: flex;
      .albumImg {
        flex: 1;
        height: 18.1875rem;
        img {
          width: 73%;
        }
      }
      .music-part {
        flex: 1;
        display: flex;
        flex-direction: column;
        margin-left: -12.5%;
        .title {
          // 垂直排列（column列方向|垂直  row行方向|水平）
          display: flex;
          flex-direction: column;
          text-align: center;
          font-size: 0.875rem;
          margin-bottom: 1.1875rem;
          color: rgb(201, 183, 183);
          .music-name {
            font-size: 1.5625rem;
            margin-bottom: 0.625rem;
            color: white;
            i {
              margin-left: 0.1875rem;
              color: #a0ff7d;
            }
          }
        }
        .lyrics {
          height: 11.875rem;
          position: relative;
          overflow: hidden;
          .show-content {
            position: absolute;
            display: flex;
            width: 100%;
            flex-direction: column;
            text-align: center;
            top: 13%;
            left: 0%;
            span.lyric-line-cotent {
              margin-top: 0.625rem;
            }
            span.lyric-line-cotent:first-child {
              color: pink;
            }
          }
        }
      }
    }
    .footer {
      width: 100%;
      .process-bar {
        width: 100%;
        height: 0.1875rem;
        background-color: white;
        display: flex;
        .real-show-part {
          display: inline-block;
          width: 0;
          height: 0.1875rem;
          background-color: pink;
        }
        .process-small-ball {
          width: 0.5625rem;
          height: 0.5625rem;
          cursor: pointer;
          margin-top: -0.1875rem;
          display: inline-block;
          border-radius: 50%;
          background-color: #a0ddf1;
        }
      }
      .music-player-part {
        width: 100%;
        height: 2.5rem;
        line-height: 2.5rem;
        text-align: center;
        margin-top: 5px;
        .play-time {
          float: left;
          font-size: 0.8438rem;
        }
        .middle-play-function {
          // margin: 0 auto;
          text-align: center;
          display: inline-block;
          #play-or-stop {
            display: inline-block;
            width: 1.875rem;
            height: 1.875rem;
            line-height: 1.875rem;
            font-size: 1rem;
            text-align: center;
            margin: 0 0.625rem;
            background-color: pink;
            border-radius: 50%;
            color: rgb(103, 92, 92);
          }
          i {
            cursor: pointer;
          }
        }
        .play-voice-size {
          position: relative;
          float: right;
          display: flex;
          .voice-control-show {
            position: absolute;
            right: 90%;
            top: 4%;
            // margin-right: 1.0625rem;
            display: none;
            align-items: center;
            width: 7.5rem;
            height: 1.75rem;
            padding: 0.3125rem 1.125rem;
            border-radius: 0.3125rem;
            transition: width 1s ease;
            background-color: rgb(41, 41, 43);
            .show-bar {
              background-color: pink;
              flex: 8;
              height: 0.1875rem;
              display: flex;
              cursor: pointer;
              position: relative;
              .no-show-part {
                display: inline-block;
                width: 60%;
                height: 100%;
                background-color: white;
              }
              .control-ball-part {
                display: inline-block;
                position: absolute;
                left: 60%;
                width: 0.6875rem;
                height: 0.6875rem;
                cursor: pointer;
                margin-top: -0.1875rem;
                border-radius: 50%;
                background-color: skyblue;
              }
            }
            #voice-show-size {
              font-size: 0.9375rem;
              display: inline-block;
              color: white;
              width: 2.75rem;
              text-align: left;
            }
          }
          .voice-icon {
            width: 1.5625rem;
            i {
              font-size: 1.1875rem;
              padding-left: 20%;
              cursor: pointer;
            }
          }
        }
      }
    }
  }
  /* 隐藏的原生video播放器 */
  video.music-video {
    width: 0;
    height: 0;
    position: absolute;
  }
  .set-tool {
    display: inline-block;
    position: fixed;
    left: 97%;
    bottom: 18%;
    opacity: 0;
    transition: opacity 0.75s ease;

    /* 普通DOM元素不包含脱离了文档流的DOM元素 */
    div.tool {
      cursor: pointer;
      position: relative;
      width: 2.1875rem;
      height: 2.1875rem;
      line-height: 2.1875rem;
      margin-bottom: 0.1875rem;
      text-align: center;
      align-items: center;
      background-color: #49b1f5;
      i {
        font-size: 1.25rem;
        color: white;
      }
      .msg-remind {
        position: absolute;
        height: 2.1875rem;
        line-height: 2.1875rem;
        text-align: center;
        width: 0;
        overflow: hidden;
        transition: width 0.6s ease;
        font-size: 15px;
        right: 2.1875rem;
        color: white;
        background-color: pink;
      }
      .msg-remind:first-child {
        bottom: 76px;
      }
      .msg-remind:nth-child(2) {
        bottom: 38px;
      }
      .msg-remind:last-child {
        bottom: 0px;
      }
    }
    div.tool:hover {
      background-color: #fe7f19;
      .msg-remind {
        width: 4.625rem;
        cursor: default; // 默认指针状态
      }
    }
    /* dialog搜索框 */
    .el-dialog {
      border-radius: 0.4375rem;
      .el-dialog__header {
        .el-dialog__title {
          font-size: 1.125rem;
        }
      }
      .el-dialog__body {
        padding-bottom: 0.3125rem;
      }
      .desp-head {
        font-size: 1rem;
        margin-right: 0.3125rem;
      }
      .input_xing {
        margin-right: 0.3125rem;
        color: red;
        font-size: 0.4375rem;
      }
      .el-form-item {
        display: inline-block;
        width: 83%;
        .el-input {
          margin: 0;
          width: 100%;
        }
      }
      .el-form-item:last-child {
        width: 100%;
        span.dialog-footer {
          width: 100%;
          text-align: right;
        }
      }
    }
  }
}
</style>