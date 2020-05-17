
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try{
            Path path = Paths.get("src/cities.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String name_first_thread = "Stream №1";
            String name_second_thread = "Stream №2";

            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Stream №1 будет smart или honest?\n");
            String status_first_thread = reader1.readLine();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите для Stream №1 процент забывания:\n");
            String pr_first_thread = reader2.readLine();
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Stream №1 будет ходить 1 или 2?\n");
            String xod_first_thread = reader3.readLine();

            BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Stream №2 будет smart или honest?\n");
            String status_second_thread = reader4.readLine();
            BufferedReader reader5 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите для Stream №2 процент забывания:\n");
            String pr_second_thread = reader5.readLine();

            Exchanger<String> ex = new Exchanger<String>();

            int i1 = Integer.parseInt(xod_first_thread);
            int i2;
            if (i1 == 1) {
                i2 = 2;
            } else {
                i2 = 1;
            }

            Thread gamer1 = new Thread1(name_first_thread, lines, ex, i1,
                    100 - Integer.parseInt(pr_first_thread), status_first_thread);
            Thread gamer2 = new Thread1(name_second_thread, lines, ex, i2,
                    100 - Integer.parseInt(pr_second_thread), status_second_thread);
            gamer1.start();
            gamer2.start();
            gamer1.join();
            gamer2.join();
            System.out.println("Игра окончена!");
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
