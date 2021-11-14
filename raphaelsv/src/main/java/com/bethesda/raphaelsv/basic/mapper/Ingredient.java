package com.bethesda.raphaelsv.basic.mapper;

import com.bethesda.raphaelsv.basic.domain.IngredientVO;
import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Ingredient {
    int insData(IngredientVO vo) throws Exception;
    int delData(IngredientVO vo) throws Exception;
    List<IngredientVO> getDataList(IngredientVO vo) throws Exception;
    List<IngredientVO> getIclassList(IngredientVO vo) throws Exception;
    List<IngredientVO> getIngcdList(IngredientVO vo) throws Exception;
    List<IngredientVO> getIngnmList(IngredientVO vo) throws Exception;
}
