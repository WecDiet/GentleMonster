package com.gentlemonster.Constant;

public class Endpoint {
    // API User
    public static final String API_PREFIX_ADMIN = "/api/v1/admin";
    public static final String API_PREFIX_SHOP = "/shop/list";

    public static final class User{
        public static final String BASE = API_PREFIX_ADMIN + "/users";
        public static final String NEW = "/new";
        public static final String ID = "/user_detail/{userID}";
        public static final String DELETE_MANY = "/delete-many";
    }

    // API Role
    public static final class Role{
        public static final String BASE = API_PREFIX_ADMIN + "/roles";
        public static final String NEW = "/new";
        public static final String ID = "/role_detail/{roleID}";
//        public static final String DELETE_MANY = "/delete-many";
    }


    // API Category
    public static final class Category{
        public static final String BASE = API_PREFIX_ADMIN + "/categories";
        public static final String NEW = "/new";
        public static final String ID = "/category_detail/{categoryID}";
        public static final String DELETE_MANY = "/delete-many";
    }

    public static final class Product{
        public static final String BASE = API_PREFIX_ADMIN + "/products";
        public static final String NEW = "/new";
        public static final String ID = "/product_detail/{productID}";
        public static final String DELETE_MANY = "/delete-many";
    }

}