package com.bethesda.raphaelsv.incom.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AttainVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = 8903254551273947847L;

    private String iclass;
    private String ingcd;
    private String ingnm;
    private String capacity;
    private String relcnt;      //출고량
    private String nowcnt;      //재고량
    private String reqcnt;      //신청량
    private String moncnt;      //월사용량
    private String flgcnt;      //비축목표량
    private String flgpercent;  //달성률
    private String flgpercentval; //달성률(% 붙어 있음)

    private String s_ingnm;
}
