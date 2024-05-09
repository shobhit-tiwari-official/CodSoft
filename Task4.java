import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Currency Converter");
        
        // Select base and target currency
        System.out.print("Enter the base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter the target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        
        // Fetch exchange rates
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        
        if (exchangeRate != -1) {
            // Take input amount
            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();
            
            // Convert currency
            double convertedAmount = amount * exchangeRate;
            
            // Display result
            System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
        }
        
        scanner.close();
    }
    
    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/" + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                
                reader.close();
                
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject rates = jsonResponse.getJSONObject("rates");
                double exchangeRate = rates.getDouble(targetCurrency);
                
                return exchangeRate;
            } else {
                System.out.println("Failed to fetch exchange rates. HTTP error code: " + responseCode);
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }
}
