package com.aceplus.e_commerce.utils;

/**
 * Created by phonelin on 5/12/17.
 */

public class DatabaseContract {

    public abstract class Product {

        public static final String tb = "products";
        public static final String id = "id";
        public static final String product_code = "product_code";
        public static final String product_group_id = "product_group_id";
        public static final String product_size_id = "product_size_id";
        public static final String created_by = "created_by";
        public static final String updated_by = "updated_by";
        public static final String deleted_by = "deleted_by";
        public static final String created_at = "created_at";
        public static final String updated_at = "updated_at";
        public static final String deleted_at = "deleted_at";


    }

    public abstract class Category {


        public static final String tb = "categories";
        public static final String id = "id";
        public static final String name = "name";
        public static final String parent_id = "parent_id";
        public static final String image = "image";
        public static final String image_encode = "image_encode";
        public static final String status = "status";
        public static final String created_by = "created_by";
        public static final String updated_by = "updated_by";
        public static final String deleted_by = "deleted_by";
        public static final String created_at = "created_at";
        public static final String updated_at = "updated_at";
        public static final String deleted_at = "deleted_at";


    }

    public abstract class Product_review {

        public static final String tb = "product_review";
        public static final String id = "id";
        public static final String customer_id = "customer_id";
        public static final String product_id = "product_id";
        public static final String review = "review";
        public static final String status = "status";


    }

    public abstract class Product_color {

        public static final String tb = "product_color";
        public static final String id = "id";
        public static final String name = "name";
        public static final String remark = "remark";

    }

    public abstract class Product_size {

        public static final String tb = "product_size";
        public static final String id = "id";
        public static final String name = "name";
        public static final String remark = "remark";

    }

    public abstract class Product_description {


        public static final String tb = "product_description";
        public static final String id = "id";
        public static final String product_group_id = "product_group_id";
        public static final String description = "description";
        public static final String status = "status";

    }

    public abstract class Product_key_feature {

        public static final String tb = "product_key_feature";
        public static final String id = "id";
        public static final String product_id = "product_id";
        public static final String description = "description";
        public static final String status = "status";


    }

    public abstract class Brands {

        public static final String tb = "brands";
        public static final String id = "id";
        public static final String name = "name";
        public static final String description = "description";


    }

    public abstract class Product_rating {

        public static final String tb = "product_rating";
        public static final String id = "id";
        public static final String product_id = "product_id";
        public static final String customer_id = "customer_id";
        public static final String rating = "rating";


    }

    public abstract class Product_gallery {

        public static final String tb = "product_gallery";
        public static final String id = "id";
        public static final String product_id = "product_id";
        public static final String image = "image";
        public static final String image_encode = "image_encode";


    }

    public abstract class Product_categories {

        public static final String tb = "product_categories";
        public static final String id = "id";
        public static final String category_id = "category_id";
        public static final String product_group_id = "product_group_id";


    }

    public abstract class Product_group {

        public static final String tb = "product_group";
        public static final String id = "id";
        public static final String model = "model";
        public static final String group_code = "group_code";
        public static final String brand_id = "brand_id";
        public static final String has_variance = "has_variance";
        public static final String product_name = "product_name";
        public static final String description = "description";
        public static final String status = "status";
        public static final String product_sku = "product_sku";
        public static final String manufacturer_id = "manufacturer_id";
        public static final String cost_price = "cost_price";
        public static final String base_price = "base_price";
        public static final String weight = "weight";
        public static final String length = "length";
        public static final String width = "width";
        public static final String height = "height";
        public static final String product_color_id="product_color_id";


    }

    public abstract class Product_discount {

        public static final String tb = "product_discount";
        public static final String id = "id";
        public static final String discount_type = "discount_type";
        public static final String discount_amount = "discount_amount";
        public static final String with_expiry_date = "with_expiry_date";
        public static final String discount_from = "discount_from";
        public static final String discount_to = "discount_to";
        public static final String status = "status";
        public static final String product_group_id = "product_group_id";
        public static final String discount_name="discount_name";


    }

    public abstract class Wishlist {

        public static final String tb = "wishlist";
        public static final String id = "id";
        public static final String customer_id = "customer_id";
        public static final String product_id = "product_id";

    }

    public abstract class Stock_reserve{

        public static final String tb="stock_reserve";
        public static final String id="id";
        public static final String reserve_session_id="reserve_session_id";
        public static final String product_id="product_id";
        public static final String customer_id="customer_id";
        public static final String qty="qty";


    }


}
