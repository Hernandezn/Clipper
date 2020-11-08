package com.clipper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clipper.model.PostImage;

@Repository
public class PostImageDao implements Dao<PostImage, Integer> {

	private SessionFactory sessionFactory;

	public PostImageDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<PostImage> findAll() {
		List<PostImage> list = sessionFactory.openSession()
				.createNativeQuery("select * from post_images", PostImage.class).list();
		return list;
	}

	@Override
	public PostImage findById(Integer i) {
		Session sess = factory.openSession();
		PostImage result = sess.createQuery("from PostImage where id = " + i, PostImage.class).list().get(0);
		sess.close();
		return result;
	}

	@Override
	public PostImage update(PostImage t) {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.merge(t);
		tx.commit();
		return t;
	}

	@Override
	public PostImage save(PostImage t) {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(t);
		tx.commit();
		return t;
	}

	@Override
	public PostImage delete(Integer i) {
		PostImage pi = findById(i);
		
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(pi);
		tx.commit();
		return pi;
	}
}
