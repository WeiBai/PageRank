package org.rank;

import java.util.*;
import java.io.*;

/**
 * Created by baiwe on 2017/7/5.
 */
public class PageRank {
    public int path[][] = {{0,1,0,0,0},{0,0,0,0,1},{1,1,0,1,1},{0,0,1,0,1},{0,0,0,1,0}};
    public double pagerank[] = new double[5];

    /**
     *
     * @param totalNodes Number of nodes in the graph.
     */
    public void calc (double totalNodes){
        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 1;
        double TempPageRank[] = new double[5];

        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 0;
        int ITERATION_STEP = 1;

        InitialPageRank = 1/totalNodes;
        System.out.println(" Total Number of Nodes: " + totalNodes + " \t Initial PageRank of All Nodes: " +
                " " +InitialPageRank);

        // Initialize the value of pagerank for each node
        for (k = 0; k < totalNodes; k++){
            this.pagerank[k] = InitialPageRank;
        }

        System.out.println("Initial PageRank values, 0th step: ");
        for (k = 0; k < totalNodes; k++){
            System.out.println("Page Rank of page " + k + " is: " + this.pagerank[k] +".");
        }

        while(ITERATION_STEP <= 100){
            // Store the pagerank for all nodes in Temporary Array
            for (k = 0; k < totalNodes; k++){
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++){
                for (ExternalNodeNumber = 0; ExternalNodeNumber < totalNodes; ExternalNodeNumber++){
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1){
                        k = 0;
                        OutgoingLinks = 0; // Count the number of outgoing links for each externalNodeNumber
                        while (k < totalNodes){
                            if (this.path[ExternalNodeNumber][k] == 1){
                                OutgoingLinks = OutgoingLinks +1;
                            }
                            k++;
                        }
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);
                    }
                }
            }
            System.out.println("After " + ITERATION_STEP + "th Step. ");

            for (k = 0; k < totalNodes; k++){
                System.out.println("Page Rank of " + k +" is: " + this.pagerank[k] +".");
            }
            ITERATION_STEP++;
        }

        // Add the damping factor to PageRank
        for (k = 0; k< totalNodes; k++){
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor*this.pagerank[k];
        }

        System.out.println(" Final page rank: ");
        for (k = 0; k < totalNodes; k++){
            System.out.println("Page rank of " + k + " is: " +this.pagerank[k]);
        }
    }

    public static void main(String[] args){
        int nodes, i, j, cost;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the NUmber of Webpages:");
        nodes = in.nextInt();
        PageRank p = new PageRank();
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->No Path Between two Webpages: ");

        p.calc(nodes);

    }
}
