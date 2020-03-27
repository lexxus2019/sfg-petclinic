package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {
    private OwnerServiceMap ownerServiceMap;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap();
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setLastName("Smith");
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1L);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(1L));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void save() {
        Owner owner = new Owner();
        owner.setId(2L);
        Owner saved = ownerServiceMap.save(owner);
        assertEquals(2L, saved.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = new Owner();
        Owner saved = ownerServiceMap.save(owner);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(1L);
        assertEquals(1L, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName("Smith");
        assertNotNull(owner);
        assertEquals(1L, owner.getId());
    }
}