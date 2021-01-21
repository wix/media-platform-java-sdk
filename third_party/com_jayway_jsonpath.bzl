load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_jayway_jsonpath_json_path",
      artifact = "com.jayway.jsonpath:json-path:2.4.0",
      artifact_sha256 = "60441c74fb64e5a480070f86a604941927aaf684e2b513d780fb7a38fb4c5639",
      deps = [
          "@net_minidev_json_smart",
          "@org_slf4j_slf4j_api"
      ],
    # EXCLUDES org.ow2.asm:asm
  )
