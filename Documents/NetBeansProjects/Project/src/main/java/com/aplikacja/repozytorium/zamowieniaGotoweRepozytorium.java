package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.zamowieniaGotowe;
import org.springframework.data. repository.CrudRepository;

public interface zamowieniaGotoweRepozytorium extends CrudRepository<zamowieniaGotowe, Integer>{
    
    List<zamowieniaGotowe> findByNazwa(String name);
}
