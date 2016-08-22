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

In the server expose a route that returns the signed URL and upload token:

```java
app.get('/upload/:mediaType/credentials', function(req, res, next) {

    mediaPlatform.fileUploader.getUploadUrl('userId', req.params.mediaType,  function (error, urlAndToken) {

        if (error) {
            res.status(500).send(error.message);
            return;
        }

        res.send(urlAndToken);
    });
    
});
```

From the browser GET the URL and POST the form to it, including the token in the form body 

```html
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
var fromDto = require('media-platform-js-sdk').image.fromDto;
var imageRequest = fromDto('media.wixapps.net', imageDto);

/**
* A new image request from a previously generated url
*/
var urlToImageRequest = require('media-platform-js-sdk').image.urlToImageRequest;
var imageRequest = urlToImageRequest('//media.wixapps.net/wixmedia-samples/images/000c45e21f8a433cb3b2483dfbb659d8/v1/fit/w_300,h_200/image.jpg#w_600,h_400,mt_image%2Fjpeg');

/**
* A new image request from the base url and the file id
*/
var ImageRequest = require('media-platform-js-sdk').image.ImageRequest;
var imageRequest = new ImageRequest('media.wixapps.net/wixmedia-samples/images', '000c45e21f8a433cb3b2483dfbb659d8');

var url = imageRequest.fit(500, 500).negative().saturation(-90).toUrl().url;

/**
* A pre-configured operation from a previously generated url
*/
var fromUrl = require('media-platform-js-sdk').image.fromUrl;
var imageOperation = fromUrl('//media.wixapps.net/wixmedia-samples/images/000c45e21f8a433cb3b2483dfbb659d8/v1/fit/w_300,h_200/image.jpg#w_600,h_400,mt_image%2Fjpeg');

var url = imageOperation.negative().saturation(-90).toUrl().url;

```

## File Management

Wix Media Platform exposes a comprehensive set of APIs tailored for the management of previously uploaded files.

```java
var fileManager = mediaPlatform.fileManager;
```

Retrieve a list of uploaded files

```java
var ListFilesRequest = require('media-platform-js-sdk').file.ListFilesRequest;

var listFilesRequest = new ListFilesRequest()
    .ascending()
    .setCursor('c')
    .setMediaType(MediaType.IMAGE)
    .orderBy('date')
    .setSize(10)
    .setTag('dog')
    .setParentFolderId('parentFolderId');
fileManager.listFiles('userId', listFilesRequest, callback)
```

Get an uploaded file metadata 

(*does not return the actual file*)

```java
fileManager.getFile('fileId', callback)
```

Update a file metadata

```java
var UpdateFileRequest = require('media-platform-js-sdk').file.UpdateFileRequest;

var updateFileRequest = new UpdateFileRequest()
    .setOriginalFileName('dog.jpeg')
    .setParentFolderId('folderId')
    .setTags(['dog', 'Schnauzer']);
fileManager.updateFile('userId', 'fileId', updateFileRequest, callback);
```

Delete file

*Warning: The file will no longer be reachable*

```java
fileManager.deleteFile('userId', 'fileId', callback);
```

### Folder Management

Wix Media Platform supports folders
 
List child folders

```java
fileManager.listFolders('folderId', callback);
```

Create a new folder

```java
var NewFolderRequest = require('media-platform-js-sdk').file.NewFolderRequest;

var newFolderRequest = new NewFolderRequest()
    .setMediaType(MediaType.IMAGE)
    .setFolderName('Doberman Pinscher')
    .setParentFolderId('folderId');
fileManager.newFolder('userId', newFolderRequest, callback);
```

Update a folder

```java
var UpdateFolderRequest = require('media-platform-js-sdk').file.UpdateFolderRequest;

var updateFolderRequest = new UpdateFolderRequest()
    .setFolderName('Doberman Pinscher');
fileManager.updateFolder('userId', 'folderId', updateFolderRequest, callback);
```

Delete a folder

*this will not delete the folder content*

```java
fileManager.deleteFolder('folderId', callback);
```

## Collection Management

The collection service enables the creation, management and publishing of item groups such as curated image galleries, audio playlist etc.

```java
var collectionManager = mediaPlatform.collectionManager;
```

Create a new collection

