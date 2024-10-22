/**
 * 
 */
/**
 * @author james nicholas
 *
 */
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
	private static ArrayList<Task> tasks = new ArrayList<>();
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Boolean run = true;
		while(run) {
			int input = -1;
			System.out.println("ToDo List:");
			System.out.println("1. Add Task");
			System.out.println("2. view Tasks");
			System.out.println("3. edit Task");
			System.out.println("4. mark task as done");
			System.out.println("5. delete task");
			System.out.println("6. Exit");
			
			System.out.print("your choice: ");
			input = scan.nextInt();
			scan.nextLine();//removes the \n from the end of the line
			
			switch(input) {
				case 1:
					addTask();
					break;
				case 2:
					viewTasks();
					break;
				case 3:
					editTask();
					break;
				case 4:
					markTaskDone();
					break;
				case 5:
					deleteTask();
					break;
				case 6:
					scan.close();
					run = false;
					break;
				default:
					System.out.println("error: invalid input");
					
			}
		}
	}
	
	private static void addTask() {
		System.out.println("What is the name of your task? ");
		String name = scan.nextLine();
		System.out.println("What is the description of your task? ");
		String desc = scan.nextLine();
		System.out.println("How long will the task take? ");
		int time = scan.nextInt();
		
		Task task = new Task(name, desc, time);
		tasks.add(task);
	}
	
	private static void editTask() {
		viewTasks();
		System.out.println("Which task would you like to edit? (name): ");
		String name = scan.nextLine();
		for(int i = 0; i<tasks.size(); i++) {
			Task temp = tasks.get(i);
			String tempName = temp.getName();
			if(name.equals(tempName)) {
				System.out.println("1. Name");
				System.out.println("2. Description");
				System.out.println("3. Time");
				System.out.println("which would you like to edit? ");
				
				int input = scan.nextInt();
				scan.nextLine();//removes the \n at the end of the line
				switch(input) {
					case 1:
						System.out.println("What is the new name? ");
						String newName = scan.nextLine();
						temp.setName(newName);
						break;
					case 2:
						System.out.println("What is the new description? ");
						String newDesc = scan.nextLine();
						temp.setDesc(newDesc);
						break;
					case 3:
						System.out.println("What is the new time? ");
						int newTime = scan.nextInt();
						scan.nextLine();//removes \n
						temp.setTime(newTime);
						break;
					default:
						System.out.println("error: incorect input");
				}
					
				return;
			}
		}
		System.out.println("that taks is not on your to-do list");
		
		System.out.println("edit");
	}
	
	private static void viewTasks() {
		if (tasks.isEmpty()) {
			System.out.println("there are no tasks");
			return;
		}
		
		for(int i = 0; i<tasks.size(); i++) {
			Task temp = tasks.get(i);
			String name = temp.getName();
			String desc = temp.getDesc();
			int time = temp.getTime();
			
			System.out.println("----------");
			System.out.print("Name: ");
			System.out.println(name);
			System.out.print("Description: ");
			System.out.println(desc);
			System.out.print("Time: ");
			System.out.println(time);
			System.out.println("----------");
		}
	}
	
	private static void markTaskDone() {
		viewTasks();
		System.out.println("Which task would you like to mark as completed? (name): ");
		String name = scan.nextLine();
		for(int i = 0; i<tasks.size(); i++) {
			Task temp = tasks.get(i);
			String tempName = temp.getName();
			if(name.equals(tempName)) {
				temp.markDone();
				System.out.print(name);
				System.out.println(" was marked completed!");
				return;
			}
		}
		System.out.println("that taks is not on your to-do list");
	}
	
	private static void deleteTask() {
		viewTasks();
		System.out.println("Which task would you like to remove from your to-do list? (name): ");
		String name = scan.nextLine();
		for(int i = 0; i<tasks.size(); i++) {
			Task temp = tasks.get(i);
			String tempName = temp.getName();
			if(name.equals(tempName)) {
				tasks.remove(i);
				System.out.print(name);
				System.out.println(" was removed from your to-do list!");
				return;
			}
		}
		System.out.println("that taks is not on your to-do list");
		
		System.out.println("delete");
	}
}