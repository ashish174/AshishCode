package systemdesign;

class TraversalOnClock {

    /*
     * Complete the 'getTime' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static long getTime(String s) {
        // Write your code here
        char[] chars = s.toCharArray();
        long totalTime = 0;
        char lastVisitedChar = 'A';
        for(char ch : s.toCharArray()){
            int timeToVisitClockwise = Math.abs((int)ch - (int) lastVisitedChar);
            int timeToVisitAntiClockWise = 26-timeToVisitClockwise;
            int minTimeToVisit = Math.min(timeToVisitClockwise, timeToVisitAntiClockWise);
            totalTime+=minTimeToVisit;
            lastVisitedChar = ch;
        }
        return totalTime;
    }

    public static void main(String[] args) {
        System.out.println(getTime("AZGB"));
    }
}