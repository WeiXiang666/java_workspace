//Subject.java，科目对应的数据类
import java.util.*;

public class Subject implements java.io.Serializable {
	private String name; // 科目名称
	private List<Category> categories = new ArrayList<Category>(); // 科目下题型集合
	private String instruction; // 科目对应的做题提示
	private List<Question> questions = new ArrayList<Question>(); // 科目下试题集合

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	// 添加试题
	public void addQuestion(Question q) {
		questions.add(q);
	}

	// 添加题型
	public void addCategory(Category c) {
		categories.add(c);
	}

	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(Object o) {
		if (o instanceof Subject) {
			Subject s = (Subject) o;
			return name.equals(s.name);
		} else
			return false;
	}
}
