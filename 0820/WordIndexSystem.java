import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {

    public static void main(String[] args) {
        String[] sentences = {
            "Java is powerful, and Java is popular.",
            "Data structures are important, and Java helps.",
            "Java and data structures are useful."
        };

        Map<String, Integer> wordCounts = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            String cleaned = sentence
                    .toLowerCase()
                    .replace(".", "")
                    .replace(",", "");

            String[] words = cleaned.split("\\s+");

            for (String word : words) {
                uniqueWords.add(word);

                wordCounts.put(
                        word,
                        wordCounts.getOrDefault(word, 0) + 1
                );
            }
        }

        System.out.println("=== 單字次數 ===");
        System.out.println(wordCounts);

        System.out.println("\n=== 不重複單字 ===");
        System.out.println(uniqueWords);

        System.out.println("\n=== 出現至少兩次的單字 ===");

        for (Map.Entry<String, Integer> entry
                : wordCounts.entrySet()) {

            if (entry.getValue() >= 2) {
                System.out.println(
                        entry.getKey()
                        + "：" + entry.getValue() + " 次"
                );
            }
        }
    }
}
