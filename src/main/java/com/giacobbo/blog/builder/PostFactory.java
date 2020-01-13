package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Base64;

import com.giacobbo.blog.model.Post;

public class PostFactory implements Serializable {

	  private static final long serialVersionUID = 1L;
	  
	  private PostFactory() {
	  }
	  
	  public static Post create(String title, String content, LocalDateTime date, String image, String code) {
		  return new PostBuilder()
                  .addTitle(title)
                  .addContent(content)
                  .addData(date)
                  .addImage(Base64.getEncoder().encode(image.getBytes()))
                  .addCode(code)
                  .instance();
	  }

	public static Post createPublic(String title, String content, LocalDateTime date) {
		  return new PostBuilder()
                .addTitle(title)
                .addContent(content)
                .addData(date)
                .addPublic(Boolean.TRUE)
                .instance();
	}

}
