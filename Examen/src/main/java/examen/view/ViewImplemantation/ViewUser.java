package newstart.view.ViewImplemantation;

import newstart.core.View;
import newstart.entities.Client;
import newstart.enums.Role;

public interface  ViewUser<T> extends View<T> {
    Role saisieRole();
    T saisieClientCompte(Client client);
}
