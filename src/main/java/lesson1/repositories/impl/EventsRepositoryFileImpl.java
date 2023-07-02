package lesson1.repositories.impl;

import lesson1.models.Event;
import lesson1.models.User;
import lesson1.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventsFileName;
    private final String eventsAndUsersFileName;

    public EventsRepositoryFileImpl(String filename, String eventsAndUsersFileName) {
        this.eventsFileName = filename;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }


    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsFileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(eventsFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields[1].equals(nameEvent)) {
                    return Event.builder()
                            .id(fields[0])
                            .name(nameEvent)
                            .date(LocalDate.parse(fields[2]))
                            .build();
                }
            }
            return null;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Event> findAllByMembersContains(User user) {

        String id = user.getId();
        ArrayList<String> eventsId = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields[0].equals(id)) {
                    eventsId.add(fields[1]);
                }
            }
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }

        List<Event> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(eventsFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                for (String string: eventsId) {
                    if (fields[0].equals(string)) {
                        events.add(Event.builder()
                                .id(fields[0])
                                .name(fields[1])
                                .date(LocalDate.parse(fields[2]))
                                .build());
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return events;
    }
}


