package com.seoulmate.seoulgo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.seoulmate.seoulgo.dao.UserDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;

// DB에서 유저 정보를 직접 가져오는 인터페이스
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO uDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO mdto = uDAO.getUserById(username);
		if(mdto==null) {
			throw new UsernameNotFoundException(username);
		}
		return mdto;
	}

}