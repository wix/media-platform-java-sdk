load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_apache_httpcomponents_httpclient",
      artifact = "org.apache.httpcomponents:httpclient:4.5.6",
      artifact_sha256 = "c03f813195e7a80e3608d0ddd8da80b21696a4c92a6a2298865bf149071551c7",
      deps = [
          "@commons_codec_commons_codec",
          "@commons_logging_commons_logging",
          "@org_apache_httpcomponents_httpcore"
      ],
  )


  import_external(
      name = "org_apache_httpcomponents_httpcore",
      artifact = "org.apache.httpcomponents:httpcore:4.4.10",
      artifact_sha256 = "78ba1096561957db1b55200a159b648876430342d15d461277e62360da19f6fd",
  )
