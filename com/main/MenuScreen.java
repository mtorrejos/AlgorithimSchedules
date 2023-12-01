package com.main;

import java.util.Scanner;

public class MenuScreen {

	public static void main(String[] args) {
		while(true) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1 - SCAN");
		System.out.println("2 - Priority Scheduling");
		System.out.println("3 - Shortest Job Next");
		System.out.println("4 - Exit");
		System.out.println("Choose a scheduling algorithm: ");
		int choice = scanner.nextInt();

		switch (choice) {
			case 1:
				SCAN scan = new SCAN();
				scan.run();
				break;
			case 2:
				PriorityPreemptiveScheduling pps = new PriorityPreemptiveScheduling();
				pps.run();
				break;
			case 3:
				SJF sjf = new SJF();
				sjf.run();
				break;
			case 4:
				System.out.print("Exiting...");
				return;
			default:
				System.out.print("Error: Invalid input");
				break;
			}
		}
	}
}
