package lesson1;

import lesson1.repositories.EventsRepository;
import lesson1.repositories.UsersRepository;
import lesson1.repositories.impl.EventsRepositoryFileImpl;
import lesson1.repositories.impl.UsersRepositoryFileImpl;
import lesson1.services.AppService;

import java.time.LocalDate;

public class Main {


    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");
        EventsRepository eventsRepository = new EventsRepositoryFileImpl("events.txt", "events_users.txt");
        AppService appService = new AppService(usersRepository, eventsRepository);

        appService.signUp("admin@gmail.com", "qwerty007");
        appService.signUp("marsel@gmail.com", "qwerty008");
        appService.signUp("dariya@gmail.com", "qwerty009");

        appService.addEvent("Практика по Java", LocalDate.now());
        appService.addEvent("Практика по ИИ", LocalDate.now());
        appService.addEvent("Практика по Golang", LocalDate.now().plusDays(1));

        appService.addUserToEvent("marsel@gmail.com", "Практика по Golang");
        appService.addUserToEvent("dariya@gmail.com", "Практика по Java");

        System.out.println(appService.getAllEventsByUser("dariya@gmail.com"));
    }
}

