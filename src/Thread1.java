
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Thread1 extends Thread{

        private List<String> list = new ArrayList<String>();
        private Exchanger<String> exchanger;
        private String city;
        private int o = 0;
        private int p = 0;
        private String lev;

        Thread1(String name, List<String> lines, Exchanger<String> ex, int or_1_or_2, int percentage_ratio, String level){
            super(name);
            this.exchanger = ex;
            this.o = or_1_or_2;
            this.list = lines;
            this.p = percentage_ratio;
            this.lev = level;
        }
        public void run() {
            System.out.printf("%s started... \n", Thread.currentThread().getName());

            double ps = (double)(p*list.size())/100;
            for(int i = 0; i < (list.size() - (int)ps); i++) {
                list.remove(list.get(i));
            }

            try{
                Game_function g_f = new Game_function();
                while (true){
                    if (o == 1) {
                        city = g_f.random_city(list, list.size());
                        System.out.printf("%s: " + city + "\n", Thread.currentThread().getName());
                        Character cc = g_f.getLastChar(city);
                        System.out.printf("%s: Тебе на " + cc + "\n", Thread.currentThread().getName());
                        list.remove(city);
                        city = exchanger.exchange(city);
                        o = 0;
                    } else {
                            city = exchanger.exchange(city);
                            if (city.equals("Победа")) {
                                System.out.printf("%s: " + city + "\n", Thread.currentThread().getName());
                                city = exchanger.exchange("Победа");
                                break;
                            } else {
                                list.remove(city);
                                Character c = g_f.getLastChar(city);
                                city = g_f.find_city(list, c, lev);
                                if (city.equals("Не знаю")) {
                                    System.out.printf("%s: " + city + "\n", Thread.currentThread().getName());
                                    city = exchanger.exchange("Победа");
                                    break;
                                } else {
                                    System.out.printf("%s: " + city + "\n", Thread.currentThread().getName());
                                    Character c1 = g_f.getLastChar(city);
                                    System.out.printf("%s: Тебе на " + c1 + "\n", Thread.currentThread().getName());
                                    city = exchanger.exchange(city);
                                }
                            }
                    }
                }
                System.out.printf("%s finished... \n", Thread.currentThread().getName());
            } catch(Exception r){
                System.out.println(r.toString());
            }
        }
    }
