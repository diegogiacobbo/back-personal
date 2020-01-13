package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Base64;

import com.giacobbo.blog.dto.PostDtoResponse;

public class PostDtoResponseFactory implements Serializable {

	  private static final long serialVersionUID = 1L;
	  
	  private PostDtoResponseFactory() {
	  }
	  
	  public static PostDtoResponse create(Long id, String title, String content, LocalDateTime date, byte[] image, String code, Boolean isPublic) {
		  return new PostDtoResponseBuilder()
				  .addId(id)
				  .addTitle(title)
                  .addContent(content)
                  .addData(date)
                  .addImage(image == null ? "" : Base64.getEncoder().encodeToString(image))
                  .addCode(code)
                  .addPublic(isPublic)
                  .instance();
	  }
}
