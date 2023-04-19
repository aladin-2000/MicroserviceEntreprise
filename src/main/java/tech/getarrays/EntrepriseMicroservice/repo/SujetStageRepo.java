package tech.getarrays.EntrepriseMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.getarrays.EntrepriseMicroservice.model.SujetStage;

import java.util.List;
import java.util.Optional;

public interface SujetStageRepo extends JpaRepository<SujetStage, Long> {
    void deleteSujetStageById(Long id);

    Optional<SujetStage> findSujetById(Long id);
    @Query("SELECT s FROM SujetStage s WHERE s.filere = ?1")
    List<SujetStage> findByFilere(String filere);

    @Query("SELECT s FROM SujetStage s WHERE s.taken = true")
    List<SujetStage> findAllTakenSujetStage();
    @Query("SELECT s FROM SujetStage s WHERE s.taken = false")
    List<SujetStage> findAllNoneTakenSujetStage();

    @Query("SELECT s FROM SujetStage s WHERE s.idEntreprise = ?1")
    List<SujetStage> findSujetStageByIdEntreprise(Long idEntreprise);








}
