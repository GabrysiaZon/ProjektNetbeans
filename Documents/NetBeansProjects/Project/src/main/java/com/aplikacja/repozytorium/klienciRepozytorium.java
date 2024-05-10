package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.klienci;
import org.springframework.data.repository.CrudRepository;

public interface klienciRepozytorium extends CrudRepository<klienci, Integer>{
    
    List<klienci> findByNazwa(String name);
}
