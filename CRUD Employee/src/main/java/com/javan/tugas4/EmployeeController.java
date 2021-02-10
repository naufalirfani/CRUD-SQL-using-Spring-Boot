package com.javan.tugas4;

import com.javan.tugas4.service.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                 @RequestParam(value = "companyId", defaultValue = "0") int companyId) {
        if(operasi.equals("delete"))
            databaseService.deleteData(id);
        else if(operasi.equals("edit"))
            databaseService.updateData(nama, atasanId, companyId, id);

        databaseService.getData();
        model.addAttribute("employeeList", databaseService.employeeList);
        return "employeeTable";
    }
}
