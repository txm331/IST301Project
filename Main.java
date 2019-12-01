package ist.psu.edu;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	int tCount = 1;

	public static void main(String[] args) {
		Main main = new Main();

		//char variables used for user input
		final char TRAN_CODE = 'T';
		final char EXIT_CODE = 'E';
		final char TRAN_PRINT = 'P';
		char userAction;


		//variable for users options
		final String PROMPT_ACTION = "Add 'T'ransaction, 'P'rint transaction, 'E'xit.";
		ArrayList<Block> bList = new ArrayList<>();

		userAction = getAction(PROMPT_ACTION);

		//loop used for user interface
		while (userAction != EXIT_CODE) {
			switch (userAction) {
				case TRAN_CODE:
					bList.add(main.addTransaction());
					break;
				case TRAN_PRINT:
					Block.listTransactions(bList);
					break;
			}
			userAction = getAction(PROMPT_ACTION);
		}
	}




		//method used for getting user input
		public static char getAction (String prompt){
			Scanner scnr = new Scanner(System.in);
			String answer = "";
			System.out.println(prompt);
			answer = scnr.nextLine().toUpperCase() + " ";
			char firstChar = answer.charAt(0);
			return firstChar;
		}

		//method used for allowing user to add data
		public Block addTransaction () {

			Block transaction = new Block("0");
			Scanner scanner = new Scanner(System.in);
			System.out.println("What Payment type do you prefer cash, credit, debit?");
			transaction.setPaymentType(PaymentType.valueOf(scanner.nextLine()));
			System.out.println("What is the Transaction for?");
			transaction.setDescription(scanner.nextLine());
			System.out.println("How much money would you like to transfer?");
			transaction.setTransaction(scanner.nextInt());

			return transaction;
		}
	}

