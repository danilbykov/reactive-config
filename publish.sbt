enablePlugins(GitVersioning)

git.useGitDescribe := true

organization in ThisBuild := "com.github.fit51"
homepage in ThisBuild := Some(url("https://github.com/fit51/reactive-config"))

scmInfo in ThisBuild := Some(
  ScmInfo(url("https://github.com/fit51/reactive-config"), "git@github.com:fit51/reactive-config.git")
)
developers in ThisBuild := List(
  Developer("fit51", "Pavel Kondratyuk", "fit511@yandex.ru", url("https://github.com/fit51")),
  Developer("danilbykov", "Danil Bykov", "d.bykov@tinkoff.ru", url("https://github.com/danilbykov")),
  Developer("realvikr", "Viktor Sinchikov", "v.sinchikov@tinkoff.ru", url("https://github.com/realvikr"))
)
licenses in ThisBuild += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle in ThisBuild := true

publishTo in ThisBuild := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

pomIncludeRepository in ThisBuild := { _ => false }

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)

updateOptions in ThisBuild := updateOptions.value.withGigahorse(false)
