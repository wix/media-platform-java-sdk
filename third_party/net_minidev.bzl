load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "net_minidev_accessors_smart",
      artifact = "net.minidev:accessors-smart:1.2",
      artifact_sha256 = "0c7c265d62fc007124dc32b91336e9c4272651d629bc5fa1a4e4e3bc758eb2e4",
  )


  import_external(
      name = "net_minidev_json_smart",
      artifact = "net.minidev:json-smart:2.3",
      artifact_sha256 = "903f48c8aa4c3f6426440b8d32de89fa1dc23b1169abde25e4e1d068aa67708b",
      deps = [
          "@net_minidev_accessors_smart"
      ],
  )
