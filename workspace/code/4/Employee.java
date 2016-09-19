class Employee {				//没有继承声明，
	String id, name;			//默认是从java.lang.Object继承下来。
	float salary;
}
class ProjectManager extends Employee {	//ProjectManager继承了Employee
	//additional feature					//ProjectManager类扩展了Employee
	String project;						//增加了额外的特征
}
class Test {
	public static void main(String[] args) {
		Employee e1 = new Employee();
		e1.id = "008";
		e1.name = "zxc";
		e1.salary = 8888.88f;
		//e1.project = “comedy”;	这个语句是错误的，类Employee没有project成员特征
		
		ProjectManager p1 = new ProjectManager();
		p1.id = "007"; 			//id,name,salary都是继承下来的特征
		p1.name = "james";
		p1.salary = 1.0f;
		p1.project = "spy";			//子类增加的特征
	}
}
