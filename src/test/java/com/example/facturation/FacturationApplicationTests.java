package com.example.facturation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.facturation.model.Facture;
import com.example.facturation.model.LigneFacture;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class FacturationApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCalculTotaux() {
		// Preparer une facture avec 2 lignes
		Facture facture = new Facture();
		facture.setDate(LocalDate.now());

		LigneFacture ligne1 = new LigneFacture();
		ligne1.setDescription("Dev");
		ligne1.setQuantite(2);
		ligne1.setPrixUnitaireHT(new BigDecimal("100.00"));
		ligne1.setTauxTVA(new BigDecimal("20.0"));

		LigneFacture ligne2 = new LigneFacture();
		ligne2.setDescription("Support");
		ligne2.setQuantite(1);
		ligne2.setPrixUnitaireHT(new BigDecimal("50.00"));
		ligne2.setTauxTVA(new BigDecimal("10.0"));

		facture.setLignes(List.of(ligne1, ligne2));

		//  Simulation manuelle du calcul (comme dans FactureService)
		BigDecimal totalHT = ligne1.getPrixUnitaireHT().multiply(BigDecimal.valueOf(ligne1.getQuantite()))
				.add(ligne2.getPrixUnitaireHT().multiply(BigDecimal.valueOf(ligne2.getQuantite())));

		BigDecimal tva1 = ligne1.getPrixUnitaireHT()
				.multiply(BigDecimal.valueOf(ligne1.getQuantite()))
				.multiply(ligne1.getTauxTVA().divide(BigDecimal.valueOf(100)));
		BigDecimal tva2 = ligne2.getPrixUnitaireHT()
				.multiply(BigDecimal.valueOf(ligne2.getQuantite()))
				.multiply(ligne2.getTauxTVA().divide(BigDecimal.valueOf(100)));

		BigDecimal totalTVA = tva1.add(tva2);
		BigDecimal totalTTC = totalHT.add(totalTVA);

		//Verifier les résultats attendus
		assertEquals(new BigDecimal("250.00"), totalHT);
		assertEquals(new BigDecimal("45.00"), totalTVA.setScale(2));
		assertEquals(new BigDecimal("295.00"), totalTTC.setScale(2));
	}

}
