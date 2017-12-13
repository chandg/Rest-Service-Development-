package com.rest.demorest;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;


@Path("list")
public class ListFiles {
	
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String address_javaFile(String x) throws Throwable  {
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
	            int i =0;
	            if ( temp.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {
	            	//System.out.println("*************************");
	            	String val = x+"/"+temp;
	            	path[i]= val;
	            	System.out.println("found: " + path[i]);
	            	i++;
	            	//System.out.println("*************************");
	            } 

	        repository.close();  

	    }

	        return("hello");


	    }
	}