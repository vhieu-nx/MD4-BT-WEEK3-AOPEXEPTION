package service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> showAll();
    T findById(int id);
    void update(int id, T t);
    void save(T t);
    void remove(int id);
    T searchByName(String name);
}
