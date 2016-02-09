package com.arcbees.client.place;

public class NameTokens {
    public static final String PARAM_ID = "id";
    public static final String WRAPPED_PARAM_ID = "/{" + PARAM_ID + "}";

    public static final String USERS = "/users";
    public static final String USER = USERS + WRAPPED_PARAM_ID;

}
