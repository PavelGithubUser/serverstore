package com.severstore.severstore.controller;

import com.google.gson.Gson;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.service.GoodService;
import com.severstore.severstore.service.OrderLineService;
import com.severstore.severstore.service.OrderService;
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
@WebMvcTest(OrderLineController.class)
public class OrderLineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineService orderLineService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private GoodService goodService;

    @Test
    public void whenGetOrderLineById_thenReturnOrderLineOrderLineDTO() throws Exception {
        // given
        OrderLineDTO expectedOrderLineDTO = new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100));
        when(orderLineService.getById(1L)).thenReturn(expectedOrderLineDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/orderline/get/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedOrderLineDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenSave_thenReturnOrderLineDTO() throws Exception {
        // given
        OrderLineDTO expectedOrderLineDTO = new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100));
        Gson gson = new Gson();
        when(orderLineService.save(expectedOrderLineDTO)).thenReturn(expectedOrderLineDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/orderline/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(gson.toJson(expectedOrderLineDTO))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(gson.toJson(expectedOrderLineDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenGetOrderLineList_thenReturnListOrderLineOrderLineDTO() throws Exception {
        // given
        List<OrderLineDTO> expectedListOrderLineDTO = new ArrayList<>();
        expectedListOrderLineDTO.add(new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 2L,
                new GoodDTO(2L, "name2", 200)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 2L,
                new GoodDTO(2L, "name2", 200)));
        when(orderLineService.getAll()).thenReturn(expectedListOrderLineDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/orderline/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedListOrderLineDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }



    @Test
    public void whenGetOrderLineListByOrder_thenReturnListOrderLineOrderLineDTO() throws Exception {
        // given
        List<OrderLineDTO> expectedListOrderLineDTO = new ArrayList<>();
        expectedListOrderLineDTO.add(new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 1L,
                new GoodDTO(2L, "name2", 200)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 1L,
                new GoodDTO(2L, "name2", 200)));
        when(orderLineService.getOrderLineListByOrder(1L)).thenReturn(expectedListOrderLineDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/orderline/allbyorder/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(new Gson().toJson(expectedListOrderLineDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenDeleteById_thenOrderLineDTOShouldBeDeleted() throws Exception {
        // given
        when(orderLineService.deleteById(1L)).thenReturn(true);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/orderline/delete/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(orderLineService, times(1)).deleteById(1L);
    }

}
