package helmes.proovitoo.service;

import helmes.proovitoo.model.SectorClassifier;
import helmes.proovitoo.repository.SectorsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorsService {

    private final SectorsRepository sectorsRepository;

    public List<SectorClassifier> getAllSectors() {
        return sectorsRepository.getAllSectors();
    }

    public List<SectorClassifier> getSectorsForUser(String userName) {
        return sectorsRepository.getSectorsForUser(userName);
    }

    public void addSectors(Long[] sectorIds, String userName) {

        List<SectorClassifier> currentSectors = getSectorsForUser(userName);

        List<Long> currentSectorIds = currentSectors.stream().map(SectorClassifier::getId).toList();

        //Insert missing sectors
        Arrays.stream(sectorIds).filter(id -> !currentSectorIds.contains(id))
                .forEach(id -> insertSectorForUser(id, userName));

        //Delete deselected sectors
        currentSectorIds.stream().filter(id -> !Arrays.asList(sectorIds).contains(id))
                .forEach(id -> deleteSectorFromUser(id, userName));
    }

    public void deleteSectorFromUser(Long id, String userName) {
        sectorsRepository.delete(userName, id);
    }

    public void insertSectorForUser(Long id, String userName) {
        sectorsRepository.insert(userName, id);
    }
}
