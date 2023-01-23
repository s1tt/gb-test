package com.gb.nursery;

import com.gb.nursery.animals.count.Counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import static java.lang.System.exit;

public class AppToSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/nursery";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String GET_ALL_ANIMALS =
            "select type, name, birth_day from dogs\n" +
                    "join pets on dogs.animal_type = pets.id\n" +
                    "union all\n" +
                    "select type, name, birth_day from cats\n" +
                    "join pets on cats.animal_type = pets.id\n" +
                    "union all\n" +
                    "select type, name, birth_day from hamsters\n" +
                    "join pets on hamsters.animal_type = pets.id\n" +
                    "union all\n" +
                    "select type, name, birth_day from camels\n" +
                    "join pack_animals on camels.animal_type = pack_animals.id\n" +
                    "union all\n" +
                    "select type, name, birth_day from horses\n" +
                    "join pack_animals on horses.animal_type = pack_animals.id\n" +
                    "union all\n" +
                    "select type, name, birth_day from donkeys\n" +
                    "join pack_animals on donkeys.animal_type = pack_animals.id;";

    public void connect() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String type = "";

            String[] options = {"1 - Завести новое животное",
                    "2 - Показать список команд, которые выполняет животное",
                    "3 - Обучить животное новым командам",
                    "4 - Показать всех животных",
                    "5 - Exit",
            };

            int option = -1;
            while (option != 5) {
                printMenu(options);
                try {
                    option = Integer.parseInt(BUFFERED_READER.readLine());

                    switch (option) {
                        case 1:
                            type = typeOfAnimal();

                            try (PreparedStatement stmt = con.prepareStatement("insert into " + type + " (name, birth_day, commands) values (?, ?, ?);");
                                 Counter counter = new Counter()) {
                                System.out.println("Введи имя животного");
                                String birth_day = BUFFERED_READER.readLine();
                                System.out.println("Дата рождения");
                                String name = BUFFERED_READER.readLine();
                                System.out.println("Введи через запятую навыки животного");
                                String commands = BUFFERED_READER.readLine();

                                stmt.setString(1, name);
                                stmt.setString(2, birth_day);
                                stmt.setString(3, commands);

                                stmt.executeUpdate();
                                counter.add();
                                System.out.println("Животное успешно добавлено!");
                            } catch (SQLException e) {
                                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                            }
                            break;
                        case 2:
                            type = typeOfAnimal();

                            try (PreparedStatement stmt = con.prepareStatement("select name, birth_day, commands from " + type + ";");
                                 ResultSet rs = stmt.executeQuery()) {
                                while (rs.next()) {
                                    String name = rs.getString(1);
                                    String birth = rs.getString(2);
                                    String commands = rs.getString(3);
                                    System.out.printf("Имя: %s, День рождения: %s, Умеет: %s %n", name, birth, commands);
                                }
                            } catch (SQLException e) {
                                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                            }
                            break;
                        case 3:
                            String commands = "";
                            System.out.println("Выбери тип животного");
                            type = typeOfAnimal();

                            System.out.println("Введи id животного:");
                            try (PreparedStatement stmt = con.prepareStatement("select id, name, birth_day, commands from " + type + ";");
                                 ResultSet rs = stmt.executeQuery()) {
                                while (rs.next()) {
                                    int id = Integer.parseInt(rs.getString(1));
                                    String name = rs.getString(2);
                                    String birth_day = rs.getString(3);
                                    String commands1 = rs.getString(4);
                                    System.out.printf("ID: %s, Имя: %s, День рождения: %s, Умеет: %s %n", id, name, birth_day, commands1);
                                }
                                int id = Integer.parseInt(BUFFERED_READER.readLine());

                                System.out.println("Введи через запятую новые команды для животного:");
                                commands = BUFFERED_READER.readLine();
                                stmt.executeUpdate(addNewCommand(type, id, commands));

                            } catch (SQLException e) {
                                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                            }
                            break;
                        case 4:
                            try (PreparedStatement stmt = con.prepareStatement(GET_ALL_ANIMALS);
                                 ResultSet rs = stmt.executeQuery()) {
                                while (rs.next()) {
                                    String typeOfAnimal = rs.getString(1);
                                    String name = rs.getString(2);
                                    String birth = rs.getString(3);
                                    System.out.printf("Тип животного: %s, Имя: %s, День рождения: %s %n", typeOfAnimal, name, birth);
                                }
                            } catch (SQLException e) {
                                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                            }
                            break;
                        case 5:
                            System.out.println("До свидания!");
                            exit(0);
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("An unexpected error happened. Please try again");
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    private String addNewCommand(String type, int id, String commands) {
        return "update " + type + " set commands = concat(commands, ', " + commands + "') where id = " + id + ";";
    }

    private String typeOfAnimal() throws IOException {
        String type = "";
        String[] options = {"-------Домашние животные-------",
                "1 - Собаки",
                "2 - Кошки",
                "3 - Хомяки",
                "-------Вьючные живолтные-------",
                "4 - Лошади",
                "5 - Верблюды",
                "6 - Ослы",
                "----------------------------",
                "7 - Выход",
        };
        int option = -1;
        while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8) {
            printMenu(options);
            option = Integer.parseInt(BUFFERED_READER.readLine());
        }
        if (option == 1) {
            type = "dogs";
        } else if (option == 2) {
            type = "cats";
        } else if (option == 3) {
            type = "hamsters";
        } else if (option == 4) {
            type = "horses";
        } else if (option == 5) {
            type = "camels";
        } else if (option == 6) {
            type = "donkeys";
        } else if (option == 7) {
            exit(0);
        }
        return type;
    }
}
