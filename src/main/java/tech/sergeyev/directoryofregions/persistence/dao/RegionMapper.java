package tech.sergeyev.directoryofregions.persistence.dao;

import org.apache.ibatis.annotations.*;
import tech.sergeyev.directoryofregions.persistence.model.Region;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RegionMapper {
    @Insert("insert into regions(name, abbreviation) values(#{name}, #{abbreviation})")
    @SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Integer.class)
    void insertRegion(Region region);

    @Select("select id, name, abbreviation from regions where id = #{id}")
    Optional<Region> getById(int id);

    @Select("select id, name, abbreviation from regions")
    List<Region> getAll();

    @Select("select exists(select 1 from regions where id = #{id})")
    Boolean existsById(int id);

    @Update("update regions set " +
            "name = #{region.getName()}," +
            "abbreviation = #{region.getAbbreviation()}" +
            "where id = #{region.getId()}")
    Integer update(Region region);
}
