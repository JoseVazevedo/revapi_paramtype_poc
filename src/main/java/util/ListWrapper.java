package util;

import java.util.List;

public class ListWrapper {

    private final List<String> stringList;

    public ListWrapper(final List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getTest() {
        return stringList;
    }

    public void mergeList(final List<String> listToMerge) {
        stringList.addAll(listToMerge);
    }
}
