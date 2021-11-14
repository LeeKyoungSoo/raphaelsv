package com.bethesda.raphaelsv.basic.service;

import com.bethesda.raphaelsv.basic.domain.IngredientVO;
import com.bethesda.raphaelsv.basic.domain.PharmaVO;
import com.bethesda.raphaelsv.basic.mapper.Ingredient;
import com.bethesda.raphaelsv.basic.mapper.Pharma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmaService {

    @Autowired
    Pharma pharma;

    public int insData(PharmaVO vo) throws Exception {
        return pharma.insData(vo);
    }

    public int delData(PharmaVO vo) throws Exception {
        return pharma.delData(vo);
    }

    public List<PharmaVO> getDataList(PharmaVO vo) throws Exception {
        return pharma.getDataList(vo);
    }

    public List<PharmaVO> getPharmaList(PharmaVO vo) throws Exception {
        return pharma.getPharmaList(vo);
    }
}
