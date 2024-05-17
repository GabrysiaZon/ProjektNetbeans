package com.aplikacja.kontroler;

import com.aplikacja.model.szczegolyZamowienia;
import com.aplikacja.repozytorium.szczegolyZamowieniaRepozytorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("szczegolyZamowienia")
public class SzczegolyZamowieniaKontroler {
    
    @Autowired
    szczegolyZamowieniaRepozytorium szczegolyZamowieniaRepo;

    @GetMapping("/dodajTestowe")
    public String dodajTestowe(){

        szczegolyZamowieniaRepo.saveAll (Arrays. asList(
                new szczegolyZamowienia(1, 1, 2),
                new szczegolyZamowienia (1, 2, 1),
                new szczegolyZamowienia (2, 3, 2)));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping()
    public List<szczegolyZamowienia> pokarzWszystkie(){
        List<szczegolyZamowienia> listaszczegolyZamowienia = new ArrayList<szczegolyZamowienia>();
        for(szczegolyZamowienia projekt : szczegolyZamowieniaRepo.findAll()){
            listaszczegolyZamowienia.add(projekt) ;
        }
        return listaszczegolyZamowienia;
    }
    
    @GetMapping("/szukajPoId/{id}")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = szczegolyZamowieniaRepo.findById (id) .toString();
        return result;
    }
    
    @GetMapping("/szukajPoIdZamowienia/{idZamowienia}")
    public List<szczegolyZamowienia> szukajPoIdZamowienia(@PathVariable("idZamowienia") int idZamowienia) {
        List<szczegolyZamowienia> listaSzczegolyZamowienia = new ArrayList<szczegolyZamowienia>();
        for (szczegolyZamowienia projekt: szczegolyZamowieniaRepo.findByIdZamowienie (idZamowienia) ) {
            listaSzczegolyZamowienia.add(projekt);
        }
        return listaSzczegolyZamowienia;
    }
    
    @DeleteMapping("usun/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        szczegolyZamowieniaRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/utworz")
    public szczegolyZamowienia utworz(@RequestBody Map<String, String> body) {
        int idZamowienie = Integer.parseInt(body.get("idZamowienie"));
        int idProdukt = Integer.parseInt(body.get("idProdukt"));
        int ilosc = Integer.parseInt(body.get("ilosc"));
        return szczegolyZamowieniaRepo.save(new szczegolyZamowienia(idZamowienie, idProdukt, ilosc));
    }
    
    @PutMapping ("/zmien")
    public szczegolyZamowienia zmien(@RequestBody Map<String, String> body) {
        int szczegolyZamowieniaId = Integer.parseInt(body.get("id"));
        int idZamowienie = Integer.parseInt(body.get("idZamowienie"));
        int idProdukt = Integer.parseInt(body.get("idProdukt"));
        int ilosc = Integer.parseInt(body.get("ilosc"));
        return szczegolyZamowieniaRepo.save(new szczegolyZamowienia(szczegolyZamowieniaId, idZamowienie, idProdukt, ilosc));
    }
}
