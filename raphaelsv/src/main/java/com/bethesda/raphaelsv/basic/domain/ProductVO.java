package com.bethesda.raphaelsv.basic.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO extends CommonVO {
    private String ingcd;
    private String iclass;
    private String ingnm;
    private String phacd;
    private String useYn;
    private String regid;
    private String regdt;
    private String modid;
    private String moddt;
    private String prdnm;

    private String s_ingcd;
    private String s_iclass;
    private String s_ingnm;
    private String s_phacd;
    private String s_useYn;
    private String s_prdnm;

    private String phanm;
    private String s_phanm;
    private String capacity;
}
