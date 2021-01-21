load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "net_bytebuddy_byte_buddy",
      artifact = "net.bytebuddy:byte-buddy:1.10.5",
      artifact_sha256 = "3c9c603970bb9d68572c1aa29e9ae6b477d602922977a04bfa5f3b5465d7d1f4",
  )


  import_external(
      name = "net_bytebuddy_byte_buddy_agent",
      artifact = "net.bytebuddy:byte-buddy-agent:1.10.5",
      artifact_sha256 = "290c9930965ef5810ddb15baf3b3647ce952f40fa2f0af82d5f669e04ba87e5b",
  )
