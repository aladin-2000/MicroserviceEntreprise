package tech.getarrays.EntrepriseMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.EntrepriseMicroservice.model.SujetStage;
import tech.getarrays.EntrepriseMicroservice.repo.SujetStageRepo;
import tech.getarrays.EntrepriseMicroservice.exception.UserNotFoundException;



import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class SujetStageService {
    private final SujetStageRepo sujetStageRepo;


    @Autowired
    public SujetStageService(SujetStageRepo sujetStageRepo) {
        this.sujetStageRepo = sujetStageRepo;
    }

    public SujetStage addSujet(SujetStage sujetStage) {
        sujetStage.setTaken(false);

        return sujetStageRepo.save(sujetStage);
    }

    public List<SujetStage> findAllSujets() {
        return sujetStageRepo.findAll();
    }

    public SujetStage updateSujet(SujetStage sujetStage) {

        sujetStage.setTaken(false);

        return sujetStageRepo.save(sujetStage);
    }

    public SujetStage findSujetById(Long id) {
        return sujetStageRepo.findSujetById(id)
                .orElseThrow(() -> new UserNotFoundException("Sujet not found"));
    }

    public void deleteSujet(Long id){
        sujetStageRepo.deleteSujetStageById(id);
    }

    public void validerSujet(Long id,Long idEncadrant){

        SujetStage sujetStage;
        sujetStage = sujetStageRepo.findSujetById(id).orElseThrow(() -> new UserNotFoundException("Sujet not found"));
        sujetStage.setTaken(true);
    }
    public List<SujetStage> findByFilere(String filere) {
        return sujetStageRepo.findByFilere(filere);
    }

    public List<SujetStage> findSujetStageNonDisponible() {
        return sujetStageRepo.findAllTakenSujetStage();
    }
    public List<SujetStage> findSujetStageDisponible() {
        return sujetStageRepo.findAllNoneTakenSujetStage();
    }

    public List<SujetStage> findSujetStageByIdEntreprise(Long idEntreprise) {
        return sujetStageRepo.findSujetStageByIdEntreprise(idEntreprise);
    }







}
