package com.bethesda.raphaelsv.etcmng.controller;

import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import com.bethesda.raphaelsv.etcmng.service.ComcodeService;
import com.bethesda.raphaelsv.user.domain.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/etcmng/comcodeApi")
public class ComcodeApiController {
    @Autowired
    ComcodeService comcodeService;

    @PostMapping("/dataList")
    public HashMap goDataList(ComcodeVO vo) throws Exception {
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
        List<ComcodeVO> dataList = comcodeService.getDataList(vo);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataEdit")
    public HashMap goDataEdit(ComcodeVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = comcodeService.insData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(ComcodeVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int dataState = comcodeService.delData(vo);

        resultMap.put("dataState", dataState);

        return resultMap;
    }
}
