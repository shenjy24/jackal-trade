package com.shenjy.common;

/**
 * 缓存前缀
 *
 * @author shenjy 2019/06/02
 */
public enum CachePrefix {

    /** 订单 */
    ORDER_CREATE_LOCK("order:create:lock:", "创建订单分布式锁", 10L),

    /** 支付 */
    PAY_NOTIFY_LOCK("pay:notify:lock:", "支付回调分布式锁", 10L),
    ;

    private String prefix;
    private String message;
    private Long timeout;

    CachePrefix(String prefix, String message, Long timeout) {
        this.prefix = prefix;
        this.message = message;
        this.timeout = timeout;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimeout() {
        return timeout;
    }
}
