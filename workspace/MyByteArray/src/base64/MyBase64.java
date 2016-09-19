package base64;


public class MyBase64 {
	String index;     //转码对应表 
	public MyBase64()
	{
		this.index=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}
	public String ASCBinary(byte[] b) {
		String binary=new String();
		binary="";
		for (int i = 0; i < b.length; i++) {
			int asc=b[i];
			String temp=Integer.toBinaryString(asc);//将一个ascii码转成2进制存入String temp中
			while(temp.length()<8)
				temp="0"+temp;   //少于8位前面补0
		    binary+=temp;    
		}
		return binary;         //得到一个ascii码序列
	}
	public String Base64Binary(String b) {
		String binary=new String("");
		for (int i = 0; i < b.length(); i++) {
			if(b.substring(i,i+1).equals("="))//提取字符串中的每一个元素，如果为”=“ 则执行下次循环
			{
				continue;
			}
			int indexnum = index.indexOf(b.substring(i, i+1));//得到对应的Base64编码方式对应的 Index num
			String temp=Integer.toBinaryString(indexnum);//将Indexnum转换成二进制序列存在temp中
			while(temp.length()<6)                  //长度小于6 前面补零
				temp="0"+temp;                     
			binary+=temp;                           //binary为Base64对应的二进制序列  
		}
		while(binary.length()%8!=0)//8个分为一组
			binary=binary.substring(0,binary.length()-2);//去掉末尾的0 得到ascii码序列
		return binary;
	}
	public String encode(byte[] data) 
	{
		String binary = ASCBinary(data);
		String result =new String("");
		while(binary.length()%6!=0)
			binary+="0";
		while(binary.length()%24!=0)
		{
			binary+="A";
		}
		String A="AAAAAA";
		for(int i=0;i<=binary.length()-6;i+=6)
		{
			String temp=binary.substring(i,i+6);//6位一组编码
			if(temp.equals(A))
			{
				result+="=";
			}
			else {
				int tmp = Integer.parseInt(temp,2); //6位一组的字符串二进制序列转位10进制！
				result+=(index.charAt(tmp))+"";
			}
           		
			
		}
		return result;
	}
	public byte[] decode(String b) {
		String result=new String("");
		String asciiString=Base64Binary(b);
		for (int i = 0; i < asciiString.length(); i+=8) 
		{
			String temp=asciiString.substring(i,i+8);
			int ascii=Integer.parseInt(temp, 2);
//			System.out.println(ascii);
			result+=(char)ascii+"";
		}
		return result.getBytes();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO buto-generated method stub
		byte []b=new String("liujie").getBytes();
		String a=new MyBase64().encode(b);
	    System.out.println(a);
	    byte[] c= new MyBase64().decode(a);
	    for (int i = 0; i < c.length; i++) {
			System.out.print((char)c[i]);
		}
	}

}
