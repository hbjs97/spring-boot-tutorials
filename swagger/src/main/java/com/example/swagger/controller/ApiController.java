package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Api Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@ApiImplicitParams({@ApiImplicitParam(name = "x", value = "x 값", required = true, dataType = "int"), @ApiImplicitParam(name = "y", value = "y 값", required = true, dataType = "int")})
	@GetMapping("/plus/{x}")
	public int plus(
//		@ApiParam(value = "x")
		@PathVariable int x,
//		@ApiParam(value = "y")
		@RequestParam int y) {
		return x + y;
	}
	
	@ApiResponse(code = 502, message = "사용자 나이 10살 이하일때")
	@ApiOperation(value = "사용자 나이 및 이름 echo")
	@GetMapping("/user")
	public UserRes user(UserReq userReq) {
		return new UserRes(userReq.getName(), userReq.getAge());
	}
}
