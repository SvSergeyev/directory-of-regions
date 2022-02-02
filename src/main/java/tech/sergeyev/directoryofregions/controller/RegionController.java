package tech.sergeyev.directoryofregions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.directoryofregions.persistence.dao.RegionMapper;
import tech.sergeyev.directoryofregions.persistence.model.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionMapper regionMapper;

    public RegionController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        Optional<List<Region>> regions = regionMapper.getAll();
        return ResponseEntity.ok(regions.orElse(new ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegion(@PathVariable int id) {
        Optional<Region> region = regionMapper.getById(id);
        return region.isPresent()
                ? ResponseEntity.ok(region)
                : new ResponseEntity<>("Region with this id was not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRegion(@RequestBody Region region) {
        if (regionMapper.existsById(region.getId())) {
            return ResponseEntity.ok(regionMapper.getById(region.getId()));
        }
        regionMapper.insertRegion(region);
        return new ResponseEntity<>("Region successfully created", HttpStatus.CREATED);
    }



}
