package com.giacobbo.blog.factory;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.giacobbo.blog.model.Post;

public class PostFactory implements Serializable {

	  private static final long serialVersionUID = 1L;
	  
	  private PostFactory() {
	  }
	  
	  public static Post create(String content, String title, LocalDateTime date) {
		  return new PostBuilder()
                  .addContent(content)
                  .addTitle(title)
                  .addData(date)
                  .instance();
	  }

}
