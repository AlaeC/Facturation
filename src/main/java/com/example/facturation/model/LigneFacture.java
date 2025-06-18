package com.example.facturation.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
/**
 * @author LENOVO
 **/





@Entity
public class LigneFacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantite;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix unitaire doit être positif")
    private BigDecimal prixUnitaireHT;

    @DecimalMin(value = "0.0", message = "Le taux de TVA ne peut pas être négatif")
    private BigDecimal tauxTVA;

    @ManyToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(BigDecimal prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
    }

    public BigDecimal getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(BigDecimal tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}
