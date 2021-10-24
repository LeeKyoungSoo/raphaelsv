package com.bethesda.raphaelsv.etcmng.mapper;

import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Comcode {
    int insData(ComcodeVO vo) throws Exception;
    int delData(ComcodeVO vo) throws Exception;
    List<ComcodeVO> getDataList(ComcodeVO vo) throws Exception;
}
