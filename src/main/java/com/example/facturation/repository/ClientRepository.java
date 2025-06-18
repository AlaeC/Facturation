package com.example.facturation.repository;
import com.example.facturation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LENOVO
 **/


public interface ClientRepository extends JpaRepository<Client, Long> {
}