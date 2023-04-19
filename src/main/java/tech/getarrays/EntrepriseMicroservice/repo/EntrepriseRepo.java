package tech.getarrays.EntrepriseMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.EntrepriseMicroservice.model.Entreprise;
import java.util.Optional;

public interface EntrepriseRepo extends JpaRepository<Entreprise, Long> {
    void deleteEntrepriseById(Long id);

    Optional<Entreprise> findEntrepriseById(Long id);
    Optional<Entreprise> findEntrepriseByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Entreprise a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableEntreprise(String email);







    @Transactional
    @Modifying
    @Query("UPDATE Postulation a " +
            "SET a.Valide = TRUE WHERE a.idEtudiant = ?1 AND a.idSujet = ?2")
    void validerPostulation(Long idEtudiant, Long idSujet);

    @Transactional
    @Modifying
    @Query("UPDATE SujetStage a SET a.taken = TRUE WHERE a.id = ?1")
    void marquerSujetPris(Long idSujet);

    @Transactional
    @Modifying
    @Query("DELETE FROM Postulation p WHERE p.idSujet = ?1 AND p.Valide = false")
    void nettoyerTable(Long idSujetStage);


}
