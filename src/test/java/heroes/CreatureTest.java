package heroes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreatureTest {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two");
        List<String> subList = list.subList(0, 1);
        for(String s : subList) {
            System.out.println(s);
        }
    }

    @Test
    public void parseDamage() {
        String text = "1-13";
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : text.split("\\-")) {
            numbers.add(Integer.parseInt(part));
        }

        for(Integer i : numbers) {
            System.out.println(i + " ");
        }

        int a = numbers.get(0);
        int b = numbers.get(1);
        int x = a + (int)(Math.random() * ((b - a) + 1));
        System.out.println("Случайное число x: " + x);
    }

}
