import java.io.*;
import java.util.regex.Pattern;

public class PhoneNumbersValidation {
    private static final String PATCH = "src/main/resources/task1.txt";

    public static void main(String[] args) {
        File file = new File(PATCH);

        if(!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exists");
        }

        StringBuilder phoneNumbers = new StringBuilder();

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(PATCH))) {
            int ch = bufferedInputStream.read();
            while(ch != -1) {
                phoneNumbers.append((char) ch);
                ch = bufferedInputStream.read();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }

        String[] numbers = phoneNumbers.toString().split("\n");
        for(int i = 0; i < numbers.length; i++) {
            String number = numbers[i];
            if(Pattern.matches("((\\(\\d{3}\\)\\s)|(\\d{3}-))\\d{3}-\\d{4}", number.trim())) {
                System.out.print(i != numbers.length - 1 ? number + "\n" : number);
            }
        }
    }
}
