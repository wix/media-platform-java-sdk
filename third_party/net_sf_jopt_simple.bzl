load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "net_sf_jopt_simple_jopt_simple",
      artifact = "net.sf.jopt-simple:jopt-simple:5.0.3",
      artifact_sha256 = "6f45c00908265947c39221035250024f2caec9a15c1c8cf553ebeecee289f342",
  )
