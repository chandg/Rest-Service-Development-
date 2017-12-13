
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class FileServer_Node2 {
	
	
	
	public static void main(String args[]) throws IOException
	{
		ServerSocket ss = new ServerSocket(9998);
		Socket s = ss.accept();
		BufferedReader br0 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		
		PrintWriter Out = new PrintWriter(os);
		
		String msgin="",msgout="";
		
		while(!msgin.equals(null))
		{
			String path1 = br0.readLine();
			System.out.println("client data : "+ path1);
	        int complexity = 1;
	        String fileName;
	        String[] keywords = {"if", "else", "while", "case", "for", "switch", "do", "continue", "break", "&&",
	            "||", "?", ":", "catch", "finally", "throw", "throws", "default", "return", "foreach", "elseif", "or", "and", "xor"};
	        String words = "";
	        String line = null;

	        try {
	            fileName = path1;
	            FileReader fr = new FileReader(fileName);
	            BufferedReader br = new BufferedReader(fr);
	            line = br.readLine();
	            while (line != null) {
	                StringTokenizer stTokenizer = new StringTokenizer(line);
	                while (stTokenizer.hasMoreTokens()) {
	                    words = stTokenizer.nextToken();
	                    for (int i = 0; i < keywords.length; i++) {
	                        if (keywords[i].equals("\\")) {
	                            break;
	                        } else {
	                            if (keywords[i].equals(words)) {
	                                complexity++;
	                            }
	                        }
	                    }
	                }
	                line = br.readLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

			Out.println(complexity);
			os.flush();	
			}
		s.close();
	}

}
