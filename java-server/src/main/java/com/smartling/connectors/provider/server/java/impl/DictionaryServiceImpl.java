package com.smartling.connectors.provider.server.java.impl;

import com.smartling.connectors.provider.server.java.api.DictionaryControllerApiDelegate;
import com.smartling.connectors.provider.server.java.model.ResponseCodes;
import com.smartling.connectors.provider.server.java.model.ResponseListResponseSanityProjectDTO;
import com.smartling.connectors.provider.server.java.model.ResponseListResponseSanityProjectDTOAllOfData;
import com.smartling.connectors.provider.server.java.model.ResponseListResponseSanityProjectDTOAllOfDataItems;
import com.smartling.connectors.provider.server.java.model.RestApiResponseListResponseSanityProjectDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryControllerApiDelegate
{
    @Override
    public ResponseEntity<RestApiResponseListResponseSanityProjectDTO> getSanityProjects(String projectId)
    {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestApiResponseListResponseSanityProjectDTO.builder()
                        .response(ResponseListResponseSanityProjectDTO.builder()
                                .code(ResponseCodes.SUCCESS)
                                .data(ResponseListResponseSanityProjectDTOAllOfData.builder()
                                        .items(List.of(
                                                ResponseListResponseSanityProjectDTOAllOfDataItems.builder()
                                                        .id(projectId)
                                                        .name("sample project " + projectId)
                                                        .build()))
                                        .build())
                                .build())
                        .build());
    }
}
