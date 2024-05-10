package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.produkty;
import org.springframework.data.repository.CrudRepository;

public interface produktyRepozytorium extends CrudRepository<produkty, Integer>{
    
    List<produkty> findByNazwa(String name);
}

