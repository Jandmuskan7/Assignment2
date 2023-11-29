package assignment2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Assignment2 {

    private static final String API_KEY = "895a187f9bb24cd29c7a1617ac815d40"; // Replace with your NewsAPI key

    public static void main(String[] args) {
        try {
            String apiUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Parse the JSON response
            // (You may want to use a JSON library for a more robust solution)
            String newsInfo = parseNewsInfo(response.toString());
            System.out.println("Top News Headlines:");
            System.out.println(newsInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseNewsInfo(String jsonResponse) {
        // This is a simple example; you may want to use a JSON library for a more robust solution
        // Extract the relevant information from the JSON response
        StringBuilder result = new StringBuilder();

        String[] articles = jsonResponse.split("\"title\":\"");
        for (int i = 1; i < articles.length; i++) {
            String title = articles[i].split("\",\"")[0];
            result.append(i).append(". ").append(title).append("\n\n");
        }

        return result.toString();
    }
}
