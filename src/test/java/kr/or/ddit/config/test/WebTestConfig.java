package kr.or.ddit.config.test;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.config.RootConfig;
import kr.or.ddit.config.ServletConfig;
import kr.or.ddit.config.spring.DatasourceConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * @ContextConfiguration(locations =
 * {"classpath:kr/or/ddit/config/spring/servlet-context.xml",
 * "classpath:kr/or/ddit/config/spring/context-root.xml",
 * "classpath:kr/or/ddit/config/spring/context-datasource-test.xml",
 * "classpath:kr/or/ddit/config/spring/context-transaction.xml" })
 */
@ContextConfiguration(classes = {ServletConfig.class, RootConfig.class, DatasourceConfigTest.class})
@WebAppConfiguration //스프링 컨테이너를 구성할 web기반 application context로 구성
public class WebTestConfig {
	@Resource(name="dataSource")
	private BasicDataSource datasource;
	
	//controller를 테스트 하기 위해 필요한 것 2가지
	//applicationContext : 스프링 컨테이너
	//MockMvc : dispathcerServlet (applicationContext 객체를 통해 생성)
		
	//주입하려고하는 필드의 타입과 일치할 경우 이름과 관계없이 주입
	//만약 주입하려고 하는 필드의 타입과 스프링 빈중에 타입이 일치하는 빈이 2개이상 존재할경우 에러
	@Autowired 
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	 
	@Before
	public void setup() {
		//init.sql에 있는 모든 sql 문장을  테스트 메소드 실행전에 실행
	    //init.sql에는 table 데이터 삭제, 데이터 입력 sql 문장이 있다.
	    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(new ClassPathResource("/kr/or/ddit/db/init.sql"));
	    populator.setContinueOnError(false);   //init.sql을 실행하다 에러가 발생할 경우 중지 
	    DatabasePopulatorUtils.execute(populator, datasource);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Ignore
	@Test
	public void dummy() {}

}
