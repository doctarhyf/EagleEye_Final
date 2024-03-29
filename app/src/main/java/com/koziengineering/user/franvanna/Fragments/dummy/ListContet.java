package com.koziengineering.user.franvanna.Fragments.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ListContet {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ListItem> ITEMS = new ArrayList<ListItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ListItem> ITEM_MAP = new HashMap<String, ListItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ListItem createDummyItem(int position) {
        return new ListItem(String.valueOf(position), "Item " + position, makeDetails(position), "dummy");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ListItem {
        public  String id;
        public  String content;
        public  String details;
        public  String territoire;
        private String listName;

        public ListItem(String id, String content, String details, String listName) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.territoire = "NO_TERITOIRE_DATA";
            this.listName = listName;
        }

        @Override
        public String toString() {
            return content;
        }

        public String getTerritoire() {
            return territoire;
        }

        public void setTerritoire(String newTerritoire){
            this.territoire = newTerritoire;
        }

        public String getListName() {
            return listName;
        }

        public void setListName(String listName){
            this.listName = listName;
        }
    }
}
