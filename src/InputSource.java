import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;


// A class that generates lines from the input.

public class InputSource {

    private BufferedReader in;

    public InputSource ( ) {
		try {
			in = new BufferedReader (new InputStreamReader (System.in));
		} catch (Exception e) {
			System.err.println ("Couldn't access keyboard!");
			System.exit (1);
		}
    }
    
    public InputSource (String fileName) {
		try {
			in = new BufferedReader (new InputStreamReader (new FileInputStream (fileName)));
		} catch (Exception e) {
			System.err.println ("Couldn't access file!");
			System.exit (1);
		}
    }
	
    public String readLine ( ) {
		String line = "";
		try {
			line = in.readLine ( );
		} catch (IOException e) {
			System.err.println ("input error");
			System.exit (1);
		}
		if (line == null) {
			return null;
		}
		// Added call to trim: March 18, 2011.
		return line.toLowerCase ( ).trim ( );
    }
    
   
}