package com.example.iPharmacy.security;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.iPharmacy.database.UserInfoRepository;

@Configurable
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userRepo;
	
	//@Autowired
	//private BCryptPasswordEncoder bCrypt;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//String passwordAndSaltInfo = userRepo.findPasswordAndSaltByUsername(username);
		UserInfo user = userRepo.findByUsername(username);
		//System.out.println("trying to authenticate...");
		//System.out.println(passwordAndSaltInfo);
		if(user != null)
		{
			System.out.println("success?");
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		}
		else
			throw new UsernameNotFoundException("Username not found");
	}
	
	public void saveUser(UserInfo user) {
		System.out.println("saveUser()");
		userRepo.insert(user);
	}

}
