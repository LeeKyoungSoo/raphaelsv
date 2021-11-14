package com.bethesda.raphaelsv.incom.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class IncomingVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = 2369028936297651054L;

    private String incomcd;
    private String supcd;
    private String ingcd;
    private String iclass;
    private String ingnm;
    private String phacd;
    private String prdnm;
    private String expdt;
    private String incomstd;
    private String incomcnt;
    private String incomstdcnt;
    private String nowcnt;
    private String bigo;
    private String regid;
    private String regdt;
    private String modid;
    private String moddt;

    private String s_incomcd;
    private String s_supcd;
    private String s_ingcd;
    private String s_iclass;
    private String s_ingnm;
    private String s_phacd;
    private String s_prdnm;
    private String s_expdt;
    private String s_regid;

    private String phanm;
    private String s_phanm;
    private String capacity;
    private String gubun;
    private String s_gubun;

    private String relcnt;
    private String abrcnt;
    private String incdt;
    private String reldt;
    private String inccnt;
}
