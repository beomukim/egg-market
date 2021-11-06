package com.untitled.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untitled.comment.bo.CommentBO;
import com.untitled.comment.model.Comment;
import com.untitled.post.model.Article;
import com.untitled.post.model.Post;
import com.untitled.user.bo.UserBO;
import com.untitled.user.model.User;

@Service
public class ArticleBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public List<Article> getArticleList(String search, String sort) {
		List<Article> articleList = new ArrayList<>();
		
		List<Post> postList = postBO.getPostList(search, sort);
		for (Post post : postList) {
			Article article = new Article();
			article.setPost(post);
			
			User user = userBO.getUserByUserId(post.getUserId());
			article.setUser(user);
			
			articleList.add(article);
		}
		
		return articleList;
	}
	
	public Article getArticle(int postId) {
		Article article = new Article();
		Post post = postBO.getPost(postId);
		article.setPost(post);
		
		User user = userBO.getUserByUserId(post.getUserId());
		article.setUser(user);
		
		List<Comment> commentList = commentBO.getCommentList(post.getId());
		article.setCommentList(commentList);
		
		return article;
	}
}
