package com.bethesda.raphaelsv.basic.mapper;

import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Product {
    int insData(ProductVO vo) throws Exception;
    int delData(ProductVO vo) throws Exception;
    List<ProductVO> getDataList(ProductVO vo) throws Exception;

    List<ProductVO> getIclassList(ProductVO vo) throws Exception;
    List<ProductVO> getIngcdList(ProductVO vo) throws Exception;
    List<ProductVO> getIngnmList(ProductVO vo) throws Exception;
    List<ProductVO> getPhacdList(ProductVO vo) throws Exception;
    List<ProductVO> getPrdnmList(ProductVO vo) throws Exception;
}
