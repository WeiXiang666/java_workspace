package weixiao;

public class Node implements Comparable<Node>{
        String chars = "";  
        int frequence = 0;  
        Node parent;  
        Node leftNode;  
        Node rightNode;
    
  
        public int compareTo(Node n) //实现compare接口，有利于将来对频率比较 排序！
        {  
            return frequence - n.frequence;  
        }  
  
        public boolean isLeaf() {  
            return chars.length() == 1;  
        }  
  
        public boolean isRoot() {  
            return parent == null;  
        }  
  
        public boolean isLeftChild() {  
            return parent != null && this == parent.leftNode;  
        }  
  
        public int getFrequence() {  
            return frequence;  
        }  
  
        public void setFrequence(int frequence) {  
            this.frequence = frequence;  
        }  
  
        public String getChars() {  
            return chars;  
        }  
  
        public void setChars(String chars) {  
            this.chars = chars;  
        }  
  
        public Node getParent() {  
            return parent;  
        }  
  
        public void setParent(Node parent) {  
            this.parent = parent;  
        }  
  
        public Node getLeftNode() {  
            return leftNode;  
        }  
  
        public void setLeftNode(Node leftNode) {  
            this.leftNode = leftNode;  
        }  
  
        public Node getRightNode() {  
            return rightNode;  
        }  
  
        public void setRightNode(Node rightNode) {  
            this.rightNode = rightNode;  
        }  
      

}


