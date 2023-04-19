package tech.getarrays.EntrepriseMicroservice.model;



import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String nomEntreprise;
    private String email;
    private String phone;
    private String descreption;
    private String password;
    private boolean enabled;



    public Entreprise() {}

    public Entreprise(String nomEntreprise, String email, String phone,String descreption, String password) {
        this.nomEntreprise=nomEntreprise;
        this.email = email;
        this.phone = phone;
        this.password=password;

        this.descreption=descreption;
        this.enabled=false;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getEnabled(){return this.enabled;}
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getNomEntreprise() {
        return nomEntreprise;
    }
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }



    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getDescreption(){return descreption;}
    public void setDescreption(String descreption){this.descreption=descreption;}





    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
