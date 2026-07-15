import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PwcPasswordEncryptor {
    private static final Logger LOG = LoggerFactory.getLogger(PwcPasswordEncryptor.class);

    private static final String ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";

    private final PooledPBEStringEncryptor encryptor;

    public PwcPasswordEncryptor(String masterPassword) {
        encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(masterPassword);
        config.setAlgorithm(ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("4");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
    }


    public String encrypt(String value) {
        return encryptor.encrypt(value);
    }


    public String decrypt(String encryptedValue) {
        return encryptor.decrypt(encryptedValue);
    }
}
