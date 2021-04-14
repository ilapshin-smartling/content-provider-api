package com.smartling.connectors.provider.server.kotlin.impl

import com.smartling.connectors.provider.server.kotlin.api.AssetsControllerApiController
import com.smartling.connectors.provider.server.kotlin.api.AssetsControllerApiService
import com.smartling.connectors.provider.server.kotlin.api.DictionaryControllerApiController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KotlinServerConfig {

    @Bean
    fun dictionaryController(): DictionaryControllerApiController {
        return DictionaryControllerApiController(
            DictionaryControllerApiServiceImpl()
        )
    }

    @Bean
    fun assetsController() : AssetsControllerApiController {
        return AssetsControllerApiController(
            AssetsControllerApiServiceImpl()
        )
    }
}