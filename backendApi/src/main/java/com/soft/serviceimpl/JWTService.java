package com.soft.serviceimpl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.soft.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private String secreatKey;

	public String generatToken(User user) {
		
		Map<String, Object> claims= new HashMap<String, Object>();
		claims.put("Role", "USER");
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(user.getUserEmail())
				.setIssuer("DCB")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 10 *100))
				.signWith(generateKey(),SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public SecretKey generateKey() {
//		byte[] decode=Decoders.BASE64.decode(getSecretKey());
//		System.out.println(Arrays.toString(decode));
//		return Keys.hmacShaKeyFor(decode);
		return Keys.hmacShaKeyFor(getSecretKey().getBytes(StandardCharsets.UTF_8));

	}
	
	public String getSecretKey() {
		return secreatKey= "vT9Lxk8w3N98F2hT0l2J9Mzs7xBpYQwH1YwQkX6a8uE=";
	}
	
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	private <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
		
		Claims claims=extractClaims(token);
		
		return claimResolver.apply(claims);
	}

	private Claims extractClaims(String token) {
		return Jwts
			.parser()
			.verifyWith(generateKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
		
	}

	public boolean tokenValid(String token , UserDetails userdetails) {
		String username = extractUserName(token);
		
		return (username.equals(userdetails.getUsername()) && !tokenExpired(token));
	}

	private boolean tokenExpired(String token) {
		
		return extractExpriration(token).before(new Date());
	}

	private Date extractExpriration(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getExpiration);
	}

}
