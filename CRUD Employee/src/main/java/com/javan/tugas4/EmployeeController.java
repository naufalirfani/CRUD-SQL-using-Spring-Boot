package com.javan.tugas4;

import com.javan.tugas4.service.DatabaseService;
import com.javan.tugas4.service.EmployeeExcelExporter;
import com.javan.tugas4.service.EmployeePDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EmployeeController {

    DatabaseService databaseService = new DatabaseService();

    @GetMapping("/employee")
    public String mainEmployee(){
        return "mainEmployee";
    }

    @GetMapping("/employee/showtable")
    public String showEmployee(Model model) {
        databaseService.getData();
        model.addAttribute("employeeList", databaseService.employeeList);
        model.addAttribute("employeeListPrint", databaseService.employeeListPrint);
        return "employeeTable";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model) {
        return "addEmployee";
    }

    @GetMapping("/employee/edit")
    public String editEmployee(Model model,
                               @RequestParam(value = "nama") String nama,
                               @RequestParam(value = "atasanId") int atasanId,
                               @RequestParam(value = "companyId") String company,
                               @RequestParam(value = "id") int id) {
        model.addAttribute("nama", nama);
        model.addAttribute("atasanId", atasanId);
        model.addAttribute("company", company);
        model.addAttribute("id", id);
        return "editEmployee";
    }

    @PostMapping("/employee")
    public String addEmployee(@RequestParam(value = "nama", defaultValue = "") String nama,
                              @RequestParam(value = "atasanId", defaultValue = "0") int atasanId,
                              @RequestParam(value = "companyId", defaultValue = "0") int companyId){

        if(!nama.equals("") && atasanId != 0 && companyId != 0)
            databaseService.insertData(nama, atasanId, companyId);

        return "mainEmployee";
    }

    @PostMapping("/employee/showtable")
    public String postEmployee(@RequestParam(value = "operasi", defaultValue = "") String operasi,
                               @RequestParam(value = "id", defaultValue = "0") int id,
                               Model model,
                               @RequestParam(value = "nama", defaultValue = "") String nama,
                               @RequestParam(value = "atasanId", defaultValue = "0") int atasanId,
                               @RequestParam(value = "companyId", defaultValue = "0") int companyId,
                               HttpServletResponse response) throws DocumentException, IOException {

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";

        switch (operasi) {
            case "delete":
                databaseService.deleteData(id);
                break;
            case "edit":
                databaseService.updateData(nama, atasanId, companyId, id);
                break;
            case "exportPDF":
                response.setContentType("application/pdf");
                String headerValuePdf = "attachment; filename=TableEmployee_" + currentDateTime + ".pdf";
                response.setHeader(headerKey, headerValuePdf);
                EmployeePDFExporter exporter = new EmployeePDFExporter(databaseService.employeeListPrint);
                exporter.export(response);
                break;
            case "exportExcel":
                response.setContentType("application/octet-stream");
                String headerValueExcel = "attachment; filename=TableEmployee_" + currentDateTime + ".xlsx";
                response.setHeader(headerKey, headerValueExcel);
                EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(databaseService.employeeListPrint);
                excelExporter.export(response);
                break;
            default:
                break;
        }
        databaseService.getData();
        model.addAttribute("employeeList", databaseService.employeeList);
        model.addAttribute("employeeListPrint", databaseService.employeeListPrint);
        return "employeeTable";
    }
}
