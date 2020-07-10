package com.seoulmate.seoulgo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	MemberDTO mdto;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String memberID = (String)authentication.getPrincipal();
		String memberPW = (String)authentication.getCredentials();

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if(mdto.getmLevel() == null) {
			roles.add(new SimpleGrantedAuthority(""));
		}else if(mdto.getmLevel() != null) {
			if(mdto.getmLevel().equals("ROLE_MEMBER") == true) {
				roles.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
			}else {
				roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
		}
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(memberID,memberPW,roles);
		result.setDetails(mdto);
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}