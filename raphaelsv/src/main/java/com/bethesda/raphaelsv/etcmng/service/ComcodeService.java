package com.bethesda.raphaelsv.etcmng.service;

import com.bethesda.raphaelsv.etcmng.domain.ComcodeVO;
import com.bethesda.raphaelsv.etcmng.mapper.Comcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComcodeService {

    @Autowired
    Comcode comcode;

    public int insData(ComcodeVO vo) throws Exception {
        return comcode.insData(vo);
    }

    public int delData(ComcodeVO vo) throws Exception {
        return comcode.delData(vo);
    }

    public List<ComcodeVO> getDataList(ComcodeVO vo) throws Exception {
        return comcode.getDataList(vo);
    }
}
