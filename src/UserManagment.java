import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagment {
    public static class User {

        public String name;

        public User() {

        }

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "}";
        }
    }
    public static void addUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File dataFile = new File("userData");
        List<User> userList = new ArrayList<>();

        if (dataFile.exists() && dataFile.length() > 2) {
            try {
                userList = mapper.readValue(dataFile, new TypeReference<List<User>>() {
                });
                System.out.println("✅ Benutzerdatei gefunden und geladen.");

                System.out.println("Bitte lege einen neuen Benutzer an.");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Gib den Namen ein: ");
                String userName = scanner.nextLine();

                UserManagment.User newUser = new UserManagment.User(userName);
                userList.add(newUser);
                mapper.writeValue(dataFile, userList);
                System.out.println("✅ Neuer Benutzer wurde erfolgreich angelegt und gespeichert!");
                Main.userSelect(userList);

            } catch (IOException e) {
                System.out.println("⚠️ Fehler beim Lesen der Datei. Starte mit leerer Liste.");
            }
        } else {

                System.out.println("Bitte lege einen neuen Benutzer an.");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Gib den Namen ein: ");
                String userName = scanner.nextLine();

                UserManagment.User newUser = new UserManagment.User(userName);
                userList.add(newUser);
                mapper.writeValue(dataFile, userList);
                System.out.println("✅ Neuer Benutzer wurde erfolgreich angelegt und gespeichert!");
                Main.userSelect(userList);
        }
    }
}
