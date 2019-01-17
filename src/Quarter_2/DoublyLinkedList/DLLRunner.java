package Quarter_2.DoublyLinkedList;

public class DLLRunner {

    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 0; i < 30; i++)
            list.add((int) (Math.random() * 900 + 100));
        System.out.println(list.toString());
        System.out.println(list.toReversedString());

        DoublyLinkedList<Integer> temp = new DoublyLinkedList<>();
        while (list.size() > 0) {
            System.out.print((int) list.getRoot().getValue() + " ");
            temp.add(list.remove(0));
        }
        list = temp;
        System.out.println();

        temp = new DoublyLinkedList<>();
        while (list.size() > 0) {
            System.out.print((int) list.getEnd().getValue() + " ");
            temp.add(list.remove(list.size() - 1));
        }
        list = temp;
        System.out.println();

        System.out.println(list.size());

        temp = new DoublyLinkedList<>();
        double sum = 0, size = list.size();
        while (list.size() > 0) {
            sum += (int) list.getRoot().getValue();
            temp.add(list.remove(0));
        }
        list = temp;
        System.out.println(sum / size);
        System.out.println();

        temp = new DoublyLinkedList<>();
        sum = 0;
        size = list.size() / 2.0;
        while (list.size() > 0) {
            sum += (int) list.getRoot().getValue();
            System.out.print(list.getRoot().getValue() + " ");
            temp.add(list.remove(0));
            temp.add(list.remove(0));
        }
        list = temp;
        System.out.println();
        System.out.println(sum / size);
        System.out.println();

        temp = new DoublyLinkedList<>();
        sum = 0;
        size = list.size() / 2.0;
        while (list.size() > 0) {
            if (list.size() > 0 && list.getRoot().hasNext())
                temp.add(list.remove(0));
            sum += (int) list.getRoot().getValue();
            System.out.print(list.getRoot().getValue() + " ");
            temp.add(list.remove(0));
        }
        list = temp;
        System.out.println();
        System.out.println(sum / size);
        System.out.println();

        list.add(4, 454545);


        list = new DoublyLinkedList<>();
        list.add(10);
        list.add(20);
        list.clear();
        list.add(0, 10);
        list.add(0, 20);
        list.clear();
        list.add(list.size(), 10);
        list.add(list.size(), 20);
        list.add(1, 30);
//        try {
//            list.add(-1, 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        list.clear();
        list.add(10);
        list.remove(0);
        list.add(10);
        list.remove(list.size() - 1);
        list.add(10);
        list.add(20);
        list.remove(0);
        list.add(30);
        list.remove(list.size() - 1);
        list.add(30);
        list.add(40);
        list.remove(1);

        list.clear();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        while (list.size() > 0) {
            if (((int)list.getRoot().getValue()) % 4 == 0)
                System.out.print((int) list.getRoot().getValue() + " ");
            temp.add(list.remove(0));
        }
        list = temp;
        System.out.println();

        list.clear();
        list.add(3);
        list.add(6);
        list.add(8);
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(4);
        list.add(9);
        list.add(1);
        list.add(0);
        System.out.println(list.toString());

        for (int i = 1; i < list.size(); i++) {
            int current = list.get(i), j;
            for (j = i - 1; j >= 0 && list.get(j) > current; j--)
                list.set(j + 1, list.get(j));
            list.set(j + 1, current);
        }
        System.out.println(list.toString());

        for (int i = 0; i < (list.size() / 2) - 1; i++)
            temp.add(list.remove(0));

        if (list.size() % 2 == 0)
            System.out.println(((int)list.getRoot().getValue() + (int)list.getRoot().getNext().getValue()) / 2.0);
        else
            System.out.println((int)list.getRoot().getNext().getValue());

    }

}
