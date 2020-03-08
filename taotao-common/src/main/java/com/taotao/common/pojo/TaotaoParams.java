package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

public class TaotaoParams implements Serializable {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
