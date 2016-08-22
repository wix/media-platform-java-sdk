package com.wix.mediaplatform.collection;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.BaseTest;
import com.wix.mediaplatform.authentication.AuthenticationFacade;
import com.wix.mediaplatform.configuration.Configuration;
import com.wix.mediaplatform.dto.collection.*;
import com.wix.mediaplatform.http.AuthenticatedHTTPClient;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CollectionManagerTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private AuthenticatedHTTPClient authenticatedHTTPClient = new AuthenticatedHTTPClient(authenticationFacade, httpClient, gson);

    private CollectionManager collectionManager = new CollectionManager(authenticatedHTTPClient, configuration);

    @Test
    public void newCollection() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("new-collection-response.json")));

        CollectionDTO collectionDTO = collectionManager.newCollection("userId", new NewCollectionRequest()
                .setType("type")
                .setTitle("title")
                .setThumbnailUrl("thumbnailUrl")
                .addTag("tag")
                .putPrivateProperty("priv-key", "priv-value")
                .putPublicProperty("pub-key", "pub-value")
        );

        assertThat(collectionDTO.getPrivateProperties().get("private-prop"), is("private-value"));
    }

    @Test
    public void listCollections() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/collections?type=type"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("list-collections-response.json")));

        CollectionDTO[] collections = collectionManager.listCollections("userId", "type");

        assertThat(collections[0].getPublicProperties().get("public-prop"), is("public-value"));
    }

    @Test
    public void getCollection() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(get(urlEqualTo("/collections/collectionId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-collection-response.json")));

        CollectionDTO collection = collectionManager.getCollection("userId", "collectionId");

        //noinspection ConstantConditions
        assertThat(collection.getType(), is("type"));
    }

    @Test
    public void updateCollection() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(put(urlEqualTo("/collections/collectionId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-collection-response.json")));

        CollectionDTO collection = collectionManager.updateCollection("userId", "collectionId", new UpdateCollectionRequest()
                .addTag("tag")
                .setTitle("title")
        );

        assertThat(collection.getTags(), contains("fish"));
    }

    @Test
    public void publishCollection() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("publish-collection-response.json")));

        String response = collectionManager.publishCollection("userId", "collectionId");

        assertThat(response, is("/path/to.json"));
    }

    @Test
    public void deleteCollection() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(delete(urlEqualTo("/collections/collectionId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        collectionManager.deleteCollection("userId", "collectionId");
    }

    @Test
    public void prependItems() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/prepend"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.prependItems("userId", "collectionId", new NewItemRequest[]{
                new NewItemRequest()
                        .setTitle("title")
        });

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void appendItems() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/append"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.appendItems("userId", "collectionId", new NewItemRequest[]{
                new NewItemRequest()
                        .setTitle("title")
        });

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void insertBefore() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/insert-before/itemId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.insertBefore("userId", "collectionId", "itemId", new NewItemRequest[]{
                new NewItemRequest()
                        .setTitle("title")
        });

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void insertAfter() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/insert-after/itemId"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.insertAfter("userId", "collectionId", "itemId", new NewItemRequest[]{
                new NewItemRequest()
                        .setTitle("title")
        });

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void updateItems() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(put(urlEqualTo("/collections/collectionId/items"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.updateItems("userId", "collectionId", new UpdateItemRequest[]{
                new UpdateItemRequest()
                        .setId("itemId")
                        .setTitle("title")
        });

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void moveToStart() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/move-first"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.moveToStart("userId", "collectionId", new String[]{"itemId"});

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void moveToEnd() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/move-last"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.moveToEnd("userId", "collectionId", new String[]{"itemId"});

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void moveBefore() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/move-before/itemId1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.moveBefore("userId", "collectionId", "itemId1", new String[]{"itemId2"});

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void moveAfter() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/move-after/itemId1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wrapped-items-response.json")));

        ItemDTO[] items = collectionManager.moveAfter("userId", "collectionId", "itemId1", new String[]{"itemId2"});

        assertThat(items[0].getTitle(), is("cat"));
    }

    @Test
    public void deleteItems() throws Exception {
        when(authenticationFacade.getHeader("userId")).thenReturn("header");
        stubFor(post(urlEqualTo("/collections/collectionId/items/delete"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        collectionManager.deleteItems("userId", "collectionId", new String[]{"itemId2"});
    }
}