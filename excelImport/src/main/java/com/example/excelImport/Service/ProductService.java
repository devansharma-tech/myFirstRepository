package com.example.excelImport.Service;

import com.example.excelImport.Entity.ProductEntity;
import com.example.excelImport.Repository.ProductRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveExcelToDataBase(MultipartFile file) throws IOException {
        List<ProductEntity> productsList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet){
            if (row.getRowNum()==0){
                continue;
            }
            else {
                ProductEntity product = new ProductEntity();
                product.setProductId((int) row.getCell(0).getNumericCellValue());
                product.setProductName(row.getCell(1).getStringCellValue());
                product.setProductDesk(row.getCell(2).getStringCellValue());
                product.setProductPrice(row.getCell(3).getNumericCellValue());

                productsList.add(product);
            }
        }

        productRepository.saveAll(productsList);
        workbook.close();
    }

    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }
}

