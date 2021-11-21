package com.bethesda.raphaelsv.incom.controller;

import com.bethesda.raphaelsv.basic.domain.ProductVO;
import com.bethesda.raphaelsv.incom.domain.IncomingVO;
import com.bethesda.raphaelsv.incom.domain.SupportVO;
import com.bethesda.raphaelsv.incom.service.IncomingService;
import com.bethesda.raphaelsv.incom.service.SupportService;
import com.bethesda.raphaelsv.util.DateTimeUtil;
import com.bethesda.raphaelsv.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
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

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String TAG_USER = "METADATA";

    @Value("${excel.fileDownPath.window}")
    private String fileDownPathWin;

    @Value("${excel.fileDownPath.linux}")
    private String fileDownPathLinux;

    @PostMapping("/dataListExcel")
    public void goDataDownloadExcel(IncomingVO vo) throws Exception {
        String metaPath = "Incoming";
        String fileDownPath = OS.contains("win") ? fileDownPathWin : fileDownPathLinux;
        String filePath = new StringBuffer()
                .append(fileDownPath).append(File.separator)
                .append(metaPath).toString();
        File file = null;
        FileOutputStream outFile = null;
        String fileNm = metaPath + "_" + DateTimeUtil.getNowDateHb("_") + ".xlsx";
        SXSSFWorkbook wb = null;
        vo.setOffSet(-1);
        try {
            // keep 100 rows in memory, exceeding rows will be flushed to disk
            wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);

            // temp files will be gzipped
            wb.setCompressTempFiles(true);

            Sheet sheet = wb.createSheet();     // Sheet
            Row row = null;                     // Row
            Cell cell = null;                   // Cell

            List<Integer> width = new ArrayList<Integer>();
            for (int i = 0; i < 8; i++) {
                width.add(20);
            }

            for (int i = 0; i < width.size(); i++) {
                sheet.setColumnWidth(i, width.get(i) * 256);
            }

            int rowIdx = 0;

            // Header 첫줄
            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setWrapText(true);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setBorderTop(CellStyle.BORDER_THIN);
            headerStyle.setBorderRight(CellStyle.BORDER_THIN);
            headerStyle.setBorderBottom(CellStyle.BORDER_THIN);

            List<String> header = new ArrayList<String>();
            List<String> dataKeys = new ArrayList<String>();

            header.add("입고일");
            header.add("코드");
            header.add("성분명");
            header.add("용량");
            header.add("제약사");
            header.add("상품명");
            header.add("Expired Date");
            header.add("입고규격");
            header.add("입고량");
            header.add("입고수량");
            header.add("잔고수량");
            header.add("비고");

            dataKeys.add("regdt");
            dataKeys.add("ingcd");
            dataKeys.add("ingnm");
            dataKeys.add("capacity");
            dataKeys.add("phanm");
            dataKeys.add("prdnm");
            dataKeys.add("expdt");
            dataKeys.add("incomstd");
            dataKeys.add("incomcnt");
            dataKeys.add("incomstdcnt");
            dataKeys.add("nowcnt");
            dataKeys.add("bigo");

            row = sheet.createRow(rowIdx++);
            for (int i = 0; i < header.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(header.get(i));
                cell.setCellStyle(headerStyle);
            }

            // Data Style 지정
            CellStyle dataStyle = wb.createCellStyle();
            dataStyle.setWrapText(true);
            dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
            dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            dataStyle.setBorderTop(CellStyle.BORDER_DASHED);
            dataStyle.setBorderRight(CellStyle.BORDER_DASHED);
            dataStyle.setBorderBottom(CellStyle.BORDER_DASHED);

            //취소선
            CellStyle soStyle = wb.createCellStyle();
            soStyle.cloneStyleFrom(dataStyle);
            Font soFont = wb.createFont();
            soFont.setStrikeout(true);
            soStyle.setFont(soFont);

            List<IncomingVO> dataList = incomingService.getDataList(vo);

            if (dataList.size() > 100000) {
                row = sheet.createRow(rowIdx++);
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("검색된 데이타가 10만건이 넘었습니다. 검색 범위를 재설정하시거나 관리자에게 문의하세요.");
                cell.setCellStyle(dataStyle);
            } else {
                for (IncomingVO excelRowData : dataList) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map result = objectMapper.convertValue(excelRowData, Map.class);
                    row = sheet.createRow(rowIdx++);
                    for (int i = 0; i < dataKeys.size(); i++) {
                        cell = row.createCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String temp = "";
                        if ( result.get(dataKeys.get(i)) != null ) {
                            temp = result.get(dataKeys.get(i)).toString();
                        }
                        cell.setCellValue(temp);
                        cell.setCellStyle(dataStyle);
                    }
                }
            }

            File Folder = new File(filePath);
            if (!Folder.exists()) {
                try{
                    Folder.mkdirs();
                    log.info(TAG_USER + " : " +  "Create Folder Success");
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            } else {
                log.info(TAG_USER + " : " +  "The folder already exists");
            }

            String tempfile = new StringBuffer()
                    .append(filePath).append(File.separator)
                    .append(DateTimeUtil.getNowDate()).toString();

            outFile = new FileOutputStream(tempfile);
            file = new File(tempfile);
            wb.write(outFile);
            wb.dispose();

            //파일다운로드.
            WebUtil.downFile(file, fileNm);

        } catch (Exception e) {
            if (outFile != null) try {
                outFile.close();
            } catch (Exception ignore) {
            }
        } finally {
            try {
                wb.close();
                wb.dispose();
                if (outFile != null) try {
                    outFile.close();
                } catch (Exception ignore) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/dataListJegoExcel")
    public void goDataJegoDownloadExcel(IncomingVO vo) throws Exception {
        String metaPath = "IncomingJego";
        String fileDownPath = OS.contains("win") ? fileDownPathWin : fileDownPathLinux;
        String filePath = new StringBuffer()
                .append(fileDownPath).append(File.separator)
                .append(metaPath).toString();
        File file = null;
        FileOutputStream outFile = null;
        String fileNm = metaPath + "_" + DateTimeUtil.getNowDateHb("_") + ".xlsx";
        SXSSFWorkbook wb = null;
        vo.setOffSet(-1);
        try {
            // keep 100 rows in memory, exceeding rows will be flushed to disk
            wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);

            // temp files will be gzipped
            wb.setCompressTempFiles(true);

            Sheet sheet = wb.createSheet();     // Sheet
            Row row = null;                     // Row
            Cell cell = null;                   // Cell

            List<Integer> width = new ArrayList<Integer>();
            for (int i = 0; i < 8; i++) {
                width.add(20);
            }

            for (int i = 0; i < width.size(); i++) {
                sheet.setColumnWidth(i, width.get(i) * 256);
            }

            int rowIdx = 0;

            // Header 첫줄
            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setWrapText(true);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setBorderTop(CellStyle.BORDER_THIN);
            headerStyle.setBorderRight(CellStyle.BORDER_THIN);
            headerStyle.setBorderBottom(CellStyle.BORDER_THIN);

            List<String> header = new ArrayList<String>();
            List<String> dataKeys = new ArrayList<String>();

            header.add("분류");
            header.add("코드");
            header.add("성분명");
            header.add("용량");
            header.add("제약사");
            header.add("상품명");
            header.add("잔고");
            header.add("최종출고일");
            header.add("최종입고일");

            dataKeys.add("iclass");
            dataKeys.add("ingcd");
            dataKeys.add("ingnm");
            dataKeys.add("capacity");
            dataKeys.add("phanm");
            dataKeys.add("prdnm");
            dataKeys.add("nowcnt");
            dataKeys.add("reldt");
            dataKeys.add("incdt");


            row = sheet.createRow(rowIdx++);
            for (int i = 0; i < header.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(header.get(i));
                cell.setCellStyle(headerStyle);
            }

            // Data Style 지정
            CellStyle dataStyle = wb.createCellStyle();
            dataStyle.setWrapText(true);
            dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
            dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            dataStyle.setBorderTop(CellStyle.BORDER_DASHED);
            dataStyle.setBorderRight(CellStyle.BORDER_DASHED);
            dataStyle.setBorderBottom(CellStyle.BORDER_DASHED);

            //취소선
            CellStyle soStyle = wb.createCellStyle();
            soStyle.cloneStyleFrom(dataStyle);
            Font soFont = wb.createFont();
            soFont.setStrikeout(true);
            soStyle.setFont(soFont);

            List<IncomingVO> dataList = incomingService.getJegoDataList(vo);

            if (dataList.size() > 100000) {
                row = sheet.createRow(rowIdx++);
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("검색된 데이타가 10만건이 넘었습니다. 검색 범위를 재설정하시거나 관리자에게 문의하세요.");
                cell.setCellStyle(dataStyle);
            } else {
                for (IncomingVO excelRowData : dataList) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map result = objectMapper.convertValue(excelRowData, Map.class);
                    row = sheet.createRow(rowIdx++);
                    for (int i = 0; i < dataKeys.size(); i++) {
                        cell = row.createCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String temp = "";
                        if ( result.get(dataKeys.get(i)) != null ) {
                            temp = result.get(dataKeys.get(i)).toString();
                        }
                        cell.setCellValue(temp);
                        cell.setCellStyle(dataStyle);
                    }
                }
            }

            File Folder = new File(filePath);
            if (!Folder.exists()) {
                try{
                    Folder.mkdirs();
                    log.info(TAG_USER + " : " +  "Create Folder Success");
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            } else {
                log.info(TAG_USER + " : " +  "The folder already exists");
            }

            String tempfile = new StringBuffer()
                    .append(filePath).append(File.separator)
                    .append(DateTimeUtil.getNowDate()).toString();

            outFile = new FileOutputStream(tempfile);
            file = new File(tempfile);
            wb.write(outFile);
            wb.dispose();

            //파일다운로드.
            WebUtil.downFile(file, fileNm);

        } catch (Exception e) {
            if (outFile != null) try {
                outFile.close();
            } catch (Exception ignore) {
            }
        } finally {
            try {
                wb.close();
                wb.dispose();
                if (outFile != null) try {
                    outFile.close();
                } catch (Exception ignore) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/incomDataExcel")
    public void goIcomDataDownloadExcel(IncomingVO vo) throws Exception {
        String metaPath = "incomData";
        String fileDownPath = OS.contains("win") ? fileDownPathWin : fileDownPathLinux;
        String filePath = new StringBuffer()
                .append(fileDownPath).append(File.separator)
                .append(metaPath).toString();
        File file = null;
        FileOutputStream outFile = null;
        String fileNm = metaPath + "_" + DateTimeUtil.getNowDateHb("_") + ".xlsx";
        SXSSFWorkbook wb = null;
        vo.setOffSet(-1);
        try {
            // keep 100 rows in memory, exceeding rows will be flushed to disk
            wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);

            // temp files will be gzipped
            wb.setCompressTempFiles(true);

            Sheet sheet = wb.createSheet();     // Sheet
            Row row = null;                     // Row
            Cell cell = null;                   // Cell

            List<Integer> width = new ArrayList<Integer>();
            for (int i = 0; i < 8; i++) {
                width.add(20);
            }

            for (int i = 0; i < width.size(); i++) {
                sheet.setColumnWidth(i, width.get(i) * 256);
            }

            int rowIdx = 0;

            // Header 첫줄
            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setWrapText(true);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setBorderTop(CellStyle.BORDER_THIN);
            headerStyle.setBorderRight(CellStyle.BORDER_THIN);
            headerStyle.setBorderBottom(CellStyle.BORDER_THIN);

            List<String> header = new ArrayList<String>();
            List<String> dataKeys = new ArrayList<String>();

            header.add("Expired Date");
            header.add("제약사");
            header.add("상품명");
            header.add("입고일");
            header.add("입고수량");
            header.add("입고규격");
            header.add("입고량");
            header.add("재고량");

            dataKeys.add("expdt");
            dataKeys.add("phanm");
            dataKeys.add("prdnm");
            dataKeys.add("regdt");
            dataKeys.add("incomstd");
            dataKeys.add("incomcnt");
            dataKeys.add("incomstdcnt");
            dataKeys.add("nowcnt");

            row = sheet.createRow(rowIdx++);
            for (int i = 0; i < header.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(header.get(i));
                cell.setCellStyle(headerStyle);
            }

            // Data Style 지정
            CellStyle dataStyle = wb.createCellStyle();
            dataStyle.setWrapText(true);
            dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
            dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            dataStyle.setBorderTop(CellStyle.BORDER_DASHED);
            dataStyle.setBorderRight(CellStyle.BORDER_DASHED);
            dataStyle.setBorderBottom(CellStyle.BORDER_DASHED);

            //취소선
            CellStyle soStyle = wb.createCellStyle();
            soStyle.cloneStyleFrom(dataStyle);
            Font soFont = wb.createFont();
            soFont.setStrikeout(true);
            soStyle.setFont(soFont);

            List<IncomingVO> dataList = incomingService.getIncomDataList(vo);

            if (dataList.size() > 100000) {
                row = sheet.createRow(rowIdx++);
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("검색된 데이타가 10만건이 넘었습니다. 검색 범위를 재설정하시거나 관리자에게 문의하세요.");
                cell.setCellStyle(dataStyle);
            } else {
                for (IncomingVO excelRowData : dataList) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map result = objectMapper.convertValue(excelRowData, Map.class);
                    row = sheet.createRow(rowIdx++);
                    for (int i = 0; i < dataKeys.size(); i++) {
                        cell = row.createCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String temp = "";
                        if ( result.get(dataKeys.get(i)) != null ) {
                            temp = result.get(dataKeys.get(i)).toString();
                        }
                        cell.setCellValue(temp);
                        cell.setCellStyle(dataStyle);
                    }
                }
            }

            File Folder = new File(filePath);
            if (!Folder.exists()) {
                try{
                    Folder.mkdirs();
                    log.info(TAG_USER + " : " +  "Create Folder Success");
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            } else {
                log.info(TAG_USER + " : " +  "The folder already exists");
            }

            String tempfile = new StringBuffer()
                    .append(filePath).append(File.separator)
                    .append(DateTimeUtil.getNowDate()).toString();

            outFile = new FileOutputStream(tempfile);
            file = new File(tempfile);
            wb.write(outFile);
            wb.dispose();

            //파일다운로드.
            WebUtil.downFile(file, fileNm);

        } catch (Exception e) {
            if (outFile != null) try {
                outFile.close();
            } catch (Exception ignore) {
            }
        } finally {
            try {
                wb.close();
                wb.dispose();
                if (outFile != null) try {
                    outFile.close();
                } catch (Exception ignore) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
