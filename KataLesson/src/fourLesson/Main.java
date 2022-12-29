package fourLesson;

import firstLess.Temp;

import java.nio.charset.Charset;
import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.*;

// Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.
public class Main {
    public static void main(String[] args) throws IOException {

        String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;

        MailMessage mailMess1 = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        MailMessage mailMess2 = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );
        MailMessage mailMess3 = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );
        List<MailMessage> message = Arrays.asList(
                mailMess1, mailMess2, mailMess3
        );
        MailService<String> mailService = new MailService<>();
        message.stream().forEachOrdered(mailService);
//        mailService.getMailBox().forEach((s, a) -> System.out.print(s + a + "\n"));

        Map<String, List<String>> mailBox = mailService.getMailBox();
//        if (mailBox.containsKey("H.P. Lovecraft") && mailBox.containsValue(Arrays.asList("This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!")))
//            System.out.println("true");
//        if (mailBox.containsKey("Christopher Nolan") && mailBox.containsValue(Arrays.asList("Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
//                "Я так и не понял Интерстеллар.")))
//            System.out.println("true");

        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        MailService<Integer> salaryService = new MailService<>();

        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        Map<String, List<Integer>> salaries = salaryService.getMailBox();

        System.out.println(salaries.get(salary1.getTo()));
        System.out.println(salaries.get(salary2.getTo()));
        System.out.println(salaries.get(null));

        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)):"wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)):"wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)):"wrong salaries mailbox content (3)";
    }




    public static class MailService<T> implements Consumer<Mail<T>> {
    private final Map<String, List<T>> mailBox = new HashMap<>() {
        @Override
        public List<T> get(Object key) {
            return (super.get(key) == null) ? new ArrayList<>() : super.get(key);
        }
    };
        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }

        @Override
        public void accept(Mail<T> t) {
           mailBox.computeIfAbsent(t.getFrom(),k ->  new ArrayList<>()).add(t.content);
           mailBox.computeIfAbsent(t.getTo(), k -> new ArrayList<>()).add(t.content);
        }
    }
    public static class Salary extends Mail<Integer>{
        public Salary(String from, String to, int content){
            super(from, to, content);
        }
    }

    public static class MailMessage extends Mail<String>{
        public MailMessage(String from, String to, String content) {
            super(from, to , content);

        }
    }
    public abstract static class Mail<T> {
        private String from;
        private String to;
        private T content;

        public Mail(String from, String to, T content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        public String getFrom() {
            return from;
        }
        public String getTo() {
            return to;
        }
        public T getContent() {
            return content;
        }
    }


}