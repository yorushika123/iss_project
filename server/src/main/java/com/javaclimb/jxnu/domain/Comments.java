package com.javaclimb.jxnu.domain;
import lombok.Data;
import org.joda.time.DateTime;
@Data
public class Comments {

    private int id;
    private int post_id;
    private String comments_content;
    private int user_id;
    private DateTime create_time;
}
