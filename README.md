# Wix Media Platform

[![Build Status][travis-image]][travis-url] 
[![MVN version][mvn-image]][mvn-url]

[Wix Media Platform][wixmp-url] is an end-to-end solution for all modern web media management, handling images, video and audio in the most efficient way on the market. From upload, storage, metadata management and all the way to delivery, Wix Media Platform takes care of all possible media workflows.

# Java SDK

This artifact is a Java library, compatible with Java 7+.

## Installation

```xml
<dependency>
    <groupId>com.wix</groupId>
    <artifactId>media-platform-java-sdk</artifactId>
    <version>[5.0,6.0)</version>
</dependency>
```

## JavaScript SDK

The respective JavaScript (for the Browser and Node.js) package can be found [here.][npm-url]

## Instantiating the Media Platform

First, if you haven't done so yet, register at [Wix Media Platform][wixmp-url], create your [organization, project][org-and-project-start] and [application][application-start].
__Parameters__:
- `domain` (String) - the project host url as appears in the application page
- `appId` (String) - the application id as appears in the application page
- `sharedSecret` (String) - the application's shared secret as appears in the application page

```java
MediaPlatform mediaPlatform = new MediaPlatform("domain", "appId", "secret");
```

## File Management
[File Management API Documentation](https://support.wixmp.com/en/article/file-management)
This API provides the basic set of file operations available in Wix Media Platform, such as upload, list and delete.
> Please note: for every File Management action, your application needs to have the relevant [permissions](https://support.wixmp.com/en/article/about-permissions). Add the application to a security group or create a permission that applies to it.

### File Upload
[File Upload API Documentation](https://support.wixmp.com/en/article/file-management#upload-file)
Requests an upload url and upload token from the application, and performs a `POST` request to that url with the relevant file information.
__Parameters__:
- `path` (string) - destination path of the file in Wix Media PLatform file manager.
- `mimeType` (string) - mimetype of the uploaded file.
- `file` (file | inputStream) - the file to be uploaded.
//TODO: waiting for a new function without the "file_name.ext" param

__Return value__:
An array of [FileDescriptor](https://support.wixmp.com/en/article/file-management#filedescriptor-object) objects.
```java
File file = new File(...);
FileDescriptor[] files = mediaPlatform.fileManager().uploadFile("/destination_path/file_name.ext", "mime_type", "file_name.ext", file, "private||public");
```

### Get Upload URL

Generates a signed URL and token, required for uploading from the browser.

```java
GetUploadUrlResponse getUploadUrlResponse = mediaPlatform.fileManager().getUploadUrl();
```

### File Import
[File Import API documentation](https://support.wixmp.com/en/article/file-import)
Creates a [job](https://support.wixmp.com/en/article/jobs) that imports an existing file from an external url.

```java
ImportFileRequest importFileRequest = new ImportFileRequest()
        .setSourceUrl("from URL")
        .setDestination(new Destination()
                .setPath("/to/file.ext")
                .setAcl("private||public"));
Job job = mediaPlatform.fileManager().importFile(importFileRequest);
```

### Download a Secure File
[File Download API documentation](https://support.wixmp.com/en/article/file-download)

File access can be restricted by setting the acl to 'private'. In order to access these files, a secure URL must be generated.

```java
String signedUrl = mediaPlatform.fileDownloader().getDownloadUrl("path/to/file.ext");
```

### List Files in a Directory
Retrieves a list of the files in the directory by sending a GET request.
__Parameters__: `path` (string) - the path of the required directory.
__Return value__: the payload of the http response.
```java
ListFilesResponse response = mediaPlatform.fileManager().listFiles("directory path");
```

### Get File Descriptor
[Get File API Documentation](https://support.wixmp.com/en/article/file-management#get-file)
Retrieves a file's fileDescriptor object by path.
__Parameters__: `path` (string) - the path of the required file.
__Return value__: [fileDescriptor](https://support.wixmp.com/en/article/file-management#filedescriptor-object) object.
```java
FileDescriptor fileDescriptor = mediaPlatform.fileManager().getFile("/path/");
```

### Get File Metadata
[File Metadata API Documentation](https://support.wixmp.com/en/article/file-metadata)
Retrieves a file's metadata object, either by file ID or by path.
__Parameters__: `fileId` (string) - the file ID of the required file.
__Return value__: file metadata object.
```java
FileMetadata fileMetadata = mediaPlatform.fileManager().getFileMetadataById("file id");
```
__Parameters__: `path` (string) - the path of the required file.
__Return value__: file metadata object.
```java
FileMetadata fileMetadata = mediaPlatform.fileManager().getFileMetadataByPath("path");
```

### Delete File
[Delete File API Documentation](https://support.wixmp.com/en/article/file-management#delete-file)
Deletes a file by either file ID or path.
__Parameters__: `fileId` (string) - the file ID of the file to be deleted.
__Return value__: null.
```java
mediaPlatform.fileManager().deleteFileById("file id");
```

__Parameters__: path (string) - the path of the file to be deleted.
__Return value__: null.
```java
mediaPlatform.fileManager().deleteFileByPath("path");
```

## Archive Functions
[Archive API Documentation](https://support.wixmp.com/en/article/archive-service)

### Archive Creation

In order to archive several files in WixMP to a zip|tar etc. file, create a createArchiveRequest, and than pass it as a parameter to the createArchive method:

```java
CreateArchiveRequest createArchiveRequest = new CreateArchiveRequest()
        .setSource(new Source().setFileId("file id"))
        .setDestination(new Destination().setPath("/fish/file.zip").setAcl("private"))
        .setArchiveType("zip");
Job job = mediaPlatform.archiveManager.createArchive(createArchiveRequest);
```

### Archive Extraction

Instead of uploading multiple files separately, you can upload a single zip file
and later extract its content to a destination directory.
Create an extractArchiveRequest, and than pass it as a parameter to the createArchive method:

```java
ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
        .setSource(new Source().setFileId("file id"))
        .setDestination(new Destination().setAcl("public").setDirectory("/demo/extracted"));
Job job = mediaPlatform.archiveManager().extractArchive(extractArchiveRequest);
```

## Jobs

The [Jobs API][jobs-api] forms the basis for all long running asynchronous operations in the platform.

### Job Lifecycle

A job is created by a service that performs a long running operation, such as video transcode or file import.

1. When a job is created, it is queued for execution, and its status is initially set to 'pending'.
2. Once the job execution commences, the job status is updated to 'working'.
3. On job completion, the job status is updated to either 'success' or 'error', and the 'result' property is populated (prior to the job's completion, the 'result' field is null).

### Get Job
__Parameters__: `jobId` (string) - the ID of the requested job.
__Return value__: [job](https://support.wixmp.com/en/article/jobs#the-job-object) object.
```java
job = mediaPlatform.jobManager().getJob("jobId");
```
### Get Job Group
__Parameters__: jobGroupId (string) - the ID of the requested job group.
__Return value__: An array of [job](https://support.wixmp.com/en/article/jobs#the-job-object) objects.
```java
Job[] jobGroup = mediaPlatform.jobManager().getJobGroup("jobGroupId");
```

## Image Service
[Image Service API Documentation](https://support.wixmp.com/en/article/image-service-3835799)
The SDK enables generating image URLs and manipulating images.

###Image Class
An istance of this class is required for all image-related actions.
You can create an instance of this class by passing one of the following as the parameter:
- an [image FileMetadata](https://support.wixmp.com/en/article/file-metadata#image-metadata) object
- a [FileDescriptor](https://support.wixmp.com/en/article/file-management#filedescriptor-object) object
- the image's url (string)
```java
Image image1 = new Image(fileMetadata);
Image image2 = new Image(fileDescriptor);
Image image3 = new Image("url");
```
The Image class has the following methods:

```java
Image image = new Image(fileDescriptor).setHost("images service host");

image.crop(width, height, x, y, scale);

String url = image.toUrl(); 
```


## Transcoding

[Transcode API Documentation](https://support.wixmp.com/en/article/video-transcoding-5054232)
After uploading a video to your project, you can have it transcoded in order to enable adaptive video playback on any browser or internet-connected device.

To initiate a transcode job, create a transcodeRequest, and pass it as a parameter to the transcodeVideo method:

```java
TranscodeRequest transcodeRequest = new TranscodeRequest()
    .addSource(new Source().setPath("/demo/video.mp4") )
    .addSpecification( new TranscodeSpecification()
        .setDestination(new Destination()
            .setDirectory("/demo/encodes/")
            .setAcl("public"))
        .setQualityRange( new QualityRange()
            .setMinimum("240p")
            .setMaximum("1440p")));

TranscodeJobResult response = mediaPlatform.transcodeManager().transcodeVideo(transcodeRequest);

System.out.println(gson.toJson(response));
```

## Reporting Issues

Please use [the issue tracker](https://github.com/wix/media-platform-java-sdk/issues) to report issues related to this library, or to the Wix Media Platform API in general.

## License

We use a custom license, see [LICENSE.md](LICENSE.md).

## About Wix

[Wix.com][wix-url] is a leading cloud-based web development platform with more than 100 million registered users worldwide. 
Our powerful technology makes it simple for everyone to create a beautiful website and grow their business online.

## About Google Cloud Platform

[Google Cloud Platform](https://cloud.google.com/) enables developers to build, test and deploy applications on Google’s reliable infrastructure.
It offers computing, storage and application services for web, mobile and backend solutions.

[wix-url]: https://www.wix.com/
[wixmp-url]: https://gcp.wixmp.com/
[mvn-image]: https://img.shields.io/maven-central/v/com.wix/media-platform-java-sdk.svg
[mvn-url]: https://mvnrepository.com/artifact/com.wix/media-platform-java-sdk
[npm-url]: https://npmjs.org/package/media-platform-js-sdk
[travis-image]: https://travis-ci.org/wix/media-platform-java-sdk.svg?branch=master
[travis-url]: https://travis-ci.org/wix/media-platform-java-sdk
[org-and-project-start]: https://support.wixmp.com/en/article/creating-your-organization-and-project
[application-start]: https://support.wixmp.com/en/article/creating-your-first-application
[jobs-api]: https://support.wixmp.com/en/article/jobs
