package com.aplikacja.kontroler;

import com.aplikacja.model.zamowieniaWymiar;
import com.aplikacja.repozytorium.zamowieniaWymiarRepozytorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zamowieniaWymiar")
public class ZamowieniaWymiarKontroler {

    @Autowired
    zamowieniaWymiarRepozytorium zamowieniaWymiarRepo;

    @GetMapping("/dodajTestowe")
    public String dodajTestowe(){
        zamowieniaWymiarRepo.saveAll (Arrays. asList(
                new zamowieniaWymiar(1, 1, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 5, 12), 1000.0),
                new zamowieniaWymiar (2, 2, LocalDate.of(2023, 4, 29), LocalDate.of(2023, 5, 30), 1600.0),
                new zamowieniaWymiar (3, 3, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 6, 12), 950.0)));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping()
    public List<zamowieniaWymiar> pokarzWszystkie(){
        List<zamowieniaWymiar> listazamowieniaWymiar = new ArrayList<zamowieniaWymiar>();
        for(zamowieniaWymiar projekt : zamowieniaWymiarRepo.findAll()){
            listazamowieniaWymiar.add(projekt) ;
        }
        return listazamowieniaWymiar;
    }
    
    @GetMapping("/szukajPoId/{id}")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = zamowieniaWymiarRepo.findById (id) .toString();
        return result;
    }
    
    @GetMapping("/szukajPoIdKlienta/{idKlienta}")
    public List<zamowieniaWymiar> szukajPoIdKlienta(@PathVariable("idKlienta") int idKlienta) {
        List<zamowieniaWymiar> listaZamowieniaWymiar = new ArrayList<zamowieniaWymiar>();
        for (zamowieniaWymiar projekt: zamowieniaWymiarRepo.findByIdKlient (idKlienta) ) {
            listaZamowieniaWymiar.add(projekt);
        }
        return listaZamowieniaWymiar;
    }
    
    @DeleteMapping("usun/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        zamowieniaWymiarRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/utworz")
    public zamowieniaWymiar utworz(@RequestBody Map<String, String> body) {
        int idKlient = Integer.parseInt(body.get("idKlient"));
        int idProjektant = Integer.parseInt(body.get("idProjektant"));
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu"));
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji"));
        Double cena = Double.parseDouble(body.get("cena"));
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar (idKlient,idProjektant, dataZakupu, dataRealizacji, cena));
    }
    
    @PutMapping ("/zmien")
    public zamowieniaWymiar zmien(@RequestBody Map<String, String> body) {
        int zamowieniaWymiarId = Integer.parseInt(body.get("id"));
        int idKlient = Integer.parseInt(body.get("idKlient"));
        int idProjektant = Integer.parseInt(body.get("idProjektant"));
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu"));
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji"));
        Double cena = Double.parseDouble(body.get("cena"));
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar(zamowieniaWymiarId, idKlient, idProjektant, dataZakupu, dataRealizacji, cena));
    }
    
}
