package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    /**
     * 内容列表展示
     * */
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows);
    /**
     * 新增内容
     * */
    public TaotaoResult saveContent(TbContent tbContent);
    /***
     * 更新内容
     * */
    public TaotaoResult updateContent(TbContent tbContent);
    /**
     * 删除内容
     * */
    public TaotaoResult deleteContent(List<Long> Ids);
}
