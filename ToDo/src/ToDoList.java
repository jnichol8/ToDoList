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
	
	
	public void addTask(String name, String desc, int time) {//adds a task to the list
		Task task = new Task(name, desc, time);
		tasks.add(task);
	}
	
	private static void editTask() {//allows the user to change one part of a task
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
	
	private static void viewTasks() {//allows the user to see the current list of tasks
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
	
	public void markTaskDone(int index) {//marks the task done
		tasks.get(index).markDone();
	}
	
	public void deleteTask(int index) {//removes a task from the list
		tasks.remove(index);
	}
	
	public Task getTask(int index) {
		return tasks.get(index);
	}
}