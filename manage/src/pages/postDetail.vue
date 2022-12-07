<template>
  <div style="width: 100%;background-color: #999aaa;padding: 8px 12px 0 12px;">


 <div  style="width:20%;">
   <el-card class="aside_box" style="text-align: left;width: 268px;float: left;margin:0 10px 0 auto">
<div class="intro">
<div class="image">
<img :src="author.avatar" style="width: 100%;height: 100%;border-radius:50%"/>
<!--  <div slot="placeholder" class="image-slot">
    <i class="el-icon-loading"/>
  </div>-->
</div>
  <div class="user_top">
<span class="user_top_name">{{author.username}}</span>
      <span class="years">{{author.professional}}</span>
    <span v-if="author.status=='老师'">
      <img src="../assets/img/laoshi.png" class="user_icon">
      <i style="font-size: 10px;">老师认证</i>
    </span>
    <span v-if="author.status=='专家'">

      <img src="../assets/img/zhuanjia.png" class="user_icon">
      <i style="font-size: 10px;">专家认证</i>
    </span>
  </div>
</div>

     <div style="margin: 10px auto 10px auto;text-align: center">
       <div style="border-bottom: 1px solid #999aaa">
     <span class="tongJi_text">
       {{ sumPost }}
     </span>
         <span style="margin: 0px 50px 0px 50px" class="tongJi_text">
       {{ sumGood }}
     </span>
         <span class="tongJi_text">
      {{ sumView }}
     </span>
       </div>
       <div>
          <span class="tongJi_text">
       原创
     </span>
         <span style="margin: 0px 30px 0px 30px" class="tongJi_text">
       点赞
     </span>
         <span class="tongJi_text">
     点击量
     </span>
       </div>
     </div>
<div>
  <el-button round style="width: 100%;margin-top: 20px" @click="dialogVisible = true">提问</el-button>
</div>
     <el-dialog
       title="请输入你的问题"
       :visible.sync="dialogVisible"
       width="50%"
       :before-close="handleClose">
       <el-input
         type="textarea"
         :rows="20"
         placeholder="请输入内容"
         v-model="textarea">
       </el-input>
       <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false" v-on:click="question()">提 问</el-button>
  </span>
     </el-dialog>
 </el-card>
   <div >
     <el-card  class="aside_box2" >
       <div class="assideNextTop">
         <span class="assideNext">用户的其他文章</span></div>

     </el-card>
   </div>
 </div>


  <div class="articles-area">
    <el-card style="text-align: left;width: 990px;margin: 0 auto 0 10px;min-height: 400px">
      <div>
        <span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span>

        <div style="font-size: 8px;margin: 10px 0 0 40px"><el-divider content-position="left" ><span style="color: #409EFF">发布于：</span><!--{{article.articleDate}}-->2022-11-28</el-divider></div>

        <div class="markdown-body">
          <div v-html="article.articleContentHtml"></div>
        </div>
      </div>
    </el-card>
    <el-card style="text-align: left;width: 990px;margin: 10px auto 0 279px">
      <div>
        <div class="image" style="display: flex">
          <img :src=users.avatar style="width: 100%;height: 100%;border-radius:50%"/>
        </div>
        <div class="user_top">
          <span class="user_top_name" style="width: 100%">{{users.username}}</span>
        </div>
        <!--        <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 5}"
                  placeholder="说说你的高见"
                  v-model="text1"
                style="margin-top: 10px">
                </el-input>-->
        <el-form :model="messageForm" >
          <el-form-item prop="content">
            <el-input :rows="5" v-model="messageForm.content" type="textarea" placeholder="说说你的高见"></el-input>
          </el-form-item>
          <el-form-item style="text-align: right">
            <el-button type="primary" @click="publish">点击发表</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>

  </div>

</template>

<script>
import "../assets/css/markdown.css"
import axios from "axios";

