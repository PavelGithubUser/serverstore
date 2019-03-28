package com.severstore.severstore.dao;

import com.severstore.severstore.entity.OrderEntity;
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
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void whenFindById_thenReturnOrderEntity() {
        // given
        OrderEntity expectedOrderEntity = orderRepository.save(new OrderEntity(1L, "client1", new Date(Calendar.getInstance().getTime().getTime()), "address1"));

        // when
        OrderEntity actualOrderEntity = orderRepository.findById(expectedOrderEntity.getId()).get();

        //then
        assertEquals(expectedOrderEntity, actualOrderEntity);
    }

    @Test
    public void whenFindAll_thenReturnListOrderEntity() {
        // given
        List<OrderEntity> expectedListOrderEntity = new ArrayList<>();
        expectedListOrderEntity.add(orderRepository.save(new OrderEntity("client1",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address1")));
        expectedListOrderEntity.add(orderRepository.save(new OrderEntity("client2",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address2")));
        expectedListOrderEntity.add(orderRepository.save(new OrderEntity("client3",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address3")));

        // when
        List<OrderEntity> actualListOrderEntity = orderRepository.findAll();

        //then
        assertEquals(expectedListOrderEntity, actualListOrderEntity);
    }

    @Test
    public void whenSave_thenOrderEntity() {
        // given
        OrderEntity expectedOrderEntity = new OrderEntity("client1",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address1");

        // when
        OrderEntity actualOrderEntity = orderRepository.save(expectedOrderEntity);

        //then
        assertEquals(expectedOrderEntity, actualOrderEntity);
    }

    @Test
    public void whenDelete_thenOrderEntityShouldBeDeleted(){
        // given
        OrderEntity expectedOrderEntity = orderRepository.save(new OrderEntity( "client1",
                new Date(Calendar.getInstance().getTime().getTime()),
                "address1"));

        // when
        orderRepository.deleteById(expectedOrderEntity.getId());

        //then
        assertEquals(orderRepository.findAll().size(), 0);
    }

}
