package com.smartling.connectors.provider.server.java.impl;

import com.smartling.connectors.provider.server.java.api.AssetsControllerApiDelegate;
import com.smartling.connectors.provider.server.java.model.AssetDetails;
import com.smartling.connectors.provider.server.java.model.ListAssetsRequest;
import com.smartling.connectors.provider.server.java.model.ResponseAssetContentDTO;
import com.smartling.connectors.provider.server.java.model.ResponseCodes;
import com.smartling.connectors.provider.server.java.model.ResponseListWithNextPageTokenAssetDetails;
import com.smartling.connectors.provider.server.java.model.RestApiResponseAssetContentDTO;
import com.smartling.connectors.provider.server.java.model.RestApiResponseListWithNextPageTokenAssetDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;

public class AssetsServiceImpl implements AssetsControllerApiDelegate
{
    private static final AssetDetails SAMPLE_ASSET = AssetDetails.builder()
            .externalLink("assetExternalLink")
            .fileUri("assetExternalUri")
            .assetUid("assetUid")
            .contentTypeUid("contentTypeUid")
            .identity("identity")
            .title("title")
            .isLocalizable(true)
            .updatedAt(OffsetDateTime.now())
            .build();

    @Override
    public ResponseEntity<RestApiResponseListWithNextPageTokenAssetDetails> getAssetList(String projectId, ListAssetsRequest listAssetsRequest)
    {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestApiResponseListWithNextPageTokenAssetDetails.builder()
                        .response(ResponseListWithNextPageTokenAssetDetails.builder()
                                .code(ResponseCodes.SUCCESS)
                                .items(List.of(SAMPLE_ASSET))
                                .build())
                        .build());
    }

    @Override
    public ResponseEntity<RestApiResponseAssetContentDTO> getAssetContent(String projectId, String assetUid)
    {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestApiResponseAssetContentDTO.builder()
                        .response(ResponseAssetContentDTO.builder()
                                .code(ResponseCodes.SUCCESS)
                                .build())
                        .build());
    }
}
