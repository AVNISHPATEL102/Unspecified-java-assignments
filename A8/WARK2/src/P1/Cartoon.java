package P1;

public class Cartoon extends Drawing {
    public Cartoon() {
		System.out.println("Cartoon constructor");
	}
	 
	// We create a cartoon object, thereby automatically invoking
	// a chain of parent constructors
	public static void main(String[] args) {
        Cartoon x = new Cartoon();
	}
	
}