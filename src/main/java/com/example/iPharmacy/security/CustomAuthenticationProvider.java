package com.example.iPharmacy.security;

import java.util.HashSet;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.iPharmacy.database.CustomUserInfoRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserInfoRepository customUserRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String inputUsername = (String)authentication.getPrincipal();
		String inputPassword = (String)authentication.getCredentials();

		UserInfo storedUser = customUserRepo.findPasswordAndSaltAndIdByUsername(inputUsername);

		if (storedUser == null) {
			System.out.println("Username '" + inputUsername + "' does not exist.");
			throw new BadCredentialsException("Username does not exist.");
		} 
		else {
			String storedSalt = storedUser.getSalt();
			String storedPassword = storedUser.getPassword();
			String id = storedUser.getId();

			//hash entered password with same salt and compare with stored password
			String hashedInputPassword = Hex
					.encodeHexString(UserInfo.hashPassword(inputPassword.toCharArray(), storedSalt.getBytes()));

			if(!hashedInputPassword.equals(storedPassword)) {
				System.out.println("Login failed for user '" + inputUsername + "'.");
				throw new BadCredentialsException("Password is incorrect!");
			} 
			else {
				UserInfo validUserIdAndUsername = new UserInfo(id, inputUsername);
				System.out.println("Login Succeeded for user '" + inputUsername + "'.");
				return new UsernamePasswordAuthenticationToken(validUserIdAndUsername, "", new HashSet<GrantedAuthority>());
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
