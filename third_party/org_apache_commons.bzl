load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_apache_commons_commons_lang3",
      artifact = "org.apache.commons:commons-lang3:3.10",
      artifact_sha256 = "28968ae55fff465494083aeba856f8824c34902329882bf61e77246a91e25aa9",
  )
