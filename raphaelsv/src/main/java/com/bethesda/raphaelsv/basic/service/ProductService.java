package com.bethesda.raphaelsv.basic.service;

import com.bethesda.raphaelsv.basic.domain.PharmaVO;
import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.basic.mapper.Pharma;
import com.bethesda.raphaelsv.basic.mapper.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    Product product;

    public int insData(ProductVO vo) throws Exception {
        return product.insData(vo);
    }

    public int delData(ProductVO vo) throws Exception {
        return product.delData(vo);
    }

    public List<ProductVO> getDataList(ProductVO vo) throws Exception {
        return product.getDataList(vo);
    }

    public List<ProductVO> getIclassList(ProductVO vo) throws Exception {
        return product.getIclassList(vo);
    }

    public List<ProductVO> getIngcdList(ProductVO vo) throws Exception {
        return product.getIngcdList(vo);
    }

    public List<ProductVO> getIngnmList(ProductVO vo) throws Exception {
        return product.getIngnmList(vo);
    }

    public List<ProductVO> getPhacdList(ProductVO vo) throws Exception {
        return product.getPhacdList(vo);
    }

    public List<ProductVO> getPrdnmList(ProductVO vo) throws Exception {
        return product.getPrdnmList(vo);
    }
}
