package com.rest.demorest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;


@Path("pearl")
public class PearlResource {
	
	 private static final String REMOTE_URL = "https://github.com/chandg/chat_room-server.git";
	@GET 
	@Produces(MediaType.APPLICATION_XML)
	public  Pearl getPearl() throws Throwable, GitAPIException, GitAPIException
	{   System.out.println("getting called....");
		Pearl a1=new Pearl();
		a1.setName("pearl");
		a1.setPoints(1);		
	        File localPath = File.createTempFile("C:/Users/CHANDG/Desktop/demo", "");
	        if(!localPath.delete()) {
	            throw new IOException("Could not delete temporary file " + localPath);
	        }

	        // then clone
	        String t = localPath.getPath() ;
	        System.out.println(t);
	        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
	        try (Git result = Git.cloneRepository()
	                .setURI(REMOTE_URL)
	                .setDirectory(localPath)
	                .call()) {
	        	
		        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
		        System.out.println("Having repository: " + result.getRepository().getDirectory());
		        System.out.println("done");
		        
	        }
	        MyResource b = new MyResource();
	        b.getIt( t);
	        return a1;
	    }
	}
	
	
	
	
	
	

