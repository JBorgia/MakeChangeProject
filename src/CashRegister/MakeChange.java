package CashRegister;

import java.util.Scanner;

public class MakeChange {
	static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		int total, paid, denoms[];
		char quit = 'Y';

		welcome();

		do {
			total = ringItems();
			paid = getMoney(total);
			denoms = changeFilter(paid - total);
			printDenoms(denoms);

			System.out.println("Would you like to run another transaction (Y/N)? ");
			quit = kb.next().toUpperCase().charAt(0);
		} while (quit == 'Y');

		System.out.println(quit);
	} // main

	public static void printDenoms(int denoms[]) {
		String monies[][] = { { "Penny", "Pennies" }, { "Nickel", "Nickels" }, { "Dime", "Dimes" },
				{ "Quarter", "Quarters" }, { "One", "Ones" }, { "Five", "Fives" }, { "Ten", "Tens" },
				{ "Twenty", "Twenties" } };

		for (int i = monies.length - 1; i >= 0; i--) {
			if (denoms[i] == 1) {
				System.out.println(denoms[i] + " " + monies[i][0]);
			} else if (denoms[i] > 1) {
				System.out.println(denoms[i] + " " + monies[i][1]);
			}
		}
	}// printDenoms

	public static int[] changeFilter(int change) {
		int denoms[] = new int[8];

		System.out.println("\nChange due:\t$ " + (((double) (change)) / 100) + "\n");

		// change filters through denomination sized ifs
		while (change > 0) {
			if (change >= 2000) {
				denoms[7]++;
				change = change - 2000;
			} else if (change >= 1000) {
				denoms[6]++;
				change = change - 1000;
			} else if (change >= 500) {
				denoms[5]++;
				change = change - 500;
			} else if (change >= 100) {
				denoms[4]++;
				change = change - 100;
			} else if (change >= 25) {
				denoms[3]++;
				change = change - 25;
			} else if (change >= 10) {
				denoms[2]++;
				change = change - 10;
			} else if (change >= 5) {
				denoms[1]++;
				change = change - 5;
			} else if (change >= 1) {
				denoms[0]++;
				change = change - 1;
			}
		}

		return denoms;
	} // changeFilter

	public static void welcome() {
		String name;
		System.out.print("Hi, what's your name? ");

		name = kb.nextLine();

		System.out.print("\n+--------------------------");
		for (int i = 0; i < name.length(); i++) {
			System.out.print("-");
		}
		System.out.println("+");
		System.out.println("|   Welcome to " + name + "'s Market   |");
		System.out.print("+--------------------------");
		for (int i = 0; i < name.length(); i++) {
			System.out.print("-");
		}
		System.out.println("+");
		System.out.println("\nRing up items (enter '0' when done)\n");
	}

	public static int ringItems() {
		int item, total = 0;

		while (true) {
			System.out.print("Item price: ");
			item = (int) ((kb.nextDouble()) * 100);
			if (item == 0) {
				break;
			} else {
				total = total + item;
			}
			System.out.print("Total:\t$ " + (((double) (total)) / 100) + "\n");
		}
		System.out.println("\n---------------------------------------------");
		System.out.print("\nTotal:\t$ " + (((double) (total)) / 100) + "\n");
		System.out.println("\n---------------------------------------------");

		return total;
	}// ringItems

	public static int getMoney(int total) {
		int paid;

		do {
			System.out.print("Enter total cash collected: ");
			paid = (int) ((kb.nextDouble()) * 100);
			if (paid < total) {
				System.out.println("That's not enough!\nTell them to give you all their money!: ");
			}
		} while (paid < total);

		return paid;
	}
}// MakeChange