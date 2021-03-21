package io.spring.batch.writer.preparedStatmementSetter;

import io.spring.batch.domain.TaxEmailBillInfoVO;
import io.spring.batch.domain.TbTaxBillInfoEncVO;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

final public class TbTaxBillInfoEncSetter implements ItemPreparedStatementSetter<TaxEmailBillInfoVO> {

    @Override
    public void setValues(TaxEmailBillInfoVO vo, PreparedStatement pstmt) throws SQLException {
        int idx = 1;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        pstmt.setString(idx++, "2"); //ioCode   //1-매출, 2-매입
        pstmt.setString(idx++, vo.getIssueDay());

        //키값 중복방지를 위해 임의로 uuid로 설정해줌 (수정필요)
        // bizManageId= UUID.randomUUID().toString();


        pstmt.setString(idx++, sdf.format(timestamp).toString()); //bizManageId

        //UPDATE
        for (int i=1; i<=2; i++) {
            //20170525 수정 유종일
            //if (i==2) {
            //pstmt.setString(idx++, vo.getIoCode());
            //pstmt.setString(idx++, vo.getIssueDay());
            //pstmt.setString(idx++, vo.getBizManageId());
            //}
            pstmt.setString(idx++, ""); //svcManageId
            pstmt.setString(idx++, ""); //signature
            pstmt.setString(idx++, vo.getIssueId());
            pstmt.setString(idx++, vo.getBillTypeCode());
            pstmt.setString(idx++, vo.getPurposeCode());
            pstmt.setString(idx++, vo.getAmendmentCode());//10
            pstmt.setString(idx++, vo.getDescription());
            pstmt.setString(idx++, vo.getImportDocId());
            pstmt.setString(idx++, vo.getImportPeriodStartDay());
            pstmt.setString(idx++, vo.getImportPeriodEndDay());
            pstmt.setInt(idx++, vo.getImportItemQuantity());
            pstmt.setString(idx++, vo.getInvoicerPartyId());
            pstmt.setString(idx++, vo.getInvoicerTaxRegistId());
            pstmt.setString(idx++, vo.getInvoicerPartyName());
            pstmt.setString(idx++, vo.getInvoicerCeoName());
            pstmt.setString(idx++, vo.getInvoicerAddr());//20
            pstmt.setString(idx++, vo.getInvoicerType());
            pstmt.setString(idx++, vo.getInvoicerClass());
            pstmt.setString(idx++, vo.getInvoicerContactDepart());
            pstmt.setString(idx++, vo.getInvoicerContactName());
            pstmt.setString(idx++, vo.getInvoicerContactPhone());
            pstmt.setString(idx++, vo.getInvoicerContactEmail());
            pstmt.setString(idx++, vo.getInvoiceeBusinessTypeCode());
            pstmt.setString(idx++, vo.getInvoiceePartyId());
            pstmt.setString(idx++, vo.getInvoiceeTaxRegistId());
            pstmt.setString(idx++, vo.getInvoiceePartyName());//30
            pstmt.setString(idx++, vo.getInvoiceeCeoName());
            pstmt.setString(idx++, vo.getInvoiceeAddr());
            pstmt.setString(idx++, vo.getInvoiceeType());
            pstmt.setString(idx++, vo.getInvoiceeClass());
            pstmt.setString(idx++, vo.getInvoiceeContactDepart1());
            pstmt.setString(idx++, vo.getInvoiceeContactName1());
            pstmt.setString(idx++, vo.getInvoiceeContactPhone1());
            pstmt.setString(idx++, vo.getInvoiceeContactEmail1());
            pstmt.setString(idx++, vo.getInvoiceeContactDepart2());
            pstmt.setString(idx++, vo.getInvoiceeContactName2());//40
            pstmt.setString(idx++, vo.getInvoiceeContactPhone2());
            pstmt.setString(idx++, vo.getInvoiceeContactEmail2());
            pstmt.setString(idx++, vo.getBrokerPartyId());
            pstmt.setString(idx++, vo.getBrokerTaxRegistId());
            pstmt.setString(idx++, vo.getBrokerPartyName());
            pstmt.setString(idx++, vo.getBrokerCeoName());
            pstmt.setString(idx++, vo.getBrokerAddr());
            pstmt.setString(idx++, vo.getBrokerType());
            pstmt.setString(idx++, vo.getBrokerClass());
            pstmt.setString(idx++, vo.getBrokerContactDepart());//50
            pstmt.setString(idx++, vo.getBrokerContactName());
            pstmt.setString(idx++, vo.getBrokerContactPhone());
            pstmt.setString(idx++, vo.getBrokerContactEmail());
            pstmt.setString(idx++, vo.getPaymentTypeCode1());
            pstmt.setLong(idx++, vo.getPayAmount1());
            pstmt.setString(idx++, vo.getPaymentTypeCode2());
            pstmt.setLong(idx++, vo.getPayAmount2());
            pstmt.setString(idx++, vo.getPaymentTypeCode3());
            pstmt.setLong(idx++, vo.getPayAmount3());
            pstmt.setString(idx++, vo.getPaymentTypeCode4());//60
            pstmt.setLong(idx++, vo.getPayAmount4());
            pstmt.setLong(idx++, vo.getChargeTotalAmount());
            pstmt.setLong(idx++, vo.getTaxTotalAmount());
            pstmt.setLong(idx++, vo.getGrandTotalAmount());
            pstmt.setString(idx++, "");  //statusCode
            pstmt.setString(idx++, "N"); //electronicReportYn  전자신고완료여부 ( N:신고대상, Y:신고완료, F:미신고대상(미동의)
            pstmt.setString(idx++, ""); //onlineGubcode
            pstmt.setString(idx++, "N"); //addTaxYn 가산세 여부
            pstmt.setString(idx++, ""); //invoiceGubCode
            pstmt.setString(idx++, "");//70 //relSystemId
            pstmt.setString(idx++, ""); //jobGubCode
            pstmt.setString(idx++, "ppauser");   // 사용자 ID를 가져올 수 없기에 임의로 설정..registId
            pstmt.setString(idx++, "ppauser");  // 사용자 ID를 가져올 수 없기에 임의로 설정..modifyId
            pstmt.setString(idx++, ""); //upperManageId
            pstmt.setString(idx++, ""); //erpSndYn
            pstmt.setString(idx++, "");//eseroRtnCode
            pstmt.setString(idx++, ""); //erpAccYear
            pstmt.setString(idx++, "");//80 //erpSlipNo

            pstmt.setString(idx++, ""); //taxTypeCode
            pstmt.setString(idx++, ""); //taxCancelData
            pstmt.setString(idx++, ""); //erpSendCode
            pstmt.setString(idx++, ""); //vatGubCode
            pstmt.setString(idx++, ""); //receiptGubCode
            pstmt.setString(idx++, ""); //uuid
            pstmt.setString(idx++, vo.getIssueId());


//            if(vo.getMain().getAspIssueId().equals("")|| vo.getMain().getAspIssueId().length() < 24 ){			//2012.05.29.
//                pstmt.setString(idx++, vo.getIssueId());
//            }else{
//                pstmt.setString(idx++, vo.getMain().getAspIssueId());
//            }

            if (i==1) {
                pstmt.setString(idx++, "2"); //ioCode  //1-매출, 2-매입
                pstmt.setString(idx++, vo.getIssueDay()); //issueDay

                //키값 중복방지를 위해 임의로 uuid로 설정해줌 (수정필요)
                pstmt.setString(idx++,  sdf.format(timestamp)); //bizManageId
            }
        }

    }

}
