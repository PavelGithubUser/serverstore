package com.severstore.severstore.service;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.OrderDTO;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    GoodRepository goodRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderLineRepository orderLineRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetById_thenReturnGoodDTO() {
        // given
        OrderEntity givenOrderEntity = new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");

        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(givenOrderEntity));

        // when
        OrderDTO expectedOrderDTO = new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        OrderDTO actualOrderDTO = orderService.getById(1L);

        //then
        assertEquals(expectedOrderDTO, actualOrderDTO);
    }

    @Test
    public void whenGetAll_thenReturnListGoodDTO() {
        // given
        List<OrderEntity> expectedListOrderEntity = new ArrayList<>();
        expectedListOrderEntity.add(new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1"));
        expectedListOrderEntity.add(new OrderEntity(2L, "client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2"));
        expectedListOrderEntity.add(new OrderEntity(3L, "client3",
                new Date(Calendar.getInstance().getTime().getTime()), "address3"));
        when(orderRepository.findAll()).thenReturn(expectedListOrderEntity);

        // when
        List<OrderDTO> expectedListOrderDTO = new ArrayList<>();
        expectedListOrderDTO.add(new OrderDTO(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1"));
        expectedListOrderDTO.add(new OrderDTO(2L, "client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2"));
        expectedListOrderDTO.add(new OrderDTO(3L, "client3",
                new Date(Calendar.getInstance().getTime().getTime()), "address3"));
        List<OrderDTO> actualListOrderEntity = orderService.getAll();

        //then
        assertEquals(expectedListOrderDTO, actualListOrderEntity);
    }

    @Test
    public void whenSave_thenGoodDTO() {
        // given
        Date cuurentDate = new Date(Calendar.getInstance().getTime().getTime());
        OrderEntity givenOrderEntity = new OrderEntity(1L, "client1",
                cuurentDate, "address1");
        when(orderRepository.save(givenOrderEntity)).thenReturn(givenOrderEntity);
        OrderEntity test = orderRepository.save(givenOrderEntity);
        // when
        OrderDTO expectedOrderDTO = new OrderDTO(1L, "client1",
                cuurentDate, "address1");
        OrderDTO actualOrderDTO = orderService.save(expectedOrderDTO);

        //then
        assertEquals(expectedOrderDTO, actualOrderDTO);
    }

    @Test
    public void whenDeleteById_thenGoodDTOShouldBeDeleted(){
        // given
        List<OrderLineEntity> expectedListOrderLineEntity = new ArrayList<>();
        expectedListOrderLineEntity.add(new OrderLineEntity());
        OrderEntity expectedOrderEntity = new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        expectedOrderEntity.setOrderLineEntities(expectedListOrderLineEntity);
        when(orderRepository.findById(expectedOrderEntity.getId())).
                thenReturn(java.util.Optional.of((expectedOrderEntity)));

        // when
        orderService.deleteById(1L);

        // verify
        verify(orderLineRepository, times(1)).deleteAll(expectedListOrderLineEntity);
        verify(orderRepository, times(1)).deleteById(1L);
    }

}
