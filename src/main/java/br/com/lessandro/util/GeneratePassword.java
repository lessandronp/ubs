package br.com.lessandro.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {

	public static void main(String[] args) {
		String encoded = new BCryptPasswordEncoder().encode("123");
		System.out.println(encoded);
	}

}
