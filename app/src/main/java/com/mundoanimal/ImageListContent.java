package com.mundoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageListContent {

    public List<ImageListItem> ITEMS = new ArrayList<ImageListItem>();
    public Map<String, ImageListItem> ITEM_MAP = new HashMap<String, ImageListItem>();
    public static final int[] leonImageList = new int[]{R.drawable.leon_main, R.drawable.leon_1};
    public static final int[] elefanteImageList = new int[]{R.drawable.elefante_main, R.drawable.elefante_1};
    public static final int[] leopardoImageList = new int[]{R.drawable.leopardo_main, R.drawable.leopardo_1};
    public static final int[] rinoceronteImageList = new int[]{R.drawable.rinoceronte_main, R.drawable.rinoceronte_1};
    public static final int[] zebraImageList = new int[]{R.drawable.zebra_main, R.drawable.zebra_1};
    public static final int[] jirafaImageList = new int[]{R.drawable.jirafa_main, R.drawable.jirafa_1};
    public static final int[] osoPandaImageList = new int[]{R.drawable.oso_panda_main, R.drawable.oso_panda_1};
    public static final int[] tigreBengalaImageList = new int[]{R.drawable.tigre_bengala_main, R.drawable.tigre_bengala_1};
    public static final int[] dragonComodoImageList = new int[]{R.drawable.dragon_comodo_main, R.drawable.dragon_comodo_1};
    public static final int[] armadilloImageList = new int[]{R.drawable.armadillo_main, R.drawable.armadillo_1};
    public static final int[] iguanaImageList = new int[]{R.drawable.iguana_main, R.drawable.iguana_1};
    public static final int[] llamaImageList = new int[]{R.drawable.llama_main, R.drawable.llama_1};
    public static final int[] quetzalImageList = new int[]{R.drawable.quetzal_main, R.drawable.quetzal_1};
    //public static final int[] tuImageList = new int[]{R.drawable.tu_main, R.drawable.tu_1};
    private static final int COUNT = 25;

    public void fillListToShow(String animal) {
        switch (animal) {
            case "LEON" :
                createAnimalItemList(leonImageList);
                break;
            case "ELEFANTE" :
                createAnimalItemList(elefanteImageList);
                break;
            case "LEOPARDO" :
                createAnimalItemList(leopardoImageList);
                break;
            case "RINOCERONTE" :
                createAnimalItemList(rinoceronteImageList);
                break;
            case "ZEBRA" :
                createAnimalItemList(zebraImageList);
                break;
            case "JIRAFA" :
                createAnimalItemList(jirafaImageList);
                break;
            case "OSO PANDA" :
                createAnimalItemList(osoPandaImageList);
                break;
            case "TIGRE DE BENGALA" :
                createAnimalItemList(tigreBengalaImageList);
                break;
            case "DRAGON DE COMODO" :
                createAnimalItemList(dragonComodoImageList);
                break;
            case "ARMADILLO" :
                createAnimalItemList(armadilloImageList);
                break;
            case "IGUANA" :
                createAnimalItemList(iguanaImageList);
                break;
            case "LLAMA" :
                createAnimalItemList(llamaImageList);
                break;
            case "QUETZAL" :
                createAnimalItemList(quetzalImageList);
                break;
            case "TU" :
              //  createAnimalItemList(tuImageList);
                break;
        }
    }

    private void addItem(ImageListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private void createAnimalItemList(int[] animalList) {
        ITEMS = new ArrayList<ImageListItem>();
        ITEM_MAP = new HashMap<String, ImageListItem>();
        for (int i = 0; i < animalList.length; i++) {
            ImageListItem item = new ImageListItem(String.valueOf(animalList[i]), animalList[i]);
            addItem(item);
        }
    }

    public static class ImageListItem {
        public final String id;
        public final int content;

        public ImageListItem(String id, int content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
