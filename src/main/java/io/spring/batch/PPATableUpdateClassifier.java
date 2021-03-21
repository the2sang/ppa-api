package io.spring.batch;

import io.spring.batch.domain.TaxEmailBillInfoVO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.classify.Classifier;

public class PPATableUpdateClassifier
        implements Classifier<TaxEmailBillInfoVO, ItemWriter<? super TaxEmailBillInfoVO>> {


    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> tbTaxBillInfoEncTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> tbTradeItemListTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> tbStatusHistTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> ifTaxBillResultInfoTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> etsTaxMetaInfoTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> etsTaxMainInfoTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> estTaxLineInfoTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> ifTaxBillInfoTableWriter;
//    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> ifTaxBillItemTableWriter;
    private final JdbcBatchItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoTableWriter;


    public PPATableUpdateClassifier(JdbcBatchItemWriter<TaxEmailBillInfoVO> tbTaxBillInfoEncTableWriter,
                                    JdbcBatchItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoTableWriter ) {
        this.tbTaxBillInfoEncTableWriter = tbTaxBillInfoEncTableWriter;
        this.taxEmailBillInfoTableWriter = taxEmailBillInfoTableWriter;

    }

    @Override
    public ItemWriter<? super TaxEmailBillInfoVO> classify(TaxEmailBillInfoVO classifiable) {

        if(classifiable instanceof TaxEmailBillInfoUpdate) {
            return tbTaxBillInfoEncTableWriter;
        }
        else if(classifiable instanceof TbTaxBillInfoEncUpdate) {
            return taxEmailBillInfoTableWriter;
        }
        else {
            throw new IllegalArgumentException("Invalid type: " + classifiable.getClass().getCanonicalName());
        }
    }



}
