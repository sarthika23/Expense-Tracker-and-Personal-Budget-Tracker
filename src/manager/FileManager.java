package manager;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

import java.io.*;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.UserData;

import java.time.LocalDateTime;
import java.util.Scanner;


public class FileManager {
    public static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            out.value(value.format(formatter));  // 🪄 Custom format
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            return LocalDateTime.parse(in.nextString(), formatter);  // Parse same format
        }
    }

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()  // Adds line breaks and indentation
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();



    public static UserData loadUserData(String filename) {
        File file = new File(filename);
        UserData data = new UserData();

        try {
            if (!file.exists() || file.length() == 0) {
                boolean created = file.createNewFile();
                if (created || file.length() == 0) {
                    //System.out.println("New data file created: " + filename);
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter your initial bank balance: ₹");
                    int initialBalance = sc.nextInt();
                    data.setBankBalance(initialBalance);
                    saveUserData(data, filename);
                    return data;
                }
            }

            try (Reader reader = new FileReader(file)) {
                data = gson.fromJson(reader, UserData.class);
                if (data == null) data = new UserData();
            }
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Error loading data. Resetting file: " + e.getMessage());
            saveUserData(data, filename);
        }

        return data;
    }


    public static void saveUserData(UserData data, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Error writing to JSON: " + e.getMessage());
        }
    }
}
