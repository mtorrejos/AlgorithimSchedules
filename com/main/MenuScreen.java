package com.main;

import java.util.Scanner;

public class MenuScreen {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1 - SCAN");
		System.out.println("2 - Priority Scheduling");
		System.out.println("3 - Shortest Job Next");
		System.out.println("Choose a scheduling algorithm: ");
		int choice = scanner.nextInt();

		switch (choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				SJF sjf = new SJF();
				sjf.run();
				break;
			default:
				System.out.print("Error: Invalid input");
				break;
			}
	}
}
