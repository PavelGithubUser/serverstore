package com.severstore.severstore.controller;

import com.google.gson.Gson;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.service.GoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GoodController.class)
public class GoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodService goodService;

    @Test
    public void whenGetGoodById_thenReturnGoodDTO() throws Exception {
        // given
        GoodDTO expectedGoodDTO = new GoodDTO(1L, "name1", 100);
        when(goodService.getById(1L)).thenReturn(expectedGoodDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/good/get/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedGoodDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenSave_thenReturnGoodDTO() throws Exception {
        // given
        GoodDTO expectedGoodDTO = new GoodDTO(1L, "name1", 100);
        GoodDTO givenGoodDTO = new GoodDTO("name1", 100);
        Gson gson = new Gson();
        when(goodService.save(givenGoodDTO)).thenReturn(expectedGoodDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/good/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(gson.toJson(givenGoodDTO))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(gson.toJson(expectedGoodDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenGetGoodsList_thenReturnListGoodDTO() throws Exception {
        // given
        List<GoodDTO> expectedListGoodDTO = new ArrayList<>();
        expectedListGoodDTO.add(new GoodDTO(1L, "name1", 100));
        expectedListGoodDTO.add(new GoodDTO(2L, "name2", 200));
        expectedListGoodDTO.add(new GoodDTO(3L, "name3", 300));
        when(goodService.getAll()).thenReturn(expectedListGoodDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/good/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedListGoodDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenGetAllNotAddToOrder_thenReturnListGoodEntity() throws Exception {
        // given
        List<GoodDTO> expectedListGoodDTO = new ArrayList<>();
        expectedListGoodDTO.add(new GoodDTO(1L, "name1", 100));
        expectedListGoodDTO.add(new GoodDTO(2L, "name2", 200));
        expectedListGoodDTO.add(new GoodDTO(3L, "name3", 300));
        when(goodService.getAllNotAddToOrder(1L)).thenReturn(expectedListGoodDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/good/allnotaddtoorder/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedListGoodDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenDeleteById_thenGoodDTOShouldBeDeleted() throws Exception {
        // given
        when(goodService.deleteById(1L)).thenReturn(true);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/good/delete/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(goodService, times(1)).deleteById(1L);
    }

}
