package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    //通过节点的id查询节点的子节点列表
    public List<EasyUITreeNode> getContentCategoryList(Long parentId);
    //添加内容分类
    /**
     * @param parentId 父节点的id
     * @param name 新增节点的名称
     * */
    public TaotaoResult createContentCategory(Long parentId,String name);
    //更新内容分类
    /**
     * @param Id 节点的id
     * @param name 新增节点的名称
     * */
    public TaotaoResult updateContentCategory(Long Id,String name);
}
