package com.example.excelImport.Controller;
import com.example.excelImport.Entity.ProductEntity;
import com.example.excelImport.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ProductController {

    @Autowired
   private ProductService productService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        try{
            productService.saveExcelToDataBase(file);
            return "file uploaded and data saved successfully";
        }
        catch (Exception e ){
            e.printStackTrace();
            return "failed to upload file";
        }
    }
//hello
    @GetMapping("/show")
    public List<ProductEntity> showExcelData(){
        return productService.getAllProducts();
    }
}