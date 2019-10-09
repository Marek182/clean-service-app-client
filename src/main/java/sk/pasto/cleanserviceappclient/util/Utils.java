package sk.pasto.cleanserviceappclient.util;

public class Utils<T> {

    /**
     * fully qualified name to loverCase class name + s
     * example: FROM sk.pasto.cleanserviceappclient.modelDTO.Person TO persons
     */
    public static <T> String getEntityName(T t) {
        String name = t.getClass().getName();
        String[] arr = name.split("\\.");
        int len = arr.length;
        String res = (arr[len-1].toLowerCase()) + "s";
        return res;
    }

}
