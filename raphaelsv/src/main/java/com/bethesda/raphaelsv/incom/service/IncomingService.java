package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.mapper.Incoming;
import com.bethesda.raphaelsv.incom.mapper.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingService {

    @Autowired
    Incoming incoming;

    public int insData(IncomingVO vo) throws Exception {
        return incoming.insData(vo);
    }

    public int delData(IncomingVO vo) throws Exception {
        return incoming.delData(vo);
    }

    public List<IncomingVO> getDataList(IncomingVO vo) throws Exception {
        return incoming.getDataList(vo);
    }

    public List<IncomingVO> getIncomingSupCdList(IncomingVO vo) throws Exception {
        return incoming.getIncomingSupCdList(vo);
    }

    public List<IncomingVO> getJegoDataList(IncomingVO vo) throws Exception {
        return incoming.getJegoDataList(vo);
    }

    public List<IncomingVO> getIncomDataList(IncomingVO vo) throws Exception {
        return incoming.getIncomDataList(vo);
    }

    public IncomingVO getJegoDataView(IncomingVO vo) throws Exception {
        return incoming.getJegoDataView(vo);
    }
}
