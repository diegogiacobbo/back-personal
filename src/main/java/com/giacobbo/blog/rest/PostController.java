package com.giacobbo.blog.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giacobbo.blog.dto.PostDto;
import com.giacobbo.blog.factory.PostFactory;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.service.PostServiceImpl;

@Controller
@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostServiceImpl postService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	List<Post> all() {
		return postService.findAll();
	}
	
	@GetMapping("/ispublic/")
	@ResponseStatus(HttpStatus.OK)
	List<PostDto> ispublic() {
		return postService.findPublicPosts();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable String id) {
		return postService.getPost(id);
	}

	@RequestMapping(value = "/{post}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addPost(@RequestBody String post) {
		JSONObject jsonObj = new JSONObject(post.toString());
		return postService.postAdd(PostFactory.create(jsonObj.get("title").toString(),
				jsonObj.get("content").toString(), LocalDateTime.now()));
	}

}
