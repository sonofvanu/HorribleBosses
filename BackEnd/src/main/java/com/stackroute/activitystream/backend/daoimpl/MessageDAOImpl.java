package com.stackroute.activitystream.backend.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.backend.dao.MessageDAO;
import com.stackroute.activitystream.backend.model.Message;

@Repository(value = "messageDAO")
@Transactional
@EnableTransactionManagement
public class MessageDAOImpl implements MessageDAO {
	
	public static final Logger logger=LoggerFactory.getLogger(MessageDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean sendMessage(Message singleUserMessage) {
		// TODO Auto-generated method stub
		try
		{
			sessionFactory.getCurrentSession().save(singleUserMessage);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}


	@Override
	public boolean removeMessageToAUser(int messageId) {
		// TODO Auto-generated method stub
		try
		{
			Message singleUserMessage=sessionFactory.getCurrentSession().get(Message.class,messageId);
			sessionFactory.getCurrentSession().delete(singleUserMessage);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	

	@Override
	public boolean removeMessageFromACircle(int messageId) {
		// TODO Auto-generated method stub
		try
		{
			Message circleMessage=sessionFactory.getCurrentSession().get(Message.class,messageId);
			sessionFactory.getCurrentSession().delete(circleMessage);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Message> allMessageToAUser(String senderId, String receiverId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Message.class);
			criteria.add(Restrictions.eq("senderId", senderId));
			criteria.add(Restrictions.eq("receiverId", receiverId));
			criteria.addOrder(Order.asc("messageId"));
			List<Message> allMessageToUser=criteria.list();
			return allMessageToUser;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	@Override
	public List<Message> messgaeBetweentwoUsers(String senderId, String userId) {
		// TODO Auto-generated method stub
		try {
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Message.class);
			Criterion condition1=Restrictions.and(Restrictions.eq("senderId", senderId),Restrictions.eq("receiverId", userId));
			Criterion condition2=Restrictions.and(Restrictions.eq("senderId", userId),Restrictions.eq("receiverId", senderId));
			criteria.addOrder(Order.asc("messageId"));
			return  criteria.add(Restrictions.or(condition1,condition2)).list();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Message> allMessagesSentByAUser(String senderId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Message.class);
			criteria.add(Restrictions.eq("senderId", senderId));
			List<Message> allMessagesSentByUser=criteria.list();
			return allMessagesSentByUser;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Message> allMessageToACircle(String circleName, String senderId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Message.class);
			criteria.add(Restrictions.eq("circleName", circleName));
			criteria.add(Restrictions.eq("senderId", senderId));
			List<Message> allMessageToACircle=criteria.list();
			return allMessageToACircle;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Message> allMessageOfACircle(int circleId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Message.class);
			criteria.add(Restrictions.eq("circleId", circleId));
			List<Message> allMessageOfCircle=criteria.list();
			return allMessageOfCircle;
		}
		catch(Exception e)
		{
			return null;
		}
	}




}
