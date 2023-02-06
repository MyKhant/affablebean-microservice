package com.example.affablebeanbackend.controller;

import com.example.affablebeanbackend.dao.CategoryDao;
import com.example.affablebeanbackend.entity.Category;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/backend")
public class ProductController {

    @Autowired
    private CategoryDao categoryDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @Transactional
    @SneakyThrows
    @ResponseBody
    @PostMapping("/read-excel")
    public String readExcel(@RequestParam("file")MultipartFile file){
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Category> categories = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Category category = new Category();
            Iterator<Cell> cellIterator = null;
            if (row.getRowNum() != 0){
                cellIterator = row.cellIterator();

                while (cellIterator.hasNext()){

                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() == 0) {
                        category.setId((int) cell.getNumericCellValue());
                        System.out.print((int) cell.getNumericCellValue() + "\t\t");
                    } else if (cell.getColumnIndex() == 1) {
                        category.setName(cell.getStringCellValue());
                        System.out.println(cell.getStringCellValue());
                    }
                }
            }
            if (category.getName() != null){
                categories.add(category);
            }

        }

       categories.forEach(System.out::println);
        categories.forEach(categoryDao :: save);
        return "success";
    }

}
