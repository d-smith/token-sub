package xtdevops;


import repos.CustomRepoConfig$;
import repos.RepoGAV$;

public class XMLPath2GAV {
    public static void main(String[] args) {
        checkArgs(args);
        System.out.println(
                RepoGAV$.MODULE$.xmlpath2GAV(args[0])
        );
    }

    private static void checkArgs(String[] args) {
        if(args.length != 1) {
            System.err.println(
                    String.format("Usage: java %s <path to xml>",
                            ReplaceFeatureDescTokens.class.getCanonicalName())
            );

            System.exit(1);
        }
    }

}
