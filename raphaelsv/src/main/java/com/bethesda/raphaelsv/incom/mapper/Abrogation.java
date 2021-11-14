package com.bethesda.raphaelsv.incom.mapper;

import com.bethesda.raphaelsv.incom.domain.AbrogationVO;
import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Abrogation {
    int insData(AbrogationVO vo) throws Exception;
    int delData(AbrogationVO vo) throws Exception;
    List<AbrogationVO> getDataList(AbrogationVO vo) throws Exception;
}
