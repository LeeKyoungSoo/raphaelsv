package com.bethesda.raphaelsv.incom.mapper;

import com.bethesda.raphaelsv.incom.domain.AttainVO;
import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Attain {
    List<AttainVO> getDataList(AttainVO vo) throws Exception;
}
