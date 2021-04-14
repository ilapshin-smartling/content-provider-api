package com.smartling.connectors.provider.server.java.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {JavaServerConfig.class})
public class JavaServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(JavaServer.class);
    }
}
