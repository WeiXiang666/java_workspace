//NumberGuessBean.java
package num;

import java.util.Random;

public class NumberGuessBean implements java.io.Serializable {
	private int answer; // 程序产生的随机数，也就是用户要猜的数
	private String hint; // 给用户的提示
	private int numGuesses; // 用户猜的次数
	private boolean success; // 游戏对象的状态

	private Random random = new Random();

	public NumberGuessBean() { // 构造方法，要进行游戏初始化操作
		reset();
	}

	public int getAnswer() { // 属性数据读方法，getter。
		return answer;
	}

	public String getHint() { // 属性数据读方法，getter。
		return "" + hint;
	}

	public int getNumGuesses() { // 属性数据读方法，getter。
		return numGuesses;
	}

	public boolean getSuccess() { // 属性数据读方法，getter。
		return success;
	}

	/* 属性数据写方法，用户在界面中输入数据，调用此方法，进行判断 */
	public void setGuess(String guess) {
		numGuesses++; // 猜的次数加1

		int g;
		try {
			g = Integer.parseInt(guess); // 将字符串转换为整数
		} catch (NumberFormatException e) {
			g = -1; // 用户可能误操作，输入非数值数据
		}

		if (g == answer) { // 猜对了
			success = true;
		} else if (g == -1) { // 输入非法数值数据
			hint = "a number next time";
		} else if (g < answer) { // 猜低了
			hint = "higher"; // 下次请猜高点儿
		} else if (g > answer) { // 猜高了
			hint = "lower"; // 下次请猜低点儿
		}
	}

	public void reset() { // 复位游戏状态
		answer = Math.abs(random.nextInt() % 100) + 1;
		success = false; // 没成功
		numGuesses = 0; // 次数复原为0
	}
}
