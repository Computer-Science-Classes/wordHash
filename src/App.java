import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * This class serves as the applications entry point.
 * It fetches a text from a given URL, processes the text into words,
 * and adds them into a hash set. It then prints various satistice about the
 * hash set
 */
public class App {
    private static final String SPECIAL_CHARACTERS = "\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\|\\\\\\:\\\"\\;\\'\\<\\>\\,\\.\\/\\“\\”\\™";

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            HashStringSet hashSet = new HashStringSet(9973); // Initialize with a prime number as number of buckets

            // Fetch the book
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.gutenberg.org/files/4300/4300-h/4300-h.htm")) // Replace link
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Process the text
            Scanner scanner = new Scanner(response.body());
            while (scanner.hasNext()) {
                String word = scanner.next();
                word = normalize(word);
                hashSet.add(word);
            }

            // Print the results
            System.out.println("Number of words: " + hashSet.size());
            System.out.println("Number of buckets: " + hashSet.numBuckets());
            System.out.println("Number of used buckets: " + hashSet.numUsedBuckets());
            System.out.println("Load factor: " + hashSet.loadFactor());
            System.out.println("Memory efficiency factor: " + hashSet.memoryEfficiencyFactor());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Normalizes a word by converting it to lower case and removing special
     * characters
     * 
     * @param word The word to normalize
     * @return The normalized word
     */
    private static String normalize(String word) {
        word = word.toLowerCase();
        word = word.replaceAll("[" + SPECIAL_CHARACTERS + "]", "");
        return word;
    }
}
