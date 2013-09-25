package repos


class FeatureDescsFromCustomRepos private (configPath: String, repos: List[String]) {

  def reposToAdd() : String = {
    "," + repos.mkString(",")
  }

  def addReposToRepolist() : String = {
    import props.PropertyMapFactory.propertyMapFromFile
    import collection.JavaConverters.enumerationAsScalaIteratorConverter

    val builder = new StringBuilder

    val propMap = propertyMapFromFile(configPath)
    propMap.foreach(keyvalpair => {
      val (k,v)  = keyvalpair
      k match {
        case "featuresRepositories" => {
          builder.append(k + "=" + v + reposToAdd + '\n')
        }
        case _ => builder.append(k + "=" + v + '\n')
      }
    })

    builder.toString
  }

}

object FeatureDescsFromCustomRepos {
  def apply(args: Array[String]) : FeatureDescsFromCustomRepos = {
    args.toList match {
      case configPath :: repos => new FeatureDescsFromCustomRepos(configPath, repos)
      case _ => throw new IllegalArgumentException
    }
  }
}
