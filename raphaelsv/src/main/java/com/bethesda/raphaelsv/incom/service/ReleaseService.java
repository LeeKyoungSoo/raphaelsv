package com.bethesda.raphaelsv.incom.service;

import com.bethesda.raphaelsv.incom.domain.ReleaseVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.mapper.Release;
import com.bethesda.raphaelsv.incom.mapper.Support;
import com.bethesda.raphaelsv.util.BaseMap;
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

    public int incomCount(ReleaseVO vo) throws Exception {
        return release.incomCount(vo);
    }

    public List<HashMap> uploadExcelData(MultipartFile excelFile) throws Exception {
        List<HashMap> list = new ArrayList<HashMap>();
        String[] cellList = {"regdt","iclass","ingcd","ingnm","phanm","prdnm", "relcnt", "bigo"};

        OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            HashMap baseMap = new HashMap();
            XSSFRow row = sheet.getRow(i);
            if ( row == null ) {
                continue;
            }

            XSSFCell cell;
            for (int j = 0; j < cellList.length; j++) {
                cell = row.getCell(j);
                if (cell != null) {
                    baseMap.put(cellList[j], cell);
                }
            }
            list.add(baseMap);
        }
        return list;
    }
}
