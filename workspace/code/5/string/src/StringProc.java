public class StringProc implements EmailValidator {
	public boolean validate(String email) {
		int len = email.length();
		if (len < 3) {
			return false;
		}
		// 首字符不能是@， 末尾字符也不能是@，不能一个@也没有
		// 也不能包含多个@
		return email.charAt(0) != '@' && email.charAt(len - 1) != '@'
				&& email.indexOf('@') != -1
				&& email.indexOf('@') == email.lastIndexOf('@');
	}
}
