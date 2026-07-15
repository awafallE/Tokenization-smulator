public class TestCrypt {

        public static void main(String[] args) {
            String alias = "pcard001";  // même alias que WS_SMART_SUBS_SIGNKEY_ALIAS
            String plainPassword = "hpssmart001";  // votre mot de passe en clair

            PwcPasswordEncryptor encryptor = new PwcPasswordEncryptor(alias);
            String encrypted = encryptor.encrypt(plainPassword);

            System.out.println("Encrypted: " + encrypted);

            // Vérification immédiate en re-déchiffrant
            String decrypted = encryptor.decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);
            System.out.println("Match: " + plainPassword.equals(decrypted));
        }

}
