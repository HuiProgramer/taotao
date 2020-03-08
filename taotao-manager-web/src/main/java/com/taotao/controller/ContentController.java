package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.common.pojo.TaotaoParams;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    //显示内容列表
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page, Integer rows){
        //引入服务
        //注册服务
        //调用服务的方法
        EasyUIDataGridResult result = contentService.getContentList(categoryId,page, rows);
        return result;
    }

    //添加内容
    @RequestMapping(value="/content/save",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent tbContent){
        //1.引入服务
        //2.注入服务
        //3.调用
        return contentService.saveContent(tbContent);
    }
    //更新内容
    @RequestMapping(value="/rest/content/edit",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateContent(TbContent tbContent){
        return contentService.updateContent(tbContent);
    }

    //删除内容
    @RequestMapping(value = "/content/delete",method = RequestMethod.POST)
    @ResponseBody
    public  TaotaoResult deleteContent(TaotaoParams params){
        return contentService.deleteContent(params.getIds());
    }
}
