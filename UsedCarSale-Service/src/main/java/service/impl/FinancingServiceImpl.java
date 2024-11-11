package service.impl;

import com.dao.FinancingMapper;
import com.pojo.Financing;
import common.Assist;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FinancingService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Service
public class FinancingServiceImpl implements FinancingService {
    @Autowired
    private FinancingMapper financingMapper;

    @Override
    public long getFinancingRowCount(Assist assist) {
        return financingMapper.getFinancingRowCount(assist);
    }

    @Override
    public List<Financing> selectFinancing(Assist assist) {
        return financingMapper.selectFinancing(assist);
    }

    @Override
    public List<Financing> selectFinancingOfPaging(Assist assist) {
        return financingMapper.selectFinancingOfPaging(assist);
    }

    @Override
    public Financing selectFinancingByObj(Financing obj) {
        return financingMapper.selectFinancingByObj(obj);
    }

    @Override
    public Financing selectFinancingById(Integer id) {
        return financingMapper.selectFinancingById(id);
    }

    @Override
    public int insertFinancing(Financing value) {
        return financingMapper.insertFinancing(value);
    }

    @Override
    public int insertNonEmptyFinancing(Financing value) {
        return financingMapper.insertNonEmptyFinancing(value);
    }

    @Override
    public int insertFinancingByBatch(List<Financing> value) {
        return financingMapper.insertFinancingByBatch(value);
    }

    @Override
    public int deleteFinancingById(Integer id) {
        return financingMapper.deleteFinancingById(id);
    }

    @Override
    public int deleteFinancing(Assist assist) {
        return financingMapper.deleteFinancing(assist);
    }

    @Override
    public int updateFinancingById(Financing enti) {
        return financingMapper.updateFinancingById(enti);
    }

    @Override
    public int updateFinancing(Financing value, Assist assist) {
        return financingMapper.updateFinancing(value, assist);
    }

    @Override
    public int updateNonEmptyFinancingById(Financing enti) {
        return financingMapper.updateNonEmptyFinancingById(enti);
    }

    @Override
    public int updateNonEmptyFinancing(Financing value, Assist assist) {
        return financingMapper.updateNonEmptyFinancing(value, assist);
    }

    @Override
    public void getExcelData(OutputStream outputStream) throws IOException {
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("financing.companyId", 1));
        List<Financing> financingList = selectFinancing(assist);

        XSSFWorkbook sheets = new XSSFWorkbook();
        XSSFSheet sheet = sheets.createSheet();
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell titleCell0 = titleRow.createCell(0);
        XSSFCell titleCell1 = titleRow.createCell(1);
        XSSFCell titleCell2 = titleRow.createCell(2);

        titleCell0.setCellValue("时间");
        titleCell1.setCellValue("涉及金额");
        titleCell2.setCellValue("财务类别");

        int nowRowIndex = 1;
        for (Financing financing : financingList) {

            XSSFRow nowRow = sheet.createRow(nowRowIndex);

            Date financingTime = financing.getFinancingTime();
            Double financingMoney = financing.getFinancingMoney();
            String financingType = financing.getFinancingType();

            XSSFCell nowCell0 = nowRow.createCell(0);
            XSSFCell nowCell1 = nowRow.createCell(1);
            XSSFCell nowCell2 = nowRow.createCell(2);

            nowCell0.setCellValue(financingTime);
            nowCell1.setCellValue(financingMoney);
            nowCell2.setCellValue("1".equals(financingType) ? "收入" : "支出");

            nowRowIndex++;
        }

        sheets.write(outputStream);
    }

    public FinancingMapper getFinancingMapper() {
        return this.financingMapper;
    }

    public void setFinancingMapper(FinancingMapper financingMapper) {
        this.financingMapper = financingMapper;
    }

}