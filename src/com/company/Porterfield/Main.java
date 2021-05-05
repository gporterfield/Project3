package com.company.Porterfield;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Main  {

    public static ArrayList<Task> tasks = new ArrayList();
    public static Scanner input = new Scanner(System.in);



    public static void main(String[] args) {

        boolean pass = true;
        while (pass) {

            try {
                menu();
                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Goodbye!");
                    Gson gson = new Gson();
                    ArrayList task1 = new ArrayList();
                    task1.add(tasks);
                    String json = gson.toJson(task1);

                    try {
                        FileWriter writer = new FileWriter("data.json");
                        gson.toJson(tasks, writer);
                        writer.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                }
                if (choice == 1) {
                    tasks.add(addTask());
                }
                if (choice == 2) {
                    tasks.remove(removeTask());
                }
                if (choice == 3) {
                    updateTask(tasks);
                }
                if (choice == 4) {
                    Gson gson1 = new Gson();
                    BufferedReader br = null;
                    try{
                        br = new BufferedReader(new FileReader("data.json"));
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }

                    ArrayList<Task> task2 = gson1.fromJson(br, ArrayList.class);
                    System.out.println(task2);
                    allTasks(tasks);
                }
                if (choice == 5) {
                    priority(tasks);
                }
                pass = true;
            } catch (Exception e) {
                pass = false;
                System.out.println("please enter a valid option");
            }
        }

    }


    static void menu() {
        System.out.println("Please choose an option:");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List Tasks of a Certain Priority.");
        System.out.println("(0) Exit");
    }

    static Task addTask() {

        Scanner input2 = new Scanner(System.in);
        System.out.println("Please enter the task: ");
        String name = input2.nextLine();
        System.out.println("Please enter a description: ");
        String description = input2.nextLine();
        System.out.println("Please enter the priority: ");
        int priority = input2.nextInt();
        Task task = new Task(name, description, priority);
        return task;
    }

    static int removeTask() {

        Scanner input = new Scanner(System.in);
        System.out.println("Please type which index to remove");
        int i = input.nextInt();

        return i;
    }

    static void updateTask(ArrayList<Task> a) {

        System.out.println("What is the index of the task you'd like to change?");
        int index = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the task:");
        String name = input.nextLine();
        System.out.println("Please enter a description: ");
        String description = input.nextLine();
        System.out.println("Please enter the priority: ");
        int priority = input.nextInt();

        a.set(index, new Task(name, description, priority));
    }

    static void allTasks(ArrayList<Task> a) {
        Collections.sort(a);

        for (Task task : a) {
            System.out.println(task.toString());
        }

    }

    static void priority(ArrayList<Task> a) {

        System.out.println("What is the priority number?: ");
        int prior = input.nextInt();
        input.nextLine();

        for (Task task : a) {
            if (prior == task.getPriority()) {
                System.out.println(task.toString());
            }
        }
    }
}