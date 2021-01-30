package br.edu.ifsp.utils;

public class UserPermissionManager {

    private static String userPermission;

    public static String getUserPermission() {
        return userPermission;
    }

    protected  void setUserPermission(String userPermission){
        this.userPermission = userPermission;
    }
}
