package web.backend.gothere.Web.API.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.ScheduleTableReservationService;
import web.backend.gothere.Services.TableService;
import web.backend.gothere.Services.Models.ScheduleTableReservationDTO;
import web.backend.gothere.Services.Models.TableDTO;

@RestController
@RequestMapping("api/tables")
public class TableController {

    private final TableService tableService;

    private final ScheduleTableReservationService scheduleTableReservationService;

    TableController(TableService tableService, ScheduleTableReservationService scheduleTableReservationService) {
        this.tableService = tableService;
        this.scheduleTableReservationService = scheduleTableReservationService;
    }

    @GetMapping()
    public List<TableDTO> GetTable() {
        return tableService.getAll();
    }

    @PostMapping
    public TableDTO AddTable(@RequestBody TableDTO table) {
        return tableService.add(table);
    }

    @PutMapping("/schedule")
    public ResponseEntity<HttpStatus> AddSchedules(@RequestBody ScheduleTableReservationDTO table) {
        if( scheduleTableReservationService.add(table) != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // update
    @PutMapping("/{id}")
    public TableDTO UpdateTable(@RequestBody TableDTO table, @PathVariable("id") Long idtable) {
        return tableService.update(idtable, table);
    }

    // DELETEAR
    @DeleteMapping("/{id}")
    public void DeleteTable(@PathVariable("id") Long idTable) {
        tableService.delete(idTable);
    }
    //por bar y fecha (esta es la importante para el listado de front)
    @GetMapping("/{iBar}/{date}")
    public List<TableDTO> GetTableBydateAndBar(@PathVariable("iBar") Long idBar, @PathVariable("date")@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return tableService.getTableByBarAndAvailabilityDate(idBar, date);
    }
    //por bar
    @GetMapping("/{iBar}")
    public List<TableDTO> GetTableByBar(@PathVariable("iBar") Long idBar) {
        return tableService.getByBarId(idBar);
    }

}
