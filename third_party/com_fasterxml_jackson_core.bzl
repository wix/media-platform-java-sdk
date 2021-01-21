load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_fasterxml_jackson_core_jackson_annotations",
      artifact = "com.fasterxml.jackson.core:jackson-annotations:2.10.3",
      artifact_sha256 = "49dfdc4cfa46d165ecfed630ba164b6641d59d5fe1aa698a19c13f966d3f13cf",
  )


  import_external(
      name = "com_fasterxml_jackson_core_jackson_core",
      artifact = "com.fasterxml.jackson.core:jackson-core:2.10.3",
      artifact_sha256 = "fb185f7e6ecba1e2b4803788d278faa023312ca6d3109b2fa146d9e0435a9494",
  )


  import_external(
      name = "com_fasterxml_jackson_core_jackson_databind",
      artifact = "com.fasterxml.jackson.core:jackson-databind:2.10.3",
      artifact_sha256 = "50eec40443f387be50a409186165298aaadbb6c4d4826d319720e245714600d2",
      deps = [
          "@com_fasterxml_jackson_core_jackson_annotations",
          "@com_fasterxml_jackson_core_jackson_core"
      ],
  )
