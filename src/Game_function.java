
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

    class Game_function {

    String random_city(List<String> list, int b){
        int n = (int) (Math.random() * b);
        return list.get(n);
    }

    Character getFirstChar(String city) {
        return city.charAt(0);
    }

    Character getLastChar(String city) {
        int pos = city.length() - 1;
        char lastChar = city.toUpperCase().charAt(pos);
        if (city.toUpperCase().charAt(pos) == 'Й') {
            return 'И';
        }
        else if (lastChar == 'Ь' || lastChar == 'Ы' || lastChar == 'Ъ') {
            pos--;
        }
        return city.toUpperCase().charAt(pos);
    }

    private List<Character> getFirstCharMin(List<List<String>> s) {
        List<Character> b = new ArrayList<Character>();
        List<Integer> a = new ArrayList<Integer>();
        for (List<String> d: s) {
            a.add(d.size());
        }
        Collections.sort(a);
        for (Integer i: a) {
            for (List<String> d : s) {
                if (d.size() == i) {
                    b.add(d.get(0).charAt(0));
                }
            }
        }
        return b;
    }

    String find_city(List<String> list, Character c, String level) {
        Game_function g_f = new Game_function();
        if (level.equals("smart")) {
            String al = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
            List<List<String>> ls = new ArrayList<List<String>>();
            for(Character b: al.toCharArray()) {
                List<String> array = new ArrayList<String>();
                for(String city: list) {
                    if (city.charAt(0) == b) {
                        array.add(city);
                    }
                }
                if (array.size() != 0) {
                    ls.add(array);
                }
            }
            for (int i = 0; i < ls.size(); i++) {
                for (String city : list) {
                    if (city.charAt(0) == c) {
                        if (g_f.getLastChar(city).equals(g_f.getFirstCharMin(ls).get(i))) {
                            List<Integer> a = new ArrayList<Integer>();
                            for (List<String> d: ls) {
                                a.add(d.size());
                            }
                            Collections.sort(a);
                            return city;
                        }
                    }
                }
            }
        } else {
            for(String city: list){
                if (city.charAt(0) == c){
                    return city;
                }
            }
        }
        return "Не знаю";
    }
}
