package newstart.view.connexion;

import java.util.List;
import java.util.Scanner;

import newstart.entities.User;
import newstart.view.ViewImpl;


public class ConnectView extends ViewImpl<User> {
    private final Scanner scanner;

    public ConnectView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public User saisie() {
        User user = new User();
        System.out.println("Entrer le login: ");
        user.setLogin(scanner.nextLine());
        System.out.println("Entrer le password: ");
        user.setPassword(scanner.nextLine());
        user.setRole(null);
        return user;
    }


    @Override
    public void affiche(List<User> user) { 
        /* TODO document why this method is empty */
    }
}
