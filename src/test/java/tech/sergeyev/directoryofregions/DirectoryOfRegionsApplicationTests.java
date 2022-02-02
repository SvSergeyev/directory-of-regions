package tech.sergeyev.directoryofregions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.sergeyev.directoryofregions.persistence.dao.RegionMapper;
import tech.sergeyev.directoryofregions.persistence.model.Region;

@SpringBootTest
class DirectoryOfRegionsApplicationTests {

//    @Autowired
//    RegionMapper mapper;
//
//    @Test
//    public void findById() {
//        Region region = mapper.getById(1).get();
//        assert region.getName().equals("Omsk");
//        assert region.getAbbreviation().equals("55");
//    }
//
//    @Test
//    public void createRegion() {
//        Region region = new Region(0, "Moscow", "197");
//        mapper.insertRegion(region);
//        Region newRegion = mapper.getById(region.getId()).get();
//        assert newRegion.getName().equals("Moscow");
//        assert newRegion.getAbbreviation().equals("197");
//    }
}
