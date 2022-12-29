package lessOne;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
public static void main(String[] args) {
    User user = new User(
            "Alex", "Zaradin", 17
    );
    User user1 = new User(
            "Stepan", "Sorokin", 21
    );

    List<User> userBin = new LinkedList<>();
    userBin.add(user);
    userBin.add(user1);
    userBin.forEach(System.out::println);
}
}