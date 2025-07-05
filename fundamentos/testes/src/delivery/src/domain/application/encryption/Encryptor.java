package testes.src.delivery.src.domain.application.encryption;

import java.util.Map;

public interface Encryptor {
    public String hash(String plan);
    public Boolean compare(String hash, String plan);
    public String encrypt(String payload);
}
