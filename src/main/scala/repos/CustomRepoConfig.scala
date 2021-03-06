package repos

trait RepoSpec
case object DefaultRepositories extends RepoSpec
case object Repositories extends RepoSpec

class CustomRepoConfig private (configPath: String, basedir: String, repos: List[String]) {
  def dumpInputs : Unit = {
    println("basedir:" + basedir)
    repos.foreach(println)
  }

  def addedConfig: String = {
    def addedConfigR(builder: StringBuilder, theRepos: List[String]) : String = {

      builder.append(",\\\n\t")
      builder.append(basedir + "/" + theRepos.head + "@snapshots@id=" + theRepos.head)

      theRepos match {
        case r :: Nil => builder.toString
        case h :: t => addedConfigR(builder, t)
      }
    }

    addedConfigR(new StringBuilder(), repos)
  }

  def addReposToConfigInFile(reposToAdd: String, repoType: RepoSpec) : String = {
    import props.PropertyMapFactory.propertyMapFromFile
    import collection.JavaConverters.enumerationAsScalaIteratorConverter

    val builder = new StringBuilder

    val propMap = propertyMapFromFile(configPath)
     propMap.foreach(keyvalpair => {
       val (k,v)  = keyvalpair
       k match {
         case "org.ops4j.pax.url.mvn.defaultRepositories" => {
           if(repoType == DefaultRepositories) builder.append(k + "=" + v + reposToAdd + '\n')
         }
         case "org.ops4j.pax.url.mvn.repositories" => {
           if(repoType == Repositories) builder.append(k + "=" + v + reposToAdd + '\n')
         }

         case _ => builder.append(k + "=" + v + '\n')
       }
     })
    builder.toString
  }

  def emitFabLocalReposConfig = {

  }
}

object CustomRepoConfig {
  def apply(args: Array[String]) : CustomRepoConfig = {
    args.toList match {
      case path :: basedir :: repos => new CustomRepoConfig(path, basedir, repos)
      case _ => throw new IllegalArgumentException
    }

  }
}
