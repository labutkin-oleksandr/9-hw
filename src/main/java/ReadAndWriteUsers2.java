import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ReadAndWriteUsers2 {
    private static final String PATCH_READ_FILE = "src/main/resources/task2.txt";
    private static final String PATCH_WRITE_FILE = "src/main/resources";

    public static void main(String[] args) {
        File file = new File(PATCH_READ_FILE);
        if(!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exists");
        }

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

        StringBuilder wroteUsersFile = new StringBuilder();

        try(FileWriter writer = new FileWriter(new File(PATCH_WRITE_FILE, "users2.json"))) {
            String[] users = readFile.toString().split("\n");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            wroteUsersFile.append("[\n");

            for(int i = 1; i < users.length; i++) {
                String[] user = users[i].split(" ");
                int age = Integer.parseInt (user[1]);
                User2 newUser = new User2(user[0], age);

                String json = gson.toJson(newUser);
                wroteUsersFile.append(i != users.length - 1 ? json + ",\n" : json);
            }
            wroteUsersFile.append("\n]");
            writer.write(wroteUsersFile.toString());
            writer.flush();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
