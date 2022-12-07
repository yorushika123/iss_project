package com.javaclimb.jxnu.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Contents {
    private  Integer id;/*主键*/

    private  String articleTitle;

    private  String articleCover;

    private  String articleContentHtml;

    private  String articleContentMd;

    private  String  articleAbstract;

    private Date articleDate;
}
