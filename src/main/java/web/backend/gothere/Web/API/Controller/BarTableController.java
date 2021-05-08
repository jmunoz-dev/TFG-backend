package web.backend.gothere.Web.API.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.BarTableService;
import web.backend.gothere.Services.Models.BarTableDTO;

@RestController
@RequestMapping("api/tables")
public class BarTableController {

    private final BarTableService barTableService;

    BarTableController(BarTableService barTableService) {
        this.barTableService = barTableService;
    }

    @GetMapping()
    public List<BarTableDTO> GetBarTable() {
        return barTableService.getAll();
    }

    @PostMapping
    public BarTableDTO AddBar(@RequestBody BarTableDTO bar) {
        return barTableService.add(bar);
    }

    // update
    @PutMapping("/{id}")
    public BarTableDTO UpdateBar(@RequestBody BarTableDTO bar, @PathVariable("id") Long idbar) {
        return barTableService.update(idbar, bar);
    }

    // DELETEAR
    @DeleteMapping("/{id}")
    public void DeleteBar(@PathVariable("id") Long idbar) {
        barTableService.delete(idbar);
    }
    @GetMapping("/{iBar}/{date}")
    public List<BarTableDTO> GetBarTableBydateAndBar(@PathVariable("iBar") Long idBar, @PathVariable("date")@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return barTableService.getTableByBarAndAvailabilityDate(idBar, date);
    }

}
