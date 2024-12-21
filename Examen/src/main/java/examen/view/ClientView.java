package newstart.view;

import java.util.List;
import java.util.Scanner;

import newstart.entities.Client;
import newstart.service.DetteService;

public class ClientView extends ViewImpl<Client>{
    private final Scanner scanner;
    private final DetteService detteService;
    
    public ClientView(DetteService detteService) {
        this.scanner = new Scanner(System.in);
        this.detteService = detteService;
    }
    @Override
    public Client saisie() {
        Client client = new Client();
        System.out.println("Entrer le surnom: ");
        client.setSurnom(scanner.nextLine());
        System.out.println("Entrer le telephone: ");
        client.setTelephone(scanner.nextLine());
        System.out.println("Entrer l'adresse: ");
        client.setAdresse(scanner.nextLine());
        return client;
    }
    @Override
    public void affiche(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
                System.out.println("La somme totale des dettes de ce client est : "+detteService.getMontantTotalClient(client.getId()));
                System.err.println("\n");
            }
        }
    }
}
