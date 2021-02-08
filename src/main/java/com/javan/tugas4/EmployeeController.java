package com.javan.tugas4;

import com.javan.tugas4.data.DataEmployee;
import com.javan.tugas4.service.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/employee")
    public String addEmployee(@RequestParam(value = "nama", defaultValue = "") String nama,
                              @RequestParam(value = "atasanId", defaultValue = "0") int atasanId,
                              @RequestParam(value = "companyId", defaultValue = "0") int companyId){

        if(!nama.equals("") && atasanId != 0 && companyId != 0)
            databaseService.insertData(nama, atasanId, companyId);

        return "mainEmployee";
    }

    @PostMapping("/employee/showtable")
    public String deleteEmployee(@RequestParam(value = "id", defaultValue = "dsada") String id) {

        System.out.println(id);
        return "employeeTable";
    }
}
