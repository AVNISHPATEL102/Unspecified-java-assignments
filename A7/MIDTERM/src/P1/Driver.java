package P1;

public class Driver 
{
public static void main (String []args) {
	Vegetable var1 = new Computer();
	Mineral var2 = new Animal();
	Vegetable var3 = new Mineral();
	Object var4 = new Mineral();
	
	//var1.a();
	//var1.b();
	//var1.c();
	//var2.a();
	//var2.b();
	//var3.a();
	//var3.b();
	//var4.a();
	//((Computer) var1).b();
	//((Computer) var1).c();
	//((Computer) var2).c();
	
	//((Animal) var2).c();
	//((Computer) var3).b();
	//((Vegetable) var4).b();
	((Animal) var4).c();

	
}
}
