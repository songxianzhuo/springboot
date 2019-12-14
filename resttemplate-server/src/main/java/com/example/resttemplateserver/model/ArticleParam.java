package com.example.resttemplateserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: ArticleParam
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/10/30 16:36
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class ArticleParam implements Serializable {

    private static final long serialVersionUID = 5370263602844630766L;
    private String mcnid;
    private Integer platId;
    private String type;
}
