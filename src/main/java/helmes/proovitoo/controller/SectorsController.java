package helmes.proovitoo.controller;

import helmes.proovitoo.model.SectorClassifier;
import helmes.proovitoo.service.SectorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/Sectors")
@Slf4j
public class SectorsController {

    private final SectorsService sectorsService;

    @GetMapping("/GetSectorsForUser")
    public List<SectorClassifier> getSectorForUser(@AuthenticationPrincipal OAuth2User principal) {
        return sectorsService.getSectorsForUser(principal.getAttribute("preferred_username"));
    }

    @GetMapping("/GetAllSectors")
    public List<SectorClassifier> getAllSectors() {
        return sectorsService.getAllSectors();
    }


    @PostMapping("/AddUserSector")
    public void addUserSector(@RequestBody Long[] selectedSectors, @AuthenticationPrincipal OAuth2User principal) {
        String userName = principal.getAttribute("preferred_username");
        sectorsService.addSectors(selectedSectors, userName);
    }

}
