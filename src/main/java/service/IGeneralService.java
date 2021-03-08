package service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> showAll();
    T findById(int id);
    void update(T model);
    void save(T model);
    void remove(int id);
    T searchByName(String name);
}
