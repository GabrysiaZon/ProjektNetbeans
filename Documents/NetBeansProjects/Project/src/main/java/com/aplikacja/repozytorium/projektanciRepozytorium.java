
package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.projektanci;
import org.springframework.data. repository.CrudRepository;

public interface projektanciRepozytorium extends CrudRepository<projektanci, Integer>{
  
    List<projektanci> findByNazwa(String name);
}
