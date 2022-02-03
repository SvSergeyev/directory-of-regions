package tech.sergeyev.directoryofregions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.directoryofregions.persistence.dao.RegionMapper;
import tech.sergeyev.directoryofregions.persistence.model.Region;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionController.class);
    private final RegionMapper regionMapper;

    public RegionController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionMapper.getAll();
        if (regions.isEmpty()) {
            LOGGER.info("No region found");
            return ResponseEntity.ok(new ArrayList<>());
        }
        LOGGER.info("Get all regions: " + regions);
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{id}")
    @Cacheable("region")
    public ResponseEntity<?> getRegion(@PathVariable int id) {
        Region region = regionMapper.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Region with id=" + id + " not found"));
        if (region != null) {
            LOGGER.info("Get region with id={}: {}", id, region);
            return ResponseEntity.ok(region);
        }
        LOGGER.info("Region with id={} was not found", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Cacheable(value = "create", key = "#region.name")
    public ResponseEntity<?> createRegion(@RequestBody Region region) {
        int id = region.getId();
        if (regionMapper.existsByName(region.getName())) {
            LOGGER.info("Region with name={} already exists", region.getName());
            return ResponseEntity.ok(regionMapper.getById(id));
        }
        regionMapper.insertRegion(region);
        Region newRegion = regionMapper.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Region with id=" + id + " not found"));
        LOGGER.info("Created new region: " + newRegion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRegion);
    }

    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRegion(@PathVariable(value = "id") int id,
                                          @RequestBody Region region) {
        if (!regionMapper.existsById(id)) {
            LOGGER.info("No region with id=" + id);
            return ResponseEntity.notFound().build();
        }
        regionMapper.update(region);
        Region newRegion = regionMapper.getById(region.getId())
                .orElseThrow(() -> new EntityNotFoundException("Region with id=" + id + " not found"));
        LOGGER.info("Region was successfully updated. Updated region: " + newRegion);
        return ResponseEntity.ok(newRegion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        if (!regionMapper.existsById(id)) {
            LOGGER.info("No region with id=" + id);
            return ResponseEntity.notFound().build();
        }
        regionMapper.deleteById(id);
        LOGGER.info("Region with id={} has been removed", id);
        return ResponseEntity.ok("Region with id=" + id + " has been removed");
    }
}
