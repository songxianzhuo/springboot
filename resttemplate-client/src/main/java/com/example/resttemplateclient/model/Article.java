package com.example.resttemplateclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Article
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/10/30 16:36
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 432340129543161439L;

    private String mcnid;
    private Integer platId;
    private String platform;
    private String tittle;
    private String publish_time;
    private String type;
}
