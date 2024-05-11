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
        int klientId = Integer.parseInt(body.get("klientId"));
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String plec = body.get("plec");
        LocalDate dataUrodzenia = LocalDate.parse(body.get("dataUrodzenia"));
        String email = body.get("email");
        String miasto = body.get("miasto");
        String ulica = body.get("ulica");
        String numerDomu = body.get("numerDomu");
        String numerMieszkania = body.get("numerMieszkania");
        return klienciRepo.save(new klienci(klientId, imie, nazwisko, plec, dataUrodzenia, email, miasto, ulica, numerDomu, numerMieszkania) ) ;
    }  
    
    
    @Autowired
    produktyRepozytorium produktyRepo;
    @GetMapping("/produkty/dodajTestowe")
    public String dodajDaneTestoweProdukty (){

        produktyRepo.saveAll (Arrays.asList(
                new produkty("Stoły", "Stół jadalniany", "Biały", 399.99),
                new produkty("Sofy", "Sofa narożna", "Szara", 1499.99),
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
        int produktId = Integer.parseInt(body.get("produktId"));
        String kategoria = body.get("kategoria");
        String nazwa = body.get("nazwisko");
        String kolor = body.get("email");
        Double cena = Double.parseDouble(body.get("cena"));
        return produktyRepo.save(new produkty(produktId, kategoria, nazwa, kolor, cena) ) ;
    }
    
    
    @Autowired
    projektanciRepozytorium projektanciRepo;
    @GetMapping("/projektanci/dodajTestowe")
    public String dodajDaneTestoweProjektanci (){

    projektanciRepo.saveAll (Arrays. asList(
    new projektanci ("Anna", "Kowalska", "anna.kowalska@example.com"),
    new projektanci ("Jan", "Nowak", "jan.nowak@example.com"),
    new projektanci ("Alicja", "Jankowska", "alicja.jankowska@example.com")));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/projektanci/pokazWszystkie")
    public List<projektanci> pokarzWszystkieProjektanci(){
        List<projektanci> listaprojektanci = new ArrayList<projektanci>();
        for(projektanci projekt : projektanciRepo.findAll()){
        listaprojektanci.add(projekt) ;
        }
        return listaprojektanci;
    }    
    @GetMapping("/projektanci/wyszukajPoId/(id)")
    public String szukajPoIdProjektanci(@PathVariable("id") Integer id) {
        String result = projektanciRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/projektanci/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaProjektanci (@PathVariable("nazwa") String nazwa) {
        for (projektanci projekt: projektanciRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }   
    @DeleteMapping("/projektanci/{id}")
    public String usunPoIdProjektanci(@PathVariable("id") Integer id) {
        projektanciRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/projektanci/utworz")
    public projektanci utworzProjektanci (@RequestBody Map<String, String> body) {
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci (imie, nazwisko, email) ) ;
    }   
    @PutMapping ("/projektanci/zmien")
    public projektanci zmienProjektanci (@RequestBody Map<String, String> body) {
        int projektanciId = Integer.parseInt(body.get("projektanciId"));
        String imie = body.get("imie");
        String nazwisko = body.get("nazwisko");
        String email = body.get("email");
        return projektanciRepo.save(new projektanci(projektanciId, imie, nazwisko, email) ) ;
    }
    
    @Autowired
    szczegolyZamowieniaRepozytorium szczegolyZamowieniaRepo;
    @GetMapping("/szczegolyZamowienia/dodajTestowe")
    public String dodajDaneTestoweSzczegolyZamowienia (){

    szczegolyZamowieniaRepo.saveAll (Arrays. asList(
    new szczegolyZamowienia (1, 1, 2, 250.0),
    new szczegolyZamowienia (1, 2, 1, 150.0),
    new szczegolyZamowienia (2, 3, 2, 300.0)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/szczegolyZamowienia/pokazWszystkie")
    public List<szczegolyZamowienia> pokarzWszystkieSzczegolyZamowienia(){
        List<szczegolyZamowienia> listaszczegolyZamowienia = new ArrayList<szczegolyZamowienia>();
        for(szczegolyZamowienia projekt : szczegolyZamowieniaRepo.findAll()){
        listaszczegolyZamowienia.add(projekt) ;
        }
        return listaszczegolyZamowienia;
    }    
    @GetMapping("/szczegolyZamowienia/wyszukajPoId/(id)")
    public String szukajPoIdSzczegolyZamowienia(@PathVariable("id") Integer id) {
        String result = szczegolyZamowieniaRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/szczegolyZamowienia/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaSzczegolyZamowienia (@PathVariable("nazwa") String nazwa) {
        for (szczegolyZamowienia projekt: szczegolyZamowieniaRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }   
    @DeleteMapping("/szczegolyZamowienia/{id}")
    public String usunPoIdSzczegolyZamowienia(@PathVariable("id") Integer id) {
        szczegolyZamowieniaRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/szczegolyZamowienia/utworz")
    public szczegolyZamowienia utworzSzczegolyZamowienia (@RequestBody Map<String, String> body) {
        int idZamowienie = Integer.parseInt(body.get("idZamowienie"));
        int idProdukt = Integer.parseInt(body.get("idProdukt"));
        int ilosc = Integer.parseInt(body.get("ilosc"));
        Double cena = Double.parseDouble(body.get("cena"));
        return szczegolyZamowieniaRepo.save(new szczegolyZamowienia(idZamowienie, idProdukt, ilosc, cena));
    }   
    @PutMapping ("/szczegolyZamowienia/zmien")
    public szczegolyZamowienia zmienSzczegolyZamowienia (@RequestBody Map<String, String> body) {
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
    public String dodajDaneTestoweZamowieniaGotowe (){

    zamowieniaGotoweRepo.saveAll (Arrays. asList(
    new zamowieniaGotowe (1, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 20), 600.0),
    new zamowieniaGotowe (2, LocalDate.of(2023, 5, 2), LocalDate.of(2023, 5, 12), 455.0),
    new zamowieniaGotowe (3, LocalDate.of(2023, 5, 14), LocalDate.of(2023, 5, 22), 999.99)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/zamowieniaGotowe/pokazWszystkie")
    public List<zamowieniaGotowe> pokarzWszystkieZamowieniaGotowe(){
        List<zamowieniaGotowe> listazamowieniaGotowe = new ArrayList<zamowieniaGotowe>();
        for(zamowieniaGotowe projekt : zamowieniaGotoweRepo.findAll()){
        listazamowieniaGotowe.add(projekt) ;
        }
        return listazamowieniaGotowe;
    }    
    @GetMapping("/zamowieniaGotowe/wyszukajPoId/(id)")
    public String szukajPoIdZamowieniaGotowe(@PathVariable("id") Integer id) {
        String result = zamowieniaGotoweRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/zamowieniaGotowe/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaZamowieniaGotowe (@PathVariable("nazwa") String nazwa) {
        for (zamowieniaGotowe projekt: zamowieniaGotoweRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }   
    @DeleteMapping("/zamowieniaGotowe/{id}")
    public String usunPoIdZamowieniaGotowe(@PathVariable("id") Integer id) {
        zamowieniaGotoweRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/zamowieniaGotowe/utworz")
    public zamowieniaGotowe utworzZamowieniaGotowe (@RequestBody Map<String, Object> body) {
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe (idKlient, dataZakupu, dataRealizacji, cena));
    }   
    @PutMapping ("/zamowieniaGotowe/zmien")
    public zamowieniaGotowe zmienZamowieniaGotowe (@RequestBody Map<String, Object> body) {
        int zamowieniaGotoweId = Integer.parseInt(body.get("zamowieniaGotoweId").toString());
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaGotoweRepo.save(new zamowieniaGotowe(zamowieniaGotoweId, idKlient, dataZakupu, dataRealizacji, cena));
    }
    
    @Autowired
    zamowieniaWymiarRepozytorium zamowieniaWymiarRepo;
    @GetMapping("/zamowieniaWymiar/dodajTestowe")
    public String dodajDaneTestoweZamowieniaWymiar(){

    zamowieniaWymiarRepo.saveAll (Arrays. asList(     
    new zamowieniaWymiar (1, 1, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 5, 12), 1000.0),
    new zamowieniaWymiar (2, 2, LocalDate.of(2023, 4, 29), LocalDate.of(2023, 5, 30), 1600.0),
    new zamowieniaWymiar (3, 3, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 6, 12), 950.0)));
    
    return "Testowe rekordy dodane!";
    }
    @GetMapping("/zamowieniaWymiar/pokazWszystkie")
    public List<zamowieniaWymiar> pokarzWszystkieZamowieniaWymiar(){
        List<zamowieniaWymiar> listazamowieniaWymiar = new ArrayList<zamowieniaWymiar>();
        for(zamowieniaWymiar projekt : zamowieniaWymiarRepo.findAll()){
        listazamowieniaWymiar.add(projekt) ;
        }
        return listazamowieniaWymiar;
    }    
    @GetMapping("/zamowieniaWymiar/wyszukajPoId/(id)")
    public String szukajPoIdZamowieniaWymiar(@PathVariable("id") Integer id) {
        String result = zamowieniaWymiarRepo.findById (id) .toString();
        return result;
    }    
    @GetMapping("/zamowieniaWymiar/szukajPoNazwie/(nazwa)")
    public String fetchDataByNazwaZamowieniaWymiar (@PathVariable("nazwa") String nazwa) {
        for (zamowieniaWymiar projekt: zamowieniaWymiarRepo.findByNazwa (nazwa) ) {
            return projekt.toString ();
        }
        return null;
    }   
    @DeleteMapping("/zamowieniaWymiar/{id}")
    public String usunPoIdZamowieniaWymiar(@PathVariable("id") Integer id) {
        zamowieniaWymiarRepo.deleteById (id);
        return "Rekord usunięty";
    }    
    @PostMapping("/zamowieniaWymiar/utworz")
    public zamowieniaWymiar utworzZamowieniaWymiar (@RequestBody Map<String, Object> body) {
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        int idProjektant = Integer.parseInt(body.get("idProjektant").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar (idKlient,idProjektant, dataZakupu, dataRealizacji, cena));
    }   
    @PutMapping ("/zamowieniaWymiar/zmien")
    public zamowieniaWymiar zmienZamowieniaWymiar (@RequestBody Map<String, Object> body) {
        int zamowieniaWymiarId = Integer.parseInt(body.get("zamowieniaWymiarId").toString());
        int idKlient = Integer.parseInt(body.get("idKlient").toString());
        int idProjektant = Integer.parseInt(body.get("idProjektant").toString());
        LocalDate dataZakupu = LocalDate.parse(body.get("dataZakupu").toString());
        LocalDate dataRealizacji = LocalDate.parse(body.get("dataRealizacji").toString());
        Double cena = Double.parseDouble(body.get("cena").toString());
        return zamowieniaWymiarRepo.save(new zamowieniaWymiar(zamowieniaWymiarId, idKlient, idProjektant, dataZakupu, dataRealizacji, cena));
    }
    
}
