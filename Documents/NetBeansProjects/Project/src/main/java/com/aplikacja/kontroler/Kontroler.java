package com.aplikacja.kontroler;

import com.aplikacja.model.projektanci;
import com.aplikacja.model.szczegolyZamowienia;
import com.aplikacja.model.zamowieniaGotowe;
import com.aplikacja.model.zamowieniaWymiar;
import com.aplikacja.repozytorium.projektanciRepozytorium;
import com.aplikacja.repozytorium.szczegolyZamowieniaRepozytorium;
import com.aplikacja.repozytorium.zamowieniaGotoweRepozytorium;
import com.aplikacja.repozytorium.zamowieniaWymiarRepozytorium;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org. springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kontroler {
    
    @Autowired
    projektanciRepozytorium projektanciRepo;
    @GetMapping("/projektanci/dodajTestowe")
    public String dodajDaneTestowe (){

    projektanciRepo.saveAll (Arrays. asList(new projektanci ("Produkt 1", "222.22", "kijb"),
    new projektanci ("Produkt 1", "222.22", "kijb"),
    new projektanci ("Produkt 1", "222.22", "kijb"),
    new projektanci ("Produkt 1", "222.22", "kijb"),
    new projektanci ("Produkt 1", "222.22", "kijb")));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/projektanci/pokazWszystkie")
    public List<projektanci> pokarzWszystkie(){
        List<projektanci> listaprojektanci = new ArrayList<projektanci>();
        for(projektanci projekt : projektanciRepo.findAll()){
        listaprojektanci.add(projekt) ;
        }
        return listaprojektanci;
    }    
    @GetMapping("/projektanci/wyszukajPoId/(id)")
    public String szukajPoId(@PathVariable("id") Integer id) {
        String result = projektanciRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/projektanci/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwa (@PathVariable("nazwa") String nazwa) {
        for (projektanci projekt: projektanciRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }   
    @DeleteMapping("/projektanci/{id}")
    public String usunPoId(@PathVariable("id") Integer id) {
        projektanciRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/projektanci/utworz")
    public projektanci utworz (@RequestBody Map<String, String> body) {
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci (imie, nazwisko, email) ) ;
    }   
    @PutMapping ("/projektanci/zmien")
    public projektanci zmien (@RequestBody Map<String, String> body) {
        int projektanciId = Integer.parseInt(body.get("projektanciId"));
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci(projektanciId, imie, nazwisko, email) ) ;
    }
    
    @Autowired
    szczegolyZamowieniaRepozytorium szczegolyZamowieniaRepo;
    @GetMapping("/szczegolyZamowienia/dodajTestowe")
    public String dodajDaneTestowe2 (){

    szczegolyZamowieniaRepo.saveAll (Arrays. asList(new szczegolyZamowienia (1 , 2, 3, 45.5),
    new szczegolyZamowienia (1 , 2, 3, 45.5),
    new szczegolyZamowienia (1 , 2, 3, 45.5),
    new szczegolyZamowienia (1 , 2, 3, 45.5),
    new szczegolyZamowienia (1 , 2, 3, 45.5)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/szczegolyZamowienia/pokazWszystkie")
    public List<szczegolyZamowienia> pokarzWszystkie2(){
        List<szczegolyZamowienia> listaszczegolyZamowienia = new ArrayList<szczegolyZamowienia>();
        for(szczegolyZamowienia szczegol : szczegolyZamowieniaRepo.findAll()){
        listaszczegolyZamowienia.add(szczegol) ;
        }
        return listaszczegolyZamowienia;
    }    
    @GetMapping("/szczegolyZamowienia/wyszukajPoId/(id)")
    public String szukajPoId2(@PathVariable("id") Integer id) {
        String result = szczegolyZamowieniaRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/szczegolyZamowienia/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwa2 (@PathVariable("nazwa") String nazwa) {
        for (szczegolyZamowienia szczegol: szczegolyZamowieniaRepo.findByNazwa (nazwa) ) {
            return szczegol.toString ();
        }
        return null;
    }   
    @DeleteMapping("/szczegolyZamowienia/{id}")
    public String usunPoId2(@PathVariable("id") Integer id) {
        szczegolyZamowieniaRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/szczegolyZamowienia/utworz")
    public szczegolyZamowienia utworz2 (@RequestBody Map<String, String> body) {
        int idZamowienie = Integer.parseInt(body.get("idZamowienie"));
        int idProdukt = Integer.parseInt(body.get("idProdukt"));
        int ilosc = Integer.parseInt(body.get("ilosc"));
        Double cena = Double.parseDouble(body.get("cena"));
        return szczegolyZamowieniaRepo.save(new szczegolyZamowienia(idZamowienie, idProdukt, ilosc, cena));
    }   
    @PutMapping ("/szczegolyZamowienia/zmien")
    public szczegolyZamowienia zmien2 (@RequestBody Map<String, String> body) {
        int szczegolyZamowieniaId = Integer.parseInt(body.get("szczegolyZamowieniaId"));
        int idZamowienie = Integer.parseInt(body.get("idZamowienie"));
        int idProdukt = Integer.parseInt(body.get("idProdukt"));
        int ilosc = Integer.parseInt(body.get("ilosc"));
        Double cena = Double.parseDouble(body.get("cena"));
        return szczegolyZamowieniaRepo.save(new szczegolyZamowienia(szczegolyZamowieniaId, idZamowienie, idProdukt, ilosc, cena));
    }
    
    @Autowired
    zamowieniaGotoweRepozytorium zamowieniaGotoweRepo;
    @GetMapping("/zamowieniaGotowe/dodajTestowe")
    public String dodajDaneTestowe3 (){

    zamowieniaGotoweRepo.saveAll (Arrays. asList(new zamowieniaGotowe (2, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaGotowe (2, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaGotowe (2, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaGotowe (2, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaGotowe (2, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/zamowieniaGotowe/pokazWszystkie")
    public List<zamowieniaGotowe> pokarzWszystkie3(){
        List<zamowieniaGotowe> listazamowieniaGotowe = new ArrayList<zamowieniaGotowe>();
        for(zamowieniaGotowe gotowe : zamowieniaGotoweRepo.findAll()){
        listazamowieniaGotowe.add(gotowe) ;
        }
        return listazamowieniaGotowe;
    }    
    @GetMapping("/zamowieniaGotowe/wyszukajPoId/(id)")
    public String szukajPoId3(@PathVariable("id") Integer id) {
        String result = zamowieniaGotoweRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/zamowieniaGotowe/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwa3 (@PathVariable("nazwa") String nazwa) {
        for (zamowieniaGotowe gotowe: zamowieniaGotoweRepo.findByNazwa (nazwa) ) {
            return gotowe.toString ();
        }
        return null;
    }   
    @DeleteMapping("/zamowieniaGotowe/{id}")
    public String usunPoId3(@PathVariable("id") Integer id) {
        zamowieniaGotoweRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/zamowieniaGotowe/utworz")
    public zamowieniaGotowe utworz3 (@RequestBody Map<String, Object> body) {
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe (idKlient, dataZakupu, dataRealizacji, cena));
    }   
    @PutMapping ("/zamowieniaGotowe/zmien")
    public zamowieniaGotowe zmien3 (@RequestBody Map<String, Object> body) {
        int zamowieniaGotoweId = Integer.parseInt(body.get("zamowieniaGotoweId").toString());
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe(zamowieniaGotoweId, idKlient, dataZakupu, dataRealizacji, cena));
    }
    
    @Autowired
    zamowieniaWymiarRepozytorium zamowieniaWymiarRepo;
    @GetMapping("/projektanci/dodajTestowe")
    public String dodajDaneTestowe4 (){

    zamowieniaWymiarRepo.saveAll (Arrays. asList(new zamowieniaWymiar (2, 3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaWymiar (2, 3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaWymiar (2, 3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaWymiar (2, 3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5),
    new zamowieniaWymiar (2, 3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 12), 45.5)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/projektanci/pokazWszystkie")
    public List<zamowieniaWymiar> pokarzWszystkie4(){
        List<zamowieniaWymiar> listazamowieniaWymiar = new ArrayList<zamowieniaWymiar>();
        for(zamowieniaWymiar wymiar : zamowieniaWymiarRepo.findAll()){
        listazamowieniaWymiar.add(wymiar) ;
        }
        return listazamowieniaWymiar;
    }    
    @GetMapping("/projektanci/wyszukajPoId/(id)")
    public String szukajPoId4(@PathVariable("id") Integer id) {
        String result = zamowieniaWymiarRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/projektanci/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwa4 (@PathVariable("nazwa") String nazwa) {
        for (zamowieniaWymiar wymiar: zamowieniaWymiarRepo.findByNazwa (nazwa) ) {
            return wymiar.toString ();
        }
        return null;
    }   
    @DeleteMapping("/projektanci/{id}")
    public String usunPoId4(@PathVariable("id") Integer id) {
        zamowieniaWymiarRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/projektanci/utworz")
    public zamowieniaWymiar utworz4 (@RequestBody Map<String, Object> body) {
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        int idProjektant = Integer.parseInt(body.get("idProjektant").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar (idKlient,idProjektant, dataZakupu, dataRealizacji, cena));
    }   
    @PutMapping ("/projektanci/zmien")
    public zamowieniaWymiar zmien4 (@RequestBody Map<String, Object> body) {
        int zamowieniaWymiarId = Integer.parseInt(body.get("zamowieniaWymiarId").toString());
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        int idProjektant = Integer.parseInt(body.get("idProjektant").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar(zamowieniaWymiarId, idKlient, idProjektant, dataZakupu, dataRealizacji, cena));
    }
    
}
