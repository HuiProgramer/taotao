package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.ItemCatResult;

import java.util.List;

public interface ItemCatService {
    /**
     * 根据父节点的id查询子节点的列表
     * @param parentId
     * @return List<EasyUITreeNode>
     */
    public List<EasyUITreeNode> getItemCatListByParentId(Long parentId);

    /**
     * 查询商品分类信息
     * @param
     * @return ItemcatResult
     * */
    public ItemCatResult getItemCatList();
}
