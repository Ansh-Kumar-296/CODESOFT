import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.print("Enter base currency (e.g., USD): ");
        String from = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String to = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double convertedAmount = convertCurrency(from, to, amount);
            System.out.printf("%.2f %s = %.2f %s\n", amount, from, convertedAmount, to);
        } catch (Exception e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }
    }

    public static double convertCurrency(String from, String to, double amount) throws IOException {
    String urlStr = String.format(
        "https://api.frankfurter.app/latest?amount=%f&from=%s&to=%s",
        amount, from, to
    );

    HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream())
    );

    StringBuilder content = new StringBuilder();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
        content.append(inputLine);

    in.close();
    con.disconnect();

    String json = content.toString();
    System.out.println("API Response:\n" + json);

    // Extract "to" currency value using regex
    String pattern = String.format("\"%s\"\\s*:\\s*(-?\\d+(\\.\\d+)?)", to);
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(json);

    if (m.find()) {
        return Double.parseDouble(m.group(1));
    } else {
        throw new IOException("Could not parse result from API");
    }
}


}
