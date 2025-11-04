public class Constants {
    // TODO move to dedicated classes as project grow

    // Basic Errors and messages
    public static final String ERROR_NOT_AS_EXPECTED = "Error: %s is not as expected";
    public static final String ERROR_MUST_NOT_BE_EMPTY = "Error: %s must no be empty";
    public static final String ERROR_INVALID_API_KEY = "Invalid or inactive API key";

    // Required properties
    public static final String VALID_API_KEY = System.getProperty("api.key");

    // Endpoints
    public static final String REQRES_IN_API = "https://reqres.in/api";
    public static final String POST_USER_ENDPOINT = "/users";
    public static final String GET_SINGLE_USER_ENDPOINT = "/users/2";
    public static final String GET_MULTI_USER_ENDPOINT = "/users?page=2";

    // Headers
    public static final String X_API_KEY = "x-api-key";
}
