package newstart.core;

import java.util.List;


public interface Repository<T>{
    void insert(T objet);
    List<T> findAll();
    T getById(int id);
    void remove(int id);
    void update(T objet)throws ClassNotFoundException;
    void delete(T objet);

}
