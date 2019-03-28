package com.severstore.severstore.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.severstore.severstore.dto.OrderDTO;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void whenGetOrderById_thenReturnOrderDTO() throws Exception {
        // given
        OrderDTO expectedOrderDTO = new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        when(orderService.getById(1L)).thenReturn(expectedOrderDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/order/get/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONAssert.assertEquals(gson.toJson(expectedOrderDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenSave_thenReturnOrderDTO() throws Exception {
        // given
        OrderDTO expectedOrderDTO = new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        OrderDTO givenOrderDTO = new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        when(orderService.save(givenOrderDTO)).thenReturn(expectedOrderDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/order/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(gson.toJson(givenOrderDTO))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        JSONAssert.assertEquals(gson.toJson(expectedOrderDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenGetOrdersList_thenReturnListOrderDTO() throws Exception {
        // given
        List<OrderDTO> expectedListOrderDTO = new ArrayList<>();
        expectedListOrderDTO.add(new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1"));
        expectedListOrderDTO.add(new OrderDTO(2L, "client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2"));
        expectedListOrderDTO.add(new OrderDTO(3L, "client3",
                new Date(Calendar.getInstance().getTime().getTime()), "address3"));
        when(orderService.getAll()).thenReturn(expectedListOrderDTO);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/order/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONAssert.assertEquals(gson.toJson(expectedListOrderDTO),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void whenDeleteById_thenOrderDTOShouldBeDeleted() throws Exception {
        // given
        when(orderService.deleteById(1L)).thenReturn(true);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/order/delete/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(orderService, times(1)).deleteById(1L);
    }

}
