package javaparactice.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysteryBox<T> {
    private static Logger logger = LoggerFactory.getLogger(MysteryBox.class);
    private List<T> mysteryBoxList;
    private static Random random = new Random();

    public MysteryBox(List<T> mysteryBoxList) {
        this.mysteryBoxList = mysteryBoxList;
    }

    public void addToMysteryBox(T item) {
        mysteryBoxList.add(item);
    }

    public List<T> getMysteryBoxList() {
        return mysteryBoxList;
    }

    public void setMysteryBoxList(List<T> mysteryBoxList) {
        this.mysteryBoxList = mysteryBoxList;
    }

    public T getRandomItemFromMysteryBox() {
        int randomIndex = random.nextInt(mysteryBoxList.size());
        return mysteryBoxList.remove(randomIndex);
    }

    public static void main(String[] args) {
        //List<Integer> cashList =  Arrays.asList(4,1,3,6,7,3,6);
        List<Integer> cashList = new ArrayList<>();
        cashList.add(4);
        cashList.add(1);
        cashList.add(3);
        cashList.add(6);
        cashList.add(7);
        MysteryBox<Integer> cashMysteryBox = new MysteryBox<>(cashList);
        while (!cashMysteryBox.getMysteryBoxList().isEmpty()) {
            Integer cashPrize = cashMysteryBox.getRandomItemFromMysteryBox();
            logger.info("Got a cash Prize of {}", cashPrize);
        }


        //List<Integer> cashList =  Arrays.asList(4,1,3,6,7,3,6);
        List<String> nameList = new ArrayList<>();
        nameList.add("ashish");
        nameList.add("anshu");
        nameList.add("billu");
        nameList.add("juli");
        nameList.add("kanu");
        MysteryBox<String> nameMysteryBox = new MysteryBox<>(nameList);
        while (!nameMysteryBox.getMysteryBoxList().isEmpty()) {
            String name = nameMysteryBox.getRandomItemFromMysteryBox();
            logger.info("Lucky winner for this round is {}", name);
        }
    }
}
