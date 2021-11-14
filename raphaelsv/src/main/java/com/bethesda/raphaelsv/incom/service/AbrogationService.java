package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.incom.domain.AbrogationVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.mapper.Abrogation;
import com.bethesda.raphaelsv.incom.mapper.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrogationService {

    @Autowired
    Abrogation abrogation;

    public int insData(AbrogationVO vo) throws Exception {
        return abrogation.insData(vo);
    }

    public int delData(AbrogationVO vo) throws Exception {
        return abrogation.delData(vo);
    }

    public List<AbrogationVO> getDataList(AbrogationVO vo) throws Exception {
        return abrogation.getDataList(vo);
    }
}
