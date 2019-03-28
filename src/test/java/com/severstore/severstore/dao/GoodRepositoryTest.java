package com.severstore.severstore.dao;

import com.severstore.severstore.entity.GoodEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GoodRepositoryTest {

    @Autowired
    GoodRepository goodRepository;

    @Test
    public void whenFindById_thenReturnGoodEntity() {
        // given
        GoodEntity expectedGoodEntity = goodRepository.save(new GoodEntity("name1", 100));

        // when
        GoodEntity actualGoodEntity = goodRepository.findById(expectedGoodEntity.getId()).get();

        //then
        assertEquals(expectedGoodEntity, actualGoodEntity);
    }

    @Test
    public void whenFindAll_thenReturnListGoodEntity() {
        // given
        List<GoodEntity> expectedListGoodEntity = new ArrayList<>();
        expectedListGoodEntity.add(goodRepository.save(new GoodEntity("name1", 100)));
        expectedListGoodEntity.add(goodRepository.save(new GoodEntity("name2", 200)));
        expectedListGoodEntity.add(goodRepository.save(new GoodEntity("name3", 300)));

        // when
        List<GoodEntity> actualListGoodEntity = goodRepository.findAll();

        //then
        assertEquals(expectedListGoodEntity, actualListGoodEntity);
    }

    @Test
    public void whenFindAllDistinctByIdNotIn_thenReturnListGoodEntity() {
        // given
        List<GoodEntity> givenListGoodEntity = new ArrayList<>();
        givenListGoodEntity.add(new GoodEntity("name1", 100));
        givenListGoodEntity.add(new GoodEntity( "name2", 200));
        givenListGoodEntity.add(new GoodEntity( "name3", 300));

        goodRepository.saveAll(givenListGoodEntity);

        List<GoodEntity> listGoodEntity = goodRepository.findAll();

        List<GoodEntity> expectedListGoodEntity = new ArrayList<>();

        expectedListGoodEntity.add(listGoodEntity.get(2));

        List<Long> expectedListGoodEntityId = new ArrayList<>();
        expectedListGoodEntityId.add(listGoodEntity.get(0).getId());
        expectedListGoodEntityId.add(listGoodEntity.get(1).getId());

        // when
        List<GoodEntity> actualListGoodEntity = goodRepository.findDistinctByIdNotIn(expectedListGoodEntityId);

        //then
        assertEquals(expectedListGoodEntity, actualListGoodEntity);
    }

    @Test
    public void whenSave_thenGoodEntity() {
        // given
        GoodEntity expectedGoodEntity = new GoodEntity("name1", 100);

        // when
        GoodEntity actualGoodEntity = goodRepository.save(expectedGoodEntity);

        //then
        assertEquals(expectedGoodEntity, actualGoodEntity);
    }

    @Test
    public void whenDelete_thenGoodEntityShouldBeDeleted(){
        // given
        GoodEntity expectedGoodEntity = goodRepository.save(new GoodEntity( "name1", 100));

        // when
        goodRepository.deleteById(expectedGoodEntity.getId());

        //then
        assertEquals(goodRepository.findAll().size(), 0);
    }

}
