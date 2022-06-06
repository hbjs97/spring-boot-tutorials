package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
	
	
	public UserResponse hello() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
			.path("/api/server/hello")
			.queryParam("name", "Jun")
			.queryParam("age", 26)
			.encode()
			.build()
			.toUri();
		
		System.out.println(uri.toString());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
		
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());
		
		return result.getBody();
	}
	
	public UserResponse post() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
			.path("/api/server/user/{userId}/name/{userName}")
			.encode()
			.build()
			.expand(1, "Jun")
			.toUri();
		System.out.println(uri);
		
		UserRequest req = new UserRequest();
		req.setName("Jun");
		req.setAge(26);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		
		return response.getBody();
	}
	
	public UserResponse exchange() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
			.path("/api/server/user/{userId}/name/{userName}")
			.encode()
			.build()
			.expand(1, "Jun")
			.toUri();
		System.out.println(uri);
		
		UserRequest req = new UserRequest();
		req.setName("Jun");
		req.setAge(26);
		
		RequestEntity<UserRequest> requestEntity = RequestEntity
			.post(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.header("a", "b")
			.header("c", "d")
			.body(req);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
		return response.getBody();
	}
	
	public Req<UserResponse> genericExchange() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
			.path("/api/server/user/{userId}/name/{userName}")
			.encode()
			.build()
			.expand(1, "Jun")
			.toUri();
		System.out.println(uri);
		
		UserRequest userRequest = new UserRequest();
		userRequest.setName("Jun");
		userRequest.setAge(26);
		
		Req<UserRequest> req = new Req<>();
		req.setHeader(new Req.Header());
		req.setResponseBody(userRequest);
		
		RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
			.post(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.header("a", "b")
			.header("c", "d")
			.body(req);
		
		RestTemplate restTemplate = new RestTemplate();
//		Req<UserResponse>.class - 제너릭.class 사용 불가, ParameterizedTypeReference 로 대체
		ResponseEntity<Req<UserResponse>>  response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});
		return response.getBody();
	}
}
