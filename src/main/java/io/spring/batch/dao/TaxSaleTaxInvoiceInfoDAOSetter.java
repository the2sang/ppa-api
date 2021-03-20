package io.spring.batch.dao;

import io.spring.batch.domain.TaxSaleTaxInvoiceInfoVO;
import io.spring.batch.domain.TaxSaleTaxInvoiceMgrVO;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

final class TaxSaleTaxInvoiceInfoDAOSetter implements ItemPreparedStatementSetter<TaxSaleTaxInvoiceMgrVO> {

    @Override
    public void setValues(TaxSaleTaxInvoiceMgrVO mgrVo, PreparedStatement pstmt)
            throws SQLException {

        //기존 소스 참고 - pstmt = new LoggableStatement(conn, sb.toString());

        int idx = 1;
        int result = 0;

        pstmt.setString(idx++, mgrVo.getInfo().getIoCode());
        pstmt.setString(idx++, mgrVo.getInfo().getIssueDay());
        pstmt.setString(idx++, mgrVo.getInfo().getBizManageId());

        //UPDATE
        for (int i=1; i<=2; i++) {
            //20170525 수정 유종일
            //if (i==2) {
            //pstmt.setString(idx++, mgrVo.getInfo().getIoCode());
            //pstmt.setString(idx++, mgrVo.getInfo().getIssueDay());
            //pstmt.setString(idx++, mgrVo.getInfo().getBizManageId());
            //}
            pstmt.setString(idx++, mgrVo.getInfo().getSvcManageId());
            pstmt.setString(idx++, mgrVo.getInfo().getSignature());
            pstmt.setString(idx++, mgrVo.getInfo().getIssueId());
            pstmt.setString(idx++, mgrVo.getInfo().getBillTypeCode());
            pstmt.setString(idx++, mgrVo.getInfo().getPurposeCode());
            pstmt.setString(idx++, mgrVo.getInfo().getAmendmentCode());//10

            pstmt.setString(idx++, mgrVo.getInfo().getDescription());
            pstmt.setString(idx++, mgrVo.getInfo().getImportDocId());
            pstmt.setString(idx++, mgrVo.getInfo().getImportPeriodStartDay());
            pstmt.setString(idx++, mgrVo.getInfo().getImportPeriodEndDay());
            pstmt.setString(idx++, mgrVo.getInfo().getImportItemQuantity());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerPartyId());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerTaxRegistId());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerPartyName());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerCeoName());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerAddr());//20

            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerType());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerClass());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerContactDepart());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerContactName());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerContactPhone());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoicerContactEmail());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeBusinessTypeCode());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceePartyId());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeTaxRegistId());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceePartyName());//30

            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeCeoName());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeAddr());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeType());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeClass());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactDepart1());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactName1());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactPhone1());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactEmail1());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactDepart2());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactName2());//40

            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactPhone2());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeContactEmail2());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerPartyId());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerTaxRegistId());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerPartyName());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerCeoName());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerAddr());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerType());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerClass());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerContactDepart());//50

            pstmt.setString(idx++, mgrVo.getInfo().getBrokerContactName());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerContactPhone());
            pstmt.setString(idx++, mgrVo.getInfo().getBrokerContactEmail());
            pstmt.setString(idx++, mgrVo.getInfo().getPaymentTypeCode1());
            pstmt.setString(idx++, mgrVo.getInfo().getPayAmount1());
            pstmt.setString(idx++, mgrVo.getInfo().getPaymentTypeCode2());
            pstmt.setString(idx++, mgrVo.getInfo().getPayAmount2());
            pstmt.setString(idx++, mgrVo.getInfo().getPaymentTypeCode3());
            pstmt.setString(idx++, mgrVo.getInfo().getPayAmount3());
            pstmt.setString(idx++, mgrVo.getInfo().getPaymentTypeCode4());//60

            pstmt.setString(idx++, mgrVo.getInfo().getPayAmount4());
            pstmt.setString(idx++, mgrVo.getInfo().getChargeTotalAmount());
            pstmt.setString(idx++, mgrVo.getInfo().getTaxTotalAmount());
            pstmt.setString(idx++, mgrVo.getInfo().getGrandTotalAmount());
            pstmt.setString(idx++, mgrVo.getInfo().getStatusCode());

            pstmt.setString(idx++, mgrVo.getInfo().getElectronicReportYn());

            pstmt.setString(idx++, mgrVo.getInfo().getOnlineGubCode());
            pstmt.setString(idx++, mgrVo.getInfo().getAddTaxYn());
            pstmt.setString(idx++, mgrVo.getInfo().getInvoiceeGubCode());
            pstmt.setString(idx++, mgrVo.getInfo().getRelSystemId());//70

            pstmt.setString(idx++, mgrVo.getInfo().getJobGubCode());
            //dt
            //dt
            pstmt.setString(idx++, mgrVo.getUserId());
            pstmt.setString(idx++, mgrVo.getUserId());
            pstmt.setString(idx++, mgrVo.getInfo().getUpperManageId());
            pstmt.setString(idx++, mgrVo.getInfo().getErpSndYn());
            pstmt.setString(idx++, "");//ESERO_RTN_CODE
            pstmt.setString(idx++, mgrVo.getInfo().getErpAccYear());
            pstmt.setString(idx++, mgrVo.getInfo().getErpSlipNo());//80

            pstmt.setString(idx++, mgrVo.getInfo().getTaxTypeCode());
            pstmt.setString(idx++, mgrVo.getInfo().getTaxCancelData());
            pstmt.setString(idx++, mgrVo.getInfo().getErpSendCode());
            pstmt.setString(idx++, mgrVo.getInfo().getVatGubCode());
            pstmt.setString(idx++, mgrVo.getInfo().getReceiptGubCode());
            pstmt.setString(idx++, mgrVo.getInfo().getUuid());


            if(mgrVo.getMain().getAspIssueId().equals("")|| mgrVo.getMain().getAspIssueId().length() < 24 ){			//2012.05.29.
                pstmt.setString(idx++, mgrVo.getInfo().getIssueId());
            }else{
                pstmt.setString(idx++, mgrVo.getMain().getAspIssueId());
            }

            if (i==1) {
                pstmt.setString(idx++, mgrVo.getInfo().getIoCode());
                pstmt.setString(idx++, mgrVo.getInfo().getIssueDay());
                pstmt.setString(idx++, mgrVo.getInfo().getBizManageId());
            }
        }

    }
    /*
    @Override
    public void setValues(TaxSaleTaxInvoiceInfoVO item, PreparedStatement ps) throws SQLException {

    }
     */
}
