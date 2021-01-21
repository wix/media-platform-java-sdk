load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "commons_io_commons_io",
      artifact = "commons-io:commons-io:2.2",
      artifact_sha256 = "675f60bd11a82d481736591fe4054c66471fa5463d45616652fd71585792ba87",
  )
