package dev.aman;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Set<Task> annTasks=TaskData.getTasks("ann");
        sortAndPrint("Ann's tasks",annTasks);

        Comparator<Task> sortByPriority=Comparator.comparing(Task::getPriority);
        Set<Task> annTasks2=TaskData.getTasks("ann");
        sortAndPrint("Ann's tasks",annTasks,sortByPriority);

        Set<Task> bobTasks=TaskData.getTasks("bob");
        Set<Task> carolTasks=TaskData.getTasks("carol");

        List<Set<Task>> listOfAssignedTasks=List.of(annTasks,bobTasks,carolTasks);

        Set<Task> setOfAllAssginedTasks=getUnion(listOfAssignedTasks);

        sortAndPrint("All tasks assigned ",setOfAllAssginedTasks);


        Set<Task> everyTask=getUnion(List.of(tasks,setOfAllAssginedTasks));//All tasks assigned and non-assigned
        sortAndPrint("All tasks assigned and non assigned",everyTask);




    }

    private static void sortAndPrint(String header, Collection<Task> collection
    ) {
        sortAndPrint(header, collection, null);
    }


    public static void sortAndPrint(String header,
                                    Collection<Task> collection,
                                    Comparator<Task> sorter) {
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);

        list.sort(sorter);

        list.forEach(System.out::println);

    }


    private static Set<Task> getUnion(List<Set<Task>> sets){ //Distinct collection of all tasks
        Set<Task> union=new HashSet<>();//we don't want to mutate the existing data
        for (var taskSet:sets
             ) {
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a,Set<Task> b){
        Set<Task> intersect=new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDiff(Set<Task> a,Set<Task> b){
        Set<Task> diff=new HashSet<>(a);
        diff.removeAll(b);
        return diff;
    }


}