package Quarter_2.Set_Problems.Intersection_Union;

import java.util.ArrayList;
import java.util.HashSet;

public class IntersectionUnionSet {

    public static void main(String[] args) {

        ArrayList<HashSet<Integer>> arrayList = new ArrayList<>();
        int random = (int) (Math.random() * 10 + 2);
        for (int i = 0; i < random; i++) {
            HashSet<Integer> hashSet = new HashSet<>();
            for (int j = 0; j < 20; j++)
                hashSet.add((int) (Math.random() * 59 + 1));
            arrayList.add(hashSet);
            System.out.println(hashSet);
        }

        HashSet<Integer> intersectionSet = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
            intersectionSet = intersection(intersectionSet, arrayList.get(i));
        System.out.println(intersectionSet);

        HashSet<Integer> unionSet = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
            unionSet = union(unionSet, arrayList.get(i));
        System.out.println(unionSet);

        HashSet<Integer> evenIntersectionSet = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
            evenIntersectionSet = evenIntersection(evenIntersectionSet, arrayList.get(i));
        System.out.println(evenIntersectionSet);

        HashSet<Integer> evenUnionSet = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
            evenUnionSet = evenUnion(evenUnionSet, arrayList.get(i));
        System.out.println(evenUnionSet);

    }

    static HashSet<Integer> intersection(HashSet<Integer> setA, HashSet<Integer> setB){
        HashSet<Integer> hashSet = new HashSet<>(setA);
        hashSet.retainAll(setB);
        return hashSet;
    }

    static HashSet<Integer> union(HashSet<Integer> setA, HashSet<Integer> setB) {
        HashSet<Integer> hashSet = new HashSet<>(setA);
        hashSet.addAll(setB);
        return hashSet;
    }

    static HashSet<Integer> evenIntersection(HashSet<Integer> setA, HashSet<Integer> setB) {
        HashSet<Integer> hashSetA = new HashSet<>(setA), hashSetB = new HashSet<>(setB);
        hashSetA.removeIf(integer -> integer % 2 != 0);
        hashSetB.removeIf(integer -> integer % 2 != 0);
        hashSetA.retainAll(hashSetB);
        return hashSetA;
    }

    static HashSet<Integer> evenUnion(HashSet<Integer> setA, HashSet<Integer> setB) {
        HashSet<Integer> hashSetA = new HashSet<>(setA), hashSetB = new HashSet<>(setB);
        hashSetA.removeIf(integer -> integer % 2 != 0);
        hashSetB.removeIf(integer -> integer % 2 != 0);
        hashSetA.addAll(hashSetB);
        return hashSetA;
    }

}
