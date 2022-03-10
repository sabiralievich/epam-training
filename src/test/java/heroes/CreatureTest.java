package heroes;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CreatureTest {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two");
        List<String> subList = list.subList(0, 1);
        for(String s : subList) {
            System.out.println(s);
        }
    }

}
