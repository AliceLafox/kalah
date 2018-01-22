package net.lafox.demo.kalah.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.GameDto;
import net.lafox.demo.kalah.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;
    private GameDto game;

    @Before
    public void setUp() throws Exception {
        game = new Game().asDto();
        given(gameService.nextTurn(game)).willReturn(game);

    }

    @Test
    public void testNewGameDefaultGame() throws Exception {
        given(gameService.newGame(null)).willReturn(game);
        String json = mapper.writeValueAsString(game);
        mvc.perform(get(ApiController.API_PATH + "/newGame")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testNextTurn() throws Exception {
        String json = mapper.writeValueAsString(game);
        mvc.perform(post(ApiController.API_PATH + "/nextTurn")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testNewGameWithGivenSeedsCount() throws Exception {
        int seeds = 4;
        game = new Game(seeds).asDto();
        given(gameService.newGame(seeds)).willReturn(game);
        String json = mapper.writeValueAsString(game);

        mvc.perform(get(ApiController.API_PATH + "/newGame?seeds=" + seeds)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json))
                .andReturn();
    }


}