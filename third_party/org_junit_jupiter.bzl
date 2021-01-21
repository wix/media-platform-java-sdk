load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_junit_jupiter_junit_jupiter_api",
      artifact = "org.junit.jupiter:junit-jupiter-api:5.6.1",
      artifact_sha256 = "ea3f1822effdd00ad9cd133e8b7b785f07bb45f17d8efaf58e1746b358e62e7b",
      deps = [
          "@org_apiguardian_apiguardian_api",
          "@org_junit_platform_junit_platform_commons",
          "@org_opentest4j_opentest4j"
      ],
  )


  import_external(
      name = "org_junit_jupiter_junit_jupiter_engine",
      artifact = "org.junit.jupiter:junit-jupiter-engine:5.6.1",
      artifact_sha256 = "dc106781a640fcd8c6d301a90569cfc220791d6a96d60504e6c1c3013486b416",
      deps = [
          "@org_apiguardian_apiguardian_api",
          "@org_junit_jupiter_junit_jupiter_api",
          "@org_junit_platform_junit_platform_engine"
      ],
  )


  import_external(
      name = "org_junit_jupiter_junit_jupiter_params",
      artifact = "org.junit.jupiter:junit-jupiter-params:5.6.1",
      artifact_sha256 = "adfc820849df2bbf7657336a93b96a6c34900f804b036837a5bc167c779d4636",
      deps = [
          "@org_apiguardian_apiguardian_api",
          "@org_junit_jupiter_junit_jupiter_api"
      ],
  )
