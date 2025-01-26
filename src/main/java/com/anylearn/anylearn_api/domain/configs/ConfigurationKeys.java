package com.anylearn.anylearn_api.domain.configs;

import java.util.Arrays;
import java.util.List;

public class ConfigurationKeys {
    public static final String QUOTE_URL = "quote_url";
    public static final String BANNER_RATIO = "banner_ratio";

    public static final String CONFIG_IOS_TRANSACTION = "ios_transaction";
    public static final String CONFIG_DISABLE_ANYPOINT = "disable_anypoint";
    public static final String CONFIG_FRIEND_TREE = "friend_tree";
    public static final String CONFIG_NUM_COURSE = "num_course";
    public static final String CONFIG_NUM_TEACHER = "num_teacher";
    public static final String CONFIG_NUM_SCHOOL = "num_school";
    public static final String CONFIG_FEE_TEACHER = "fee_teacher";
    public static final String CONFIG_FEE_SCHOOL = "fee_school";
    public static final String CONFIG_FEE_MEMBER = "fee_member";
    public static final String CONFIG_COMMISSION = "commission";
    public static final String CONFIG_DISCOUNT = "discount";
    public static final String CONFIG_BONUS_RATE = "bonus_rate";
    public static final String CONFIG_NUM_CONFIRM_GOT_BONUS = "num_confirm";
    public static final String CONFIG_COMMISSION_FOUNDATION = "bonus_foundation";
    public static final String CONFIG_COMMISSION_COMPANY = "bonus_company";
    public static final String CONFIG_COMMISSION_AUTHOR = "bonus_author";
    public static final String CONFIG_COMMISSION_SCHOOL = "bonus_school";
    public static final String CONFIG_COMMISSION_REF_SELLER = "bonus_ref_seller";
    public static final String CONFIG_COMMISSION_REF_VOUCHER = "bonus_ref_voucher";
    public static final String CONFIG_TEACHER_BANNER = "teacher_banner";
    public static final String CONFIG_SCHOOL_BANNER = "school_banner";
    public static final String CONFIG_OPENAPI_PRODUCTS = "openapi_products";
    public static final String CONFIG_HOME_POPUP = "home_popup";
    public static final String CONFIG_HOME_POPUP_WEB = "home_popup_web";
    public static final String CONFIG_HOME_SPECIALS_CLASSES = "home_special_classes";
    public static final String CONFIG_APP_BANNERS = "home_app_banners";

    public static List<String> getHomeConfigKeys() {
        return Arrays.asList(
            CONFIG_IOS_TRANSACTION,
            CONFIG_APP_BANNERS,
            BANNER_RATIO,
            QUOTE_URL,
            CONFIG_HOME_SPECIALS_CLASSES,
            CONFIG_HOME_POPUP
        );
    }
}
