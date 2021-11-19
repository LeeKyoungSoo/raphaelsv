package com.bethesda.raphaelsv.incom.mapper;

import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Incoming {
    int insData(IncomingVO vo) throws Exception;
    int delData(IncomingVO vo) throws Exception;
    List<IncomingVO> getDataList(IncomingVO vo) throws Exception;
    List<IncomingVO> getIncomingSupCdList(IncomingVO vo) throws Exception;
    List<IncomingVO> getJegoDataList(IncomingVO vo) throws Exception;
    List<IncomingVO> getIncomDataList(IncomingVO vo) throws Exception;
    IncomingVO getJegoDataView(IncomingVO vo) throws Exception;
}
