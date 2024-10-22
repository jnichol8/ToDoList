
public class Task {
	String name;
	String desc;
	String catagory;
	int time;
	Boolean isDone;
	
	Task(String newName, String newDesc, int newTime){
		name = newName;
		desc = newDesc;
		time = newTime;
		isDone = false;
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
	public Boolean getIsDone() {
		return isDone;
	}
	public void markDone() {
		isDone = true;
	}
	
	@Override
	public String toString() {
		if(time<=15)
			return (isDone ? "[Done]   " : "[Pending]   ") + name + "   quick(" + time + " mins)";
		else if(time>15 && time < 30)
			return (isDone ? "[Done]   " : "[Pending]   ") + name + "   medium(" + time + " mins)";
		else
			return (isDone ? "[Done]   " : "[Pending]   ") + name + "   long(" + time + " mins)";
	}
}
