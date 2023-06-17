# EAproject_test


Steps to use the oauth2 in your project:

1. Add the following to your resources/application.yml
```
security:
  oauth2:
    client:
      accessTokenUri: http://localhost:8088/oauth/token
      userAuthorizationUri: http://localhost:8088/oauth/authorize
      clientId: client
      clientSecret: secret
    resource:
      userInfoUri: http://localhost:8088/user
```

2. Create ResourceServerConfig class:
```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/product").hasAnyRole("MANAGER", "EMPLOYEE", "CUSTOMER")
//                .antMatchers("/contact").hasAnyRole("MANAGER", "EMPLOYEE")
//                .antMatchers("/salary").hasRole("MANAGER")
                .anyRequest()
                .authenticated();
    }
}

```
If you use the antMatchers rows please be sure you use the same roles as in the authentication project.

3. Add the following bean creator to your main class:
```java
	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext,
			OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oAuth2ClientContext);
	} 
```



APIs:
- GET /user -> give back the users detail
- POST /user -> create new user
- GET /user/id -> give back user primary key (currently username)
- DELETE /user/{id} -> delete the user by user primary key (currently username)
