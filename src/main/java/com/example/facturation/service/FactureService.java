package com.example.facturation.service;

import com.example.facturation.model.Facture;
import com.example.facturation.model.LigneFacture;
import com.example.facturation.repository.ClientRepository;
import com.example.facturation.repository.FactureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

/**
 * @author LENOVO
 **/



@Service
public class FactureService {

    private static final Set<BigDecimal> TAUX_TVA_VALIDES = Set.of(
            new BigDecimal("0.0"),
            new BigDecimal("5.5"),
            new BigDecimal("10.0"),
            new BigDecimal("20.0")
    );

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Facture creerFacture(Facture facture) {
        // Validation que une facture doit avoir au moins une ligne
        List<LigneFacture> lignes = facture.getLignes();
        if (lignes == null || lignes.isEmpty()) {
            throw new IllegalArgumentException("Une facture doit contenir au moins une ligne.");
        }

        BigDecimal totalHT = BigDecimal.ZERO;
        BigDecimal totalTVA = BigDecimal.ZERO;

        for (LigneFacture ligne : lignes) {
            // verifie taux de TVA valide
            if (!TAUX_TVA_VALIDES.contains(ligne.getTauxTVA())) {
                throw new IllegalArgumentException("Taux de TVA invalide : " + ligne.getTauxTVA());
            }

            // calcul du montant HT par ligne
            BigDecimal ligneHT = ligne.getPrixUnitaireHT()
                    .multiply(BigDecimal.valueOf(ligne.getQuantite()));

            // calcul TVA
            BigDecimal ligneTVA = ligneHT
                    .multiply(ligne.getTauxTVA())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            totalHT = totalHT.add(ligneHT);
            totalTVA = totalTVA.add(ligneTVA);

            // Lien vers la facture pour la persistance
            ligne.setFacture(facture);
        }

        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT.add(totalTVA));

        // Sauvegarde (cascade => lignes enregistrées automatiquement)
        return factureRepository.save(facture);
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElseThrow();
    }

    public List<Facture> findByClientId(Long clientId) {
        return factureRepository.findByClientId(clientId);
    }

    public List<Facture> findByDate(java.time.LocalDate date) {
        return factureRepository.findByDate(date);
    }
}

