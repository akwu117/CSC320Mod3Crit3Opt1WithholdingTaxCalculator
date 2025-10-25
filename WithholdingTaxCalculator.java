import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class WithholdingTaxCalculator {
    private static final double BRACKET1_RATE = 0.10; // < $500
    private static final double BRACKET2_RATE = 0.15; // $500–< $1500
    private static final double BRACKET3_RATE = 0.20; // $1500–< $2500
    private static final double BRACKET4_RATE = 0.30; // >= $2500

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter weekly income: ");

        if (!in.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a numeric value.");
            return;
        }

        double income = in.nextDouble();
        if (income < 0) {
            System.out.println("Income cannot be negative.");
            return;
        }

        double rate = taxRateFor(income);
        double withholding = income * rate;
        double netPay = income - withholding;

        NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percent = NumberFormat.getPercentInstance(Locale.US);
        percent.setMinimumFractionDigits(0);

        System.out.println("Income:         " + money.format(income));
        System.out.println("Tax rate:       " + percent.format(rate));
        System.out.println("Withholding Tax: " + money.format(withholding));
        System.out.println("Net pay:        " + money.format(netPay));
    }

    private static double taxRateFor(double income) {
        if (income < 500) return BRACKET1_RATE;
        if (income < 1500) return BRACKET2_RATE;
        if (income < 2500) return BRACKET3_RATE;
        return BRACKET4_RATE;
    }
}
