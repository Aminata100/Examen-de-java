package newstart.repository.implementation;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.User;
import newstart.enums.Role;

public interface RepositoryUser extends Repository<User>{
    List<User> findByRole(Role role);
    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);
}
