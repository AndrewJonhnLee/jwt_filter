package com.cloud.service.config.oauthConfig;

import com.cloud.service.jwt.CustomAuthenticationProvider;
import com.cloud.service.jwt.JWTAuthenticationFilter;
import com.cloud.service.jwt.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity 开启Spring Security 全局方法安全
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user_1").password("123456").authorities("USER")
//                .and()
//                .withUser("user_2").password("123456").authorities("USER");
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
//        return manager;
//    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers().anyRequest()//面向所有的request
//                .and()
//                .authorizeRequests()
//                .anyRequest().permitAll().and()
//                .csrf()
//                .disable()                      // 禁用 Spring Security 自带的跨域处理
//                .sessionManagement()                        // 定制我们自己的 session 策略
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session
        // 关闭csrf验证

//        DefaultAuthenticationEventPublisher
//        HttpServletRequest
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers( "/login").permitAll()
                // 添加权限检测
                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检测
                .antMatchers("/world").hasRole("ADMIN")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
//                .and().formLogin()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/404.html","/500.html","/401.html","/fonts/**","/img/**","/dist/**","/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }

    /**
     *  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     *if (principal instanceof UserDetails) {
     *String username = ((UserDetails)principal).getUsername();
     *} else {
     *String username = principal.toString();
     *}
     *
     *
     *
     *   @PreAuthorize("hasRole('ROLE_USER')")
     *
     *   List<SimpleGrantedAuthority> authorities = new ArrayList<>();
     * authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
     *
     * @RolesAllowed("ROLE_ADMIN")
     *  @Secured("ROLE_ADMIN")
     *  @PreAuthorize("hasRole('ROLE_ADMIN')")
     *
     *  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
     *  @PreAuthorize("#id<10")
     *
     *  @PreAuthorize("principal.username.equals(#username)")
     *  @PreAuthorize("#user.name.equals('abc')")
     *  方法调用完成后
     *  @PostAuthorize("returnObject.id%2==0")
     *
     *   @PostFilter("filterObject.id%2==0")
     *    @PreFilter(filterTarget="ids", value="filterObject%2==0")
     *
     *    @PreFilter和@PostFilter进行过滤

    使用@PreFilter和@PostFilter可以对集合类型的参数或返回值进行过滤。使用@PreFilter和@PostFilter时，Spring Security将移除使对应表达式的结果为false的元素。

     @PostFilter("filterObject.id%2==0")

     public List<User> findAll() {

     List<User> userList = new ArrayList<User>();

     User user;

     for (int i=0; i<10; i++) {

     user = new User();

     user.setId(i);

     userList.add(user);

     }

     return userList;

     }



     上述代码表示将对返回结果中id不为偶数的user进行移除。filterObject是使用@PreFilter和@PostFilter时的一个内置表达式，表示集合中的当前对象。当@PreFilter标注的方法拥有多个集合类型的参数时，需要通过@PreFilter的filterTarget属性指定当前@PreFilter是针对哪个参数进行过滤的。如下面代码就通过filterTarget指定了当前@PreFilter是用来过滤参数ids的。

     @PreFilter(filterTarget="ids", value="filterObject%2==0")

     public void delete(List<Integer> ids, List<String> usernames) {

     ...

     }
     *
     *
     *
     *
     *
     *
     */
}

