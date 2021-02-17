package com.javan.crudwilayah;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CRUDWilayahController {

    DatabaseService databaseService = new DatabaseService();

    @GetMapping("/wilayah")
    public String mainEmployee(){
        return "mainWilayah";
    }

    @GetMapping("/wilayah/provinsi")
    public String getProvinsi(Model model){
        databaseService.getDataProvinsi();
        model.addAttribute("provinsiList", databaseService.provinsiList);
        return "provinsi";
    }

    @PostMapping("/wilayah/provinsi")
    public String postProvinsi(Model model,
                               @RequestParam("provinsiid") String provinsiid,
                               @RequestParam("nama") String provinsi,
                               @RequestParam("operasi") String operasi){

        switch (operasi){
            case "insert":
                String queryInsert = " insert into provinsi (nama)"
                        + " values ('%s')";
                queryInsert = String.format(queryInsert, provinsi);
                databaseService.insertData(queryInsert);
                break;
            case "update":
                String query = "update provinsi set nama = '%s' where provinsiid = %d";
                query = String.format(query, provinsi, Integer.parseInt(provinsiid));
                databaseService.updateData(query);
                break;
            case "delete":
                String queryDelete = "delete from provinsi where provinsiid = %d";
                queryDelete = String.format(queryDelete, Integer.parseInt(provinsiid));
                String query2 = "ALTER TABLE `provinsi` AUTO_INCREMENT = 1";
                databaseService.deleteData(queryDelete, query2);
                break;
            default:
                break;
        }
        databaseService.getDataProvinsi();
        model.addAttribute("provinsiList", databaseService.provinsiList);
        return "provinsi";
    }

    @GetMapping("/wilayah/provinsi/update")
    public String getUpdateProvinsi(Model model,
                                    @RequestParam("provinsiid") String provinsiid,
                                    @RequestParam("nama") String provinsi){
        model.addAttribute("provinsiid", provinsiid);
        model.addAttribute("nama", provinsi);
        return "updateProvinsi";
    }
}
