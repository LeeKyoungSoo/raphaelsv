package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.incom.domain.ReleaseVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.mapper.Release;
import com.bethesda.raphaelsv.incom.mapper.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseService {

    @Autowired
    Release release;

    public int insData(ReleaseVO vo) throws Exception {
        return release.insData(vo);
    }

    public int delData(ReleaseVO vo) throws Exception {
        return release.delData(vo);
    }

    public List<ReleaseVO> getDataList(ReleaseVO vo) throws Exception {
        return release.getDataList(vo);
    }
}
