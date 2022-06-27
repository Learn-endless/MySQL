package Test;

import java.util.*;
public class Demo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        int[] array = new int[number*3];
        for(int i = 0; i < number*3; i++){
            array[i] = sc.nextInt();
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int[] arr = new int[3];
        boolean[] flag = new boolean[array.length];
        func(array,0,queue,flag,arr);

        long sum = 0;
        for(int i = 0; i < number; i++){
            sum += queue.poll();
        }
        System.out.println(sum);
    }

    private static void func(int[] array, int depth, PriorityQueue<Integer> queue, boolean[] flag, int[] arr) {
        if(depth == array.length / 3){
            int mid = findMid(arr[0], arr[1], arr[2]);
            queue.offer(mid);
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if(flag[i] == false){
                arr[depth] = array[i];
                flag[i] = true;
                func(array,depth+1,queue,flag,arr);
                flag[i] = false;
            }
        }
    }

    public static int findMid(int a,int b, int c){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(a);
        queue.offer(b);
        queue.offer(c);
        queue.poll();
        return queue.poll();
    }
}