package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import coins.coin;
import doa.Coindao;

public class Menu {

	private Coindao coindao = new Coindao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("display Coins", "display Coin", "create Coin", "delete Coin",
			"update Coins");

	public void start() {
		String selection = "";

		do {
			printMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					displayCoins();
				} else if (selection.equals("2")) {
					displaycoin();
				} else if (selection.equals("3")) {
					createCoin();
				} else if (selection.equals("4")) {
					deleteCoin();
				} else if (selection.equals("5")) {
					updateCoin();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (!selection.equals("-1")) {
				System.out.println("Press enter to continue...");
				scanner.nextLine();
			} else {
				System.out.println("Bye");
			}

		} while (!selection.equals("-1"));

	}

	private void printMenu() {
		System.out.println("Select an Option:\n-------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ")" + options.get(i));
		}
	}

	private void displayCoins() throws SQLException {
		List<coin> coins = coindao.getcoins();
		for (coin coin : coins) {
			System.out.println(coin.getCoinID() + ": " + coin.getName());
		}
	}

	private void displaycoin() throws SQLException {
		System.out.print("Enter coin id: ");
		int coinID = Integer.parseInt(scanner.nextLine());
		coin coin = coindao.getcoinById(coinID);
		System.out.println(coin.getCoinID() + ": " + coin.getName());

	}

	private void createCoin() throws SQLException {
		System.out.print(" Enter new coin name : ");
		String coinName = scanner.nextLine();
		coindao.createNewCoin(coinName);

	}

	private void deleteCoin() throws SQLException {
		System.out.print(" Enter coin id to delete: ");
		int coinID = Integer.parseInt(scanner.nextLine());
		coindao.deleteCoinById(coinID);
	}

	private void updateCoin() throws SQLException {
		System.out.print(" Enter coin id to update: ");
		int coinID = Integer.parseInt(scanner.nextLine());
		System.out.print(" Enter new coin name : ");
		String coinName = scanner.nextLine();
		coindao.UpdateCoinById(coinID, coinName);
	}
}
