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
        return "provinsi/provinsi";
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
        return "provinsi/provinsi";
    }

    @GetMapping("/wilayah/provinsi/update")
    public String getUpdateProvinsi(Model model,
                                    @RequestParam("provinsiid") String provinsiid,
                                    @RequestParam("nama") String provinsi){
        model.addAttribute("provinsiid", provinsiid);
        model.addAttribute("nama", provinsi);
        return "provinsi/updateProvinsi";
    }

    @GetMapping("/wilayah/kabupaten")
    public String getKabupaten(Model model){
        databaseService.getDataKabupaten();
        model.addAttribute("kabupatenList", databaseService.kabupatenList);
        return "kabupaten/kabupaten";
    }

    @PostMapping("/wilayah/kabupaten")
    public String postKabupaten(Model model,
                                @RequestParam("kabupatenid") String kabupatenid,
                                @RequestParam("namaKabupaten") String kabupaten,
                                @RequestParam("provinsiid") String provinsiid,
                                @RequestParam(value = "provinsiidnew", defaultValue = "0") String provinsiidnew,
                                @RequestParam("operasi") String operasi){

        switch (operasi){
            case "insert":
                String queryInsert = " insert into provinsi (nama)"
                        + " values ('%s')";
                queryInsert = String.format(queryInsert, kabupaten);
                databaseService.insertData(queryInsert);
                break;
            case "update":
                String query = "update kabupaten set nama = '%s', provinsiid = %d where kabupatenid = %d";
                query = String.format(query, kabupaten, Integer.parseInt(provinsiidnew), Integer.parseInt(kabupatenid));
                databaseService.updateData(query);
                break;
            case "delete":
                String queryDelete = "delete from kabupaten where kabupatenid = %d";
                queryDelete = String.format(queryDelete, Integer.parseInt(kabupatenid));
                String query2 = "ALTER TABLE `kabupaten` AUTO_INCREMENT = 1";
                databaseService.deleteData(queryDelete, query2);
                break;
            default:
                break;
        }
        databaseService.getDataKabupaten();
        model.addAttribute("kabupatenList", databaseService.kabupatenList);
        return "kabupaten/kabupaten";
    }

    @GetMapping("/wilayah/kabupaten/update")
    public String getUpdateKabupaten(Model model,
                                     @RequestParam("kabupatenid") String kabupatenid,
                                     @RequestParam("namaKabupaten") String kabupaten,
                                     @RequestParam("provinsiid") String provinsiid){

        databaseService.getDataProvinsi();
        model.addAttribute("kabupatenid", kabupatenid);
        model.addAttribute("namaKabupaten", kabupaten);
        model.addAttribute("provinsiid", provinsiid);
        model.addAttribute("provinsiList", databaseService.provinsiList);
        return "kabupaten/updateKabupaten";
    }
}
