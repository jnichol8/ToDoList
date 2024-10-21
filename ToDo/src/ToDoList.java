/**
 * 
 */
/**
 * @author james nicholas
 *testing again
 */
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
	private static ArrayList<String> tasks = new ArrayList<>();
	
	public static void main(String[] args) {
		Boolean run = true;
		Scanner scan = new Scanner(System.in);
		while(run) {
			System.out.println("ToDo List:");
			System.out.println("1. Add Task");
			System.out.println("2. view Tasks");
			System.out.println("3. edit Task");
			System.out.println("4. mark task as done");
			System.out.println("5. delete task");
			System.out.println("6. Exit");
			
			System.out.print("your choice: ");
			int input = scan.nextInt();
			
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
					run = false;
					break;
				default:
					System.out.println("error: invalid input");
			}
			
		}
	}
	
	private static void addTask() {
		System.out.println("add");
	}
	
	private static void editTask() {
		System.out.println("edit");
	}
	
	private static void viewTasks() {
		System.out.println("view");
	}
	
	private static void markTaskDone() {
		System.out.println("finish");
	}
	
	private static void deleteTask() {
		System.out.println("delete");
	}
}