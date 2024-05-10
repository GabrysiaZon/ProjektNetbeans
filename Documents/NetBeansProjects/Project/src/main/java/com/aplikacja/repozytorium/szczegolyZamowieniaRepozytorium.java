package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.szczegolyZamowienia;
import org.springframework.data. repository.CrudRepository;

public interface szczegolyZamowieniaRepozytorium extends CrudRepository<szczegolyZamowienia, Integer>{
    
    List<szczegolyZamowienia> findByNazwa(String name);
}
