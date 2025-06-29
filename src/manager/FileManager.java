package manager;

import com.google.gson.GsonBuilder;
import model.Expense;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.time.LocalDateTime;


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


    public static List<Expense> loadExpenses(String filename) {
        File file = new File(filename);
        List<Expense> expenses = new ArrayList<>();

        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("📁 JSON file not found. New file created: " + filename);
                }
                return expenses;
            }

            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<ArrayList<Expense>>() {}.getType();
                expenses = gson.fromJson(reader, listType);
                if (expenses == null) expenses = new ArrayList<>();
                System.out.println("✅ Expenses loaded from JSON file.");
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading from JSON: " + e.getMessage());
        }

        return expenses;
    }

    public static void saveExpenses(List<Expense> expenses, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(expenses, writer);
            System.out.println("✅ Expenses saved to JSON file: " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error writing to JSON: " + e.getMessage());
        }
    }
}
