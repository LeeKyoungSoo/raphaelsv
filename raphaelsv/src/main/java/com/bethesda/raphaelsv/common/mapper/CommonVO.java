package com.bethesda.raphaelsv.common.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonVO {
    private String pageSize;
    private String pageNum;
    private int limit;
    private int offSet;
    private int totalCount;

    private String searchValue;
    private String stdt;
    private String endt;
}
