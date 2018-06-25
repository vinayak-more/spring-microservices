package com.vinayak.restfulservice.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

	@GetMapping
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

}
