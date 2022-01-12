import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WordsReader {
    private static final String PATCH = "src/main/resources/words.txt";

    public static void main(String[] args) {
        File file = new File(PATCH);

        if(!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exists");
        }

        StringBuilder readFile = new StringBuilder();

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(PATCH))) {
            int ch = bufferedInputStream.read();
            while(ch != -1) {
                readFile.append((char) ch);
                ch = bufferedInputStream.read();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }

        String[] words = readFile.toString().replace("\n", " ").split(" ");
        showWordFrequency(words);
        }

        public static void showWordFrequency(String[] words) {
            Map<String, Integer> salaries = new HashMap<>();
            salaries.put(words[0], 1);
            for(int i = 1; i < words.length; i++) {
                String word = words[i].trim();
                int count = salaries.containsKey(word) ? salaries.get(word) + 1 : 1;
                salaries.put(word, count);
            }
            salaries.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(System.out::println);
        }
    }
