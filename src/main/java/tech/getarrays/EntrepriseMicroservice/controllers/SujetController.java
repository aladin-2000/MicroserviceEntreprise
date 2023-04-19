package tech.getarrays.EntrepriseMicroservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.EntrepriseMicroservice.model.SujetStage;
import tech.getarrays.EntrepriseMicroservice.service.SujetStageService;


import java.util.List;

@RestController
@RequestMapping("/SujetStage")
public class SujetController {
    private final SujetStageService sujetStageService;

    public SujetController(SujetStageService sujetStageService) {
        this.sujetStageService = sujetStageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SujetStage>> getAllSujets () {
        List<SujetStage> sujetStages = sujetStageService.findAllSujets();
        return new ResponseEntity<>(sujetStages, HttpStatus.OK);
    }

    @GetMapping("/find/SujetStageById/{id}")
    public ResponseEntity<SujetStage> getSujetById (@PathVariable("id") Long id) {
        SujetStage sujetStage = sujetStageService.findSujetById(id);
        return new ResponseEntity<>(sujetStage, HttpStatus.OK);
    }



    @GetMapping("/disponible")
    public List<SujetStage> getTakenSujets() {
        return sujetStageService.findSujetStageDisponible();
    }
    @GetMapping("/non-disponible")
    public List<SujetStage> getNoneTakenSujets() {
        return sujetStageService.findSujetStageNonDisponible();
    }


    @GetMapping("/filiere/{filere}")
    public ResponseEntity<List<SujetStage>> findByFilere(@PathVariable String filere) {
        List<SujetStage> sujetStages = sujetStageService.findByFilere(filere);
        return new ResponseEntity<>(sujetStages, HttpStatus.OK);
    }


    @GetMapping("/Entreprise/{idEntreprise}")
    public ResponseEntity<List<SujetStage>> findSujetStageByEntreprise(@PathVariable Long idEntreprise) {
        List<SujetStage> sujetStages = sujetStageService.findSujetStageByIdEntreprise(idEntreprise);
        return new ResponseEntity<>(sujetStages, HttpStatus.OK);
    }





    @PostMapping("/add")
    public ResponseEntity<SujetStage> addSujet(@RequestBody SujetStage sujetStage) {
        SujetStage newSujetStage = sujetStageService.addSujet(sujetStage);
        return new ResponseEntity<>(newSujetStage, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SujetStage> updateSujet(@RequestBody SujetStage sujetStage) {
        SujetStage updateSujetStage = sujetStageService.updateSujet(sujetStage);
        return new ResponseEntity<>(updateSujetStage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSujet(@PathVariable("id") Long id) {
        sujetStageService.deleteSujet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
