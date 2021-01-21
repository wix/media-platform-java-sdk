load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "commons_fileupload_commons_fileupload",
      artifact = "commons-fileupload:commons-fileupload:1.4",
      artifact_sha256 = "a4ec02336f49253ea50405698b79232b8c5cbf02cb60df3a674d77a749a1def7",
      deps = [
          "@commons_io_commons_io"
      ],
  )
