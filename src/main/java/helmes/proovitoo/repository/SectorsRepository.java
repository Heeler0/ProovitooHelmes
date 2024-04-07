package helmes.proovitoo.repository;

import helmes.proovitoo.model.SectorClassifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SectorsRepository {

    private final transient JdbcTemplate jdbcTemplate;

    private final transient String selectAllSectorsQuery = "SELECT id, sector_name FROM sectors_classifiers";

    private final transient String querySelectByUser = "SELECT sectors_classifiers.id, sectors_classifiers.sector_name " +
            "FROM sectors_classifiers RIGHT JOIN user_info ON sectors_classifiers.id = user_info.sector_id" +
            " WHERE user_name = ?";

    private final transient String insertQuery = "INSERT INTO user_info (user_name, sector_id) VALUES (?, ?)";

    private final transient String deleteFromUser = "DELETE FROM user_info WHERE user_name = ? AND sector_id = ?";

    public List<SectorClassifier> getAllSectors() {

        return jdbcTemplate.query(selectAllSectorsQuery, new SectorRowMapper());
    }

    public List<SectorClassifier> getSectorsForUser(String userName) {
        return jdbcTemplate.query(querySelectByUser, new SectorRowMapper(), userName);
    }

    public void insert(String userName, Long sectorId) {
        jdbcTemplate.update(insertQuery, userName, sectorId);
    }

    public void delete(String userName, Long sectorId) {
        jdbcTemplate.update(deleteFromUser, userName, sectorId);
    }

    public static class SectorRowMapper implements RowMapper<SectorClassifier> {
        @Override
        public SectorClassifier mapRow(ResultSet rs, int rowNum) throws SQLException {
            SectorClassifier sectorClassifier = new SectorClassifier();

            sectorClassifier.setId(rs.getLong("id"));
            sectorClassifier.setName(rs.getString("sector_name"));

            return sectorClassifier;
        }
    }


}