```java
var NewCollectionRequest = require('media-platform-js-sdk').collection.NewCollectionRequest;
var NewItemRequest = require('media-platform-js-sdk').collection.NewItemRequest;

var newCollectionRequest = new NewCollectionRequest()
    .setType('dog')
    .setPrivateProperties({prop: 'value'})
    .setPublicProperties({prop: 'value'})
    .setTags(['Doberman', 'Pinscher'])
    .setThumbnailUrl('http://this.is.a/collection.jpeg')
    .setTitle('Dogs Galore')
    .setItems([
        new NewItemRequest()
            .setType(MediaType.AUDIO)
            .setPrivateProperties({cat: 'fish'})
            .setPublicProperties({bark: 'loud'})
            .setTags(['dog', 'bark'])
            .setTitle('Whof')
    ]);
collectionManager.newCollection('userId', newCollectionRequest, callback);
```

List collections

```java
collectionManager.listCollections('userId', 'dog', callback);
```

Get collection

```java
collectionManager.getCollection('userId', 'collectionId', callback);
```

Update collection 

```java
var UpdateCollectionRequest = require('media-platform-js-sdk').collection.UpdateCollectionRequest;

var updateCollectionRequest = new UpdateCollectionRequest()
    .setPrivateProperties({prop: 'value'})
    .setPublicProperties({prop: 'value'})
    .setTags(['cats', 'purr'])
    .setThumbnailUrl('http://this.is.a/collection.jpeg')
    .setTitle('Cats Galore');
collectionManager.updateCollection('userId', 'collectionId', updateCollectionRequest, callback);
```

Publish collection

```java
collectionManager.publishCollection('userId', 'collectionId', callback);
```

Delete collection

```java
collectionManager.deleteCollection('userId', 'collectionId', callback);
```

Add items at the beginning of a collection

```java
var NewItemRequest = require('media-platform-js-sdk').collection.NewItemRequest;

var addItemRequests = [
    new NewItemRequest()
        .setType('dog')
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['Doberman', 'Pinscher'])
        .setTitle('Doberman'),
    new NewItemRequest()
        .setType('dog')
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['Doberman', 'Pinscher'])
        .setTitle('Pinscher')
];
collectionManager.prependItems('userId', 'collectionId', addItemRequests, callback);
```

Add items to the end of a collection

```java
var NewItemRequest = require('media-platform-js-sdk').collection.NewItemRequest;

var addItemRequests = [
    new NewItemRequest()
        .setType('dog')
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['Doberman', 'Pinscher'])
        .setTitle('Doberman')
];
collectionManager.appendItems('userId', 'collectionId', addItemRequests, callback);
```

Add items *before* an exiting item in a collection

```java
var NewItemRequest = require('media-platform-js-sdk').collection.NewItemRequest;

var addItemRequests = [
    new NewItemRequest()
        .setType('dog')
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['Doberman', 'Pinscher'])
        .setTitle('Doberman')
];
collectionManager.insertBefore('userId', 'collectionId', 'itemId', addItemRequests, callback);
```

Add items *after* an exiting item in a collection

```java
var NewItemRequest = require('media-platform-js-sdk').collection.NewItemRequest;

var addItemRequests = [
    new NewItemRequest()
        .setType('dog')
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['Doberman', 'Pinscher'])
        .setTitle('Doberman')
];
collectionManager.insertAfter('userId', 'collectionId', 'itemId', addItemRequests, callback);
```

Update exiting items in a collection

```java
var UpdateItemRequest = require('media-platform-js-sdk').collection.UpdateItemRequest;

var updateItemRequests = [
    new UpdateItemRequest()
        .setId('id1')
        .setType(MediaType.AUDIO)
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['moshe', 'chaim'])
        .setTitle('olala'),
    new UpdateItemRequest()
        .setId('id2')
        .setType(MediaType.AUDIO)
        .setPrivateProperties({prop: 'value'})
        .setPublicProperties({prop: 'value'})
        .setTags(['moshe', 'chaim'])
        .setTitle('olala')
];
collectionManager.updateItems('userId', 'collectionId', updateItemRequests, callback);
```

Move items to the *start* of the collection

```java
collectionManager.moveToStart('userId', 'collectionId', ['id1', 'id2'], callback);
```

Move items to the *end* of the collection

```java
collectionManager.moveToEnd('userId', 'collectionId', ['id1', 'id2'], callback);
```

Move items *before* another item

```java
collectionManager.moveBefore('userId', 'collectionId', 'itemId', ['id1', 'id2'], callback);
```

Move items *after* another item

```java
collectionManager.moveAfter('userId', 'collectionId', 'itemId', ['id1', 'id2'], callback);
```

Delete items from a collection

```java
collectionManager.deleteItems('userId', 'collectionId', ['id1', 'id2'], callback);
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
