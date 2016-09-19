package com.abc.or;

public class Property implements java.io.Serializable {
	private String name, type, column; // 名字，类型，属性列

	private boolean id; // 是否是主键属性

	public boolean getId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Property) {
			Property p = (Property) o;
			return name.equals(p.name);
		} else {
			return false;
		}
	}
}
