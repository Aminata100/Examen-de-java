package newstart.core;

import java.util.List;


public interface Service<T> {
    void create(T objet);
    List<T> show();
    void update(T objet )throws ClassNotFoundException;
}
