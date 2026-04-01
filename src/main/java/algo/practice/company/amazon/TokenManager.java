package algo.practice.company.amazon;

import java.util.PriorityQueue;
import java.util.Queue;

public class TokenManager {

    Queue<Integer> tokenQueue;
    int tokenCount;
    int generatedTokenNum = 0;

    TokenManager(int tokenCount){
        tokenQueue = new PriorityQueue<>();
        this.tokenCount = tokenCount;
    }

    int getToken(){
        if(!tokenQueue.isEmpty()){
            return tokenQueue.poll();
        }
        if(generatedTokenNum < tokenCount){
            generatedTokenNum++;
            return generatedTokenNum;
        }
        return -1;
    }

    void putToken(int tokenNum) {
        tokenQueue.add(tokenNum);
    }

}

