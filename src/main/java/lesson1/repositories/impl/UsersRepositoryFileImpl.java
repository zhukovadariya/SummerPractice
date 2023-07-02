package lesson1.repositories.impl;

import lesson1.models.User;
import lesson1.repositories.UsersRepository;

import java.io.*;


public class UsersRepositoryFileImpl implements UsersRepository {
    private final String filename;

    public UsersRepositoryFileImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields[1].equals(emailUser)) {
                    return User.builder()
                            .id(fields[0])
                            .email(emailUser)
                            .password(fields[2])
                            .build();
                }
            }
            return null;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}


