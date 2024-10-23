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
	
	public void editTask(int index, String newName, String newDesc, int newTime) {//allows the user to change one part of a task
		tasks.get(index).setName(newName);
		tasks.get(index).setDesc(newDesc);
		tasks.get(index).setTime(newTime);
		tasks.get(index).updateCatagory();
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