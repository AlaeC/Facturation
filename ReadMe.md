# 📦 Structure du projet – Module de Facturation (Spring Boot)

Ce projet suit une architecture propre, inspirée du modèle **MVC + Service + Repository** afin de faciliter la lisibilité, la maintenance et les tests.

---

## 🗂️ Arborescence


---

## 📁 Détail des dossiers

### `controller/`
Contient les classes REST comme :
- `ClientController` : gestion des clients (`GET`, `POST`, `GET by ID`)
- `FactureController` : création, export, recherche de factures

### `model/`
Contient les entités JPA :
- `Client.java`
- `Facture.java`
- `LigneFacture.java`

Avec les annotations `@Entity`, `@ManyToOne`, etc.

### `repository/`
Contient les interfaces Spring Data :
- `ClientRepository`
- `FactureRepository`
- `LigneFactureRepository`

Ces interfaces héritent de `JpaRepository`.

### `service/`
Contient la logique métier (calculs, validations) :
- `ClientService` : création simple avec `dateCreation`
- `FactureService` : calcule `HT`, `TVA`, `TTC`, vérifie taux, etc.

### `config/`
Contient la configuration de sécurité (optionnelle) :
- `SecurityConfig` : désactive ou configure Spring Security

### `test/`
Contient des tests JUnit :
- `FactureServiceTest.java` : teste le calcul automatique des montants

---

## 📌 Bonnes pratiques utilisées

- ✅ **Séparation des responsabilités**
- ✅ **Injection de dépendances (`@Autowired`)**
- ✅ **Validation (`@NotBlank`, `@Email`, `@Valid`)**
- ✅ **Respect des règles métier**
- ✅ **Tests unitaires (`@Test`, `assertEquals`)**

---
## 🧪 Test avec Postman

Une collection Postman est fournie avec ce projet pour tester facilement l’API REST.

📁 Fichier inclus :  
`Facturation.postman_collection.json`

### 🔧 Instructions :

1. Ouvre Postman
2. Clique sur **Import**
3. Sélectionne le fichier `Facturation.postman_collection.json` fourni dans le projet
4. Une collection nommée **Facturation API** apparaîtra avec tous les endpoints :
    - 🔹 Création de client
    - 🔹 Liste des clients
    - 🔹 Création de facture
    - 🔹 Export d’une facture
    - 🔹 Recherche par client ou date

> ✅ Aucun token requis si Spring Security est désactivé

Cette collection permet de tester l’ensemble du backend sans avoir besoin de Swagger.



---

## ✨ Exemple de package complet

```text
com.sahbe.facturation
├── controller
│   ├── ClientController.java
│   └── FactureController.java
├── model
│   ├── Client.java
│   ├── Facture.java
│   └── LigneFacture.java
├── repository
│   ├── ClientRepository.java
│   ├── FactureRepository.java
│   └── LigneFactureRepository.java
├── service
│   ├── ClientService.java
│   └── FactureService.java
└── config
    └── SecurityConfig.java



