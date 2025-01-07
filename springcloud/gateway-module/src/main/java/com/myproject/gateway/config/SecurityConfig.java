package com.myproject.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/signup").permitAll() // 开放登录和注册接口
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // 自定义登录页面
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder())) // 使用自定义的 JwtDecoder
                );

        return http.build();
    }

    // 定义一个 JwtDecoder Bean
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(getRSAPublicKey())
                .build();
    }

    // 将 PEM 格式的公钥字符串转换为 RSAPublicKey
    public RSAPublicKey getRSAPublicKey() {
        try {
            String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAreq3PN9DpNqDMeushpgd\n" +
                    "HHhBe0TTnT1s3vXLP4IWm5mCUiZwgdly9fEhbEAQNjPACIgFNsGcN9+nOrXfpN3d\n" +
                    "UAF3gmwzE4mJgcmaWHPOrwy+znVU5ZPg42SCWk2ViqEf5RQSbEMunRudoApu2KyK\n" +
                    "NlI8BcpZLeid2xEcxnpk4u3+N82apRhLwzdMLMd+yTq9c5rh0nrvFS4cu1dBzcIG\n" +
                    "kKNIbwH1wMeDvzXEsUkLOyJql3zqB3R66r9RQS6ewtQxk3T94oiqHaBG2rbLhM8X\n" +
                    "zxjC1+bbGEFixnAUPDMjjY8AtWVbMB4BLuGRJpCLR46BtYLbONylNLC9lcX6qTWa\n" +
                    "OwIDAQAB\n" +
                    "-----END PUBLIC KEY-----\n"; // 用你的公钥字符串替换这里

            // 去掉 "-----BEGIN PUBLIC KEY-----" 和 "-----END PUBLIC KEY-----" 之间的部分
            String publicKeyPEMFormatted = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            // 使用 Base64 解码
            byte[] encoded = Base64.getDecoder().decode(publicKeyPEMFormatted);

            // 生成 RSAPublicKey
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            java.security.spec.X509EncodedKeySpec keySpec = new java.security.spec.X509EncodedKeySpec(encoded);

            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse RSA public key", e);
        }
    }
}
