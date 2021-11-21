package com.bethesda.raphaelsv.incom.mapper;

import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import com.bethesda.raphaelsv.incom.domain.ReleaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Release {
    int insData(ReleaseVO vo) throws Exception;
    int delData(ReleaseVO vo) throws Exception;
    List<ReleaseVO> getDataList(ReleaseVO vo) throws Exception;
    int incomCount(ReleaseVO vo) throws  Exception;
}
