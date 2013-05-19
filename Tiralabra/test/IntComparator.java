/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Comparator;


public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1-o2;
    }

}
