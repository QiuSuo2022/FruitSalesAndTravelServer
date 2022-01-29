package com.guet.qiusuo.fruittravel.config;

public enum ErrorCode {
    LIMITED_AUTHORITY(-1, "limited.authority"),
    UNKNOWN_ERROR(-1, "system.error"),
    TOKEN_ERROR(-1, "system.error.token"),
    ID_NOT_EXIST_ERROR(-1, "system.error.id.notfound"),
    INSERT_ERROR(-1, "system.error.insert.fail"),
    UPDATE_ERROR(-1, "system.error.update.fail"),
    DELETE_ERROR(-1, "system.error.delete.fail"),
    PARAM_ERROR(-1, "system.error.param"),
    USER_NOT_FOUND(-1, "system.error.user.notfound"),
    USER_EXCEED_POWER(-1, "system.error.user.exceed.power"),
    USER_NAME_EXIST(-1, "system.error.user.exist"),
    USER_ROLE_EXIST(-1, "system.error.user.role.exist"),
    LOGIN_EXPIRED(-1, "system.error.login.expired"),
    PWD_ERROR(-1, "system.error.password"),
    ROLE_ERROR(-1, "system.error.role.notfound"),
    NO_ACCESS(401, "system.error.no_access"),
    LOGIN_EXCEPTION(-1, "system.error.login.error"),
    USER_NO_ROLE(-1, "system.error.user.no.role"),
    ;

    @Override
    public String toString() {
        return key;
    }

    int code;

    String key;

    ErrorCode(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

}
