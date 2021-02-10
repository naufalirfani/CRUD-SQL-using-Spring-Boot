package com.javan.tugas4;

import com.javan.tugas4.service.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyController {

    DatabaseService databaseService = new DatabaseService();

    @GetMapping("/company")
    public String mainEmployee(){
        return "mainCompany";
    }

    @GetMapping("/company/showtable")
    public String showEmployee(Model model) {
        databaseService.getData();
        model.addAttribute("companyList", databaseService.companyList);
        return "companyTable";
    }

    @GetMapping("/company/add")
    public String addEmployee(Model model) {
        return "addCompany";
    }

    @GetMapping("/company/edit")
    public String editEmployee(Model model,
                               @RequestParam(value = "nama") String nama,
                               @RequestParam(value = "alamat") String alamat,
                               @RequestParam(value = "id") int id) {
        model.addAttribute("nama", nama);
        model.addAttribute("alamat", alamat);
        model.addAttribute("id", id);
        return "editCompany";
    }

    @PostMapping("/company")
    public String addEmployee(@RequestParam(value = "nama", defaultValue = "") String nama,
                              @RequestParam(value = "alamat", defaultValue = "0") String alamat){

        if(!nama.equals("") && !alamat.equals(""))
            databaseService.insertData(nama, alamat);

        return "mainCompany";
    }

    @PostMapping("/company/showtable")
    public String postEmployee(@RequestParam(value = "operasi", defaultValue = "") String operasi,
                                 @RequestParam(value = "id", defaultValue = "0") int id,
                                 Model model,
                                 @RequestParam(value = "nama", defaultValue = "") String nama,
                                 @RequestParam(value = "alamat", defaultValue = "0") String alamat) {
        if(operasi.equals("delete"))
            databaseService.deleteData(id);
        else if(operasi.equals("edit"))
            databaseService.updateData(nama, alamat, id);

        databaseService.getData();
        model.addAttribute("companyList", databaseService.companyList);
        return "companyTable";
    }
}
