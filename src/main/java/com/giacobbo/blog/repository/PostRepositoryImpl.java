package com.giacobbo.blog.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import com.giacobbo.blog.model.Post;

@Repository
public class PostRepositoryImpl implements PostRepository {

	private final EntityManager em;

	@Autowired
	public PostRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Post.class);
	}

	@Override
	@Transactional
	public <S extends Post> S save(S entity) {
		this.em.persist(entity);
		this.em.flush();
		return entity;
	}

	@Override
	public <S extends Post> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Post> findById(String id) {
		Post post = em.find(Post.class, Long.valueOf(id));
		if (post == null) {
			throw new EntityNotFoundException("Can't find post for ID " + id);
		}
		return Optional.of(post);
	}
	

	@Override
	public Post findTopByReverseOrderByCreationDate(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> rootEntry = cq.from(Post.class);
		cq.where(cb.equal(rootEntry.get("isPublic"), Boolean.TRUE));
		cq.orderBy(cb.desc(rootEntry.get("creationDate")));
		
		CriteriaQuery<Post> publicPosts = cq.select(rootEntry);

		TypedQuery<Post> publicPostsQuery = em.createQuery(publicPosts );
		return publicPostsQuery.getResultList().get(0);
	}
	
	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Post> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> rootEntry = cq.from(Post.class);
		CriteriaQuery<Post> all = cq.select(rootEntry);

		TypedQuery<Post> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
	
	@Override
	public Iterable<Post> findPublicPosts() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> rootEntry = cq.from(Post.class);
		cq.where(cb.equal(rootEntry.get("isPublic"), Boolean.TRUE));
		cq.orderBy(cb.asc(rootEntry.get("creationDate")));
		
		CriteriaQuery<Post> publicPosts = cq.select(rootEntry);

		TypedQuery<Post> publicPostsQuery = em.createQuery(publicPosts );
		return publicPostsQuery.getResultList();
	}
	@Override
	public Iterable<Post> findAllById(Iterable<String> ids) {
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
	public void delete(Post entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll(Iterable<? extends Post> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

}
