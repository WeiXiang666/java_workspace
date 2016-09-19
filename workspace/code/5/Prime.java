
import java.util.*;

public class Prime {

    public boolean isPrime(int x) {
        int j = (int) Math.sqrt(x);
        if(x==2) { return true; }
        if(x==3) { return true; }
        
        for(int i=2;i<=j;i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }
    public LinkedList<Integer> getPrime2(int x) {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for (int j = 2; j <= x; j++) {
            if( isPrime(j) ) {
                ll.add(j);
            }
        }
        return ll;
        
    }
    
    
    public LinkedList<Integer> getPrime(int x) {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for (int j = 2; j <= x; j++) {
            ll.add(new Integer(j));
        }
        
        int i = 0;
        while(i<ll.size()-1) {
            int a = ll.get(i);
            int j=i+1;
            while(j<ll.size()) {
                if(ll.get(j) % a == 0) {
                    ll.remove(j);
                    j--;
                }
                j++;
            }
            i++;
        }
        
        
        return ll;
    }
    
    public static void main(String[] args) {
        Prime p = new Prime();
        long start = System.currentTimeMillis();
        LinkedList<Integer> al = p.getPrime(15000);
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end-start));
        System.out.println(al.size());
        start = System.currentTimeMillis();
        LinkedList<Integer> al2 = p.getPrime2(15000);
        end = System.currentTimeMillis();
         System.out.println("cost: " + (end-start));
        System.out.println(al2.size());
    }
}
