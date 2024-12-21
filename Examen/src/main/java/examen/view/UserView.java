package newstart.view;

import java.util.List;
import java.util.Scanner;

import newstart.entities.Client;
import newstart.entities.User;
import newstart.enums.Role;
import newstart.view.ViewImplemantation.ViewUser;

public class UserView extends ViewImpl<User> implements ViewUser<User>{
    private final Scanner scanner;

    public UserView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public User saisie() {
        User user = new User();
        System.out.println("Entrer le nom: ");
        user.setNom(scanner.nextLine());
        System.out.println("Entrer le prenom: ");
        user.setPrenom(scanner.nextLine());
        System.out.println("Entrer le login: ");
        user.setLogin(scanner.nextLine());
        System.out.println("Entrer le password: ");
        user.setPassword(scanner.nextLine());
        user.setRole(saisieRole());
        return user;
    }

    @Override
    public User saisieClientCompte(Client client) {
        User user = new User();
        System.out.println("Entrer le nom: ");
        user.setNom(scanner.nextLine());
        System.out.println("Entrer le prenom: ");
        user.setPrenom(scanner.nextLine());
        System.out.println("Entrer le login: ");
        user.setLogin(scanner.nextLine());
        user.setClient(client);
        user.setRole(Role.CLIENT);
        System.out.println("Entrer le password: ");
        user.setPassword(scanner.nextLine());
        return user;
    }

    @Override
    public void affiche(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Aucun compte trouvé.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    @Override
    public Role saisieRole(){
        int role;
        do{
        System.out.println("Choisir un role: ");
        System.out.println("1-Boutiquier ");
        System.out.println("2-Admin ");
        System.out.println("3-Client ");
        role=scanner.nextInt();
        }while(role < 1 || role > 3);
        return Role.values()[role - 1];
    }

}
