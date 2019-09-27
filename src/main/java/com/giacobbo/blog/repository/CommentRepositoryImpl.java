package com.giacobbo.blog.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import com.giacobbo.blog.model.Comment;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	private final EntityManager em;

	@Autowired
	public CommentRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Comment.class);
	}

	@Override
	@Transactional
	public <S extends Comment> S save(S entity) {
		this.em.persist(entity);
		this.em.flush();
		return entity;
	}

	@Override
	public <S extends Comment> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Comment> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Comment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Comment> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Comment entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Comment> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> findByPost(String postId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.where(builder.equal(root.get("post"), Long.valueOf(postId)));

		return em.createQuery(criteria).getResultList();
	}

}
