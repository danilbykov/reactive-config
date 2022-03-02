val publishVersion = "0.3.0"

ThisBuild / publishTo := {
  val nexus = "https://nexus.tcsbank.ru/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/tapi-snapshots")
  else
    Some("releases" at nexus + "content/repositories/tapi-releases")
}

ThisBuild / version := {
  val branch = git.gitCurrentBranch.value
  if (branch == "master") {
    publishVersion
  } else {
    s"${publishVersion}-$branch-SNAPSHOT"
  }
}

enablePlugins(GitVersioning)

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
