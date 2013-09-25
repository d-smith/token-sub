package xtdevops;


import repos.FeatureDescsFromCustomRepos;
import repos.FeatureDescsFromCustomRepos$;

public class UpdateKarafFeatureRepos {
    public static void main(String[] args) {
        checkArgs(args);
        FeatureDescsFromCustomRepos featureDescs = FeatureDescsFromCustomRepos$.MODULE$.apply(args);
        System.out.println(featureDescs.addReposToRepolist());
    }

    private static void checkArgs(String[] args) {
        if(args.length < 2) {
            System.err.println(
                    String.format("Usage: java %s <path to karaf features config file>  <repo1 gav> <repo2 gav> ... <repo n gav>",
                            AddCustomReposToConfig.class.getCanonicalName())
            );

            System.exit(1);
        }
    }

}
