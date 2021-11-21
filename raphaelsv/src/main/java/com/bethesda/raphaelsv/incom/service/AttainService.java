package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.incom.domain.AttainVO;
import com.bethesda.raphaelsv.incom.domain.ReleaseVO;
import com.bethesda.raphaelsv.incom.mapper.Attain;
import com.bethesda.raphaelsv.incom.mapper.Release;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AttainService {
    @Autowired
    Attain attain;

    public List<AttainVO> getDataList(AttainVO vo) throws Exception {
        return attain.getDataList(vo);
    }
}
