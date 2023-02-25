package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TranslationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void dbAccessWorks() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
	//			.andExpect(content().json("[{\"id\":1,\"shortName\":\"en\",\"fullName\":\"english\"}]"))
		;
	}

	@Test
	public void translateWorks() throws Exception {
		MultiValueMap<String, String> translationParams = new LinkedMultiValueMap<>();
		translationParams.add("targetLanguageName","English" );
		translationParams.add("text","Guten Morgen" );
		this.mockMvc.perform(get("/translate").queryParams(translationParams)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Good morning")));
	}

}
