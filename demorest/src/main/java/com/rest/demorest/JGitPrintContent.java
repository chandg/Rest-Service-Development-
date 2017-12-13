package com.rest.demorest;

import java.io.ByteArrayOutputStream;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;



@Path("content")
public class JGitPrintContent
{	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String getIt() throws Throwable  {
  {
    File gitWorkDir = new File("https://github.com/chandg/chat_room-server.git");
    Git git = Git.open(gitWorkDir);
    Repository repo = git.getRepository();
 
    ObjectId lastCommitId = repo.resolve(Constants.HEAD);
 
    RevWalk revWalk = new RevWalk(repo);
    RevCommit commit = revWalk.parseCommit(lastCommitId);
 
    RevTree tree = commit.getTree();
 
    TreeWalk treeWalk = new TreeWalk(repo);
    treeWalk.addTree(tree);
    treeWalk.setRecursive(true);
    treeWalk.setFilter(PathFilter.create("chat_room_server.java"));
    if (!treeWalk.next()) 
    {
      System.out.println("Nothing found!");
      return ("None");
    }
 
    ObjectId objectId = treeWalk.getObjectId(0);
    ObjectLoader loader = repo.open(objectId);
 
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    loader.copyTo(out);
    System.out.println("file1.txt:\n" + out.toString());
    }
  return("found");
} 
}