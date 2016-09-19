//Category.java，题型对应的数据类
import java.io.*;
import java.util.*;

public class Category implements Serializable {
	private String name; // 题型的名称
	private String instruction; // 题型做题提示
	private String subject; // 科目
	private List<Question> questions = new ArrayList<Question>(); // 题型下的试题集合

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	/* 为该题型添加一道试题 */
	public void addQuestion(Question q) {
		questions.add(q);
	}

	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(Object o) {
		if (o instanceof Category) {
			Category c = (Category) o;
			return name.equals(c.name);
		} else
			return false;
	}
}
