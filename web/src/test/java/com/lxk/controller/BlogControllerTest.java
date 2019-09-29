//package com.lxk.controller;
//
//import com.lxk.AbstractTest;
//import com.lxk.model.Blog;
//import com.lxk.service.BlogService;
//import com.lxk.utils.JsonUtils;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * {@link BlogController}
// * <p>
// * Created by lxk on 2017/5/23
// */
//public class BlogControllerTest extends AbstractTest {
//
//    @Resource(name = "blogService")
//    private BlogService blogService;
//
//    @Test
//    public void getAllBlog() {
//        List<Blog> all = blogService.findAllBlog();
//        blogService.sortAllBlog("read", all);
//        for (Blog blog : all) {
//            System.out.println(blog.getTitle());
//        }
//    }
//
//    @Test
//    public void getTopNBlog() {
//        List<Blog> result = blogService.getTopNBlog(10, "read");
//        blogService.sortAllBlog("read:", result);
//        System.out.println(JsonUtils.parseObjToJson(result));
//    }
//
//}
