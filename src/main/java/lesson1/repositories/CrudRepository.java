package lesson1.repositories;

// CRUD - Create, Read, Update, Delete
public interface CrudRepository<T> {
    void save(T model);
}

