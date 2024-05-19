/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplikacja.kontroler;

import com.aplikacja.model.produkty;
import com.aplikacja.repozytorium.produktyRepozytorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("produkty")
public class ProduktyKontroler {
    
    @Autowired
    produktyRepozytorium produktyRepo;

    @GetMapping("/dodajTestowe")
    public String dodajTestowe(){

        produktyRepo.saveAll (Arrays.asList(
                new produkty("Stoly", "Stol jadalniany", "Bialy", 399.99),
                new produkty("Sofy", "Sofa narozna", "Szara", 1499.99),
                new produkty ("Dom i ogrod", "Stol ogrodowy", "Brazowy", 299.99)));

        return "Testowe rekordy dodane!";
    }

    @GetMapping()
    public List<produkty> pokazWszystkie(){
        List<produkty> listaProdukty = new ArrayList<produkty>();
        for(produkty projekt : produktyRepo.findAll()){
            listaProdukty.add(projekt) ;
        }
        return listaProdukty;
    }

    @GetMapping("/szukajPoId/{id}")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = produktyRepo.findById (id) .toString();
        return result;
    }

    @GetMapping("/szukajPoNazwie/{nazwa}")
    public String szukajPoNazwie(@PathVariable("nazwa") String nazwa) {
        for (produkty projekt: produktyRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }

    @DeleteMapping("usun/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        produktyRepo.deleteById (id);
        return "Rekord usunięty";
    }

    @PostMapping("/utworz")
    public produkty utworz(@RequestBody Map<String, String> body) {
        String kategoria = body.get("kategoria");
        String nazwa = body.get("nazwa");
        String kolor = body.get("kolor");
        Double cena = Double.parseDouble(body.get("cena"));
        return produktyRepo.save(new produkty (kategoria, nazwa, kolor, cena) ) ;
    }

    @PutMapping ("/zmien")
    public produkty zmien(@RequestBody Map<String, String> body) {
        int produktId = Integer.parseInt(body.get("id"));
        String kategoria = body.get("kategoria");
        String nazwa = body.get("nazwa");
        String kolor = body.get("kolor");
        Double cena = Double.parseDouble(body.get("cena"));
        return produktyRepo.save(new produkty(produktId, kategoria, nazwa, kolor, cena) ) ;
    }
    
}
