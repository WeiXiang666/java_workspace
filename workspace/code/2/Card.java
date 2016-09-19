public class Card {
	int rank;
	public static void swap1(Card c1, Card c2) {	//这个方法一点意义也没有
		Card temp = c1;								//在此方法的参数变量，也是
		c1 = c2;									//局部变量，赋值，然后程序
		c2 = temp;									//返回，局部变量都被释放掉
	}
	public static void swap2(Card c1, Card c2) {
		int temp = c1.rank;							//交换的是对象的值，
		c1.rank = c2.rank;							//真正的交换.
		c2.rank = temp;
	}
	public static void main(String[] args) {
		Card c1 = new Card();
		c1.rank = 5;
		Card c2 = new Card();
		c2.rank = 6;
		swap1(c1,c2);
		System.out.println("c1 rank = " + c1.rank + "\t\tc2 rank = " + c2.rank);
		swap2(c1,c2);
		System.out.println("c1 rank = " + c1.rank + "\t\tc2 rank = " + c2.rank);
	}
}
