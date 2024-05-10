package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.zamowieniaWymiar;
import org.springframework.data. repository.CrudRepository;

public interface zamowieniaWymiarRepozytorium extends CrudRepository<zamowieniaWymiar, Integer>{
  
    List<zamowieniaWymiar> findByNazwa(String name);
}
