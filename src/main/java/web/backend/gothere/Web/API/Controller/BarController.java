package web.backend.gothere.Web.API.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.Models.BarDTO;

@RestController
@RequestMapping("api/bars")
public class BarController {

    private final BarService barService;

    BarController(BarService barService) {
        this.barService = barService;
    }

    
    @GetMapping()
    public List<BarDTO> GetBar() {
        return barService.getAll();
    }

    
    @GetMapping("/{id}")
    public BarDTO GetBarById(@PathVariable("id") Long idbar) {
        return barService.getBarById(idbar);
    }

    
    @PostMapping
    public BarDTO AddBar(@RequestBody BarDTO bar) {
        return barService.add(bar);
    }

   
    @PutMapping("/{id}")
    public BarDTO UpdateBar(@RequestBody BarDTO bar, @PathVariable("id") Long idbar) {
        return barService.update(idbar, bar);
    }


    @DeleteMapping("/{id}")
    public void DeleteBar(@PathVariable("id") Long idbar) {
        barService.delete(idbar);
    }
    @GetMapping("/coordinates")
    public List<BarDTO> GetByCoordinates(@RequestParam(name = "latitude") double latitude, @RequestParam(name = "length") double length, @RequestParam(name = "distance") double distance) {
        return barService.getByCoordinates(length, latitude, distance);
    }
    @GetMapping("/search")
    public List<BarDTO> GetByNameOrDirecction(@RequestParam(name = "q") String query) {
        return barService.getByNameOrAddress(query);
    }


}
