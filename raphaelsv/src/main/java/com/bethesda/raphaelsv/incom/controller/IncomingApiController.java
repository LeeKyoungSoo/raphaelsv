package com.bethesda.raphaelsv.incom.controller;

import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.service.IncomingService;
import com.bethesda.raphaelsv.incom.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/incom/incomingApi")
public class IncomingApiController {
    @Autowired
    IncomingService incomingService;

    @Autowired
    SupportService supportService;

    @PostMapping("/jegoDataView")
    public HashMap goJegoDataView(IncomingVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        IncomingVO dataView = incomingService.getJegoDataView(vo);
        resultMap.put("dataView", dataView);
        return resultMap;
    }

    @PostMapping("/incomingSupCdList")
    public HashMap goIngcdList(IncomingVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<IncomingVO> dataList = incomingService.getIncomingSupCdList(vo);
        resultMap.put("dataList", dataList);
        return resultMap;
    }

    @PostMapping("/incomDataList")
    public HashMap goIncomDataList(IncomingVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        List<IncomingVO> dataList = incomingService.getIncomDataList(vo);
        resultMap.put("dataList", dataList);
        return resultMap;
    }

    @PostMapping("/jegodataList")
    public HashMap goJegoDataList(IncomingVO vo) throws Exception {
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
        List<IncomingVO> dataList = incomingService.getJegoDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataList")
    public HashMap goDataList(IncomingVO vo) throws Exception {
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
        List<IncomingVO> dataList = incomingService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(IncomingVO vo, Principal principal) throws Exception {
        HashMap resultMap = new HashMap<>();
        int dataState = 0;
        int incomCount = 0;

        vo.setRegid(principal.getName());
        vo.setModid(principal.getName());

        if ( vo.getIncomcd() == null ) {

        } else {
            String[] arrIncomcd = vo.getIncomcd().split(",");
            String[] arrExpdt = vo.getExpdt().split(",");
            String[] arrIncomstd = vo.getIncomstd().split(",");
            String[] arrIncomcnt = vo.getIncomcnt().split(",");
            String[] arrIncomstdcnt = vo.getIncomstdcnt().split(",");

            for (int i = 0; i < arrExpdt.length; i++) {
                if (arrIncomcd[i].trim().equals("")) {
                    vo.setIncomcd(vo.getSupcd() + String.format("%02d", i));
                } else {
                    vo.setIncomcd(arrIncomcd[i]);
                }
                vo.setExpdt(arrExpdt[i].trim().replaceAll("-", ""));
                vo.setIncomstd(arrIncomstd[i].trim());
                vo.setIncomcnt(arrIncomcnt[i].trim());
                vo.setIncomstdcnt(arrIncomstdcnt[i].trim());
                incomCount += Integer.parseInt(arrIncomstdcnt[i].trim());
                dataState = incomingService.insData(vo);
            }

            SupportVO supportVO = new SupportVO();
            supportVO.setSupcd(vo.getSupcd());
            supportVO.setDeccnt(String.valueOf(incomCount));
            supportService.udtDeccnt(supportVO);

        }
        resultMap.put("dataState", dataState);
        resultMap.put("incomCount", incomCount);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(IncomingVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = incomingService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
