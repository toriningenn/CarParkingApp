import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<Integer> nums = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.compareTo(o2) > 0) {
                    return o1.compareTo(o2) * -1;
                } else if (o1.compareTo(o2) < 0) {
                    return Math.abs(o1.compareTo(o2));
                } else {
                    return 0;
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            nums.add((int)(Math.random()*10)); //от 0 до 10 не включительно
        }
        for (int num : nums) {
            System.out.println(num);
        }
    }
    }