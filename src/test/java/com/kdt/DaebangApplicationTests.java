package com.kdt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DaebangApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	
	@org.junit.jupiter.api.Test
	@WithMockUser(username = "test1234", password = "test1234!",roles= {"MEMBER"})
	public void test() throws Exception{
		mvc.perform(get("/api/member/myPage")).andDo(print());
	}
	

}
