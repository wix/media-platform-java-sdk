package com.wix.mediaplatform.collection;

import com.google.common.collect.ImmutableMap;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.collection.*;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;

public class CollectionManager {

    private final AuthenticatedHTTPClient authenticatedHTTPClient;
    private final String baseUrl;

    public CollectionManager(AuthenticatedHTTPClient authenticatedHTTPClient, Configuration configuration) {
        this.authenticatedHTTPClient = authenticatedHTTPClient;

        this.baseUrl = "https://" + configuration.getDomain();
    }

    public CollectionDTO newCollection(String userId, NewCollectionRequest newCollectionRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections", newCollectionRequest, null, CollectionDTO.class);
    }

    public CollectionDTO[] listCollections(String userId, String type) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.get(userId, baseUrl + "/collections", ImmutableMap.of("type", type), CollectionDTO[].class);
    }

    @Nullable
    public CollectionDTO getCollection(String userId, String collectionId) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.get(userId, baseUrl + "/collections/" + collectionId, null, CollectionDTO.class);
    }

    public CollectionDTO updateCollection(String userId, String collectionId, UpdateCollectionRequest updateCollectionRequest) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.put(userId, baseUrl + "/collections/" + collectionId, updateCollectionRequest, null, CollectionDTO.class);
    }

    public String publishCollection(String userId, String collectionId) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId, null, null, String.class);
    }

    public void deleteCollection(String userId, String collectionId) throws UnauthorizedException, IOException, URISyntaxException {
       authenticatedHTTPClient.delete(userId, baseUrl + "/collections/" + collectionId, null, null);
    }

    public ItemDTO[] prependItems(String userId, String collectionId, NewItemRequest[] newItems) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/prepend", newItems, null, ItemDTO[].class);
    }

    public ItemDTO[] appendItems(String userId, String collectionId, NewItemRequest[] newItems) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/append", newItems, null, ItemDTO[].class);
    }

    public ItemDTO[] insertBefore(String userId, String collectionId, String itemId, NewItemRequest[] newItems) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/insert-before/" + itemId, newItems, null, ItemDTO[].class);
    }

    public ItemDTO[] insertAfter(String userId, String collectionId, String itemId, NewItemRequest[] newItems) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/insert-after/" + itemId, newItems, null, ItemDTO[].class);
    }

    public ItemDTO[] updateItems(String userId, String collectionId, UpdateItemRequest[] updatedItems) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.put(userId, baseUrl + "/collections/" + collectionId + "/items", updatedItems, null, ItemDTO[].class);
    }

    public ItemDTO[] moveToStart(String userId, String collectionId, String[] items) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/move-first", items, null, ItemDTO[].class);
    }

    public ItemDTO[] moveToEnd(String userId, String collectionId, String[] items) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/move-last", items, null, ItemDTO[].class);
    }

    public ItemDTO[] moveBefore(String userId, String collectionId, String itemId, String[] items) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/move-before/" + itemId, items, null, ItemDTO[].class);
    }

    public ItemDTO[] moveAfter(String userId, String collectionId, String itemId, String[] items) throws UnauthorizedException, IOException, URISyntaxException {
        return authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/move-after/" + itemId, items, null, ItemDTO[].class);
    }

    public void deleteItems(String userId, String collectionId, String[] items) throws UnauthorizedException, IOException, URISyntaxException {
        authenticatedHTTPClient.post(userId, baseUrl + "/collections/" + collectionId + "/items/delete", items, null, null);
    }
}
