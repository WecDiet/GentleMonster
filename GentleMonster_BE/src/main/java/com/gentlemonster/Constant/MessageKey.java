package com.gentlemonster.Constant;

public class MessageKey {
    // **** Auth
    public static final String REGISTER_SUCCESSFULLY = "auth.register.register_successfully";
    public static final String REGISTER_FAILED = "auth.register.register_failed";
    public static final String LOGIN_SUCCESSFULLY = "auth.login.login_successfully";
    public static final String LOGIN_FAILED = "auth.login.login_failed";
    public static final String USER_ALREADY_EXIST = "error.auth.register.user_already_exists";
    public static final String USER_NOT_EXIST = "error.auth.login.user_not_exists";
    public static final String WRONG_INPUT = "error.auth.login.wrong_email_or_password";
    public static final String ACCOUNT_LOCKED = "error.auth.login.account_locked";
    public static final String DIFFERENT_PASSWORD = "error.auth.change.different_password";
    public static final String WRONG_PASSWORD = "error.auth.change.wrong_password";
    public static final String UPDATE_PASSWORD_SUCCESSFULLY = "auth.change.update_password_success";
    public static final String UPDATE_USER_SUCCESSFULLY = "auth.change.update_user_success";
    public static final String REFRESHTOKEN_NOT_EXIST = "error.auth.refreshtoken.not_exist";
    public static final String REFRESHTOKEN_EXPIRED = "error.auth.refreshtoken.expired";
    public static final String ACCESSTOKEN_SUCCESS = "auth.refreshtoken.get_access_token_success";
    public static final String UPDATE_AVATAR_SUCCESS = "auth.avatar.update_success";

    // ROLES
    public static final String ADD_ROLE_SUCCESS = "role.add_success";
    public static final String EDIT_ROLE_SUCCESS = "role.edit_success";
    public static final String DELETE_ROLE_SUCCESS = "role.delete_success";
    public static final String ROLE_EXISTED = "role.existed";
    public static final String ROLE_NOT_FOUND = "role.not_found";
    public static final String ROLE_GET_SUCCESS = "role.get_success";

    // USERS
    public static final String USER_GET_SUCCESS = "users.get_success";
    public static final String USER_CREATE_SUCCESS = "users.create_success";
    public static final String USER_PHONE_EXISTED = "users.phone_existed";
    public static final String USER_UPDATE_SUCCESS = "users.update_success";
    public static final String USER_DELETE_SUCCESS = "user.delete_success";
    public static final String NOT_DELETE_ADMIN_USER = "user.not_delete_admin";
    public static final String USER_GET_ONE_SUCCESS = "user.get_one_success";

    // PAYMENT TYPE
    public static final String PAYMENT_TYPE_CREATE_SUCCESS = "payment_type.create_success";
    public static final String PAYMENT_TYPE_UPDATE_SUCCESS = "payment_type.update_success";
    public static final String PAYMENT_TYPE_DELETE_SUCCESS = "payment_type.delete_success" ;
    public static final String PAYMENT_TYPE_GET_SUCCESS = "payment_type.get_success" ;

    // CATEGORY
    public static final String CATEGORY_CREATE_SUCCESS = "category.create_success";
    public static final String CATEGORY_UPDATE_SUCCESS = "category.update_success";
    public static final String CATEGORY_DELETE_SUCCESS = "category.delete_success" ;
    public static final String CATEGORY_GET_SUCCESS = "category.get_success" ;
    public static final String CATEGORY_NOT_FOUND = "category.not_found";

    // PRODUCT
    public static final String PRODUCT_CREATE_SUCCESS = "product.create_success";
    public static final String PRODUCT_UPDATE_SUCCESS = "product.update_success";
    public static final String PRODUCT_DELETE_SUCCESS = "product.delete_success" ;
    public static final String PRODUCT_GET_SUCCESS = "product.get_success" ;
    public static final String PRODUCT_NOT_EXISTED = "product.not_existed";
    public static final String PRODUCT_IMAGE_UPDATE_SUCCESS = "product.image.update_success";
    public static final String PRODUCT_LIKED = "product.liked_success";
    public static final String PRODUCT_LIKE_FAILED = "product.like_failed";
    public static final String PRODUCT_UNLIKED = "product.unlike_success";
    public static final String PRODUCT_UNLIKED_FAILED = "product.unlike_failed";
}
