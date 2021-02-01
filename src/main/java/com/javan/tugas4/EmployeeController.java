package com.javan.tugas4;

import com.javan.tugas4.service.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @GetMapping("/employee")
    public String mainEmployee(){
        return "mainEmployee";
    }

    @GetMapping("/employee/showtable")
    public String showEmployee(Model model) {
        DatabaseService databaseService = new DatabaseService();
        databaseService.getData();
        model.addAttribute("employeeList", databaseService.employeeList);
        return "employeeTable";
    }

}
