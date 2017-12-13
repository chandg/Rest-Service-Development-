package com.rest.demorest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	
	 /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
	 * @return 
     *
     * @return String that will be returned as a text/plain response.
	 * @throws IOException 
     * @throws Throwable 
     */
    //@GET
    //@Produces(MediaType.TEXT_PLAIN)
    public  void getIt(String x) throws IOException 
    	{int i =0;
		    x = x.replace(File.separator,"/");
		    String[] path = new String[100];// stores path of the no files.
	        FileRepositoryBuilder builder = new FileRepositoryBuilder();
	        Repository repository = builder
	                .setGitDir(new File(x+"/.git")).readEnvironment()
	                .findGitDir().build();

	        //listRepositoryContents(repository);
	        Ref head = repository.getRef("HEAD");

	        // a RevWalk allows to walk over commits based on some filtering that is defined
	        RevWalk walk = new RevWalk(repository);
	        
	        RevCommit commit = walk.parseCommit(head.getObjectId());
	        RevTree tree = commit.getTree();
	        System.out.println("Having tree: " + tree);

	        // now use a TreeWalk to iterate over all files in the Tree recursively
	        // you can set Filters to narrow down the results if needed
	        TreeWalk treeWalk = new TreeWalk(repository);
	        treeWalk.addTree(tree);
	        treeWalk.setRecursive(true);
	        while (treeWalk.next()) {
	        	String temp =treeWalk.getPathString();
	        	//System.out.println("found: " + temp);
	            String search  = "java";
	            
	            if ( temp.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {
	            	String val = x+"/"+temp;
	            	path[i]= val;
	            	System.out.println("found: " + path[i]);
	            	i++;
	            } 
	        repository.close();  

	    }	
	        Socket s = new Socket("127.0.0.1",9998);
	        Socket s2 = new Socket("127.0.0.1",9999);
			OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
			PrintWriter Out = new PrintWriter(os);
			OutputStreamWriter os2 = new OutputStreamWriter(s2.getOutputStream());
			PrintWriter Out2 = new PrintWriter(os2);
			int k=0;
			BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedReader br2= new BufferedReader(new InputStreamReader(s2.getInputStream()));
			
			String msgin="";//,msgout="";
			while(!msgin.equals("end"))
			{	String Str =path[k];
				String Str2=path[k+1];
				Out.println(Str);
				os.flush();
				Out2.println(Str2);
				os2.flush();
				String complexity = br.readLine();
				if (path[k]!=null)
				{
				System.out.println("Data from node1  : "+"/n"+" File with location : " + path[k]+"/n"+" complexity   : "+ complexity);
				
				}
				String complexity2 = br2.readLine();
				if (path[k+1]!=null)
				{
					System.out.println("Data from node2  : "+"/n"+" File with location : " + path[k+1]+"/n"+" complexity   : "+ complexity2);
				}
				k=k+2;
				if (path[k]==null)
				break;
			}

    }
 
        
    }
    



