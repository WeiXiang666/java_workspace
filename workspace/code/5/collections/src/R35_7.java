import java.util.*;

public class R35_7 {
	public void a() {
		List list = new LinkedList();
		for (int i = 1; i <= 35; i++) {
			list.add(new Integer(i));
		}
		List select = new LinkedList();
		for (int i = 0; i < 7; i++) {
			Collections.shuffle(list); // Collections这个集合工具类提供乱序方法
			select.add(list.remove(0));
		}
		Collections.sort(select); // Collections这个集合工具类提供排序方法
		for (int i = 0; i < 7; i++) {
			if (i < 6) {
				System.out.print(select.get(i) + ", "); // 输出数据，不添加回车换行
			} else {
				System.out.println(select.get(i)); // 输出数据并添加回车换行
			}
		}
	}

	public void b() {				// TreeSet提供升序的遍历
		Set set = new TreeSet(); 	// 不能换成HashSet，因为其不能提供升序遍历
		while (set.size() < 7) { 	// 产生7个不重复的1~35之间的随机整数
			set.add(new Integer((int) (Math.random() * 35 + 1)));
		}
		System.out.println(set); // toString方法按照元素顺序带有一定的格式输出
	}

	public static void main(String[] args) {
		R35_7 r = new R35_7();
		r.a();
		r.b();
	}
}
