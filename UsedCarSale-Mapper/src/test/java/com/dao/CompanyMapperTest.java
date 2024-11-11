package com.dao;

import com.pojo.Company;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description: 测试公司dao层
 * @author: bahjan
 * @date: 2021-04-26
 **/
public class CompanyMapperTest {

    private static Logger logger = LoggerFactory.getLogger(CompanyMapperTest.class);

    private ApplicationContext ac;

    @Before
    public void setUp() throws Exception {
        this.ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
    }

    /**
     * @description: 测试连接
     * @param:
     * @return:
     * @author: bahjan
     * @date: 2021-04-26 11:39
     **/
    @Test
    public void testConnect() throws SQLException {
        DataSource dataSource = (DataSource) this.ac.getBean("dataSource");
        Connection conn = dataSource.getConnection();
        logger.debug(conn.toString());
    }

    /**
     * @description: 测试根据主键id查询
     * @param:
     * @return:
     * @author: bahjan
     * @date: 2021-04-26 11:39
     **/
    @Test
    public void testQueryByPK() {
        CompanyMapper companyMapper = this.ac.getBean(CompanyMapper.class);
        Company company = companyMapper.selectCompanyById(1);
        logger.debug("公司名称：" + company.getCompanyName());
    }

}