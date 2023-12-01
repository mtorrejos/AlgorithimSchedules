package com.main;

import java.util.*;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int priority;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.completionTime = 0;
        this.turnaroundTime = 0;
        this.waitingTime = 0;
    }
}

public class PriorityPreemptiveScheduling {
    void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        List processes = new ArrayList < > ();
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("\nProcess " + (i + 1));
            System.out.print("Enter Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter Burst Time: ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter Priority: ");
            int priority = scanner.nextInt();

            processes.add(new Process(i + 1, arrivalTime, burstTime, priority));
        }

        calculateParameters(processes);
        displayResults(processes);
    }

    public static void calculateParameters(List<Process> processes) {
        int currentTime = 0;
        int totalProcesses = processes.size();
        int completedProcesses = 0;

        while (completedProcesses < totalProcesses) {
            Process selectedProcess = null;
            int highestPriority = Integer.MAX_VALUE;

            for (Process process: processes) {
                if (process.arrivalTime <= currentTime && process.completionTime == 0) {
                    if (process.priority < highestPriority) {
                        highestPriority = process.priority;
                        selectedProcess = process;
                    }
                }
            }

            if (selectedProcess == null) {
                currentTime++;
            } else {
                currentTime++;
                selectedProcess.burstTime--;

                if (selectedProcess.burstTime == 0) {
                    selectedProcess.completionTime = currentTime;
                    selectedProcess.turnaroundTime = selectedProcess.completionTime - selectedProcess.arrivalTime;
                    selectedProcess.waitingTime = selectedProcess.turnaroundTime - selectedProcess.burstTime;

                    completedProcesses++;
                }
            }
        }
    }

    public static void displayResults(List<Process> processes) {
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;

        for (Process process: processes) {
            totalTurnaroundTime += process.turnaroundTime;
            totalWaitingTime += process.waitingTime;

            System.out.println(process.pid + "\t\t" + process.arrivalTime + "\t\t" +
                process.burstTime + "\t\t" + process.completionTime + "\t\t" +
                process.turnaroundTime + "\t\t" + process.waitingTime);
        }

        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();
        double avgWaitingTime = (double) totalWaitingTime / processes.size();

        System.out.println("\nAverage Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}