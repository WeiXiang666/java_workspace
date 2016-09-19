//Option.java，选择题选项数据类
public class Option implements java.io.Serializable {
	private Question parent; // 该选项所属的试题对象
	private String id; // 选项的编号，值为A，B，C，D等等
	private String content; // 选项的内容

	public Question getParent() {
		return parent;
	}

	public void setParent(Question parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
