package io.spring.batch.domain;

import lombok.*;

import java.sql.Date;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbStatusHistVO {

    private String ioCode;
    private String issueDay;
    private String bizManageId;
    private int seqNo;
    private String avlEndDt;
    private String avlBeginDt;
    private String statusCode;
    private Date registDt;
    private String registId;
    private String statusDesc;

}
