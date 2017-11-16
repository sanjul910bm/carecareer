package au.com.carecareers.android.contracts;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public class UrlContract {
    private UrlContract() {

    }

    public static final String LOG_IN = "register";
    public static final String AUTHORIZE = "oauth/application";


    public class Keys {
        public static final String GRANT_TYPE = "grant_type";
        public final static String AUTHORIZATION = "authorization";
    }

    public class Values {
        public final static String CLIENT_CREDENTIALS = "client_credentials";
    }
}
