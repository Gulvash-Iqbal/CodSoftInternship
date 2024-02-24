import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.Scanner;
//import org.json.JSONObject;

class CurrencyConverter {
    public static void main(String[] args) {
        try {
            // Design UI for currency selection
            Console clB = System.console();
            String baseCurrency = clB.readLine("Enter Base Currency code e.g. USD: ");
            Console clT = System.console();
            String targetCurrency = clT.readLine("Enter Target Currency code e.g. EUR: ");
            Console clAmount = System.console();
            String inputA = clAmount.readLine("Enter Amount e.g. 100 : ");
           // String baseCurrency = "USD"; // Default base currency
            // String targetCurrency = "EUR"; // Default target currency
            
            // Fetch real-time exchange rates from exchangeratesapi.io
            double exchangeRate = fetchExchangeRate(baseCurrency.toString(), targetCurrency.toString());
            
            
            if (exchangeRate != -1) {
                // Take input for the amount to convert
                double amount = Double.parseDouble(inputA) ;//100; // Default amount
                double convertedAmount = amount * exchangeRate;
                
                // Display result
                System.out.println("Converted exchange rate for " + inputA +" " + baseCurrency +" is " + convertedAmount + " " + targetCurrency);
            } else {
                System.out.println("Failed to fetch exchange rate. Please try again later.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to fetch exchange rate from exchangeratesapi.io
    public static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        // String urlStr = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;
        String urlStr = "https://v6.exchangerate-api.com/v6/b9e3f15f87ca1cce3d2d8710/pair/" + baseCurrency + "/" + targetCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        double rate = 0.0;
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);  
            for(String name : inputLine.split(","))
                {
                    if(name.contains("conversion_rate"))
                        rate = Double.parseDouble(name.split(":")[1].replace("}",""));

                }
          
        }
        

        in.close();
        
        // JSONObject jsonResponse = new JSONObject(response.toString());
        // if (jsonResponse.has("error")) {
        //     System.out.println("Failed to fetch exchange rate. Error: " + jsonResponse.getString("error"));
        //     return -1;
        // } else {
        //     JSONObject rates = jsonResponse.getJSONObject("rates");
        //     return rates.getDouble(targetCurrency);
        // }
        return rate;
    }
}