//Question.java，选择题对应的数据类
import java.util.*;

public class Question implements java.io.Serializable, Comparable {
	private String subject; // 科目
	private String category; // 题型
	private int id; // 试题编号
	private String type, answer; // 试题类型和正确答案
	private String choice = ""; // 用户输入的选项选择
	private String stem, ask; // 试题的题干和提问
	private List<Option> options = new ArrayList<Option>(); // 试题选项

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	/* 为试题添加一个选项 */
	public void addOption(Option o) {
		options.add(o);
	}

	/* 返回试题对象的散列值 */
	public int hashCode() {
		return id;
	}

	/* 判断试题是否相等 */
	public boolean equals(Object o) {
		if (o instanceof Question) {
			Question q = (Question) o;
			return id == q.getId();
		} else
			return false;
	}

	// 试题的比较方法，按照试题编号进行大小比较
	public int compareTo(Object o) {
		Question q = (Question) o;
		return id - q.getId();
	}
}
