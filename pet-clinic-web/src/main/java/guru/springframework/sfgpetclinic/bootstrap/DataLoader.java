package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            setupData();
        }
    }

    private void setupData() {
        PetType petType1 = new PetType();
        petType1.setName("dog");
        PetType savedDog = petTypeService.save(petType1);

        PetType petType2 = new PetType();
        petType2.setName("cat");
        PetType savedCat = petTypeService.save(petType2);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kate");
        owner1.setLastName("Kozlova");
        owner1.setAddress("volzhskaya");
        owner1.setCity("Rybins");
        owner1.setPhone("3424235325");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedCat);
        mikesPet.setOwner(owner1);
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kate");
        owner2.setLastName("Utkina");
        owner2.setAddress("volochaevskaya");
        owner2.setCity("Rybins");
        owner2.setPhone("6546452654");

        Pet ow2Pet = new Pet();
        ow2Pet.setPetType(savedDog);
        ow2Pet.setOwner(owner1);
        owner2.getPets().add(ow2Pet);

        ownerService.save(owner2);

        Speciality spec1 = new Speciality();
        spec1.setDescription("heromant");
        spec1 = specialityService.save(spec1);

        Speciality spec2 = new Speciality();
        spec2.setDescription("hvostolog");
        spec2 = specialityService.save(spec2);

        System.out.println("Loaded owners...." + ownerService.findAll().size());

        Vet vet1 = new Vet();
        vet1.setFirstName("Maxx");
        vet1.setLastName("Cvetkov");
        vet1.getSpecialities().add(spec1);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Leha");
        vet2.setLastName("Volkov");
        vet2.getSpecialities().add(spec2);

        vetService.save(vet2);

        System.out.println("Loaded vets...." + vetService.findAll().size());
    }
}
