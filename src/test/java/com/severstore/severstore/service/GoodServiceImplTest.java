package com.severstore.severstore.service;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.impl.GoodServiceImpl;
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
public class GoodServiceImplTest {

    @InjectMocks
    private GoodServiceImpl goodService;

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
        when(goodRepository.findById(1L))
                .thenReturn(java.util.Optional.of((new GoodEntity(1L, "name", 100))));

        // when
        GoodDTO expectedGoodDTO = new GoodDTO(1L, "name", 100);
        GoodDTO actualGoodDTO = goodService.getById(1L);

        //then
        assertEquals(expectedGoodDTO, actualGoodDTO);
    }

    @Test
    public void whenGetAll_thenReturnListGoodDTO() {
        // given
        List<GoodEntity> expectedListGoodEntity = new ArrayList<>();
        expectedListGoodEntity.add(new GoodEntity(1L, "name1", 100));
        expectedListGoodEntity.add(new GoodEntity(2L, "name2", 200));
        expectedListGoodEntity.add(new GoodEntity(3L, "name3", 300));
        when(goodRepository.findAll()).thenReturn(expectedListGoodEntity);

        // when
        List<GoodDTO> expectedListGoodDTO = new ArrayList<>();
        expectedListGoodDTO.add(new GoodDTO(1L, "name1", 100));
        expectedListGoodDTO.add(new GoodDTO(2L, "name2", 200));
        expectedListGoodDTO.add(new GoodDTO(3L, "name3", 300));
        List<GoodDTO> actualListGoodEntity = goodService.getAll();

        //then
        assertEquals(expectedListGoodDTO, actualListGoodEntity);
    }

    @Test
    public void whenGetAllNotAddToOrder_thenReturnListGoodEntity() {
        // given
        OrderEntity orderEntity = new OrderEntity(1L,
                "client1",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address1");
        List<OrderLineEntity> listOrderLineEntity = new ArrayList<>();
        listOrderLineEntity.add(new OrderLineEntity(10,
                new OrderEntity(),
                new GoodEntity(2L, "name2", 200)));
        listOrderLineEntity.add(new OrderLineEntity(15,
                new OrderEntity(),
                new GoodEntity(3L, "name3", 300)));
        listOrderLineEntity.add(new OrderLineEntity(20,
                new OrderEntity(),
                new GoodEntity(4L, "name4", 400)));

        orderEntity.setOrderLineEntities(listOrderLineEntity);

        List<GoodEntity> expectedListGoodEntity = new ArrayList<>();
        expectedListGoodEntity.add(new GoodEntity(1L, "name3", 300));
        expectedListGoodEntity.add(new GoodEntity(5L, "name4", 400));

        List<GoodDTO> expectedListGoodDTO = new ArrayList<>();
        expectedListGoodDTO.add(new GoodDTO(1L, "name3", 300));
        expectedListGoodDTO.add(new GoodDTO(5L, "name4", 400));

        List<Long> givenListGoodEntityId = new ArrayList<>();
        givenListGoodEntityId.add(2L);
        givenListGoodEntityId.add(3L);
        givenListGoodEntityId.add(4L);

        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(orderEntity));
        when(goodRepository.findDistinctByIdNotIn(givenListGoodEntityId)).thenReturn(expectedListGoodEntity);

        // when
        List<GoodDTO> actualListGoodEntity = goodService.getAllNotAddToOrder(1L);

        //then
        assertEquals(expectedListGoodDTO, actualListGoodEntity);
    }

    @Test
    public void whenSave_thenGoodDTO() {
        // given
        GoodEntity givenGoodEntity = new GoodEntity(1L, "name1", 100);
        when(goodRepository.save(givenGoodEntity)).thenReturn(givenGoodEntity);

        // when
        GoodDTO expectedGoodDTO = new GoodDTO(1L, "name1", 100);
        GoodDTO actualGoodDTO = goodService.save(expectedGoodDTO);

        //then
        assertEquals(expectedGoodDTO, actualGoodDTO);
    }

    @Test
    public void whenDeleteById_thenGoodDTOShouldBeDeleted(){
        // given
        List<OrderLineEntity> expectedListOrderLineEntity = new ArrayList<>();
        expectedListOrderLineEntity.add(new OrderLineEntity());
        GoodEntity expectedGoodEntity = new GoodEntity(1L, "name", 100);
        expectedGoodEntity.setOrderLineEntities(expectedListOrderLineEntity);
        when(goodRepository.findById(expectedGoodEntity.getId())).
                thenReturn(java.util.Optional.of((expectedGoodEntity)));

        // when
        goodService.deleteById(1L);

        // verify
        verify(orderLineRepository, times(1)).deleteAll(expectedListOrderLineEntity);
        verify(goodRepository, times(1)).deleteById(1L);
    }

}
