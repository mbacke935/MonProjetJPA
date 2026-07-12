package en.edu.ucak.entites;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MainTestGlobal {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJpaPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Marque marque = new Marque();
            marque.setNom("Samsung");
            marque.setDescription("Marque de test globale");
            em.persist(marque);

            Produit produit = new Produit();
            produit.setCode("P100");
            produit.setNom("Ecran 24");
            produit.setDescription("Ecran de test global");
            produit.setPrix(new BigDecimal("120000.00"));
            produit.setMarque(marque);
            em.persist(produit);

            Stock stock = new Stock();
            stock.setQuantite(15);
            stock.setProduit(produit);
            em.persist(stock);

            Client client = new Client();
            client.setNom("Diop");
            client.setPrenom("Fallou");
            client.setEmail("fallou.diop@test.com");
            client.setAdresseLivraison("Touba");
            client.setNumeroTelephone("770000000");
            em.persist(client);

            Carte carte = new Carte();
            carte.setDateCreation(LocalDateTime.now());
            carte.setProprietaire(client);
            em.persist(carte);

            Facture facture = new Facture();
            facture.setNumero("FAC-001");
            facture.setClient(client);

            LigneArticle ligneArticle = new LigneArticle();
            ligneArticle.setQuantite(2);
            ligneArticle.setPrixUnitaire(120000.0);
            ligneArticle.setFacture(facture);
            ligneArticle.setProduit(produit);

            Set<LigneArticle> lignes = new HashSet<>();
            lignes.add(ligneArticle);
            facture.setLigneArticles(lignes);
            em.persist(facture);

            em.getTransaction().commit();

            System.out.println("Marque id = " + marque.getId());
            System.out.println("Produit id = " + produit.getId());
            System.out.println("Stock id = " + stock.getId());
            System.out.println("Client id = " + client.getId());
            System.out.println("Carte id = " + carte.getId());
            System.out.println("Facture id = " + facture.getId());
            System.out.println("LigneArticle id = " + ligneArticle.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
