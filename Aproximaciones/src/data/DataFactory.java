package data;

/**
 * Created by NEGU on 7/10/2015.
 */
public class DataFactory {
    public static UserData ud = null;

    public static UserData getUserData () {
        if (ud == null) {
            ud = new UserData();
        }
        return ud;
    }
}
