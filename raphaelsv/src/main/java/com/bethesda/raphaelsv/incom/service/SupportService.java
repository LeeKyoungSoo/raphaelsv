package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.basic.mapper.Product;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.mapper.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportService {

    @Autowired
    Support support;

    public int insData(SupportVO vo) throws Exception {
        return support.insData(vo);
    }

    public int delData(SupportVO vo) throws Exception {
        return support.delData(vo);
    }

    public List<SupportVO> getDataList(SupportVO vo) throws Exception {
        return support.getDataList(vo);
    }

    public int udtDeccnt(SupportVO vo) throws Exception {
        return support.udtDeccnt(vo);
    }
}
