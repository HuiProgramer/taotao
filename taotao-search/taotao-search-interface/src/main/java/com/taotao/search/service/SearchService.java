package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;


public interface SearchService {
    //导入所有的数据到索引库中
    public TaotaoResult importAllSearchItems() throws  Exception;

    /**
     * 根据查询条件查询
     * @param queryString
     * @return
     */
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception;


    //更新solr库
    public TaotaoResult updateSearchItemById(Long itemId) throws  Exception;
}
