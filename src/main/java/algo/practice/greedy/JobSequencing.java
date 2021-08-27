package algo.practice.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Each Job take single unit time but has a deadline to be completed by, so as to get profit.
 * <p>
 * 1) Sort all jobs in decreasing order of profit.
 * 2) Iterate on jobs in decreasing order of profit.For each job , do the following : *
 * For each job find an empty time slot from deadline to 0.
 * If found empty slot put the job in the slot and mark this slot filled.
 */
public class JobSequencing {
    public static Logger logger = LoggerFactory.getLogger(JobSequencing.class);

    static class Job implements Comparable<Job> {
        char id;
        int deadLine;
        int profit;

        public Job(char id, int deadLine, int profit) {
            this.id = id;
            this.deadLine = deadLine;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            return o.profit - this.profit;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "id=" + id +
                    ", deadLine=" + deadLine +
                    ", profit=" + profit +
                    '}';
        }
    }

    public static void printJobSchedulingByMaxProfit(List<Job> jobList) {
        char[] slotList = new char[10];
        List<Job> sortedJobsByProfitDesc = new ArrayList<>(jobList);
        Collections.sort(sortedJobsByProfitDesc);
        for (Job job : sortedJobsByProfitDesc) {
            int slot = findSlot(slotList, job);
            if (slot != -1) {
                logger.info("Slot for {} - {}", job, slot);
            }
        }
    }

    private static int findSlot(char[] slotList, Job job) {
        int i = -1;
        for (i = job.deadLine; i > 0; i--) {
            if (slotList[i] == 0) {
                slotList[i] = job.id;
                break;
            }
        }
        return i > 0 ? i : -1;
    }

    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>();

        jobList.add(new Job('a', 2, 100));
        jobList.add(new Job('b', 1, 19));
        jobList.add(new Job('c', 2, 27));
        jobList.add(new Job('d', 1, 25));
        jobList.add(new Job('e', 3, 15));

        logger.info("JobList : {}", jobList);

        printJobSchedulingByMaxProfit(jobList);
    }
}
