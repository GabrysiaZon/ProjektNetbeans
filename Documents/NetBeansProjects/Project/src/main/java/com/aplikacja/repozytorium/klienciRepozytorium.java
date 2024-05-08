/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplikacja.repozytorium;

import java.util.List;
import com.aplikacja.model.klienci;
import org.springframework.data.repository;

public interface klienciRepozytorium extends CrudRepository<klienci, Integer>{
    List<klienci> findByNazwa(String name);
}
