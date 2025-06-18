package com.example.facturation.controller;


import com.example.facturation.model.Facture;
import com.example.facturation.service.FactureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author LENOVO
 **/

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping
    public Facture createFacture(@RequestBody @Valid Facture facture) {
        return factureService.creerFacture(facture);
    }

    @GetMapping
    public List<Facture> getAllFactures() {
        return factureService.getAllFactures();
    }

    @GetMapping("/{id}")
    public Facture getFactureById(@PathVariable Long id) {
        return factureService.getFactureById(id);
    }

    // Export JSON structuré d’une facture
    @GetMapping("/{id}/export")
    public Map<String, Object> exportFacture(@PathVariable Long id) {
        Facture f = factureService.getFactureById(id);
        return Map.of(
                "id", f.getId(),
                "client", f.getClient(),
                "date", f.getDate(),
                "lignes", f.getLignes(),
                "totalHT", f.getTotalHT(),
                "totalTVA", f.getTotalTVA(),
                "totalTTC", f.getTotalTTC()
        );
    }

    // BONUS : recherche par client ou date
    @GetMapping("/search")
    public List<Facture> searchFactures(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) String date
    ) {
        if (clientId != null) {
            return factureService.findByClientId(clientId);
        }
        if (date != null) {
            return factureService.findByDate(LocalDate.parse(date));
        }
        return factureService.getAllFactures();
    }
}
