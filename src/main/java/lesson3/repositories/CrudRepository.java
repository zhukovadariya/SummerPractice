package lesson3.repositories;


import java.util.List;

public interface CrudRepository<T> {
    void save(T model);
    List<T> findAll();
}

