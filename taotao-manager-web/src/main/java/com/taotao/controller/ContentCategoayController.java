package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoayController {

    @Autowired
    private ContentCategoryService service;
    /**
     * 内容分类的处理
     * url:'/content/category/list'
     * animate:true
     * method:"get"
     * 参数：id
     * */
    @RequestMapping(value="/content/category/list",method=RequestMethod.GET)
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
        //1.引入服务
        //2.注入服务
        //3.调用
        return service.getContentCategoryList(parentId);
    }

    //content/category/create
    //method=post
    //参数:
    //parentId:就是新增节点的父节点的Id
    //name：新增节点的文本
    //返回值taotaoresult 包含分类的id

    @RequestMapping(value = "/content/category/create",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId,String name){
        return service.createContentCategory(parentId,name);
    }

    //content/category/update
    //method=post
    //参数:
    //Id:就是节点的Id
    //name：修改节点的文本
    //返回值taotaoresult 包含分类的id
    @RequestMapping(value = "/content/category/update",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateContentCategory(@RequestParam(value = "id") Long Id,String name){
        return service.updateContentCategory(Id,name);
    }
}
