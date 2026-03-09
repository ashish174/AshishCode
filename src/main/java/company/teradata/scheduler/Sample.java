package company.teradata.scheduler;

public class Sample {


    /**
     * Design and Implement a concurrent job scheduler that executes tasks with dependencies.
     *
     *
     task_id
     dependencies
     priority
     max_retries
     timeout

     A
     ├── B
     ├── C
     │   └── E
     └── D
     └── F

     scheduler = TaskScheduler(max_workers=3)

     scheduler.add_task("A", A, priority=5)
     scheduler.add_task("B", B, ["A"], priority=10)
     scheduler.add_task("C", C, ["A"])
     scheduler.add_task("D", D, ["B","C"])

     scheduler.run()
     scheduler.wait()

     */

/*

    public class Main {
        public static void main(String[] args) {




        }



        public class Scheduler{


            List<Worker> workers;
            Queue<Task> taskQueue;
            Map<Task, List<Task>> prerequisiteTasks;

            public Scheduler(){
                //max Heap
                taskQueue = new PriorityQueue<Task>((t1,t2) -> t2.prioty-t1.priority);
                prerequisiteTasks = new HashMap<>();
                workers = new ArrayList<>(10);
            }

            public enqueueTask(Task task){
                taskQueue.add(task);
            }

            public dequeueTask(Task task){
                if(taskQueue.isEmpty()){
                    System.out.println("Queue underflow");
                }
                taskQueue.add(task);
            }

            public startScheduler(){
                while(true){

                }

            }


            //A -> B, C
            //B -> D
            public List<Task> findTopologicalOrder(Map<Task, List<Task>> prerequisiteTasks) {
                Map<TaskId, Integer> taskVisitStatus = new HashMap<>();
                for(Task task : prerequisiteTasks.keySet()){
                    if(taskVisitStatus.contains(task.id)){
                        findTopologicalOrderByDFS(task, prerequisiteTasks, taskVisitStatus);
                    }
                }

            }

            public List<Task> findTopologicalOrderByDFS(Task task, Map<Task, List<Task>> prerequisiteTasks, Map<TaskId, int[]> taskVisitStatus) {
                taskVisitStatus.put(childTask.id, 1);
                if(taskVisitStatus.get(task.id)==0){
                    for(Task childTask : prerequisiteTasks.get(task)){
                        if(taskVisitStatus.get(childTask.id)==1){
                            System.out.println("Found a cycle");
                            //set some flag - Not possible
                        }
                        if(taskVisitStatus.get(childTask.id)==0){
                            findTopologicalOrderByDFS(childTask, prerequisiteTasks, taskVisitStatus);
                        }
                    }
                    taskVisitStatus.put(childTask.id, 2);
                }
            }





        }

        public class Worker{
            public void execute(Task task){

            }
        }




        public class Task implements Runnable{
            private int id;
            private int priority;
            private List<Task> prerequisiteTasks;

            @overrirde
            public void run(){
                System.out.println("Executing thread :- "+this.id);
            }


            A -> [B, C]
            B -> D,

        }

    }
*/

}
