package tech.getarrays.EntrepriseMicroservice.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String nomEntreprise;
    private final String email;
    private final String password;
    private final String phone;
    private final String descreption;
}
