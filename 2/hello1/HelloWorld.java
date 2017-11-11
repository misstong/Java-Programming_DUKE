import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		//FileResource res = new FileResource("hello_unicode.txt");
		URLResource webpage=new URLResource("http://www.dukelearntoprogram.com/java/somefile.txt");
		for (String line : webpage.words()) {
			System.out.println(line);
		}
	}
}
