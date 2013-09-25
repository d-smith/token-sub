package repos


object RepoGAV {

  def xmlpath2GAV(path: String): String = {
    val pathParts = path.split("/").tail
    val noPathParts = pathParts.length
    val version = pathParts(noPathParts - 2)
    val artifactId = pathParts(noPathParts - 3)
    val xmlPart = pathParts(noPathParts - 1)
    val xmlCtx = xmlPart.split(version)
    val xmlSpec = xmlCtx.tail(0).substring(1).split("\\.").reverse.mkString("/")
    val groupId = pathParts.slice(1, noPathParts - 3).mkString(".")
    Array("mvn:" + groupId, artifactId, version, xmlSpec).mkString("/")
  }

}
