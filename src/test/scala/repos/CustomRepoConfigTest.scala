package repos

import org.scalatest.FunSuite


class CustomRepoConfigTest extends FunSuite {

  test("full path to repos included in additional repos") {
    val customRepoConfig = CustomRepoConfig.apply(
      Array("path to config file", "/apps/fuse_esb/xtrac-jboss-poc-1.1/installedApps/xtrac-repos", "repo1", "repo2")
    )
    val additionalConfig = customRepoConfig.addedConfig

    assert(
      additionalConfig.indexOf("/apps/fuse_esb/xtrac-jboss-poc-1.1/installedApps/xtrac-repos/repo1")
        != -1
    )

    assert(
      additionalConfig.indexOf("/apps/fuse_esb/xtrac-jboss-poc-1.1/installedApps/xtrac-repos/repo2")
        != -1
    )
  }
}
