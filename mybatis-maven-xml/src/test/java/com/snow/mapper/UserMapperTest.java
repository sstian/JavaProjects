package com.snow.mapper;

import com.snow.entity.User;
import com.snow.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private SqlSession session;
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        this.session = MybatisUtil.getSession();
        this.mapper = session.getMapper(UserMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.session.close();
    }

    private void printList(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void getUsers() {
        List<User> users = this.mapper.getUsers();
        this.printList(users);
    }
/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:C:\Users\Snow-Angel\AppData\Local\JetBrains\IntelliJIdea2025.1\captureAgent\debugger-agent.jar=file:///C:/Users/SNOW-A~1/AppData/Local/Temp/capture7758069229489811292.props" -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=9392" -Dkotlinx.coroutines.debug.enable.creation.stack.trace=false -Ddebugger.agent.enable.coroutines=true -Dkotlinx.coroutines.debug.enable.flows.stack.trace=true -Dkotlinx.coroutines.debug.enable.mutable.state.flows.stack.trace=true -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\Snow-Angel\.m2\repository\org\junit\platform\junit-platform-launcher\1.13.4\junit-platform-launcher-1.13.4.jar;C:\Users\Snow-Angel\.m2\repository\org\junit\platform\junit-platform-engine\1.13.4\junit-platform-engine-1.13.4.jar;C:\Users\Snow-Angel\.m2\repository\org\junit\jupiter\junit-jupiter-engine\5.13.4\junit-jupiter-engine-5.13.4.jar;D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar;D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\plugins\junit\lib\junit5-rt.jar;D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\plugins\junit\lib\junit-rt.jar;D:\Develop\JavaProjects\mybatis-maven-xml\target\test-classes;D:\Develop\JavaProjects\mybatis-maven-xml\target\classes;C:\Users\Snow-Angel\.m2\repository\org\mybatis\mybatis\3.5.16\mybatis-3.5.16.jar;C:\Users\Snow-Angel\.m2\repository\org\xerial\sqlite-jdbc\3.50.3.0\sqlite-jdbc-3.50.3.0.jar;C:\Users\Snow-Angel\.m2\repository\org\projectlombok\lombok\1.18.38\lombok-1.18.38.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-classic\1.5.18\logback-classic-1.5.18.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-core\1.5.18\logback-core-1.5.18.jar;C:\Users\Snow-Angel\.m2\repository\org\slf4j\slf4j-api\2.0.17\slf4j-api-2.0.17.jar;C:\Users\Snow-Angel\.m2\repository\org\junit\jupiter\junit-jupiter-api\5.13.4\junit-jupiter-api-5.13.4.jar;C:\Users\Snow-Angel\.m2\repository\org\opentest4j\opentest4j\1.3.0\opentest4j-1.3.0.jar;C:\Users\Snow-Angel\.m2\repository\org\junit\platform\junit-platform-commons\1.13.4\junit-platform-commons-1.13.4.jar;C:\Users\Snow-Angel\.m2\repository\org\apiguardian\apiguardian-api\1.1.2\apiguardian-api-1.1.2.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 com.snow.mapper.UserMapperTest,getUsers
16:32:33,700 |-INFO in ch.qos.logback.classic.LoggerContext[default] - This is logback-classic version 1.5.18
16:32:33,704 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - No custom configurators were discovered as a service.
16:32:33,704 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - Trying to configure with ch.qos.logback.classic.joran.SerializedModelConfigurator
16:32:33,708 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - Constructed configurator of type class ch.qos.logback.classic.joran.SerializedModelConfigurator
16:32:33,711 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.scmo]
16:32:33,711 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.scmo]
16:32:33,729 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - ch.qos.logback.classic.joran.SerializedModelConfigurator.configure() call lasted 4 milliseconds. ExecutionStatus=INVOKE_NEXT_IF_ANY
16:32:33,729 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - Trying to configure with ch.qos.logback.classic.util.DefaultJoranConfigurator
16:32:33,734 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - Constructed configurator of type class ch.qos.logback.classic.util.DefaultJoranConfigurator
16:32:33,739 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
16:32:33,741 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/D:/Develop/JavaProjects/mybatis-maven-xml/target/classes/logback.xml]
16:32:33,944 |-WARN in ch.qos.logback.classic.joran.action.LevelAction - <level> element is deprecated. Near [level] on line 12
16:32:33,944 |-WARN in ch.qos.logback.classic.joran.action.LevelAction - Please use "level" attribute within <logger> or <root> elements instead.
16:32:34,120 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - Processing appender named [stdout]
16:32:34,120 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
16:32:34,153 |-INFO in ch.qos.logback.core.model.processor.ImplicitModelHandler - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
16:32:34,270 |-INFO in ch.qos.logback.core.ConsoleAppender[stdout] - BEWARE: Writing to the console can be very slow. Avoid logging to the
16:32:34,270 |-INFO in ch.qos.logback.core.ConsoleAppender[stdout] - console in production environments, especially in high volume systems.
16:32:34,270 |-INFO in ch.qos.logback.core.ConsoleAppender[stdout] - See also https://logback.qos.ch/codes.html#slowConsole
16:32:34,272 |-INFO in ch.qos.logback.classic.model.processor.LevelModelHandler - com.snow.mapper.UserMapper level set to DEBUG
16:32:34,272 |-INFO in ch.qos.logback.classic.model.processor.RootLoggerModelHandler - Setting level of ROOT logger to DEBUG
16:32:34,273 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [stdout] to Logger[ROOT]
16:32:34,274 |-INFO in ch.qos.logback.core.model.processor.DefaultProcessor@4b40f651 - End of configuration.
16:32:34,275 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@42b02722 - Registering current configuration as safe fallback point
16:32:34,275 |-INFO in ch.qos.logback.classic.util.ContextInitializer@64a40280 - ch.qos.logback.classic.util.DefaultJoranConfigurator.configure() call lasted 541 milliseconds. ExecutionStatus=DO_NOT_INVOKE_NEXT_IF_ANY

DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - Opening JDBC Connection
DEBUG [main] - Created connection 1672598778.
DEBUG [main] - ==>  Preparing: select * from User
DEBUG [main] - ==> Parameters:
DEBUG [main] - <==      Total: 3
User(id=1, name=英杰, password=123456)
User(id=2, name=张三, password=abcdef)
User(id=3, name=李四, password=987654)
DEBUG [main] - Closing JDBC Connection [org.sqlite.jdbc4.JDBC4Connection@63b1d4fa]
DEBUG [main] - Returned connection 1672598778 to pool.

Process finished with exit code 0

 */

    @Test
    void getUserById() {
        User user = this.mapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    void getUserByLike() {
        List<User> users = this.mapper.getUserByLike("李");
        this.printList(users);
    }

    @Test
    void addUser() {
        User user4 = new User(4, "李刚", "haohaiyo");
        User user5 = new User(5, "王五", "helloword");

        int result4 = this.mapper.addUser(user4);
        int result5 = this.mapper.addUser(user5);
        System.out.println(result4);
        System.out.println(result5);
    }

    @Test
    void deleteUser() {
        int result = this.mapper.deleteUser(5);
        System.out.println(result);
    }

    @Test
    void updateUser() {
        User newUser = new User(4, "李铁", "nilaiya");
        int result = this.mapper.updateUser(newUser);
        System.out.println(result);
    }

    @Test
    void updateUserByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", 4);
        map.put("userpwd", "nishishei");

        int result = this.mapper.updateUserByMap(map);
        System.out.println(result);
    }

}