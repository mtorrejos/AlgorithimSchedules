package com.main;

import java.util.*;

public class SJF {

    void run(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = in.nextInt();
        ArrayList<SJFProcess> processes = new ArrayList<>();
        int tot = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter process " + (i + 1) + " arrival time: ");
            int arrivalTime = in.nextInt();
            System.out.print("Enter process " + (i + 1) + " burst time: ");
            int burstTime = in.nextInt();
            processes.add(new SJFProcess(i + 1, arrivalTime, burstTime));
        }

        int currentTime = 0;

        while (true) {
            if (tot == n) { 
                break;
            }

            SJFProcess currentProcess = null;
            int minBurstTime = Integer.MAX_VALUE;

            for (SJFProcess process : processes) { //finds the min burst time repeatedly for non-complete processes
                if (!process.completed && process.arrivalTime <= currentTime && process.burstTime < minBurstTime) {
                    minBurstTime = process.burstTime;
                    currentProcess = process;
                }
            }

            if (currentProcess == null) {
                currentTime++;
            } else {
                currentProcess.completionTime = currentTime + currentProcess.burstTime;
                currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.burstTime;
                currentProcess.completed = true;
                currentTime += currentProcess.burstTime;
                tot++;
            }
        }

        System.out.println("\npID \tArrival \tBurst \tComplete \tTAT \tWaiting");
        for (SJFProcess process : processes) {
            avgwt += process.waitingTime;
            avgta += process.turnAroundTime;
            System.out.println(process.processId + "\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t" +
                    process.completionTime + "\t\t" + process.turnAroundTime + "\t" + process.waitingTime);
        }

        System.out.println("\naverage turnaround time is " + (float) (avgta / n));
        System.out.println("average waiting time is " + (float) (avgwt / n));

        in.close();
    }
}

class SJFProcess {

    int processId;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;
    boolean completed;

    public SJFProcess(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completed = false;
    }
}
