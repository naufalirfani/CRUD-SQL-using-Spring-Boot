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
                String queryInsert = " insert into kabupaten (nama, provinsiid)"
                        + " values ('%s', %d)";
                queryInsert = String.format(queryInsert, kabupaten, Integer.parseInt(provinsiid));
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

    @GetMapping("/wilayah/kabupaten/insert")
    public String getInsertKabupaten(Model model){

        databaseService.getDataProvinsi();
        model.addAttribute("provinsiList", databaseService.provinsiList);
        return "kabupaten/insertKabupaten";
    }

    @GetMapping("/wilayah/kecamatan")
    public String getKecamatan(Model model){
        databaseService.getDataKecamatan();
        model.addAttribute("kecamatanList", databaseService.kecamatanList);
        return "kecamatan/kecamatan";
    }

    @PostMapping("/wilayah/kecamatan")
    public String postKecamatan(Model model,
                                @RequestParam(value = "kecamatanid", defaultValue = "") String kecamatanid,
                                @RequestParam(value = "namaKecamatan", defaultValue = "") String namaKecamatan,
                                @RequestParam(value = "kabupatenid", defaultValue = "") String kabupatenid,
                                @RequestParam(value = "kabupatenidnew", defaultValue = "0") String kabupatenidnew,
                                @RequestParam("operasi") String operasi){

        switch (operasi){
            case "insert":
                String queryInsert = " insert into kecamatan (nama, kabupatenid)"
                        + " values ('%s', %d)";
                queryInsert = String.format(queryInsert, namaKecamatan, Integer.parseInt(kabupatenid));
                databaseService.insertData(queryInsert);
                break;
            case "update":
                String query = "update kecamatan set nama = '%s', kabupatenid = %d where kecamatanid = %d";
                query = String.format(query, namaKecamatan, Integer.parseInt(kabupatenidnew), Integer.parseInt(kecamatanid));
                databaseService.updateData(query);
                break;
            case "delete":
                String queryDelete = "delete from kecamatan where kecamatanid = %d";
                queryDelete = String.format(queryDelete, Integer.parseInt(kecamatanid));
                String query2 = "ALTER TABLE `kecamatan` AUTO_INCREMENT = 1";
                databaseService.deleteData(queryDelete, query2);
                break;
            default:
                break;
        }
        databaseService.getDataKecamatan();
        model.addAttribute("kecamatanList", databaseService.kecamatanList);
        return "kecamatan/kecamatan";
    }

    @GetMapping("/wilayah/kecamatan/insert")
    public String getInsertKecamatan(Model model){

        databaseService.getDataKabupaten();
        model.addAttribute("kabupatenList", databaseService.kabupatenList);
        return "kecamatan/insertKecamatan";
    }

    @GetMapping("/wilayah/kecamatan/update")
    public String getUpdateKecamatan(Model model,
                                     @RequestParam(value = "kecamatanid", defaultValue = "") String kecamatanid,
                                     @RequestParam(value = "namaKecamatan", defaultValue = "") String namaKecamatan,
                                     @RequestParam(value = "kabupatenid", defaultValue = "") String kabupatenid){

        databaseService.getDataKabupaten();
        model.addAttribute("kecamatanid", kecamatanid);
        model.addAttribute("namaKecamatan", namaKecamatan);
        model.addAttribute("kabupatenid", kabupatenid);
        model.addAttribute("kabupatenList", databaseService.kabupatenList);
        return "kecamatan/updateKecamatan";
    }

    @GetMapping("/wilayah/desa")
    public String getDesa(Model model){
        databaseService.getDataDesa();
        model.addAttribute("desaList", databaseService.desaList);
        return "desa/desa";
    }

    @PostMapping("/wilayah/desa")
    public String postDesa(Model model,
                                @RequestParam(value = "desaid", defaultValue = "") String desaid,
                                @RequestParam(value = "namaDesa", defaultValue = "") String namaDesa,
                                @RequestParam(value = "kecamatanid", defaultValue = "") String kecamatanid,
                                @RequestParam(value = "kecamatanidnew", defaultValue = "0") String kecamatanidnew,
                                @RequestParam("operasi") String operasi){

        switch (operasi){
            case "insert":
                String queryInsert = " insert into desa (nama, kecamatanid)"
                        + " values ('%s', %d)";
                queryInsert = String.format(queryInsert, namaDesa, Integer.parseInt(kecamatanid));
                databaseService.insertData(queryInsert);
                break;
            case "update":
                String query = "update desa set nama = '%s', kecamatanid = %d where desaid = %d";
                query = String.format(query, namaDesa, Integer.parseInt(kecamatanidnew), Integer.parseInt(desaid));
                databaseService.updateData(query);
                break;
            case "delete":
                String queryDelete = "delete from desa where desaid = %d";
                queryDelete = String.format(queryDelete, Integer.parseInt(desaid));
                String query2 = "ALTER TABLE `desa` AUTO_INCREMENT = 1";
                databaseService.deleteData(queryDelete, query2);
                break;
            default:
                break;
        }
        databaseService.getDataDesa();
        model.addAttribute("desaList", databaseService.desaList);
        return "desa/desa";
    }

    @GetMapping("/wilayah/desa/insert")
    public String getInsertDesa(Model model){

        databaseService.getDataKecamatan();
        model.addAttribute("kecamatanList", databaseService.kecamatanList);
        return "desa/insertDesa";
    }

    @GetMapping("/wilayah/desa/update")
    public String getUpdateDesa(Model model,
                                     @RequestParam(value = "desaid", defaultValue = "") String desaid,
                                     @RequestParam(value = "namaDesa", defaultValue = "") String namaDesa,
                                     @RequestParam(value = "kecamatanid", defaultValue = "") String kecamatanid){

        databaseService.getDataKecamatan();
        model.addAttribute("desaid", desaid);
        model.addAttribute("namaDesa", namaDesa);
        model.addAttribute("kecamatanid", kecamatanid);
        model.addAttribute("kecamatanList", databaseService.kecamatanList);
        return "desa/updateDesa";
    }
}
