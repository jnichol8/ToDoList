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
			scan.nextLine();
			
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
		
		//System.out.println("add");
	}
	
	private static void editTask() {
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
		System.out.println("view");
	}
	
	private static void markTaskDone() {
		System.out.println("finish");
	}
	
	private static void deleteTask() {
		System.out.println("delete");
	}
}