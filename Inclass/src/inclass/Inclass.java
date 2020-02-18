/*
 * By:     Matthew Fischer
 * Date:   
 */
package inclass;

/**
 *
 * @author Matthew Fischer
 */

import java.util.*;
    
public class Inclass {


    public static void main(String[] args) {
        // ArrayList 
      List a1 = new ArrayList();
      
      a1.add("Zara");
      a1.add("Mahnaz");
      a1.remove("Zara");
      a1.add("Ayan");
      a1.add("Ayan");
      System.out.println( "List size" + a1.size() );
      a1.clear();
      if(a1.isEmpty()){
          a1.add("first");
      }
      a1.add("Zara");
      a1.add("Mahnaz");
      
      
      System.out.println("hashcode " + a1.hashCode());
      System.out.println( "List size" + a1.size() );
      System.out.println("ArrayList Elements 1");
      System.out.print("\t" + a1);

      List b1 = new ArrayList();
      if(b1.isEmpty()){
          b1.addAll(a1);
      }
      
      if(a1.equals(b1)){
          System.out.println("\n list 1 and 2 are equal");
      }
      else{
          System.out.println("\nlist 1 and 2 not equal");
      }
      System.out.println("\nArrayList Elements 2");
      System.out.print("\t" + b1);
      
      
      // LinkedList
      List l1 = new LinkedList();
      if(l1.isEmpty()){
          l1.add("first");
      }
      l1.add("Zara");
      l1.remove("Zara");
      l1.add("Mahnaz");
      l1.add("Ayan");
      l1.add("Ayan");
      //l1.clear();
      
      System.out.println();
      System.out.println("hashcode " + l1.hashCode());
      System.out.println("LinkedList size" + l1.size() );
      System.out.println("LinkedList Elements 3");
      System.out.print("\t" + l1);

      // HashSet
      Set s1 = new HashSet(); 
      if(s1.isEmpty()){
          s1.add("first");
      }
      s1.add("Zara");
      s1.add("second");
      s1.add("Mahnaz");
      s1.add("Ayan");
      s1.add("Ayan");
      s1.remove("Zara");
      s1.add("last on list");
      //s1.clear();
      System.out.println();
      System.out.println("hash code " + s1.hashCode());
      System.out.println("Set Elements 4");
      System.out.print("\t" + s1);

      // HashMap
      Map m1 = new HashMap(); 
      m1.put("first", "100");
      m1.put("Zara", "8");
      m1.put("Mahnaz", "31");
      m1.put("Ayan", "12");
      m1.put("Ayan", "12");
      m1.put("Daisy", "14");
      m1.put("hello", "99");
      m1.put("world", "1");
      

      System.out.println();
      System.out.println("hash code " + m1.hashCode());
      System.out.println("Map Elements 5");
      System.out.print("\t" + m1);
    }
    
}
