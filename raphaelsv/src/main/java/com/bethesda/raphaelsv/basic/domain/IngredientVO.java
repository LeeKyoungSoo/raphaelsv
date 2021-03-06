package com.bethesda.raphaelsv.basic.domain;

import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IngredientVO extends CommonVO {
    private String ingcd;
    private String iclass;
    private String ingnm;
    private String capacity;
    private String useyn;
    private String regid;
    private String regdt;
    private String modid;
    private String moddt;
}
