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

```java
MediaPlatform mediaPlatform = new MediaPlatform(
    "<project host as appears in the application page>",
    "<application id as appears in the application page>",
    "<shared secret as appears in the application page>"
);
```

## File Upload
[File Upload API Documentation](https://support.wixmp.com/en/article/file-management#upload-file)

```java
File file = new File(...);
FileDescriptor[] files = mediaPlatform.fileManager().uploadFile("/destination_path/file_name.ext", "mime_type", "file_name.ext", file, "private||public");
```

### Get Upload URL

Generates a signed URL and token, required for uploading from the browser

```java
GetUploadUrlResponse getUploadUrlResponse = mediaPlatform.fileManager().getUploadUrl();
```

## File Import
[File Import API documentation](https://support.wixmp.com/en/article/file-import)
```java
ImportFileRequest importFileRequest = new ImportFileRequest()
        .setSourceUrl("from URL")
        .setDestination(new Destination()
                .setPath("/to/file.ext")
                .setAcl("private||public"));
Job job = mediaPlatform.fileManager().importFile(importFileRequest);
```

## Download a Secure File
[File Download API documentation](https://support.wixmp.com/en/article/file-download)

File access can be restricted by setting the acl to 'private'. In order to access these files, a secure URL must be generated

```java
String signedUrl = mediaPlatform.fileDownloader().getDownloadUrl("path/to/file.ext");
```

## Jobs

The [Jobs API][jobs-api] forms the basis for all long running asynchronous operations in the platform.

### Job Lifecycle

A job is created by a service that performs a long running operation, such as video transcode or file import.

1. When a job is created, it is queued for execution, and its status is initially set to 'pending'.
2. Once the job execution commences, the job status is updated to 'working'.
3. On job completion, the job status is updated to either 'success' or 'error', and the 'result' property is populated (prior to the job's completion, the 'result' field is null).

### Get Job

```java
job = mediaPlatform.jobManager().getJob("jobId");
```
### Get Job Group

```java
jobGroup = mediaPlatform.jobManager().getJobGroup("jobGroupId");
```

## Image Consumption

The SDK provides a programmatic facility to generate image URLs

```java
Image image = new Image(fileDescriptor).setHost("images service host");

image.crop(width, height, x, y, scale);

String url = image.toUrl(); 
```

## File Metadata & Management
[File Management API Documentation](https://support.wixmp.com/en/article/file-management)

[File Metadata API Documentation](https://support.wixmp.com/en/article/file-metadata)

Wix Media Platform provides a comprehensive set of APIs tailored for management of previously uploaded files.

### List Files in a Directory

```java
ListFilesResponse response = mediaPlatform.fileManager().listFiles("directory path");
```

### Get File Metadata

```java
FileMetadata fileMetadata = mediaPlatform.fileManager().getFileMetadataById("file id");
```

### Delete File

```java
mediaPlatform.fileManager().deleteFileById("file id");
```

## Archive Functions
[Archive API Documentation](https://support.wixmp.com/en/article/archive-service)

### Archive Creation

It is possible to create an archive from several files

```java
ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
        .setSource(new Source().setFileId("file id"))
        .setDestination(new Destination().setAcl("public").setPath("/demo/archive.zip").setAcl("private")).setArchiveType("zip");
Job job = mediaPlatform.archiveManager().extractArchive(extractArchiveRequest);
```

### Archive Extraction

Instead of uploading numerous files one by one, it is possible to upload a single zip file
and order the Media Platform to extract its content to a destination directory. 

```java
ExtractArchiveRequest extractArchiveRequest = new ExtractArchiveRequest()
        .setSource(new Source().setFileId("file id"))
        .setDestination(new Destination().setAcl("public").setDirectory("/demo/extracted"));
Job job = mediaPlatform.archiveManager().extractArchive(extractArchiveRequest);
```

## Transcoding

[Transcode API Documentation](https://support.wixmp.com/en/article/video-transcoding-5054232)

Initiate a transcode request

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
