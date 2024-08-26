import java.io.*;
import java.util.*;


 class Main {

     public static void main(String[] args) {
         List<Transaction> transactions = new ArrayList<>();
         transactions.add(new Transaction("CR", 100));
         transactions.add(new Transaction("DR", 25));
         transactions.add(new Transaction("DR", 70));
         transactions.add(new Transaction("DR", 5));
         int N = 2;

//         transactions.add(new Transaction("CR", 100));
//         transactions.add(new Transaction("CR", 25));
//         transactions.add(new Transaction("DR", 25));
//         transactions.add(new Transaction("DR", 70));
//         transactions.add(new Transaction("DR", 5));
//         transactions.add(new Transaction("DR", 15));
//         transactions.add(new Transaction("DR", 10));
//         int N = 3;

         List<List<Transaction>> result = Split.splitTransactions(transactions, N);

         for (int i = 0; i < result.size(); i++) {
             List<Transaction> split = result.get(i);
             List<Transaction> credits = new ArrayList<>();
             List<Transaction> debits = new ArrayList<>();
             for (Transaction t : split) {
                 if (t.type.equals("CR")) {
                     credits.add(t);
                 } else {
                     debits.add(t);
                 }
             }
             System.out.print("Split " + (i + 1) + ": ");
             boolean first = true;
             for (Transaction t : credits) {
                 if (!first) {
                     System.out.print(", ");
                 }
                 System.out.print(t);
                 first = false;
             }
             for (Transaction t : debits) {
                 if (!first) {
                     System.out.print(", ");
                 }
                 System.out.print(t);
                 first = false;
             }
             System.out.println();
         }
     }
 }