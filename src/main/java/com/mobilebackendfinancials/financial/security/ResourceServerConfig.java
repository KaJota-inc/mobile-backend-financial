package com.mobilebackendfinancials.financial.security;//package com.bibleappmobile.financial.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//
//@Configuration
//@Slf4j
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//
//    //    @Value("${keys.path.public}")
////    String publicKeyPath;
//    @Value("${auth.token.public-key-path}")
//    private String publicKeyPath;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .regexMatchers(".*callback.*").permitAll()
//                .anyRequest().fullyAuthenticated();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(tokenStore());
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        String publicKey = null;
//        try {
//            //publicKey = IOUtils.toString(new FileInputStream(new File(publicKeyPath)), "UTF-8");
//            publicKey = publicKeyPath;
////            Resource publicResource = new ClassPathResource(publicKeyPath);
////            publicKey = IOUtils.toString(publicResource.getInputStream(), StandardCharsets.UTF_8);
//        } catch (Exception ex) {
//            log.error("Exception occurred while trying to create access token converted because: {}", ex.getMessage(), ex);
//        }
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setVerifierKey(publicKey);
//        return converter;
//
//    }
//
//
//}