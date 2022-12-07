package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.CommentsMapper;
import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.domain.Comments;
import com.javaclimb.jxnu.domain.Contents;
import com.javaclimb.jxnu.domain.Post;
import com.javaclimb.jxnu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class PostDetailController {
    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @RequestMapping(value = "//postDetail/selectByid",method = RequestMethod.POST)
    public Result SelectPostContentByID(@RequestBody Post post){

        int post_id= post.getId();
        Contents contents=postMapper.findContentID(post_id);
        if(contents ==null){
            return new Result(Code.FAIL,"保存失败");
        }
        return new Result(Code.SUCCESS,"保存成功",contents);

    }
    @RequestMapping(value = "//postDetail/addComment",method = RequestMethod.POST)
    public Result addComment(@RequestBody Comments comments){
        System.out.println(comments);
         int post_id=comments.getPost_id();
         String comments_content= comments.getComments_content();
         int user_id=comments.getUser_id();
        System.out.println(comments_content);
        commentsMapper.addComments( post_id, user_id,comments_content);
        if(comments_content ==null){
            return new Result(Code.FAIL,"保存失败");
        }
        return new Result(Code.SUCCESS,"保存成功");

    }
    @RequestMapping(value = "/postDetail/selectAll",method = RequestMethod.POST)
    public Result selectAll( @RequestBody User user){
        System.out.println(user);
        int SumPost=postMapper.selectSumPost(user.getId());
        int SumView=postMapper.selectSumView(user.getId());
        int SumGood=postMapper.selectSumGood(user.getId());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("SumPost", SumPost);
        map.put("SumView", SumView);
        map.put("SumGood", SumGood);
        return new Result(Code.SUCCESS,"保存成功",map);
    }
    @RequestMapping(value = "//postDetail/selectAuthorByid",method = RequestMethod.POST)
    public Result SelectAuthorByID(@RequestBody Post post){
        int post_id= post.getId();
        User user=postMapper.findAuthorID(post_id);
        if(user ==null){
            return new Result(Code.FAIL,"保存失败");
        }

        return new Result(Code.SUCCESS,"保存成功",user);

    }

}
