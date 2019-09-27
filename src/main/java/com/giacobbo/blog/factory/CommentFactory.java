package com.giacobbo.blog.factory;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.model.Comment;
import com.giacobbo.blog.model.Post;

public class CommentFactory implements Serializable {

	  private static final long serialVersionUID = 1L;
	  
	  private CommentFactory() {
		  
	  }
	  
	  public static Comment create(Post post, String content, String author, LocalDateTime local) {
		  return new CommentBuilder()
				  .addPost(post)
                  .addContent(content)
                  .addAuthor(author)
                  .addData(local)
                  .instance();
	  }

}
