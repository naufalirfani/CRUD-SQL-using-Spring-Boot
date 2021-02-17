package com.javan.bioskop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BioskopController {

    DatabaseService databaseService = new DatabaseService();

    @GetMapping("/bioskop")
    public String getBioskop(Model model){
        databaseService.getDataFilm();
        databaseService.getDataPenonton();
        databaseService.getDataPesanan();

        for(int i = 0; i < databaseService.pesananList.size(); i++ ){
            for(int j = 0; j < databaseService.filmList.size(); j++){
                if(databaseService.pesananList.get(i).film.equals(String.valueOf(databaseService.filmList.get(j).filmid)))
                    databaseService.pesananList.get(i).film = databaseService.filmList.get(j).nama;
            }
            for(int k = 0; k < databaseService.penontonList.size(); k++){
                if(databaseService.pesananList.get(i).penonton.equals(String.valueOf(databaseService.penontonList.get(k).penontonid)))
                    databaseService.pesananList.get(i).penonton = databaseService.penontonList.get(k).nama;
            }
        }

        model.addAttribute("filmList", databaseService.filmList);
        model.addAttribute("penontonList", databaseService.penontonList);
        model.addAttribute("pesananList", databaseService.pesananList);

        return "bioskop";
    }

    @PostMapping("/bioskop")
    public String postBioskop(Model model,
                              @RequestParam("film") String film,
                              @RequestParam("penonton") String penonton,
                              @RequestParam(value = "pesananid", defaultValue = "0") int pesananid,
                              @RequestParam("operasi") String operasi){

        if(operasi.equals("add"))
            databaseService.insertData(Integer.parseInt(film), Integer.parseInt(penonton));
        else if(operasi.equals("delete") && pesananid != 0)
            databaseService.deleteData(pesananid);
        databaseService.getDataFilm();
        databaseService.getDataPenonton();
        databaseService.getDataPesanan();

        for(int i = 0; i < databaseService.pesananList.size(); i++ ){
            for(int j = 0; j < databaseService.filmList.size(); j++){
                if(databaseService.pesananList.get(i).film.equals(String.valueOf(databaseService.filmList.get(j).filmid)))
                    databaseService.pesananList.get(i).film = databaseService.filmList.get(j).nama;
            }
            for(int k = 0; k < databaseService.penontonList.size(); k++){
                if(databaseService.pesananList.get(i).penonton.equals(String.valueOf(databaseService.penontonList.get(k).penontonid)))
                    databaseService.pesananList.get(i).penonton = databaseService.penontonList.get(k).nama;
            }
        }

        model.addAttribute("filmList", databaseService.filmList);
        model.addAttribute("penontonList", databaseService.penontonList);
        model.addAttribute("pesananList", databaseService.pesananList);

        return "bioskop";
    }
}
