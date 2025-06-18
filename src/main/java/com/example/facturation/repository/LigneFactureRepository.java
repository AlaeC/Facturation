package com.example.facturation.repository;
import com.example.facturation.model.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LENOVO
 **/

public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {
}
