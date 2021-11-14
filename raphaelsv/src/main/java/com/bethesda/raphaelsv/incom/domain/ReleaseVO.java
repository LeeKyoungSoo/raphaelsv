package com.bethesda.raphaelsv.incom.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ReleaseVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = 3226509988409466796L;

    private String relcd;
    private String incomcd;
    private String ingcd;
    private String iclass;
    private String ingnm;
    private String phacd;
    private String prdnm;
    private String expdt;
    private String relcnt;
    private String bigo;
    private String regid;
    private String regdt;
    private String modid;
    private String moddt;

    private String s_relcd;
    private String s_incomcd;
    private String s_ingcd;
    private String s_iclass;
    private String s_ingnm;
    private String s_phacd;
    private String s_prdnm;

    private String phanm;
    private String s_phanm;
    private String capacity;
    private String gubun;
    private String s_gubun;
    private String iscnt;
}
