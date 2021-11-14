package com.bethesda.raphaelsv.basic.controller;

import com.bethesda.raphaelsv.basic.domain.PharmaVO;
import com.bethesda.raphaelsv.basic.service.PharmaService;
import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import com.bethesda.raphaelsv.etcmng.service.ComcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/basic/pharmaApi")
public class PharmaApiController {
    @Autowired
    PharmaService pharmaService;

    @PostMapping("/dataList")
    public HashMap goDataList(PharmaVO vo) throws Exception {
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
        List<PharmaVO> dataList = pharmaService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/pharmaList")
    public HashMap goPharmaList(PharmaVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<PharmaVO> dataList = pharmaService.getPharmaList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(PharmaVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = pharmaService.insData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(PharmaVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = pharmaService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
