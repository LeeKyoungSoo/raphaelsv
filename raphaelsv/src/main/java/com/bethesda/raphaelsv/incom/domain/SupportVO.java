package com.bethesda.raphaelsv.incom.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SupportVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = -4348724576811222427L;

    private String gubun;
    private String ingcd;
    private String iclass;
    private String ingnm;
    private String phacd;
    private String prdnm;
    private String supcd;
    private String jango;
    private String reqcnt;
    private String deccnt;
    private String decyn;
    private String regid;
    private String regdt;
    private String modid;
    private String moddt;

    private String s_gubun;
    private String s_ingcd;
    private String s_iclass;
    private String s_ingnm;
    private String s_phacd;
    private String s_decyn;
    private String s_prdnm;

    private String phanm;
    private String capacity;
}
