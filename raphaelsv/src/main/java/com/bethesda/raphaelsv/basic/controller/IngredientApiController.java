package com.bethesda.raphaelsv.basic.controller;

import com.bethesda.raphaelsv.basic.domain.IngredientVO;
import com.bethesda.raphaelsv.basic.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/basic/ingredientApi")
public class IngredientApiController {
    @Autowired
    IngredientService ingredientService;

    @PostMapping("/dataList")
    public HashMap goDataList(IngredientVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        if ( (vo.getPageNum() == null) || vo.getPageNum().equals("") ) {
            vo.setOffSet(-1);
        }
        else {
            int nLimit = 10;
            if ( vo.getPageSize() != null && !vo.getPageSize().equals("") ) {
                nLimit = Integer.parseInt(vo.getPageSize());
            }
            int nOffSet = (Integer.parseInt(vo.getPageNum()) - 1) * nLimit;
            vo.setLimit(nLimit);
            vo.setOffSet(nOffSet);
        }
        List<IngredientVO> dataList = ingredientService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/iclassList")
    public HashMap goIclassList(IngredientVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<IngredientVO> dataList = ingredientService.getIclassList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/ingcdList")
    public HashMap goIngcdList(IngredientVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<IngredientVO> dataList = ingredientService.getIngcdList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/ingnmList")
    public HashMap goIngnmList(IngredientVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<IngredientVO> dataList = ingredientService.getIngnmList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(IngredientVO vo, Principal principal) throws Exception {
        HashMap resultMap = new HashMap<>();

        vo.setRegid(principal.getName());
        vo.setModid(principal.getName());
        int dataState = ingredientService.insData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(IngredientVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = ingredientService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
