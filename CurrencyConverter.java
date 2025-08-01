import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------Currency Converter-------");
        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to be converted: ");
        double amountToConvert = scanner.nextDouble();

        try {
            double convertedAmount = fetchConvertedAmount(base, target, amountToConvert);
            System.out.printf("%.2f %s = %.2f %s\n", amountToConvert, base, convertedAmount, target);
        } catch (Exception error) {
            System.out.println("Sorry, Couldn't fetch the exchange rate. " + error.getMessage());
        }
        System.out.println("Thank You!!");

        scanner.close();
    }

    public static double fetchConvertedAmount(String base, String target, double amt) throws IOException {
        String apiUrl = String.format(
            "https://api.frankfurter.app/latest?amount=%f&from=%s&to=%s",
            amt, base, target
        );

        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        reader.close();
        connection.disconnect();

        String jsonResponse = responseBuilder.toString();
        System.out.println("API Response: " + jsonResponse);

        String regex = String.format("\"%s\"\\s*:\\s*(-?\\d+(\\.\\d+)?)", target);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            throw new IOException("Couldn't read the conversion result from the API response.");
        }
        
    }
}
