// ----------------------------------------------------------
// Assignment# (1)
// Written by: Avnish Patel  ,  Id : 40024628
// One student Section U
// Comp 249 Ass3
// Due Date MArch 19th,2018
// ----------------------------------------------------------
package PC1;
public class FileInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public FileInvalidException() {
		message = "Recording problematic input and ending processing for this file";
	}
	
	public FileInvalidException( String message ) {
		this.message = message;
	}
	
	public String getMessage () {
		return message;
	}
	
	

}