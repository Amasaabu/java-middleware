//package com.sample.middleware;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.ssm.SsmClient;
//@Configuration
//public class SsmConfig {
//    @Bean
//    public SsmClient ssmClient() {
//        return SsmClient.builder()
//                .region(Region.of("us-east-2"))  // Replace with your actual AWS region
//                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
//                .build();
//    }
//}
