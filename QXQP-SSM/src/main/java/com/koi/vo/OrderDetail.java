package com.koi.vo;

import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable {
    private PredateVo predateVo;
    private List<PreRepairItemDetailVo> preRepairItemDetailVoList;
    private List<PreProductDetailVo> preProductDetailVoList;
    private List<PreOtherCostDetailVo> preOtherCostDetailVoList;

    public PredateVo getPredateVo() {
        return predateVo;
    }

    public void setPredateVo(PredateVo predateVo) {
        this.predateVo = predateVo;
    }

    public List<PreRepairItemDetailVo> getPreRepairItemDetailVoList() {
        return preRepairItemDetailVoList;
    }

    public void setPreRepairItemDetailVoList(List<PreRepairItemDetailVo> preRepairItemDetailVoList) {
        this.preRepairItemDetailVoList = preRepairItemDetailVoList;
    }

    public List<PreProductDetailVo> getPreProductDetailVoList() {
        return preProductDetailVoList;
    }

    public void setPreProductDetailVoList(List<PreProductDetailVo> preProductDetailVoList) {
        this.preProductDetailVoList = preProductDetailVoList;
    }

    public List<PreOtherCostDetailVo> getPreOtherCostDetailVoList() {
        return preOtherCostDetailVoList;
    }

    public void setPreOtherCostDetailVoList(List<PreOtherCostDetailVo> preOtherCostDetailVoList) {
        this.preOtherCostDetailVoList = preOtherCostDetailVoList;
    }
}
