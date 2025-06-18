package com.example.facturation.service;

import com.example.facturation.model.Client;
import com.example.facturation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author LENOVO
 **/

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Creer un nouveau client avec la date du jour
    public Client createClient(Client client) {
        client.setDateCreation(LocalDate.now());
        return clientRepository.save(client);
    }

    // Récuperer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Récuperer un client par son ID (exception si non trouvé)
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Client introuvable avec l'id : " + id)
        );
    }
}
