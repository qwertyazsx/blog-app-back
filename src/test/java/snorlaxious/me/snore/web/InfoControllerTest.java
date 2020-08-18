package snorlaxious.me.snore.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = InfoController.class)
public class InfoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello() throws Exception {
        String hello = "Hello World!";
        mvc.perform(get("/api/v1/info/hello"))
           .andExpect(status().isOk())
           .andExpect(content().string(hello))
           .andDo(print());
    }

    @Test
    public void time() throws Exception {
        mvc.perform(get("/api/v1/info/time"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("Server time: ")))
           .andDo(print());
    }
}
