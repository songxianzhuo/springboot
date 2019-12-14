package com.example.jtaatomikos.config.datasource;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DataSourceParam
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/19 18:46
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class DataSourceParam {

    private String driverClassName;

    private String url;

    private String username;

    private String password;
}
