package cn.gnaixeuy.mediaauth.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/23
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    public static final String CREATE_TOKEN_URL = "/author/**";
//    public static final String API_DOC_URL = "/swagger/**";
//    private UserService userService;
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//    private AuthenticationConfiguration authenticationConfiguration;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                // 下面开始设置权限
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers(EndpointRequest.toAnyEndpoint())
//                        .permitAll()
//                        // 请求放开
//                        .antMatchers(
//                                WebSecurityConfig.CREATE_TOKEN_URL,
//                                WebSecurityConfig.API_DOC_URL,
//                                "/rsa/publicKey"
//                        ).permitAll()
//                        //暂时开放
//                        // 其他地址的访问均需验证权限
//                        .antMatchers("/druid/**").permitAll()
//                        .anyRequest()
//                        .authenticated()
//                )
//                .headers()
//                .frameOptions()
//                .disable()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .headers().frameOptions().disable()
//                .and()
//                .cors()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(this.restAuthenticationEntryPoint)
//                .and()
////                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
////                .addFilterBefore(new JWTAuthenticationFilter(this.authenticationManager(), this.userService), UsernamePasswordAuthenticationFilter.class)
//                // 认证用户时用户信息加载配置，注入springAuthUserService
//                .userDetailsService(this.userService)
//                .build();
//    }
//
//    /**
//     * 配置跨源访问(CORS)
//     *
//     * @return
//     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//
//    /**
//     * 密码明文加密方式配置
//     *
//     * @return BCryptPasswordEncoder密码加密编码器
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 获取AuthenticationManager（认证管理器），登录时认证使用
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Autowired
//    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
//        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
//    }
//
//    @Autowired
//    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
//        this.authenticationConfiguration = authenticationConfiguration;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
