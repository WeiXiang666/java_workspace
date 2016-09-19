package weixiao;

public class MyByteArray {
	private byte[] data;

	public MyByteArray(byte[] data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}

	public int indexOf(byte b) {
		for (int i = 0; i <data.length; i++) {
			if(data[i]==b)
				return i;
		}
		return -1;
	}

	public MyByteArray concat(MyByteArray another) 
	{
		int length1=this.data.length;
		int length2=another.data.length;
		byte[] newdata=new byte[length1+length2];
		for (int i = 0; i < length1; i++) {
			newdata[i]=this.data[i];
		}
		for (int i = length1; i < length1+length2; i++) {
			newdata[i]=another.data[i-length1];
		}
		return new MyByteArray(newdata);
	}

	// 返回部分字节数组
	public MyByteArray subByteArray(int beginIndex, int endIndex) {
		if (beginIndex<0||endIndex>this.data.length||beginIndex>endIndex) {
			throw new IndexOutOfBoundsException();
		}
		byte[] subbyte= new byte[endIndex-beginIndex];
		for (int i = beginIndex; i < endIndex; i++) {
			subbyte[i-beginIndex]=this.data[i];
		}
		return new MyByteArray(subbyte);
	}

	// 返回部分字节数组
	public MyByteArray subByteArray(int beginIndex) {
		return subByteArray(beginIndex, this.data.length);
	}

	// 返回封装的字节数组
	public byte[] getByteArray() {
		return this.data;
	}

	// 读取字节数组中某个位置的值
	public byte byteAt(int index) {
		return this.data[index];
	}

	// 修改某个位置的字节数据
	public void setByteAt(int index, byte b) {
		this.data[index]=b;
	}

	// 返回字节数组中的数据，用字符串形式输出
	public String toString() {
		String myString = new String();
		myString="";
		for (int i = 0; i < this.data.length; i++) {
			if (i==0) {
				myString+=(data[i]+"");
			}
			else {
				myString+=(","+data[i]);
			}
		}
		return myString;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] data=new byte[5];
		for (int i = 0; i < data.length; i++) {
			data[i]=(byte)(50-i);
		}
		MyByteArray mybyte = new MyByteArray(data);
		MyByteArray subbyte = mybyte.subByteArray(2);
		MyByteArray connect = mybyte.concat(subbyte);
		System.out.println(mybyte);
		System.out.println(subbyte);
		System.out.println(connect);
		byte b = 48;
		System.out.println(mybyte.indexOf(b));
		
	}

}
