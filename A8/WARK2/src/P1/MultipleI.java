package P1;
class MultiBook {
    public MultiBook (){
	   System.out.println("I am a book");
	}
}

// child class - the Dictionary "is-a" book, it just adds or refines its
// functionality

class MultiDictionary {

   public MultiDictionary (){
	  System.out.println("I am a dictionary");
   }
}

//if you use the following line to define the class, you will
// get a compiler error
//public class MultipleI extends MultiBook, MultiDictionary {
public class MultipleI extends MultiBook {
	public static void main(String[] args) {
        MultipleI myObject = new MultipleI();
	}
}