package util;

import java.util.List;

public class ListWrapper {

    private final List<String> stringList;

    public ListWrapper(final List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void mergeList(final List<String> listToMerge) {
        stringList.addAll(listToMerge);
    }
}
