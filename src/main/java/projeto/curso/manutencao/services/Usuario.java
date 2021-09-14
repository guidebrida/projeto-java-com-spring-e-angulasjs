package projeto.curso.manutencao.services;


import org.springframework.security.core.context.SecurityContextHolder;
import projeto.curso.manutencao.security.UserSS;

public class Usuario {
    public static UserSS authenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
    }
}
