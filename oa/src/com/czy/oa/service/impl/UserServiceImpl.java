package com.czy.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.czy.oa.base.BaseDaoImpl;
import com.czy.oa.domain.User;
import com.czy.oa.service.UserService;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService{

	@Override
	public User getByLoginNamePassword(String loginName, String password) {
		Session session = getSession();
		User user = null;
		try{
			user =  (User) session.createQuery("FROM User u WHERE u.loginName = ? AND u.password = ?")//
					.setParameter(0, loginName)//
					.setParameter(1, DigestUtils.md5Hex(password))//
					.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

}
