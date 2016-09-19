import java.util.regex.*; //该包中提供正则表达式的处理类

public class RegExProc implements EmailValidator {
	public boolean validate(String email) { // java.util.regex.Pattern提供模式判断
		return Pattern.matches("\\w+@\\w+", email); // 使用正则表达式来判断
		// 等同于String类的match(regex)方法
		// return email.matches("\\w+@\\w+");
	}
}
