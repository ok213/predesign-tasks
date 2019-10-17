package app.service;

public class GenerationPassword {

    public static void main(String[] args) {

        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        for (int i = 0; i < 5; i++) {
            String passwd = encoder.encode("111");
            System.out.println(passwd);
            System.out.println(encoder.matches("111", passwd));
        }

    }
}
