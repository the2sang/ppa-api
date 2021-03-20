package io.spring.batch.domain;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxEmailBillItemVO {

    private String issueId;
    private String issueDay;
    private int seqNo;
    private String purchaseDay;
    private String itemName;
    private String itemInfo;
    private String itemDesc;
    private long unitQuantity;
    private long unitAmount;
    private long invoiceAmount;
    private long taxAmount;

}
