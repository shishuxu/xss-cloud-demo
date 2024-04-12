package com.xss.parent.redisson;
/**
 *  分佈式鎖 key
 * @author xss
 * @version 1.0.0
 * @date 2022-11-25 17:00
 */
public interface RedisLockKey {
    /**
     * 同意協議派券加鎖
     */
    String LOCK_ADD_DISTRIBUTE_MEMBER_TYPE_COUPON = "LOCK_ADD_DISTRIBUTE_MEMBER_TYPE_COUPON_";
    /**
     * 套票下單 key
     */
    String LOCK_ADD_PACKAGE_ORDER = "LOCK_ADD_PACKAGE_ORDER_";
    /**
     * 支付回調
     */
    String LOCK_ADD_PAY_CALL_BACK = "LOCK_ADD_PAY_CALL_BACK_";
    /**
     * 轉數快 web支付回調
     */
    String LOCK_ADD_WEB_PAY_NOTIFY = "LOCK_ADD_WEB_PAY_NOTIFY_";
    /**
     * yedPay支付回調
     */
    String LOCK_ADD_YED_PAY_WEB_PAY_NOTIFY = "LOCK_ADD_YED_PAY_WEB_PAY_NOTIFY_";
    /**
     * eft支付結賬
     */
    String LOCK_ADD_EFT_PAY_CALL_BACK = "LOCK_ADD_EFT_PAY_CALL_BACK_";
    /**
     * K_PAY 支付
     */
    String LOCK_ADD_K_PAY_CALL_BACK = "LOCK_ADD_K_PAY_CALL_BACK_";
    /**
     * 會員註冊--加鎖
     */
    String LOCK_ADD_APP_MEMBER_REGISTER = "LOCK_ADD_APP_MEMBER_REGISTER_";
    /**
     * gsopos 上傳未完結訂單，h5掃碼點餐  並發進行--加鎖
     */
    String LOCK_ADD_GS_POS_MEMBER_SCAN = "LOCK_ADD_GS_POS_MEMBER_SCAN_";

    /**
     * 1s等待時間
     */
    long WAITE_TIME_ONE = 1;
    /**
     * 2s等待時間
     */
    long WAITE_TIME_TWO = 2;
    /**
     * 3s過期時間
     */
    long EXPIRATION_TIME_THREE = 3;
    /**
     * 4s過期時間
     */
    long EXPIRATION_TIME_FOUR = 4;
}
