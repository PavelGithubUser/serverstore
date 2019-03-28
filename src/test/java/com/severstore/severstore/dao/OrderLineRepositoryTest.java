package com.severstore.severstore.dao;

import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.entity.OrderLineEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderLineRepositoryTest {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    GoodRepository goodRepository;

    @Test
    public void whenFindById_thenReturnOrderLineEntity() {
        // given
        OrderLineEntity expectedOrderLineEntity = orderLineRepository.save(new OrderLineEntity(10,
                orderRepository.save(new OrderEntity("client1",
                        new Date(Calendar.getInstance().getTime().getTime()), "address1")),
                goodRepository.save(new GoodEntity("name1", 100))));

        // when
        OrderLineEntity actualOrderLineEntity = orderLineRepository.findById(expectedOrderLineEntity.getId()).get();

        //then
        assertEquals(expectedOrderLineEntity, actualOrderLineEntity);
    }

    @Test
    public void whenFindAll_thenReturnListOrderLineEntity() {
        // given
        List<GoodEntity> listGoodEntity = new ArrayList<>();
        listGoodEntity.add(goodRepository.save(new GoodEntity("name1", 100)));
        listGoodEntity.add(goodRepository.save(new GoodEntity("name2", 200)));
        List<OrderEntity> listOrderEntity = new ArrayList<>();
        listOrderEntity.add(orderRepository.save(new OrderEntity("client1",
                new Date(Calendar.getInstance().getTime().getTime()), "address1")));
        listOrderEntity.add(orderRepository.save(new OrderEntity("client2",
                new Date(Calendar.getInstance().getTime().getTime()), "address2")));
        List<OrderLineEntity> listOrderLineEntity = new ArrayList<>();

        listOrderLineEntity.add(new OrderLineEntity(10,
                listOrderEntity.get(0),
                listGoodEntity.get(1)));
        listOrderLineEntity.add(new OrderLineEntity(20,
                listOrderEntity.get(0),
                listGoodEntity.get(1)));

        List<OrderLineEntity> expectedListOrderLineEntity = orderLineRepository.saveAll(listOrderLineEntity);

        // when
        List<OrderLineEntity> actualListOrderLineEntity = orderLineRepository.findAll();

        //then
        assertEquals(expectedListOrderLineEntity, actualListOrderLineEntity);
    }

    @Test
    public void whenSave_thenOrderLineEntity() {
        // given
        OrderLineEntity expectedOrderLineEntity = new OrderLineEntity(10,
                orderRepository.save(new OrderEntity("client1",
                        new Date(Calendar.getInstance().getTime().getTime()), "address1")),
                goodRepository.save(new GoodEntity("name1", 100)));

        // when
        OrderLineEntity actualOrderLineEntity = orderLineRepository.save(expectedOrderLineEntity);

        //then
        assertEquals(expectedOrderLineEntity, actualOrderLineEntity);
    }

    @Test
    public void whenDelete_thenOrderLineEntityShouldBeDeleted(){
        // given
        OrderLineEntity expectedOrderLineEntity = orderLineRepository.save(new OrderLineEntity(10,
                orderRepository.save(new OrderEntity("client1",
                        new Date(Calendar.getInstance().getTime().getTime()), "address1")),
                goodRepository.save(new GoodEntity("name1", 100))));

        // when
        orderLineRepository.deleteById(expectedOrderLineEntity.getId());

        //then
        assertEquals(orderLineRepository.findAll().size(), 0);
    }

}
