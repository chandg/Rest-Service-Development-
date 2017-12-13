
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class FileClient {
	
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{	Socket s = new Socket("127.0.0.1",9999);
	
		//DataInputStream din = new DataInputStream(s.getInputStream());
		//DataOutputStream dout = new DataOutputStream(s.getOutputStream());

		String Str="C:/Users/CHANDG/Desktop/chat_room_server.java";
		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		PrintWriter Out = new PrintWriter(os);
		
		BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
		String msgin="",msgout="";
		while(!msgin.equals("end"))
		{	
			Out.println(Str);
			os.flush();
			String complexity = br.readLine();
			System.out.println("data from server   : "+ complexity);
		}
	}
}