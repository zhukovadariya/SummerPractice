package lesson1.repositories;

import lesson1.models.User;

public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String emailUser);
}

