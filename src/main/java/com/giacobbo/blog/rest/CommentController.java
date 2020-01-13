package com.giacobbo.blog.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.giacobbo.blog.dto.CommentDto;
import com.giacobbo.blog.service.CommentService;

@Controller
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{postid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CommentDto> getPost(@PathVariable String postid) {
		return commentService.getCommentsForPost(postid);
	}

	@RequestMapping(value = "/{postid}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addComment(@PathVariable String postid, @RequestBody String comment) {
		JSONObject jsonObj = new JSONObject(comment.toString());
		CommentDto teste = new CommentDto(Long.valueOf(postid), jsonObj.get("author").toString(), jsonObj.get("comment").toString());
		commentService.addComment(teste);
	}

}
