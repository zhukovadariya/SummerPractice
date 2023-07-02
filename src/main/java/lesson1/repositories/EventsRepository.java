package lesson1.repositories;


import lesson1.models.Event;
import lesson1.models.User;

import java.util.List;

public interface EventsRepository extends CrudRepository<Event> {
    Event findByName(String nameEvent);

    void saveUserToEvent(User user, Event event);
    List<Event> findAllByMembersContains(User user);
}



