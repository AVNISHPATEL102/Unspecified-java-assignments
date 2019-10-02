package second;
import first.Two;
public class Three extends Two {
public Three() { super();
System.out.println("Three() called");}
public void mthd2() { super.mthd2();
System.out.println("Three's mthd2() called"); }
}//----------------------- end of Class Three ------------------------
