package com.aplikacja.kontroler;

import com.aplikacja.model.projektanci;
import com.aplikacja.model.szczegolyZamowienia;
import com.aplikacja.model.zamowieniaGotowe;
import com.aplikacja.model.zamowieniaWymiar;
import com.aplikacja.model.produkty;
import com.aplikacja.model.klienci;
import com.aplikacja.repozytorium.projektanciRepozytorium;
import com.aplikacja.repozytorium.szczegolyZamowieniaRepozytorium;
import com.aplikacja.repozytorium.zamowieniaGotoweRepozytorium;
import com.aplikacja.repozytorium.zamowieniaWymiarRepozytorium;
import com.aplikacja.repozytorium.klienciRepozytorium;
import com.aplikacja.repozytorium.produktyRepozytorium;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kontroler {
    
    @Autowired
    klienciRepozytorium klienciRepo;
    @GetMapping("/klienci/dodajTestowe")
    public String dodajDaneTestoweKlienci(){

        klienciRepo.saveAll(Arrays.asList(
                new klienci("Jan", "Kowalski", "M", LocalDate.of(1990, 5,15), "jan.kowalski@example.com", "Warszawa", "Aleje Jerozolimskie", "10", "5A"),
                new klienci("Anna", "Nowak", "F", LocalDate.of(1985, 12, 10), "anna.nowak@example.com", "Kraków", "ul. Florianska", "20", null),
                new klienci("Patryk", "Wisniewski", "M", LocalDate.of(1988, 8, 25), "patryk.wisniewski@example.com", "Gdansk", "ul. Dluga", "15", "3")));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping("/klienci/pokazWszystkie")
    public List<klienci> pokarzWszystkieKlienci(){
        List<klienci> listaKlienci = new ArrayList<klienci>();
        for(klienci projekt : klienciRepo.findAll()){
        listaKlienci.add(projekt) ;
        }
        return listaKlienci;
    }
    
    @GetMapping("/klienci/wyszukajPoId/(id)")
    public String szukajPoIdKlienci(@PathVariable("id") Integer id) {
        String result = klienciRepo.findById (id) .toString();
        return result;
    } 
    
    @GetMapping("/klienci/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaKlienci(@PathVariable("nazwa") String nazwa) {
        for (klienci projekt: klienciRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    } 
    
    @DeleteMapping("/klienci/{id}")
    public String usunPoIdKlienci(@PathVariable("id") Integer id) {
        klienciRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/klienci/utworz")
    public klienci utworzKlienci(@RequestBody Map<String, String> body) {
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String plec = body.get("plec");
        LocalDate dataUrodzenia = LocalDate.parse(body.get("dataUrodzenia"));
        String email = body.get("email");
        String miasto = body.get("miasto");
        String ulica = body.get("ulica");
        String numerDomu = body.get("numerDomu");
        String numerMieszkania = body.get("numerMieszkania");
        return klienciRepo.save(new klienci(imie, nazwisko, plec, dataUrodzenia, email, miasto, ulica, numerDomu, numerMieszkania) ) ;
    }  
    
    @PutMapping ("/klienci/zmien")
    public klienci zmienKlienci(@RequestBody Map<String, String> body) {
        int klientId = Integer.parseInt(body.get("id"));
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String plec = body.get("plec");
        LocalDate dataUrodzenia = LocalDate.parse(body.get("dataUrodzenia"));
        String email = body.get("email");
        String miasto = body.get("miasto");
        String ulica = body.get("ulica");
        String numerDomu = body.get("numerDomu");
        String numerMieszkania = body.get("numerMieszkania");
        return klienciRepo.save(new klienci(imie, nazwisko, plec, dataUrodzenia, email, miasto, ulica, numerDomu, numerMieszkania) ) ;
    }  
    
    
    @Autowired
    produktyRepozytorium produktyRepo;
    @GetMapping("/produkty/dodajTestowe")
    public String dodajDaneTestoweProdukty (){

        produktyRepo.saveAll (Arrays.asList(
                new produkty("Odziez", "Koszula", "Bialy", 59.99),
                new produkty("Elektronika", "Smartfon", "Czarny", 999.99),
                new produkty ("Dom i ogrod", "Stol ogrodowy", "Brazowy", 299.99)));

        return "Testowe rekordy dodane!";
    }
    
    @GetMapping("/produkty/pokazWszystkie")
    public List<produkty> pokarzWszystkieProdukty(){
        List<produkty> listaProdukty = new ArrayList<produkty>();
        for(produkty projekt : produktyRepo.findAll()){
        listaProdukty.add(projekt) ;
        }
        return listaProdukty;
    }
    
    @GetMapping("/produkty/wyszukajPoId/(id)")
    public String szukajPoIdProdukty(@PathVariable("id") Integer id) {
        String result = produktyRepo.findById (id) .toString();
        return result;
    } 
    
    @GetMapping("/produkty/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaProdukty (@PathVariable("nazwa") String nazwa) {
        for (produkty projekt: produktyRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    } 
    
    @DeleteMapping("/produkty/{id}")
    public String usunPoIdProdukty(@PathVariable("id") Integer id) {
        produktyRepo.deleteById (id);
        return "Rekord usunięty";
    }
    
    @PostMapping("/produkty/utworz")
    public produkty utworzProdukty (@RequestBody Map<String, String> body) {
        String kategoria = body.get("kategoria");
        String nazwa = body.get("nazwisko");
        String kolor = body.get("email");
        Double cena = Double.parseDouble(body.get("cena"));
        return produktyRepo.save(new produkty (kategoria, nazwa, kolor, cena) ) ;
    }  
    
    @PutMapping ("/produkty/zmien")
    public produkty zmienProdukty (@RequestBody Map<String, String> body) {
        int produktId = Integer.parseInt(body.get("id"));
        String kategoria = body.get("kategoria");
        String nazwa = body.get("nazwisko");
        String kolor = body.get("email");
        Double cena = Double.parseDouble(body.get("cena"));
        return produktyRepo.save(new produkty(produktId, kategoria, nazwa, kolor, cena) ) ;
    }
    
    
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
