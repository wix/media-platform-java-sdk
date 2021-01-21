load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_jetbrains_kotlin_kotlin_stdlib",
      artifact = "org.jetbrains.kotlin:kotlin-stdlib:1.3.71",
      artifact_sha256 = "5ace22b102a96425e4ac44e0558b927f3857b56a33cbc289cf1b70aee645e6a7",
      deps = [
          "@org_jetbrains_annotations",
          "@org_jetbrains_kotlin_kotlin_stdlib_common"
      ],
  )


  import_external(
      name = "org_jetbrains_kotlin_kotlin_stdlib_common",
      artifact = "org.jetbrains.kotlin:kotlin-stdlib-common:1.3.70",
      artifact_sha256 = "6e2377cfc4898f2fb24429951b133b570b250e3f860a8458b2a1f8a63cf53a50",
  )
