
public class Task {
	String name;
	String desc;
	int time;
	
	Task(String newName, String newDesc, int newTime){
		name = newName;
		desc = newDesc;
		time = newTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String newDesc) {
		desc = newDesc;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int newTime) {
		time = newTime;
	}
}
