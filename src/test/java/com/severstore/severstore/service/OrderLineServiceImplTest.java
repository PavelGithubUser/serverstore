package com.severstore.severstore.service;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.impl.OrderLineServiceImpl;
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
public class OrderLineServiceImplTest {

    @InjectMocks
    private OrderLineServiceImpl orderLineService;

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
    public void whenGetById_thenReturnOrderLineDTO() {
        // given
        OrderLineEntity givenOrderLineEntity = new OrderLineEntity(1L, 10,
                new OrderEntity(1L, "client1",
                        new Date(Calendar.getInstance().getTime().getTime()), "address1"),
                new GoodEntity(1L, "name1", 100));
        when(orderLineRepository.findById(1L))
                .thenReturn(java.util.Optional.of((givenOrderLineEntity)));

        // when
        OrderLineDTO expectedOrderLineDTO = new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100));
        OrderLineDTO actualOrderLineDTO = orderLineService.getById(1L);

        //then
        assertEquals(expectedOrderLineDTO, actualOrderLineDTO);
    }

    @Test
    public void whenGetAll_thenReturnListOrderLineDTO() {
        // given
        List<OrderLineEntity> givenListOrderLineEntity = new ArrayList<>();
        givenListOrderLineEntity.add(new OrderLineEntity(1L, 10,  new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1"),
                new GoodEntity(1L, "name1", 100)));
        givenListOrderLineEntity.add(new OrderLineEntity(2L, 20, new OrderEntity(2L, "client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2"),
                new GoodEntity(2L, "name2", 200)));
        givenListOrderLineEntity.add(new OrderLineEntity(3L, 30, new OrderEntity(3L, "client3",
                new Date(Calendar.getInstance().getTime().getTime()), "address3"),
                new GoodEntity(3L, "name3", 300)));
        when(orderLineRepository.findAll()).thenReturn(givenListOrderLineEntity);

        List<OrderLineDTO> expectedListOrderLineDTO = new ArrayList<>();
        expectedListOrderLineDTO.add(new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 2L,
                new GoodDTO(2L, "name2", 200)));
        expectedListOrderLineDTO.add(new OrderLineDTO(3L, 30, 3L,
                new GoodDTO(3L, "name3", 300)));

        // when
        List<OrderLineDTO> actualListOrderLineDTO = orderLineService.getAll();

        //then
        assertEquals(expectedListOrderLineDTO, actualListOrderLineDTO);
    }

    @Test
    public void whenGetOrderLineListByOrder_thenReturnListOrderLineDTO() {
        // given
        OrderEntity expectedOrderEntity = new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        List<OrderLineEntity> givenListOrderLineEntity = new ArrayList<>();
        givenListOrderLineEntity.add(new OrderLineEntity(1L, 10,  new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1"),
                new GoodEntity(1L, "name1", 100)));
        givenListOrderLineEntity.add(new OrderLineEntity(2L, 20, new OrderEntity(1L, "client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2"),
                new GoodEntity(2L, "name2", 200)));
        givenListOrderLineEntity.add(new OrderLineEntity(3L, 30, new OrderEntity(1L, "client3",
                new Date(Calendar.getInstance().getTime().getTime()), "address3"),
                new GoodEntity(3L, "name3", 300)));
        expectedOrderEntity.setOrderLineEntities(givenListOrderLineEntity);

        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedOrderEntity));

        List<OrderLineDTO> expectedListOrderLineDTO = new ArrayList<>();
        expectedListOrderLineDTO.add(new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100)));
        expectedListOrderLineDTO.add(new OrderLineDTO(2L, 20, 1L,
                new GoodDTO(2L, "name2", 200)));
        expectedListOrderLineDTO.add(new OrderLineDTO(3L, 30, 1L,
                new GoodDTO(3L, "name3", 300)));

        // when
        List<OrderLineDTO> actualListOrderLineDTO = orderLineService.getOrderLineListByOrder(1L);

        //then
        assertEquals(expectedListOrderLineDTO, actualListOrderLineDTO);
    }

    @Test
    public void whenSave_thenOrderLineDTO() {
        // given
        OrderEntity givenOrderEntity = new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        GoodEntity givenGoodEntity = new GoodEntity(1L, "name1", 100);
        OrderLineEntity givenOrderLineEntity = new OrderLineEntity(1L, 10,
                givenOrderEntity, givenGoodEntity);
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(givenOrderEntity));
        when(goodRepository.findById(1L)).thenReturn(java.util.Optional.of(givenGoodEntity));
        when(orderLineRepository.save(givenOrderLineEntity)).thenReturn(givenOrderLineEntity);

        // when
        OrderLineDTO expectedOrderLineDTO = new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100));
        OrderLineDTO actualOrderLineDTO = orderLineService.save(expectedOrderLineDTO);

        //then
        assertEquals(expectedOrderLineDTO, actualOrderLineDTO);
    }

    @Test
    public void whenDeleteById_thenOrderLineDTOShouldBeDeleted(){
        // given
        OrderEntity givenOrderEntity = new OrderEntity(1L, "client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1");
        GoodEntity givenGoodEntity = new GoodEntity(1L, "name1", 100);
        OrderLineEntity givenOrderLineEntity = new OrderLineEntity(1L, 10,
                givenOrderEntity, givenGoodEntity);
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(givenOrderEntity));
        when(goodRepository.findById(1L)).thenReturn(java.util.Optional.of(givenGoodEntity));
        when(orderLineRepository.save(givenOrderLineEntity)).thenReturn(givenOrderLineEntity);
        OrderLineDTO expectedOrderLineDTO = new OrderLineDTO(1L, 10, 1L,
                new GoodDTO(1L, "name1", 100));
        orderLineService.save(expectedOrderLineDTO);

        // when
        orderLineService.deleteById(1L);

        // verify
        verify(orderLineRepository, times(1)).deleteById(1L);
    }

}
