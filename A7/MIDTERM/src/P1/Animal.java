package P1;

public class Animal extends Mineral
{

	public void a() {
		System.out.println("Animal a");
	}
	public void c() {
		b();
		System.out.println("Animal c");
	}
}
