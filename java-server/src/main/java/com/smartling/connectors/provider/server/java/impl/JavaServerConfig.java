package com.smartling.connectors.provider.server.java.impl;

import com.smartling.connectors.provider.server.java.api.AssetsControllerApiController;
import com.smartling.connectors.provider.server.java.api.DictionaryControllerApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Configuration
public class JavaServerConfig
{
    @Bean
    public DictionaryControllerApiController dictionaryController(NativeWebRequest webRequest)
    {
        return new DictionaryControllerApiController(
                new DictionaryServiceImpl() {
                    @Override
                    public Optional<NativeWebRequest> getRequest()
                    {
                        return Optional.of(webRequest);
                    }
                }
        );
    }

    @Bean
    public AssetsControllerApiController assetsController(NativeWebRequest webRequest)
    {
        return new AssetsControllerApiController(
                new AssetsServiceImpl() {
                    @Override
                    public Optional<NativeWebRequest> getRequest()
                    {
                        return Optional.of(webRequest);
                    }
                }
        );
    }
}
