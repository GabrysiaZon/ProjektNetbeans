/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplikacja.kontroler;

import com.aplikacja.model.projektanci;
import com.aplikacja.repozytorium.projektanciRepozytorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("projektanci")
public class ProjektanciKontroler {
    
    @Autowired
    projektanciRepozytorium projektanciRepo;

    @GetMapping("/dodajTestowe")
    public String dodajTestowe (){
        projektanciRepo.saveAll (Arrays. asList(
                new projektanci("Anna", "Kowalska", "anna.kowalska@example.com"),
                new projektanci ("Jan", "Nowak", "jan.nowak@example.com"),
                new projektanci ("Alicja", "Jankowska", "alicja.jankowska@example.com")));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping()
    public List<projektanci> pokarzWszystkie(){
        List<projektanci> listaprojektanci = new ArrayList<projektanci>();
        for(projektanci projekt : projektanciRepo.findAll()){
            listaprojektanci.add(projekt) ;
        }
        return listaprojektanci;
    }
    
    @GetMapping("/wyszukajPoId/{id}")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = projektanciRepo.findById (id) .toString();
        return result;
    }
    
    @GetMapping("/szukajPoEmail/{email}")
    public String szukajPoEmail(@PathVariable("email") String email) {
        for (projektanci projekt: projektanciRepo.findByEmail (email) ) {
            return projekt.toString ();
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        projektanciRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/utworz")
    public projektanci utworz(@RequestBody Map<String, String> body) {
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci (imie, nazwisko, email) ) ;
    }
    
    @PutMapping ("/zmien")
    public projektanci zmien(@RequestBody Map<String, String> body) {
        int projektanciId = Integer.parseInt(body.get("projektanciId"));
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci(projektanciId, imie, nazwisko, email) ) ;
    }
}
