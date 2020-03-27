package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {
    private Owner owner;
    private Set<Owner> owners;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService service;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owner = new Owner();
        owner.setId(1L);
        owner.setLastName("Smith");
        owners.add(owner);
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setLastName("Paul");
        owners.add(owner2);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner smith = service.findByLastName("Smith");
        assertEquals("Smith", smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> owners = service.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner owner = service.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setLastName("Paul");
        owners.add(owner3);
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = service.save(owner3);
        assertNotNull(savedOwner);
       //// assertEquals(1, savedOwner.getId());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}