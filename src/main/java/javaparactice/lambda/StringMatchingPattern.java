package javaparactice.lambda;

public class StringMatchingPattern {

    public static void main(String[] args){
        // Assume Ad is ad2
        String[] subStrings = new String[]{"ad2", "ad_2", "ad-2"};
        String strWithAD = "inframon_dummy_app-dev-dpapp-iad-ad-2-20231011";
        int indexOfAD = -1;
        String matchingPattern = null;
        String aliasForAD1;
        String aliasForAD3;
        // ad2
        if(strWithAD.indexOf(subStrings[0]) >= 0 ){
            indexOfAD = strWithAD.indexOf(subStrings[0]);
            matchingPattern = subStrings[0];
        }
        if(strWithAD.indexOf(subStrings[1]) >= 0 ){
            indexOfAD = strWithAD.indexOf(subStrings[1]);
            matchingPattern = subStrings[1];
        }
        if(strWithAD.indexOf(subStrings[2]) >= 0 ){
            indexOfAD = strWithAD.indexOf(subStrings[2]);
            matchingPattern = subStrings[2];
        }

        System.out.println("Matching Pattern: "+matchingPattern+ " , Index: "+indexOfAD);

        aliasForAD1 = strWithAD.replace(matchingPattern, matchingPattern.replace("2", "1"));
        aliasForAD3 = strWithAD.replace(matchingPattern, matchingPattern.replace("2", "3"));
        System.out.println(strWithAD);
        System.out.println(aliasForAD1);
        System.out.println(aliasForAD3);


        strWithAD = strWithAD.replaceAll("_", "").replaceAll("-", "");
        System.out.println(strWithAD.indexOf("ad"));
        System.out.println(strWithAD.substring(strWithAD.indexOf("ad")));
        //boolean IsADsubStringPresent = strWithAD.
    }
}
