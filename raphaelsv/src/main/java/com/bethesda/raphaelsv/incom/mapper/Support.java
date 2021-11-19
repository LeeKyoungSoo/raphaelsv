package com.bethesda.raphaelsv.incom.mapper;

import com.bethesda.raphaelsv.incom.domain.SupportVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Support {
    int insData(SupportVO vo) throws Exception;
    int delData(SupportVO vo) throws Exception;
    List<SupportVO> getDataList(SupportVO vo) throws Exception;
    int udtDeccnt(SupportVO vo) throws Exception;
}
