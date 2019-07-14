package org.zerock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest //이 어노테이션을 사용하면 @Controller, @Component, @ControllerAdvice 등 작성된 코드를 인식할 수 있다
public class SampleControllerTests {

	@Autowired
	MockMvc mock; //컨트롤러를 테스트 할때 사용(@WebMvcTest 사용시 @Autowired 주입만으로 가능)
	
	@Test
	public void testHello() throws Exception {
		
		//단순 예상 결과 확인
		mock.perform(get("/hello")).andExpect(content().string("Hello World"));
		
		//응답 상태 및 예상 결과 확인
		MvcResult result = mock.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World"))
				.andReturn();		
		System.out.println(result.getResponse().getContentAsString()); //결과 확인
	}
	
}
