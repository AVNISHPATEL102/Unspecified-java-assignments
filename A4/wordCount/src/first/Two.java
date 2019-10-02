package first;
public class Two extends One {
public Two() { System.out.println("Two() called"); }
public void mthd1() { System.out.println("Two's mthd1() called"); }
protected void mthd2() { super.mthd2();
System.out.println("Two's mthd2() called"); }
} //----------------------- end of Class Two ------------------------ 
