package com.bethesda.raphaelsv.incom.controller;

import com.bethesda.raphaelsv.incom.domain.AbrogationVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.service.AbrogationService;
import com.bethesda.raphaelsv.incom.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/incom/abrogationApi")
public class AbrogationApiController {
    @Autowired
    AbrogationService abrogationService;

    @PostMapping("/dataList")
    public HashMap goDataList(AbrogationVO vo) throws Exception {
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
        List<AbrogationVO> dataList = abrogationService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(AbrogationVO vo, Principal principal) throws Exception {
        HashMap resultMap = new HashMap<>();
        int dataState = 0;

        vo.setRegid(principal.getName());
        vo.setModid(principal.getName());
        String[] arrIncomcd = vo.getIncomcd().split(",");
        String[] arrIscnt = vo.getIscnt().split(",");
        String[] arrPhacd = vo.getPhacd().split(",");
        String[] arrPrdnm = vo.getPrdnm().split(",");
        for (int i = 0; i < arrIncomcd.length; i++) {
            vo.setAbrcd(arrIncomcd[i].trim() + String.format("%02d", i));
            vo.setIncomcd(arrIncomcd[i].trim());
            vo.setAbrcnt(arrIscnt[i].trim());
            vo.setPhacd(arrPhacd[i].trim());
            vo.setPrdnm(arrPrdnm[i].trim());
            dataState = abrogationService.insData(vo);
        }
        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(AbrogationVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = abrogationService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
