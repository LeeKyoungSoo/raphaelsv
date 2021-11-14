package com.bethesda.raphaelsv.basic.service;

import com.bethesda.raphaelsv.basic.domain.IngredientVO;
import com.bethesda.raphaelsv.basic.mapper.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    Ingredient ingredient;

    public int insData(IngredientVO vo) throws Exception {
        return ingredient.insData(vo);
    }

    public int delData(IngredientVO vo) throws Exception {
        return ingredient.delData(vo);
    }

    public List<IngredientVO> getDataList(IngredientVO vo) throws Exception {
        return ingredient.getDataList(vo);
    }

    public List<IngredientVO> getIclassList(IngredientVO vo) throws Exception {
        return ingredient.getIclassList(vo);
    }

    public List<IngredientVO> getIngcdList(IngredientVO vo) throws Exception {
        return ingredient.getIngcdList(vo);
    }

    public List<IngredientVO> getIngnmList(IngredientVO vo) throws Exception {
        return ingredient.getIngnmList(vo);
    }
}
