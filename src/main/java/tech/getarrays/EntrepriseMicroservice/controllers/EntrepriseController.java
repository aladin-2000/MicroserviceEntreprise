package tech.getarrays.EntrepriseMicroservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.EntrepriseMicroservice.model.Entreprise;
import tech.getarrays.EntrepriseMicroservice.service.EntrepriseService;
import tech.getarrays.EntrepriseMicroservice.service.SujetStageService;

import java.util.List;

@RestController
@RequestMapping("/Entreprise")
public class EntrepriseController {
    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Entreprise>> getAllEntreprises () {
        List<Entreprise> entreprises = entrepriseService.findAllEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById (@PathVariable("id") Long id) {
        Entreprise entreprise = entrepriseService.findEntrepriseById(id);
        return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise newEntreprise = entrepriseService.addEntrepriset(entreprise);
        return new ResponseEntity<>(newEntreprise, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Entreprise> updateEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise updateEntreprise = entrepriseService.updateEntreprise(entreprise);
        return new ResponseEntity<>(updateEntreprise, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable("id") Long id) {
        entrepriseService.deleteEntreprise(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Entreprise entreprise)
    {
        String email=entreprise.getEmail();
        String password=entreprise.getPassword();
        return new ResponseEntity<>(entrepriseService.login(email,password), HttpStatus.OK);
    }

    @PostMapping("ValiderPostulation/{idEtudiant}/{idSujetStage}")
    public ResponseEntity<String> validerSujet(
            @PathVariable Long idEtudiant,
            @PathVariable Long idSujetStage) {

        try {
            entrepriseService.validerSujetBien(idEtudiant, idSujetStage);
            return ResponseEntity.ok("Sujet validé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la validation du sujet : " + e.getMessage());
        }
    }



}
