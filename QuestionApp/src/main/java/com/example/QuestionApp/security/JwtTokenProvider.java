package com.example.QuestionApp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${questapp.secret}")
	private String APP_SECRET;
	
	@Value("${questapp.expiresin}")
	private Long EXPIRESIN;
	
	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails)auth.getPrincipal();
		Date expirationDate = new Date(new Date().getTime() + EXPIRESIN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();		
	}
	
	public Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		}
		catch(SignatureException e) {
			return false;
		}
		catch(MalformedJwtException e) {
			return false;
		}
		catch(ExpiredJwtException e) {
			return false;
		}
		catch(UnsupportedJwtException e) {
			return false;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
	}
	
	public boolean isTokenExpired(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		Date expirationDate = claims.getExpiration();
		return expirationDate.before(new Date());
	}
}
