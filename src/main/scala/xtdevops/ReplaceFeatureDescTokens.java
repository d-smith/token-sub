package xtdevops;

import props.*;
import feature.*;

import scala.collection.mutable.HashMap;

public class ReplaceFeatureDescTokens {
    public static void main(String[] args) {
        checkArgs(args);
        HashMap<String,String> propMap =
                PropertyMapFactory$.MODULE$.propertyMapFromFile(args[0]);
        String updated =
                FeatureTokenReplacement$.MODULE$.replaceTokenizedParamsFromMap(propMap, args[1]);
        System.out.println(updated);
    }

    private static void checkArgs(String[] args) {
        if(args.length != 2) {
            System.err.println(
                    String.format("Usage: java %s <hostmap file path> <feature desc file path>",
                            ReplaceFeatureDescTokens.class.getCanonicalName())
            );

            System.exit(1);
        }
    }
}
