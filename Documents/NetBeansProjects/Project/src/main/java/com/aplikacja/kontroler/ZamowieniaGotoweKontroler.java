package com.aplikacja.kontroler;

import com.aplikacja.model.zamowieniaGotowe;
import com.aplikacja.repozytorium.zamowieniaGotoweRepozytorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zamowieniaGotowe")
public class ZamowieniaGotoweKontroler {

    @Autowired
    zamowieniaGotoweRepozytorium zamowieniaGotoweRepo;

    @GetMapping("/dodajTestowe")
    public String dodajTestowe(){

        zamowieniaGotoweRepo.saveAll (Arrays. asList(
                new zamowieniaGotowe(1, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 20), 600.0),
                new zamowieniaGotowe (2, LocalDate.of(2023, 5, 2), LocalDate.of(2023, 5, 12), 455.0),
                new zamowieniaGotowe (3, LocalDate.of(2023, 5, 14), LocalDate.of(2023, 5, 22), 999.99)));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping()
    public List<zamowieniaGotowe> pokazWszystkie(){
        List<zamowieniaGotowe> listaZamowieniaGotowe = new ArrayList<zamowieniaGotowe>();
        for(zamowieniaGotowe projekt : zamowieniaGotoweRepo.findAll()){
            listaZamowieniaGotowe.add(projekt) ;
        }
        return listaZamowieniaGotowe;
    }
    
    @GetMapping("/szukajPoId/{id}")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = zamowieniaGotoweRepo.findById (id) .toString();
        return result;
    }

    @GetMapping("/szukajPoIdKlienta/{idKlienta}")
    public List<zamowieniaGotowe> szukajPoIdKlienta(@PathVariable("idKlienta") int idKlienta){
        List<zamowieniaGotowe> listaZamowieniaGotowe = new ArrayList<zamowieniaGotowe>();
        for (zamowieniaGotowe projekt: zamowieniaGotoweRepo.findByIdKlient (idKlienta) ) {
            listaZamowieniaGotowe.add(projekt);
        }
        return listaZamowieniaGotowe;
    }
            
    @DeleteMapping("usun/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        zamowieniaGotoweRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/utworz")
    public zamowieniaGotowe utworz(@RequestBody Map<String, String> body) {
        int idKlient = Integer.parseInt(body.get("idKlient"));
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu"));
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji"));
        Double cena = Double.parseDouble(body.get("cena"));
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe (idKlient, dataZakupu, dataRealizacji, cena));
    }
    
    @PutMapping ("/zmien")
    public zamowieniaGotowe zmien(@RequestBody Map<String, String> body) {
        int zamowieniaGotoweId = Integer.parseInt(body.get("id"));
        int idKlient = Integer.parseInt(body.get("idKlient"));
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu"));
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji"));
        Double cena = Double.parseDouble(body.get("cena"));
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe(zamowieniaGotoweId, idKlient, dataZakupu, dataRealizacji, cena));
    }
}
