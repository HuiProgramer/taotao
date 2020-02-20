package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    /**
     * 根据父节点的id查询子节点的列表
     * @param parentId
     * @return
     */
    public List<EasyUITreeNode> getItemCatListByParentId(Long parentId);

}
