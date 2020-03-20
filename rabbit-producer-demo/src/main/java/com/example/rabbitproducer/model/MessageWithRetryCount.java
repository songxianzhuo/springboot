package com.example.rabbitproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/19
 **/
@Data
public class MessageWithRetryCount {
    /**
     * 尝试次数
     */
    private Integer retrycount = 0;
    /**
     * 传送消息
     */
    private Object message;

    public MessageWithRetryCount(Object message){
        this.message = message;
    }

}
