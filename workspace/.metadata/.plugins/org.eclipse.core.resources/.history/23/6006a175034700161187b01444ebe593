package weixiao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.function.BinaryOperator;

public class Test {
	/* 功能统计字符出现的次数 返回映射关系map<Character,Integer> */
	public static Map<Character, Integer> statistics(char[] charArray) {  //统计出现的频率
		Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (char c : charArray) {  
            Character character = new Character(c);  
            if (map.containsKey(character)) {  
                map.put(character, map.get(character) + 1);  
            } else {  
                map.put(character, 1);  
            }  
        }  
  
        return map;  
    }  
	/* 根据统计出的Map 和 修改leafs */
	private static Tree buildTree(Map<Character, Integer> statistics,  
            List<Node> leafs) {  
        Character[] keys = statistics.keySet().toArray(new Character[0]);  
  
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();  //优先级队列 实现Node中大frequency排在尾部
        for (Character character : keys) {                              //依次进队
            Node node = new Node();  
            node.chars = character.toString();  
            node.frequence = statistics.get(character);  
            priorityQueue.add(node);  
            leafs.add(node);  
        }  
  
        int size = priorityQueue.size();  
        for (int i = 1; i <= size - 1; i++) {  
            Node node1 = priorityQueue.poll();  
            Node node2 = priorityQueue.poll();  
  
            Node sumNode = new Node();                          //合节点
            sumNode.chars = node1.chars + node2.chars;  
            sumNode.frequence = node1.frequence + node2.frequence;  
  
            sumNode.leftNode = node1;  
            sumNode.rightNode = node2;  
  
            node1.parent = sumNode;  
            node2.parent = sumNode;  
  
            priorityQueue.add(sumNode);  
        }  
  
        Tree tree = new Tree();  
        tree.root = priorityQueue.poll();  
        return tree;  
    }  
	/* 实现编码 */
	public static String encode(String originalStr,  
            Map<Character, Integer> statistics) {  
        if (originalStr == null || originalStr.equals("")) {  
            return "";  
        }  
  
        char[] charArray = originalStr.toCharArray();
        List<Node> leafNodes = new ArrayList<Node>();  
        buildTree(statistics, leafNodes);  
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);  
  
        StringBuffer buffer = new StringBuffer();
        for (char c : charArray) {  
            Character character = new Character(c);
            buffer.append(encodInfo.get(character));
        }  
  
        return buffer.toString();  
    } 
	/* 返回码字与二进制序列的映射*/
	private static Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {  
        Map<Character, String> codewords = new HashMap<Character, String>(); //码字 对应的二进制序列 
        for (Node leafNode : leafNodes) {  
            Character character = new Character(leafNode.getChars().charAt(0));  
            String codeword = "";  
            Node currentNode = leafNode;  
  
            do {  
                if (currentNode.isLeftChild()) {  //左0右1
                    codeword = "0" + codeword;  
                } else {  
                    codeword = "1" + codeword;  
                }  
  
                currentNode = currentNode.parent;  
            } while (currentNode.parent != null);  
  
            codewords.put(character, codeword);  //把码字和对应的二进制序列加入到map中
        }  
  
        return codewords;  //返回映射列表
    }  
	/* 解码 */
	public static String decode(String binaryStr,  
            Map<Character, Integer> statistics) {  
        if (binaryStr == null || binaryStr.equals("")) {  
            return null;  
        }  
  
        char[] binaryCharArray = binaryStr.toCharArray();  
        LinkedList<Character> binaryList = new LinkedList<Character>();  
        int size = binaryCharArray.length;  
        for (int i = 0; i < size; i++) 
        {  
            binaryList.addLast(new Character(binaryCharArray[i]));  
        }  
  
        List<Node> leafNodes = new ArrayList<Node>();  
        Tree tree = buildTree(statistics, leafNodes);  
  
        StringBuffer buffer = new StringBuffer();  
  
        while (binaryList.size() > 0) {  
            Node node = tree.root;  
  
            do {  
                Character c = binaryList.removeFirst();  
                if (c.charValue() == '0') {  
                    node = node.leftNode;  
                } else {  
                    node = node.rightNode;  
                }  
            } while (!node.isLeaf());  
  
            buffer.append(node.chars);  
        }  
  
        return buffer.toString();  
    }  
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 	BufferedReader br=new BufferedReader(new FileReader("weixiao.txt"));
		 	String oriStr = "";
		 	String sum="";
		 	float length1=0;
		 	float length2=0;
		 	Map<Character, Integer> statistics=null;
		 	BitWriter bw=new BitWriter("huffman.zip");
		 	while((oriStr=br.readLine())!=null)
		 	{
		        sum+=(oriStr+"\n");
		 	}
		 	statistics = statistics(sum.toCharArray()); 
		 	br = new BufferedReader(new FileReader("weixiao.txt"));
		 	while((oriStr=br.readLine())!=null)
		 	{
		 		oriStr+="\n";
		        String encodedBinariStr = encode(oriStr, statistics);  
		        System.out.println(encodedBinariStr);
		        for(int i=0;i<encodedBinariStr.length();i++)
		        {
		        	bw.writeBit(Integer.parseInt(encodedBinariStr.substring(i, i+1)));
		        }
		        length1 += encodedBinariStr.length();
		        length2 += getStringOfByte(oriStr, Charset.forName("UTF-8")).length();
		        
		 	}
		 	bw.close();
		 	
		 	BitReader bitReader = new BitReader("huffman.zip");
		 	String binary="";
		 	while(bitReader.hasNext()){
				if(bitReader.next())
					binary+=1+"";
				else
					binary+=0+"";
		 	}
		 	System.out.println("huffman："+binary);
		 	System.out.println("UTF—8："+getStringOfByte(sum,Charset.forName("UTF-8")));
		 	System.out.println("压缩率为："+((float)(length1/length2))*100+"%");
		 	String decodedStr = decode(binary, statistics);
		 	System.out.println(decodedStr);
		 	String newFileName="decode.txt";
		 	if(args.length>2)
		 	{
		 		newFileName=args[1];
		 	}
		 	FileWriter fw = new FileWriter(newFileName);
		 	fw.write(decodedStr);
		 	fw.flush();
		 	fw.close();

	}
	  
	    public static String getStringOfByte(String str, Charset charset) {  
	        if (str == null || str.equals("")) {  
	            return "";  
	        }  
	  
	        byte[] byteArray = str.getBytes(charset);  
	        int size = byteArray.length;  
	        StringBuffer buffer = new StringBuffer();  
	        for (int i = 0; i < size; i++) {  
	            byte temp = byteArray[i];  
	            buffer.append(getStringOfByte(temp));  
	        }  
	  
	        return buffer.toString();  
	    }  
	  
	    public static String getStringOfByte(byte b) {  
	        StringBuffer buffer = new StringBuffer();  
	        for (int i = 7; i >= 0; i--) {  
	            byte temp = (byte) ((b >> i) & 0x1);  
	            buffer.append(String.valueOf(temp));  
	        }  
	  
	        return buffer.toString();  
	    }  
	}

