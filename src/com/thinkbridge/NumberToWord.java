package com.thinkbridge;
import java.util.Scanner;

public class NumberToWord {
	
	static String[] single_digits = new String[]{ 
			"", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Eleven", "Twelve", "Thirteen",
			"Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	static String[] tens_multiple = new String[]{
			"", "", "Twenty", "Thirty",
			"Forty", "Fifty","Sixty",
			"Seventy", "Eighty", "Ninety"};
	
	public static String convertToWord(final int n) {

		if (n < 20) {
			return single_digits[n];
		}

		if (n < 100) {
			return tens_multiple[n / 10] + ((n % 10 != 0) ? " " : "") + single_digits[n % 10];
		}

		if (n < 1000) {
			return single_digits[n / 100] + " Hundred" + ((n % 100 != 0) ? " And " : "") + convertToWord(n % 100);
		}

		if (n < 100000) {
			return convertToWord(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convertToWord(n % 1000);
		}

		if (n < 10000000) {
			return convertToWord(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convertToWord(n % 100000);
		}

		return convertToWord(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convertToWord(n % 10000000);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		double number = 0.0;
		int no = 0;
		String numberToWord = "";
		String decimalString = null;
		String input = null;
		while(true) {
			System.out.println("Please Enter your number: ");
			input = null;
			input = sc.nextLine();
			if(input.length() <= 0 ) {
				System.out.println("Empty input, Please enter valid number!");
				continue;
			}
			
			if(!input.matches("(\\d+([.]([0-9]{0,2}))?)")) {
				System.out.println("Please enter valid number (e.g. 4200.36, 36524)");
				continue;
			}
			
			number = Double.parseDouble(input);
			if(number < 1) {
				System.out.println("Please enter number greater than 1");
				continue;
			}
			
			if(number > 999999.99 ) {
				System.out.println("Please enter number between 1 to 999999.99");
				continue;
			}
			break;
		}
		
		if(input.contains(".")) {
			decimalString = input.substring(input.indexOf("."));
			no = Integer.parseInt(input.substring(0, input.indexOf('.')));
			if(decimalString.length() == 1) {
				decimalString = "";
			} else {
				decimalString = decimalString.substring(1);
				decimalString = decimalString + "/100";
			}
		} else {
			no = Integer.parseInt(input);
		}
		
		numberToWord = "Rs. "+ convertToWord(no);
//		numberToWord = numberToWord + decimalString!=null ? " "+ decimalString: ""  + " ONLY";
		if(decimalString!=null && !decimalString.equals("")) {
			numberToWord = numberToWord + " " + decimalString;
		}
		numberToWord = numberToWord + " ONLY";
		
		System.out.println(numberToWord);
	}
}
