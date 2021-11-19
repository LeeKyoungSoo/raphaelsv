package com.bethesda.raphaelsv.incom.controller;

import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/incom/supportApi")
public class SupportApiController {
    @Autowired
    SupportService supportService;

    @PostMapping("/dataList")
    public HashMap goDataList(SupportVO vo) throws Exception {
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
        List<SupportVO> dataList = supportService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(SupportVO vo, Principal principal) throws Exception {
        HashMap resultMap = new HashMap<>();

        vo.setRegid(principal.getName());
        vo.setModid(principal.getName());
        int dataState = supportService.insData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(SupportVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = supportService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/udtDeccnt")
    public HashMap goUdtDeccnt(SupportVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = supportService.udtDeccnt(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

}
