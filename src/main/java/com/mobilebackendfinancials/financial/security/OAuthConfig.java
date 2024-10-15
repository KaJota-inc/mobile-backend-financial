package com.mobilebackendfinancials.financial.security;//package com.bibleappmobile.financial.security;
//
//import com.bibleappmobile.financial.util.EncryptionAsymmetricUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.KeyPair;
//
//
//@Component
//@Slf4j
//public class OAuthConfig {
//    @Value("${keys.path.private}")
//    private String privateKeyPath;
//    @Value("${keys.path.public}")
//    private String publicKeyPath;
//
//    @Bean
//    public JwtAccessTokenConverter tokenConverter() throws Exception {
//        log.info("Inside tokenConverter");
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
////        log.info("after JwtAccessTokenConverter");
//
//        String privateKey;
//        String publicKey;
//
//
////        privateKey = FileUtils.readFileToString(new File(privateKeyPath), StandardCharsets.UTF_8);
////        publicKey = FileUtils.readFileToString(new File(publicKeyPath), StandardCharsets.UTF_8);
////
////        log.info("Inside ClassPathResource");
//        Resource privateResource = new ClassPathResource(privateKeyPath);
//        Resource publicResource = new ClassPathResource(publicKeyPath);
//
//        privateKey = IOUtils.toString(privateResource.getInputStream(), StandardCharsets.UTF_8);
//        publicKey = IOUtils.toString(publicResource.getInputStream(), StandardCharsets.UTF_8);
//
////        log.info("Inside KeyPair");
//        KeyPair keyPair = new KeyPair(
//                EncryptionAsymmetricUtils.getPublicKeyFromString(publicKey),
//                EncryptionAsymmetricUtils.getPrivateKeyFromString(privateKey)
//        );
//
//        converter.setKeyPair(keyPair);
//        return converter;
//    }
//
//
//    @Bean
//    public JwtTokenStore tokenStore() throws Exception {
//        log.info("Inside tokenStore");
//        return new JwtTokenStore(tokenConverter());
//    }
//
//    @Bean
//    public DefaultTokenServices tokenServices() throws Exception {
//        log.info("Inside tokenServices");
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setTokenEnhancer(tokenConverter());
//        return tokenServices;
//    }
//
//
//}
