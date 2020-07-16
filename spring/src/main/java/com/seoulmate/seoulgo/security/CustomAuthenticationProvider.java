package com.seoulmate.seoulgo.security;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seoulmate.seoulgo.dto.MemberDTO;

// 로그인창에 입력한 로그인 정보와 DB에서 가져온 멤버의 정보를 비교해주는 인터페이스
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService uDetail;
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String memberID = (String)authentication.getPrincipal();
		String memberPW = (String)authentication.getCredentials();
		
		MemberDTO mdto = (MemberDTO)uDetail.loadUserByUsername(memberID);
		
		if(!pwEncoder.matches(memberPW, mdto.getPassword())) {
			throw new BadCredentialsException(memberID);
		}
		
		if(!mdto.isEnabled()) {
			throw new BadCredentialsException(memberID);
		}
		
		return new UsernamePasswordAuthenticationToken(memberID, memberPW, mdto.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}