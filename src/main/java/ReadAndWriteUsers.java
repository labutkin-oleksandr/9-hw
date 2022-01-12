import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadAndWriteUsers {
    private static final String PATCH_READ_FILE = "src/main/resources/task2.txt";
    private static final String PATCH_WRITE_FILE = "src/main/resources";

    public static void main(String[] args) {
        File file = new File(PATCH_READ_FILE);
        if(!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exists");
        }
        File newFile = new File(PATCH_WRITE_FILE, "user.ser");

        StringBuilder readFile = new StringBuilder();

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(PATCH_READ_FILE))) {
            int ch = bufferedInputStream.read();
            while(ch != -1) {
                readFile.append((char) ch);
                ch = bufferedInputStream.read();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }

        Collection<User> usersJson = new ArrayList<>();

        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(newFile))) {
            String[] users = readFile.toString().split("\n");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for(int i = 1; i < users.length; i++) {
                String[] user = users[i].split(" ");
                int age = Integer.parseInt (user[1]);
                usersJson.add(new User(user[0], age));
            }
            writer.writeObject(gson.toJson(usersJson));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
