package sk.pasto.cleanserviceappclient.utils;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public class Utils {

    /**
     * Vrati retazec linkov asociovanej entity + pripoji link pridavanej entity
     * napr. linky vsetkych kurzov studenta + link kurzu kt. chceme pridat
     * <http://localhost:8080/api/students/1/courses =>
     *     stary kurz </http://localhost:8080/api/courses/1>
     *     stary kurz </http://localhost:8080/api/courses/2>
     *     +
     *     novy kurz </http://localhost:8080/api/courses/3>
     * @param resources
     * @param <T>
     * @return
     */
    public static <T> String getLinksForAssociatedEntity(Resources<Resource<T>> resources, Resource<T> resource) {
        String links = "";
        for (Resource<T> res: resources) {
            links += res.getLink("self").getHref() + "\n";
        }
        links += resource.getLink("self").getHref();
        return links;
    }

}
