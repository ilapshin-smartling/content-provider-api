package com.smartling.connectors.provider.server.kotlin.impl

import com.smartling.connectors.provider.server.kotlin.api.DictionaryControllerApiService
import com.smartling.connectors.provider.server.kotlin.model.ResponseCodes
import com.smartling.connectors.provider.server.kotlin.model.ResponseListResponsePropertyTypeTranslationActionsResponseDTO
import com.smartling.connectors.provider.server.kotlin.model.ResponseListResponsePropertyTypeTranslationActionsResponseDTOAllOfData
import com.smartling.connectors.provider.server.kotlin.model.ResponseListResponseSanityProjectDTO
import com.smartling.connectors.provider.server.kotlin.model.ResponseListResponseSanityProjectDTOAllOfData
import com.smartling.connectors.provider.server.kotlin.model.ResponseListResponseSanityProjectDTOAllOfDataItems
import com.smartling.connectors.provider.server.kotlin.model.RestApiResponseListResponseContentTypeDTO
import com.smartling.connectors.provider.server.kotlin.model.RestApiResponseListResponseLocaleDTO
import com.smartling.connectors.provider.server.kotlin.model.RestApiResponseListResponseProjectDatasetDTO
import com.smartling.connectors.provider.server.kotlin.model.RestApiResponseListResponsePropertyTypeTranslationActionsResponseDTO
import com.smartling.connectors.provider.server.kotlin.model.RestApiResponseListResponseSanityProjectDTO
import org.springframework.stereotype.Service

class DictionaryControllerApiServiceImpl : DictionaryControllerApiService {

    override fun getSanityProjects(projectId: String): RestApiResponseListResponseSanityProjectDTO {
        return RestApiResponseListResponseSanityProjectDTO(
            response = ResponseListResponseSanityProjectDTO(
                code = ResponseCodes.SUCCESS,
                data = ResponseListResponseSanityProjectDTOAllOfData(
                    totalCount = 1,
                    items = listOf(
                        ResponseListResponseSanityProjectDTOAllOfDataItems(
                            id = projectId,
                            name = "sample project $projectId"
                        )
                    )
                )
            )
        )
    }

    override fun getCustomPropertyActions(projectId: String): RestApiResponseListResponsePropertyTypeTranslationActionsResponseDTO {
        return RestApiResponseListResponsePropertyTypeTranslationActionsResponseDTO(
            response = ResponseListResponsePropertyTypeTranslationActionsResponseDTO(
                code = ResponseCodes.SUCCESS,
                data = ResponseListResponsePropertyTypeTranslationActionsResponseDTOAllOfData(
                    items = listOf(),
                    totalCount = 0
                )
            )
        )
    }

    override fun getExternalLocales(projectId: String): RestApiResponseListResponseLocaleDTO {
        TODO("Not yet implemented")
    }

    override fun getProjectDatasets(
        projectId: String,
        sanityProjectId: String
    ): RestApiResponseListResponseProjectDatasetDTO {
        TODO("Not yet implemented")
    }

    override fun listContentTypes(projectId: String): RestApiResponseListResponseContentTypeDTO {
        TODO("Not yet implemented")
    }
}