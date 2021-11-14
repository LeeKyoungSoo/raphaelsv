package com.bethesda.raphaelsv.basic.controller;

import com.bethesda.raphaelsv.basic.domain.IngredientVO;
import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.basic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/basic/productApi")
public class ProductApiController {
    @Autowired
    ProductService productService;

    @PostMapping("/iclassList")
    public HashMap goIclassList(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<ProductVO> dataList = productService.getIclassList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/ingcdList")
    public HashMap goIngcdList(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<ProductVO> dataList = productService.getIngcdList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/ingnmList")
    public HashMap goIngnmList(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<ProductVO> dataList = productService.getIngnmList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/phacdList")
    public HashMap goPhacdList(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<ProductVO> dataList = productService.getPhacdList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/prdnmList")
    public HashMap goPrdnmList(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<ProductVO> dataList = productService.getPrdnmList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataList")
    public HashMap goDataList(ProductVO vo) throws Exception {
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
        List<ProductVO> dataList = productService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(ProductVO vo, Principal principal) throws Exception {
        HashMap resultMap = new HashMap<>();

        vo.setRegid(principal.getName());
        vo.setModid(principal.getName());
        int dataState = productService.insData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(ProductVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = productService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
