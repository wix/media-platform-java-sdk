# Wix Media Platform

[![MVN version][mvn-image]][mvn-url]

[Wix Media Platform](https://www.wixmp.com/) is a collection of services for storing, serving, uploading, and managing image, audio, and video files.

## Image

Wix Media Platform provides powerful image-processing services that support resizing, cropping, rotating, sharpening, and face-detection, as well as offering a number of filters and adjustments. Images can be easily served with on-the-fly manipulations using the Wix Media Platform SDKs or Image API.

See it in [action.](https://app.wixmp.com/dashboard/index.html)

## Audio

Wix Media Platform provides storage for professional, high-quality audio files that can then be used in commercial music-selling applications.

## Video

Video files uploaded to Wix Media Platform are automatically transcoded into additional formats of various qualities, enabling video playback on any browser or Internet-connected device.

For video playback see [Vidi](https://github.com/wix/vidi) - Adaptive video playback library.

## Documents

In addition, Wix Media Platform supports uploading and distribution of documents such as Excel and Word.

# Java SDK

This artifact is aa Java library, compatible with Java 7+.

## JavaScript SDK

The respective JavaScript (for the Browser and Node.js) library can be found [here.](https://github.com/wix/media-platform-js-sdk)

## Installation

```xml
<dependency>
    <groupId>com.wix</groupId>
    <artifactId>media-platform-java-sdk</artifactId>
    <version>[1.0,1.1)</version>
</dependency>
```

## Instantiating the Media Platform in the Server

First, if you haven't done so yet, register at [Wix Media Media Platform](https://app.wixmp.com/dashboard/index.html),
Once registered you'll be issued with your own API Key, API Secret and API Endpoint.

```java
MediaPlatform mediaPlatform = new MediaPlatform(
  "<domain as appears in the Dashboard>",
  "<appId as appears in the Dashboard>",
  "<sharedSecret as appears in the Dashboard>"
);
```

## File Upload

### Server

```java
FileUploader fileUploader = mediaPlatform.fileUploader();

File file = new File(...);
UploadRequest uploadRequest = new UploadRequest(newHashSet("fish", "cat"), "folderId");

ImageDTO imageDTO = fileUploader.uploadImage("userId", file, uploadRequest || null);

AudioDTO audioDTO = fileUploader.uploadAudio("userId", file, uploadRequest || null);

DocumentDTO documentDTO = fileUploader.uploadDocument("userId", file, uploadRequest || null);

EncodingOptions encodingOptions = new EncodingOptions(newHashSet(EncodingOptions.VideoFormat.MP4), true, true, EncodingOptions.AudioFormat.M4A, EncodingOptions.ImageFormat.JPEG);
VideoDTO videoDTO = fileUploader.uploadVideo("userId", file, uploadRequest || null, encodingOptions || null);
```

### Browser

File upload from the browser is a 2 step operation: 
 1. First the signed URL and the upload token is retrieved from the server
 2. Then a multipart/form-data request is made to the URL

In the server expose a url that returns the signed URL and upload token:

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    GetUploadUrlResponse response = fileUploader.getUploadUrl("userId");
          
    response.setContentType("application/json");
    
    PrintWriter out = response.getWriter();
    out.println(gson.toJson(response));
}
```

From the browser GET the URL and POST the form to it, including the token in the form body 

```html
<!-- Based on the JavaScript SDK -->

<form id="upload-form" enctype="multipart/form-data" action="" method="post" target="upload-result">
    <input id="file" name="file" type="file" accept="image/*">
    <input id="media-type" name="media_type" type="text" value="picture" hidden>
</form>
<button id="upload-button">Upload</button>

<script>
    var button = document.getElementById('upload-button');
    var form = document.getElementById('upload-form');
    
    button.addEventListener('click', function () {

        mediaPlatform.fileUploader.getUploadUrl(MP.MediaType.IMAGE, function(error, response) {
            
            if (error) {
                alert('Oops! Something went wrong.');
                return;
            }
            
            var formData = new FormData(form);
            formData.append('upload_token', response.uploadToken);

            var request = new XMLHttpRequest();
            request.responseType = 'json';
            request.addEventListener('load', function (event) {
                var imageDto = new ImageDTO(event.target.response[0]);
                var imageRequest = MP.image.fromDto('media.wixapps.net', imageDto);
                var imageUrl = imageRequest.crop(800, 200, 1, 1).toUrl();
                var img = document.createElement('img');
                img.setAttribute('src', imageUrl.url);
            });
            request.addEventListener('error', function (event) {
                alert('Oops! Something went wrong.');
            });

            request.open('POST', response.uploadUrl);
            request.send(formData);
        })
})</script>
```

## Image Consumption

The SDK provides a programmatic facility to generate image URLs

```java
/**
* A new image request from the host and ImageDTO (response from server)
*/
ImageRequest imageRequest = Parser.fromDto("test.wix.com", imageDto);

/**
* A new image request from a previously generated url
*/
ImageRequest imageRequest = Parser.fromUrl("//media.wixapps.net/wixmedia-samples/images/000c45e21f8a433cb3b2483dfbb659d8/v1/fit/w_300,h_200/image.jpg#w_600,h_400,mt_image%2Fjpeg");

/**
* A new image request from the base url and the file id
*/
ImageRequest imageRequestWithOriginalData = new ImageRequest("media.wixapps.net/wixmedia-samples/images", "000c45e21f8a433cb3b2483dfbb659d8", "image.jpeg", null);

String url = imageRequest.fit(500, 500).negative().saturation(-90).toUrl();

/**
* A pre-configured operation from a previously generated url
*/
Operation operation = Parser.operationFromUrl("//media.wixapps.net/wixmedia-samples/images/000c45e21f8a433cb3b2483dfbb659d8/v1/fit/w_300,h_200/image.jpg#w_600,h_400,mt_image%2Fjpeg");

String url = imageOperation.negative().saturation(-90).toUrl();
```

## File Management

Wix Media Platform exposes a comprehensive set of APIs tailored for the management of previously uploaded files.

```java
FileManager fileManager = mediaPlatform.fileManager();
```

Retrieve a list of uploaded files

```java
ListFilesResponse response = fileManager.listFiles("userId", new ListFilesRequest()
    .setOrder(ListFilesRequest.OrderBy.date)
    .descending()
    .setCursor("cursor")
    .setSize(10)
    .setMediaType(MediaType.VIDEO)
    .setParentFolderId("parentFolderId")
    .setTag("tag"));
```

Get an uploaded file metadata 

(*does not return the actual file*)

```java
ImageDTO file = (ImageDTO) fileManager.getFile("userId", "fileId");
```

Update a file metadata

```java
AudioDTO file = (AudioDTO) fileManager.updateFile("userId", "fileId", new UpdateFileRequest()
    .setOriginalFileName("file")
    .setParentFolderId("parent")
    .addTag("tag"));
```

Delete file

*Warning: The file will no longer be reachable*

```java
fileManager.deleteFile("userId", "fileId");
```

### Folder Management

Wix Media Platform supports folders
 
List folders

```java
ListFoldersResponse response = fileManager.listFolders("userId", "folderId" || null);
```

Create a new folder

```java
FolderDTO folder = fileManager.newFolder("userId", new NewFolderRequest()
    .setFolderName("name")
    .setParentFolderId("parent")
    .setMediaType(MediaType.DOCUMENT));
```

Update a folder

```java
FolderDTO folder = fileManager.updateFolder("userId", "folderId", new UpdateFolderRequest()
    .setFolderName("name"));
```

Delete a folder

*this will not delete the folder content*

```java
fileManager.deleteFolder("userId", "folderId");
```

## Collection Management

The collection service enables the creation, management and publishing of item groups such as curated image galleries, audio playlist etc.

```java
CollectionManager collectionManager = mediaPlatform.collectionManager();
```

Create a new collection

```java
CollectionDTO collectionDTO = collectionManager.newCollection("userId", new NewCollectionRequest()
    .setType("type")
    .setTitle("title")
    .setThumbnailUrl("thumbnailUrl")
    .addTag("tag")
    .putPrivateProperty("priv-key", "priv-value")
    .putPublicProperty("pub-key", "pub-value"));
```

List collections

```java
CollectionDTO[] collections = collectionManager.listCollections("userId", "type");
```

Get collection

```java
CollectionDTO collection = collectionManager.getCollection("userId", "collectionId");
```

Update collection 

```java
CollectionDTO collection = collectionManager.updateCollection("userId", "collectionId", new UpdateCollectionRequest()
    .addTag("tag")
    .setTitle("title"));
```

Publish collection

```java
String pathToCollectionJson = collectionManager.publishCollection("userId", "collectionId");
```

Delete collection

```java
collectionManager.deleteCollection("userId", "collectionId");
```

Add items at the beginning of a collection

```java
ItemDTO[] items = collectionManager.prependItems("userId", "collectionId", new NewItemRequest[]{
    new NewItemRequest()
        .setTitle("title")});
```

Add items to the end of a collection

```java
ItemDTO[] items = collectionManager.appendItems("userId", "collectionId", new NewItemRequest[]{
    new NewItemRequest()
        .setTitle("title")});
```

Add items *before* an exiting item in a collection

```java
ItemDTO[] items = collectionManager.insertBefore("userId", "collectionId", "itemId", new NewItemRequest[]{
    new NewItemRequest()
        .setTitle("title")});
```

Add items *after* an exiting item in a collection

```java
ItemDTO[] items = collectionManager.insertAfter("userId", "collectionId", "itemId", new NewItemRequest[]{
    new NewItemRequest()
        .setTitle("title")
});
```

Update existing items in a collection

```java
ItemDTO[] items = collectionManager.updateItems("userId", "collectionId", new UpdateItemRequest[]{
    new UpdateItemRequest()
        .setId("itemId")
        .setTitle("title")
});
```

Move items to the *start* of the collection

```java
ItemDTO[] items = collectionManager.moveToStart("userId", "collectionId", new String[]{"itemId"});
```

Move items to the *end* of the collection

```java
ItemDTO[] items = collectionManager.moveToEnd("userId", "collectionId", new String[]{"itemId"});
```

Move items *before* another item

```java
ItemDTO[] items = collectionManager.moveBefore("userId", "collectionId", "itemId1", new String[]{"itemId2"});
```

Move items *after* another item

```java
ItemDTO[] items = collectionManager.moveAfter("userId", "collectionId", "itemId1", new String[]{"itemId2"});
```

Delete items from a collection

```java
collectionManager.deleteItems("userId", "collectionId", new String[]{"itemId2"});
```

## Reporting Issues

Please use [the issue tracker](https://github.com/wix/media-platform-java-sdk/issues) to report issues related to this library, or to the Wix Media Platform API in general.

## License

This library uses the BSD-3-Clause License.

## About Wix

[Wix.com](https://www.wix.com) is a leading cloud-based web development platform with more than 86 million registered users worldwide. 
Our powerful technology makes it simple for everyone to create a beautiful website and grow their business online.

## About Google Cloud Platform

[Google Cloud Platform](https://cloud.google.com/) enables developers to build, test and deploy applications on Google’s reliable infrastructure.
It offers computing, storage and application services for web, mobile and backend solutions.


[mvn-image]: https://img.shields.io/maven-central/v/wix.com/media-platfrom-java-sdk.svg
[mvn-url]: https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22media-platfrom-java-sdk%22
