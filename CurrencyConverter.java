import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your actual API key
    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double rate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;

            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) { // Success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            
            in.close();
            connection.disconnect();
            
            JSONObject json = new JSONObject(content.toString());
            return json.getJSONObject("rates").getDouble(targetCurrency);
        } else {
            throw new Exception("Failed to fetch exchange rate");
        }
    }
}



