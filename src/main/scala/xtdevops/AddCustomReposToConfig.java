package xtdevops;


import repos.CustomRepoConfig;
import repos.CustomRepoConfig$;

public class AddCustomReposToConfig {
    public static void main(String[] args) {
        checkArgs(args);
        CustomRepoConfig config = CustomRepoConfig$.MODULE$.apply(args);
        String additionalConfig = config.addedConfig();
        System.out.println(config.addReposToConfigInFile(additionalConfig));
    }

    private static void checkArgs(String[] args) {
        if(args.length < 3) {
            System.err.println(
                    String.format("Usage: java %s <path to config file> <xtrac-repos root path> <repo1 dir> <repo2 dir> ... <repo n dir>",
                            AddCustomReposToConfig.class.getCanonicalName())
            );

            System.exit(1);
        }
    }
}
