package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	OAuth2RestTemplate restTemplate;


	@GetMapping("/product")
	public String getProduct() {
		return "Product";
	}

	@GetMapping("/contact")
	public String getContact() {
		return "Contact";
//		return restTemplate.getForObject("http://localhost:8091/contact", String.class);
	}


	@GetMapping("/salary")
	public String getSalary() {
		return "Salary";
//		return restTemplate.getForObject("http://localhost:8092/salary", String.class); 
	}

	@GetMapping("/userid")
	public String getUserId() {
		return restTemplate.getForObject("http://localhost:8088/user/id", String.class);
	}
}

