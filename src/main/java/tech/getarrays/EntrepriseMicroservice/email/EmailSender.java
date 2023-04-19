package tech.getarrays.EntrepriseMicroservice.email;

public interface EmailSender {
    void send(String to, String email);
}
