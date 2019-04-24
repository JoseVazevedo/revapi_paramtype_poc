import java.util.ArrayList;
import java.util.List;
import util.ListWrapper;

public class Main {

    public static void main(final String[] args) {
        final List<String> list = new ArrayList<String>(3);
        list.add("sample");
        list.add("list");
        list.add("string");

        final ListWrapper listWrapper = new ListWrapper(list);

        printList(listWrapper.getStringList());

        final List<String> newList = new ArrayList<String>(1);
        newList.add("new string");

        listWrapper.mergeList(newList);

        printList(listWrapper.getStringList());
    }

    private static void printList(final List<String> listToPrint) {
        for (final String string : listToPrint) {
            System.out.println(string);
        }
        System.out.println();
    }

}
