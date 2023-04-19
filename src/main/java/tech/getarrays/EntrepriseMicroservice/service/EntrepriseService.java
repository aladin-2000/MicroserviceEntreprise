package tech.getarrays.EntrepriseMicroservice.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.EntrepriseMicroservice.exception.UserNotFoundException;
import tech.getarrays.EntrepriseMicroservice.model.Entreprise;
import tech.getarrays.EntrepriseMicroservice.repo.EntrepriseRepo;
import tech.getarrays.EntrepriseMicroservice.registration.token.ConfirmationToken;
import tech.getarrays.EntrepriseMicroservice.registration.token.ConfirmationTokenService;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EntrepriseService {
    private final EntrepriseRepo entrepriseRepo;
    private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    public EntrepriseService(EntrepriseRepo entrepriseRepo , ConfirmationTokenService confirmationTokenService) {
        this.entrepriseRepo = entrepriseRepo;
        this.confirmationTokenService=confirmationTokenService;
    }

    public Entreprise addEntrepriset(Entreprise entreprise) {
        entreprise.setPassword(BCrypt.hashpw(entreprise.getPassword(),BCrypt.gensalt()));
        entreprise.setEnabled(true);
        return entrepriseRepo.save(entreprise);
    }



    public List<Entreprise> findAllEntreprises() {
        return entrepriseRepo.findAll();
    }

    public Entreprise updateEntreprise(Entreprise entreprise) {
        return entrepriseRepo.save(entreprise);
    }

    public Entreprise findEntrepriseById(Long id) {
        return entrepriseRepo.findEntrepriseById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEntreprise(Long id){
        entrepriseRepo.deleteEntrepriseById(id);
    }

    public Entreprise login(String email, String password){
        Entreprise entreprise = entrepriseRepo.findEntrepriseByEmail(email).orElseThrow(() -> new UserNotFoundException("Email ou mot de passe est incorrecte"));
        if (!( BCrypt.checkpw(password,entreprise.getPassword()) && entreprise.getEnabled())==true) {
            throw new UserNotFoundException("Email ou mot de passe sont incorrecte ");
        }
        return entreprise;


    }

    public String addEntreprise(Entreprise entreprise) {
        entreprise.setPassword(BCrypt.hashpw(entreprise.getPassword(),BCrypt.gensalt()));
        entrepriseRepo.save(entreprise);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                entreprise
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableEntreprise(String email) {
        return entrepriseRepo.enableEntreprise(email);
    }


    public void SujetTaken(Long idSujetStage){
        entrepriseRepo.marquerSujetPris(idSujetStage);
    }

    public void NetoyerLaTable(Long idSujetStage){
        entrepriseRepo.nettoyerTable(idSujetStage);
    }

    public void ValiderPostulation(Long idEtudiant , Long idSujet){
        entrepriseRepo.validerPostulation(idEtudiant,idSujet);
    }

    @Transactional
    public void validerSujetBien(Long idEtudiant, Long idSujetStage) {
        entrepriseRepo.validerPostulation(idEtudiant, idSujetStage);
        entrepriseRepo.marquerSujetPris(idSujetStage);
        entrepriseRepo.nettoyerTable(idSujetStage);
    }


}
