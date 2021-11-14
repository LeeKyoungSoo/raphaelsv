package com.bethesda.raphaelsv.basic.mapper;

import com.bethesda.raphaelsv.basic.domain.PharmaVO;
import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Pharma {
    int insData(PharmaVO vo) throws Exception;
    int delData(PharmaVO vo) throws Exception;
    List<PharmaVO> getDataList(PharmaVO vo) throws Exception;
    List<PharmaVO> getPharmaList(PharmaVO vo) throws Exception;
}
