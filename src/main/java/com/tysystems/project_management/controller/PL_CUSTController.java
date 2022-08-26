package com.tysystems.project_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tysystems.batch.support.BatchService;
import com.tysystems.file.EachFilePaths;
import com.tysystems.file.FilePathDTO;
import com.tysystems.file.FileStatus;
import com.tysystems.file.plcust.PLCUSTDataFormValidator;

//import antlr.StringUtils;

import com.tysystems.file.service.FileService;
import com.tysystems.project_management.domain.CompositeKey;
import com.tysystems.project_management.domain.PL_CUST;
import com.tysystems.project_management.dto.PL_CUSTVO;
import com.tysystems.project_management.dto.PL_CUSTVOList;
import com.tysystems.project_management.service.PL_CUSTService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Controller
@AllArgsConstructor
//@RestController
//@RequestMapping("/custs") // 주로 명사, 복수로 생성
public class PL_CUSTController {

    PL_CUSTService pl_custService;
    FileService<PL_CUSTVO> fileService;
    BatchService batchService;

    /*@Autowired
    public PL_CUSTController(PL_CUSTService pl_custService, FileService<PL_CUSTVO> fileService) {
        this.pl_custService = pl_custService;
        this.fileService = fileService;
    }*/

    // 전체 조회
    @GetMapping("/custs")
    public String getLists(Model model, @RequestParam(value = "page", required = false) Integer page/*, @RequestParam("size") Integer size*/) {
        // 처음 진입시 page는 null이므로 1 넣어주기.
        if(isEmpty(page)) page = 1;

        PageRequest pageRequest = PageRequest.of(page - 1, 20);
        Page<PL_CUST> custs = pl_custService.getLists(pageRequest);
        model.addAttribute("custs", custs);

        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/custs")
    public String postMethod(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<PL_CUST> custs = pl_custService.getLists(pageRequest);
        model.addAttribute("custs", custs);
        return "index";
    }

    // 삭제
    @RequestMapping(value = "/cust/delete", method = RequestMethod.GET)
    public String deleteCust(@RequestParam String cust_code, @RequestParam String cust_name) {
        CompositeKey compositeKey = new CompositeKey();
        compositeKey.setCust_code(cust_code);
        compositeKey.setCust_name(cust_name);
        compositeKey.setCompany("TSS");
        compositeKey.setBusiness_unit("TSSBU");

        pl_custService.delete(compositeKey);
        return "redirect:/custs";
    }
    // 키로 하나 조회
    @RequestMapping(value = "/cust/search", method = RequestMethod.GET)
    public String getByCust(Model model, @RequestParam String cust_code, @RequestParam String cust_name) {
        CompositeKey compositeKey = new CompositeKey();
        compositeKey.setCust_code(cust_code);
        compositeKey.setCust_name(cust_name);
        compositeKey.setCompany("TSS");
        compositeKey.setBusiness_unit("TSSBU");

        PL_CUST pl_cust = pl_custService.getList(compositeKey);
        model.addAttribute("cust", pl_cust);
        return "customer";
    }
    // 추가
    @RequestMapping(value = "/cust/insert", method = RequestMethod.GET)
    public String saveOne(Model model, @RequestParam String cust_code, @RequestParam String cust_name) {
        PL_CUST pl_cust = new PL_CUST();
        pl_cust.setCompany("TSS");
        pl_cust.setBusiness_unit("TSSBU");
        pl_cust.setCust_code(cust_code);
        pl_cust.setCust_name(cust_name);
        pl_custService.save(pl_cust);
        return "redirect:/custs";
    }
    // 선택
    @RequestMapping(value = "/cust/select", method = RequestMethod.GET)
    public String selectCust(Model model, @RequestParam String cust_code, @RequestParam String cust_name) {
        CompositeKey compositeKey = new CompositeKey();
        compositeKey.setCust_code(cust_code);
        compositeKey.setCust_name(cust_name);
        compositeKey.setCompany("TSS");
        compositeKey.setBusiness_unit("TSSBU");

        PL_CUST pl_cust = pl_custService.getList(compositeKey);
        model.addAttribute("cust", pl_cust);
        return "selectCust";
    }
    // 수정
    @RequestMapping(value = "/cust/update", method = RequestMethod.GET)
    public String update(@RequestParam String company, @RequestParam String business_unit
            , @RequestParam String cust_code, @RequestParam String cust_name
            , @RequestParam String cust_desc, @RequestParam String status
            , @RequestParam String grade, @RequestParam String reg_num) {
        PL_CUST pl_cust = new PL_CUST();
        pl_cust.setCompany(company);
        pl_cust.setBusiness_unit(business_unit);
        pl_cust.setCust_code(cust_code);
        pl_cust.setCust_name(cust_name);
        pl_cust.setCust_desc(cust_desc);
        pl_cust.setStatus(status);
        pl_cust.setGrade(grade);
        pl_cust.setReg_num(reg_num);

        pl_custService.save(pl_cust);
        return "redirect:/custs";
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/test")
    public String test(){


        try {
            File file = new File("C:\\Work\\Document\\file.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write("hello this is Spring / " + new Date() + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "test";
    }


    /*
     * 엑셀 파일 올리면, json으로 바꿔서 저장
     */
    @GetMapping("/cust/excelfile")
    public String excelFile() {
        return "cust/custexcelfile";
    }
    @PostMapping("/cust/excelfile")
    @ResponseBody
    public String excelFileSave(@RequestBody List<PL_CUSTVO> pl_CUSTVOList) {

        String excelFilePath = EachFilePaths.custFilePathWithoutName + "plcust_" + LocalDate.now() + ".json";

        // 파일 검사
        PLCUSTDataFormValidator validator = new PLCUSTDataFormValidator();
        int valiInt = validator.validateForm(pl_CUSTVOList);
        if (valiInt == 1) return FileStatus.isFileNull;
        else if (valiInt == 2) return FileStatus.isKeyNull;

        // 파일 검사시 이상이 없고, 
        // Spring Batch가 돌아가고 있는 중이면 파일 업로드 막음
        if (batchService.isBatchRunning()) return FileStatus.isRunning;
        else {
            fileService.saveFile(pl_CUSTVOList, excelFilePath);
            return FileStatus.completed;
        }

    }
    // BI-Matrix에서만 연결되는 페이지(/cust/excelfile로 연결해주는 페이지)
    @GetMapping("/cust/excelfile2")
    public String excelFile2() {
        return "cust/custexcelfile2";
    }

    /*
     * 저장된 엑셀 파일 목록 보여줌
     */
    @GetMapping("/cust/excelfile/list")
    public String excelFileList() {
        return "cust/custexcelfilelist";
    }
    @GetMapping("/cust/excelfile/list/names")
    @ResponseBody
    public List<FilePathDTO> excelFileGetList() {
        
        List<FilePathDTO> fileNames = fileService.fileList(EachFilePaths.custFilePathWithoutName);
        return fileNames;
    }

    /*
     * 저장된 엑셀 파일들 내용 확인 및 삭제
     */
    @GetMapping("/cust/excelfile/list/one")
    public String excelFileOne() {
        return "cust/custexcelfileone";
    }
    @DeleteMapping("/cust/excelfile/list/one")
    @ResponseBody
    public String excelFileOneDelete(@RequestParam("name") String name) {

        return String.valueOf(fileService.deleteFile(EachFilePaths.custFilePathWithoutName, name));
    }
    @GetMapping("/cust/excelfile/list/one/content")
    @ResponseBody
    public List<String> excelFileOneContent(@RequestParam("name") String name) throws FileNotFoundException, IOException {

        return fileService.fileContent(EachFilePaths.custFilePathWithoutName, name);
    }

}
