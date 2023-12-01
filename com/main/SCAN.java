package com.main;

import java.util.Arrays;
import java.util.Scanner;

public class SCAN {
    public void run() {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Input current position: ");
        int headPosition = scanner.nextInt();
        
        System.out.print("Input track size: ");
        int trackSize = scanner.nextInt();
        
        while(true) {
            System.out.print("Input number of request (max of 10): ");
            int n = scanner.nextInt();

            if(n<=10 && n>0) {
                int[] requests = new int[n];
                
                for (int i = 0; i < n; i++) {
                    int j = i+1;
                    System.out.print("Loc "+j+":");
                    requests[i] = scanner.nextInt();
                    
                    // Check if the request is within the track size
                    if (requests[i] < 0 || requests[i] >= trackSize) {
                        System.out.println("Invalid request. Request should be within the track size.");
                        i--;
                        continue;
                    }
                }
                
                System.out.println("Original Request Order: " + Arrays.toString(requests));
                System.out.println("Initial Head Position: " + headPosition);

                scanDiskScheduling(requests, headPosition, trackSize);

                break; }
            
            else {
                System.out.println("Invalid number. Choose between 1-10. ");
                continue; }
        }
    }



	public static void scanDiskScheduling(int[] requests, int headPosition, int trackSize) {
	    Arrays.sort(requests);
	
	    int totalHeadMovements = 0;
	    int currentHeadPosition = headPosition;
	    int tail = trackSize;
	            
	    int headIndex = Arrays.binarySearch(requests, headPosition);
	
	    if (headIndex < 0) {
	        headIndex = -headIndex - 1;
	    }
	    
	    // Move the disk arm towards the end of the disk
	    for (int i = headIndex; i < requests.length; i++) {
	        System.out.println("Servicing Request: " + requests[i]);
	        totalHeadMovements += Math.abs(requests[i] - currentHeadPosition);
	        currentHeadPosition = requests[i];
	    }
	    
	    // Go to the tail
	    System.out.println("Servicing Request: " + tail+"[Tail]");
	    totalHeadMovements += Math.abs(tail - currentHeadPosition);
	    currentHeadPosition = tail;
	            
	    // Move the disk arm towards the beginning of the disk
	    for (int i = headIndex - 1; i >= 0; i--) {
	        System.out.println("Servicing Request: " + requests[i]);
	        totalHeadMovements += Math.abs(requests[i] - currentHeadPosition);
	        currentHeadPosition = requests[i];
	    }
	
	    System.out.println("Total Head Movements: " + totalHeadMovements + "\n");
	    
	}
}
