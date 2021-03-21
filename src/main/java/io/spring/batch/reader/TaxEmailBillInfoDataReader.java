package io.spring.batch.reader;

import io.spring.batch.domain.TaxEmailBillInfoRowMapper;
import io.spring.batch.domain.TaxEmailBillInfoVO;
import lombok.Setter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class TaxEmailBillInfoDataReader {

    @Autowired
    @Setter
    private DataSource dataSource;

    public final int READER_FETCH_SIZE = 10;

    public JdbcPagingItemReader<TaxEmailBillInfoVO> getPagingReader() {
        JdbcPagingItemReader<TaxEmailBillInfoVO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(READER_FETCH_SIZE);
        reader.setRowMapper(new TaxEmailBillInfoRowMapper());

        reader.setQueryProvider(createQuery());

//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("MAIL_GUB_CODE", "2");  // 개별메일인 경우 (1-대표메일)
//        //parameters.put("MAIL_STATUS_CODE", "is null");  //메일진행상태(null:DEFAULT,'01':작성중및회계처리중,'02'회계처리완료,'98':공급받는자반려
//
//        reader.setParameterValues(parameters);
        return reader;
    }

    private OraclePagingQueryProvider createQuery() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("ISSUE_DAY", Order.ASCENDING);

        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("*");
        queryProvider.setFromClause("from TAX_EMAIL_BILL_INFO");
        queryProvider.setWhereClause(getWhereClause());
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

    private String getWhereClause() {

        StringBuilder sb = new StringBuilder();

        sb.append("MAIL_GUB_CODE = '2'");  // 개별메일인 경우 (1-대표메일)
        sb.append("   AND MAIL_STATUS_CODE IS NULL");  //메일진행상태(null:DEFAULT,'01':작성중및회계처리중,'02'회계처리완료,'98':공급받는자반려
        sb.append("   AND ISSUE_DAY LIKE '2016%'");  //1건 테스트를 위해 선택

        //sb.append("  AND ISSUE_DT >= TO_CHAR(sysdate - 30, 'YYYYMMDDhh24miss')");  // 최근 30일 이후 발생분.. 바뀔 가능성 높음

        return sb.toString();
    }


}
