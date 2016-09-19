class ValHold{  public int i = 10;  }

class ObjectParameter{
	public static void main(String argv[]){
        ObjectParameter o = new ObjectParameter();
        o.amethod();
    }
    public void amethod(){
        int i = 99;
        ValHold v = new ValHold();
        v.i = 30;
        another(v,i);
        System.out.println(v.i);
    }//End of amethod
    public void another(ValHold v, int i){
        i = 0;
        v.i = 20;
        ValHold vh = new ValHold();
        v = vh;
        System.out.print(v.i + " " + i + " ");
    }//End of another
}
