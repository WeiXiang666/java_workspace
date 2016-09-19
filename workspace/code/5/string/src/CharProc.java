public class CharProc implements EmailValidator { // 第一种实现
	public boolean validate(String email) {
		boolean found = false; // 是否发现有@字符的标志
		int len = email.length();
		if (len < 3) { // 至少是a@b
			return false;
		}
		for (int i = 0; i < len; i++) {
			if (email.charAt(i) == '@') {
				if (found) { // 如果已经发现有@字符，且再次发现@字符
					return false;
				} else {
					if (i == 0) { // 串首字符是@
						return false;
					}
					if (i == len - 1) { // 串尾字符是@
						return false;
					}
					found = true;
				}
			}

		}
		return found; // 一个@字符也没有发现，
		// 或者发现唯一一个@，并且不是串首，也不是串尾
	}
}
