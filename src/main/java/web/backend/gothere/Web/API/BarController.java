package web.backend.gothere.Web.API;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // get all
    @GetMapping()
    public List<BarDTO> GetBar() {
        return barService.getAll();
    }

    // get by id
    @GetMapping("/{id}")
    public BarDTO GetBarById(@PathVariable("id") Long idbar) {
        return barService.getBarById(idbar);
    }

    // añadir
    @PostMapping
    public BarDTO AddBar(@RequestBody BarDTO bar) {
        return barService.add(bar);
    }

    // update
    @PutMapping("/{id}")
    public BarDTO UpdateBar(@RequestBody BarDTO bar, @PathVariable("id") Long idbar) {
        return barService.update(idbar, bar);
    }

    // DELETEAR
    @DeleteMapping("/{id}")
    public void DeleteBar(@PathVariable("id") Long idbar) {
        barService.delete(idbar);
    }

}