export default {

  name: "postDetail",
  data(){
    return{
      dialogVisible:false,
      post_id:'25',
      users:{},
      text1:'测试下回复功能',
      sumPost:'0',
      sumView:'0',
      sumGood:'0',
      author_id:'',
      messageForm:{
        content:'',
      },
      messageList:[
        {
          id:'1',
          createTime:'2022',
          nickname:'测试',
        }
      ],
      author: {},
      article:{articleTitle:'测试一下',
        articleDate:'2022-11-25',
        articleContentHtml:'<p>测试一下</p>',
      },

    }
  },
  methods:{
    question() {
      axios.post('http://localhost:8888/Interlocution/addInterlocution',
        {"experts_name": this.author.name, "question_content": this.textarea,"creater_name":localStorage.getItem('userName')},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200){
          alert(res.data.msg);
        }else {
          alert(res.data.msg);
        }
      })
    },
    publish(){
      if(this.users.username!=null){
        console.log(1)
        this.$http.post('http://localhost:8888//postDetail/addComment', {
          comments_content: this.messageForm.content,
          user_id:this.users.id,
          post_id:this.post_id,
        }).then(res=>{
          if (res.data.code == 200) {
            this.messageForm.content=null
            this.$message({
              type: 'success',
              message: '评论成功'
            })
          }
          else{
            this.$message({
              type: 'info',
              message: '评论失败'
            })
          }
        })
      }

    },
    selectByid(){
      this.$http.post('http://localhost:8888//postDetail/selectByid', {
        id:this.post_id,

      }).then(res=>{
        if (res.data.code == 200) {
          this.$message({
            type: 'success',
            message: '用户接受成功'
          })
        }
        this.article.articleTitle=res.data.data.articleTitle,
          this.article.articleContentHtml=res.data.data.articleContentHtml,
          this.article.articleDate=res.data.data.articleDate
      })
    },
    getUser(){
      this.$http.post('http://localhost:8888/User/findUserByName2', {
          'username':localStorage.getItem('userName')
      }).then(res=>{
        this.users=res.data.data
      })
    },

    getAuthor(){
      this.$http.post('http://localhost:8888//postDetail/selectAuthorByid', {
        id:this.post_id,
      }).then(res=>{
        if (res.data.code == 200) {
          this.$message({
            type: 'success',
            message: '作者接受成功'
          })
        }
        this.author = res.data.data
        this.$http.post('http://localhost:8888//postDetail/selectAll', {
          id:res.data.data.id
        }).then(res2=>{
          this.sumPost= res2.data.data.SumPost,
            this.sumView= res2.data.data.SumView,
            this.sumGood= res2.data.data.SumGood
        })
      })
    },
    getSumPost(){

    }
  },
  created() {
    this.selectByid()
    this.getUser()
    this.getAuthor()

  },
  computed: {
    userName () {
      return localStorage.getItem('userName')
    },

  }
}
</script>

<style scoped>
.aside_box{
 /* background-color: #f5f6f7;*/
  margin-bottom: 8px;
  border-radius: 2px;
/*  display: block;*/
}
.intro {
  padding: 16px 16px 6px 1px;
  display: flex;
}
.image{
  position: relative;
  width: 48px;
  height: 48px;
  flex-shrink: 0;
}
.user_top{
  margin-left: 8px;
  width: 156px;
  position: relative;
  padding-top: 4px;
  line-height: 20px;
  min-width: 62px;
}
.user_top_name{
  width: 100%;
  font-size: 14px;
  font-weight: 500;
  height: 20px;
  line-height: 20px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  min-width: 62px;
}
.years{
  width: 60px !important;
  color: #999aaa;
  font-size: 13px;
  line-height: 20px;
  height: 20px;
  overflow: hidden;
  margin-right: 2px;
}
.user_icon{
  width: 16px;
  height: 16px;
  vertical-align: -2px;
}
.tongJi{
  padding: 9px 10px;
}
.tongJi_text{
  color: #4a4d52;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
}
.assideNextTop{
  color: #3d3d3d;
  background: #f5f5f5;
  padding: 0 16px;
  height: 38px;
  line-height: 38px;
}
.assideNext{
  position: relative;
  padding: 0px 16px 0;
  font-size: 14px;
  color: #333;
  font-weight: bold;
}
</style>
<style>

.aside_box2{
  border-radius: 2px;
  text-align: center;width: 268px;
  float: left;
  margin:10px 10px 8px auto;
  min-height: 100px;
}
.aside_box2 .el-card__body {
  padding: 0 0 20px 0 !important;
}

</style>
