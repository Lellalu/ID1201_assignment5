package se.kth.id1201;

import java.util.Random;
import java.lang.Math;

import se.kth.id1201.DoubleLinkedList.DoubleNode;
import se.kth.id1201.LinkedList.Node;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        Benchmark();
    }

    public static double average(double[] arr) {
        double sum = 0;
        for(double number : arr) {
            sum += number;
        }
        return sum/arr.length;
    }

    public static double standard_deviation(double[] arr, double mean) {
        double sum = 0;
        for(double number : arr) {
            sum += (number-mean)*(number-mean);
        }
        sum = sum/(arr.length -1);
        return Math.sqrt(sum);
    }

    public static double error_margin(double[] arr, double mean){
        double std = standard_deviation(arr, mean);
        double ninetyfive_confidence = 1.96;
        return ninetyfive_confidence * (std/Math.sqrt(arr.length));
    }

    public static void Benchmark(){
        int k = 1000;
        int[] k_random_numbers;
        LinkedList linked_list;
        DoubleLinkedList double_linked_list;
        Random random = new Random();

        Node[] linked_nodes;
        Node linked_node;
        DoubleNode[] double_linked_nodes;
        DoubleNode double_linked_node;

        long startTime;
        long endTime;
        double[] linkedTime;
        double[] doubleLinkedTime;

        int repetition = 1000;

        double linked_mean;
        double linked_error_margin;
        double double_linked_mean;
        double double_linked_error_margin;

        System.out.printf("#Insert and unlink elements in linked listed and double linked list of length n, time in ns\n");
        System.out.printf("#%12s%12s%12s\n", "n ", "linkedList         ", "doubleLinkedList");

        for(int n = 1; n <= 512; n *= 2){
            linkedTime = new double[repetition];
            doubleLinkedTime = new double[repetition];
            for(int times = 0; times < repetition; times++){
                linked_list = new LinkedList(n);
                double_linked_list = new DoubleLinkedList(n);

                linked_nodes = new Node[n];
                double_linked_nodes = new DoubleNode[n];
                linked_node = linked_list.first;
                double_linked_node = double_linked_list.first;
                for(int i=0; i < n; i++){
                    linked_nodes[i] = linked_node;
                    double_linked_nodes[i] = double_linked_node;

                    linked_node = linked_node.next;
                    double_linked_node = double_linked_node.next;
                }

                k_random_numbers = new int[k];
                for(int i=0; i < k; i++){
                    k_random_numbers[i] = random.nextInt(n);
                }

                startTime = System.nanoTime();
                for(int i=0; i < k; i++){
                    linked_list.unlink(linked_nodes[k_random_numbers[i]]);
                    linked_list.insert(linked_nodes[k_random_numbers[i]]);
                }
                endTime = System.nanoTime();
                linkedTime[times] = (endTime-startTime);

                startTime = System.nanoTime();
                for(int i=0; i < k; i++){
                    double_linked_list.unlink(double_linked_nodes[k_random_numbers[i]]);
                    double_linked_list.insert(double_linked_nodes[k_random_numbers[i]]);
                }
                endTime = System.nanoTime();
                doubleLinkedTime[times] = (endTime-startTime);
            }
            linked_mean = average(linkedTime);
            linked_error_margin = error_margin(linkedTime, linked_mean);
            double linked_interval_1 = linked_mean - linked_error_margin;
            double linked_interval_2 = linked_mean + linked_error_margin;
            double_linked_mean = average(doubleLinkedTime);
            double_linked_error_margin = error_margin(doubleLinkedTime, double_linked_mean);
            double double_linked_interval_1 = double_linked_mean - double_linked_error_margin;
            double double_linked_interval_2 = double_linked_mean + double_linked_error_margin;
            System.out.printf("%8d&", n);
            System.out.printf("%12.2f, %.2f)&%12.2f, %f)\\\n", linked_interval_1, linked_interval_2 , double_linked_interval_1, double_linked_interval_2);
        }
    }
}
