package weixiao;
import java.util.List;
import java.io.*;
import java.util.*;
public class AddNum {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> lines = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream("a.txt"),"UTF-8"));
		String line="";
		int count=0;
		while ((line=br.readLine())!=null) 
		{
			count++;
			lines.add(count+" : "+line);
		}
		br.close();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt"),"UTF-8"));
		for(Iterator<String> it=lines.iterator();it.hasNext();)
		{
			bw.write(it.next().toString()+"\n");
		}
		bw.close();
	}

}
