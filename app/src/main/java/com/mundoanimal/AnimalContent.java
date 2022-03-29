package com.mundoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalContent {
    public List<AnimalItem> ITEMS = new ArrayList<AnimalItem>();
    public Map<String, AnimalItem> ITEM_MAP = new HashMap<String, AnimalItem>();
    public static final String[] africaList = new String[]{"leon", "elefante", "leopardo", "rinoceronte", "zebra", "jirafa"};
    public static final String[] asiaList = new String[]{"oso panda", "tigre de bengala", "dragon de comodo", "lobo indio"};
    public static final String[] americaList = new String[]{"armadillo", "iguana", "llama", "quetzal", "guacamaya",};
    public static final int[] africaAnimalImagesList = new int[]{R.drawable.leon_main, R.drawable.elefante_main, R.drawable.leopardo_main, R.drawable.rinoceronte_main, R.drawable.zebra_main, R.drawable.jirafa_main};
    public static final int[] asiaAnimalImagesList = new int[]{R.drawable.oso_panda_main, R.drawable.tigre_bengala_main, R.drawable.dragon_comodo_main, R.drawable.dragon_comodo_main};
    public static final int[] americaAnimalImagesList = new int[]{R.drawable.armadillo_main, R.drawable.iguana_main, R.drawable.llama_main, R.drawable.quetzal_main, R.drawable.quetzal_main};
    private static final int COUNT = 25;


    public void fillListToShow(String continente) {
        switch (continente) {
            case "africa" :
                createAnimalItemList(africaList, africaAnimalImagesList);
                break;
            case "asia" :
                createAnimalItemList(asiaList, asiaAnimalImagesList);
                break;
            case "america" :
                createAnimalItemList(americaList, americaAnimalImagesList);
                break;
        }
    }

    private void addItem(AnimalItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private void createAnimalItemList(String[] animalList, int[] animalImagesList) {
        ITEMS = new ArrayList<AnimalItem>();
        ITEM_MAP = new HashMap<String, AnimalItem>();
        for (int i = 0; i < animalList.length; i++) {
            AnimalItem item = new AnimalItem(animalList[i].toUpperCase(), animalImagesList[i]);
            addItem(item);
        }
    }

    public static class AnimalItem {
        public final String id;
        public final int content;

        public AnimalItem(String id, int content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
