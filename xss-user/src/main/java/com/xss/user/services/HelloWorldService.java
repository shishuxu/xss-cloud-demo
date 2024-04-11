package com.xss.user.services;

import com.xss.user.entity.TestName;
import com.xss.user.mapper.TestNameMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 测试类
 */
@Service
public class HelloWorldService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    //验证 事务注解失效的原因
    @Transactional
    public void update() {
        String a = "update t_test_name set name='2'  where id=1";
        jdbcTemplate.update(a);
        throw new RuntimeException();
    }

    public void update1() {
        update();
    }

    //-------------------------------------------------------------------------------------------
    // 验证 mybatis的大数据量的 批处理  提升明显
    @Transactional
    public void batchInsertUsers() {
        try (
                SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
                //在（）声明的变量，不必再在finally中手动关闭，但该类必须是继承了Closeable

        ) {
            TestNameMapper mapper = session.getMapper(TestNameMapper.class);
            List<TestName> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                TestName t = TestName.builder().id(i).age(2).name("abd")
                        .createTime(System.currentTimeMillis()).build();
                list.add(t);
            }
            for (TestName user : list) {
                mapper.insert(user);
                //调用方式二：直接使用 SqlSession的api
//                session.insert("com.xss.test.mapper.insert",user);

            }
            // 提交事务，执行批量插入
            session.commit();
            // 清除缓存，避免内存溢出
            session.clearCache();
        }
    }

    // 验证 jdbc的批处理 ---------------------------------------------------------------------
    public static void generalInsert() throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&charactorEncoding=utf8&useSSL=true&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "123456");
        connection.setAutoCommit(false);
        PreparedStatement cmd = connection.prepareStatement("insert into t_test_name values(?,?,?,?)");
        for (int i = 0; i < 1000000; i++) {
            cmd.setInt(1, i + 1);
            cmd.setInt(2, 1);
            cmd.setString(3, "test");
            cmd.setLong(4, System.currentTimeMillis());
            cmd.addBatch();
            if (i % 1000 == 0) {
                cmd.executeBatch();
            }
        }
        cmd.executeBatch();
        connection.commit();
        cmd.close();
        connection.close();

        long end = System.currentTimeMillis();
        System.out.println(end - start);//107170 76330 毫秒
    }

//---------------测试aop----------------------------------------------------------------------------

    public void testAop(){
        System.out.println("service aop");
    }
}
