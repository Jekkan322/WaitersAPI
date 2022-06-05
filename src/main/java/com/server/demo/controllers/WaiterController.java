package com.server.demo.controllers;

import com.server.demo.entities.WaitersEntity;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.Waiters;
import com.server.demo.model.WaitersPlugin;
import com.server.demo.service.WaitersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WaiterController {

    @Autowired
    WaitersService waitersService;

    @GetMapping("/waiters")
    public ResponseEntity findAllWaiters(@RequestParam String firstName, @RequestParam String lastName ,@RequestParam String middleName){
        try{
            return ResponseEntity.ok(waitersService.searchByName(firstName,lastName,middleName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("waiters/{id}")
    public ResponseEntity findWaiter(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.getWaiter(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/waiters/all")
    public ResponseEntity getAllWaiters(){
        try{
            return ResponseEntity.ok(waitersService.getAllWaiters());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/waiters/add")
    public ResponseEntity addWaiters(@RequestBody Waiters waitersEntity){
        try{
            return ResponseEntity.ok(waitersService.createWaiters(waitersEntity));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/waiters/change/{id}")
    public ResponseEntity updateWaiters(@PathVariable Long id,@RequestBody Waiters waiters){
        try{
            return ResponseEntity.ok(waitersService.updateWaiters(id,waiters));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("waiters/delete/{id}")
    public ResponseEntity deleteWaiters(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.deleteWaiter(id));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("waiters/{id}/allAchievements")
    public ResponseEntity getWaiterAllAchievements(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.getAllAchievements(id));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
    @GetMapping("waiters/{id}/allMissions")
    public ResponseEntity getWaiterAllMissions(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.getAllMissions(id));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("waiters/{id}/type")
    public ResponseEntity filterRating(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.filter(id));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("waiters/{id}/statistics")
    public ResponseEntity getStatistics(@PathVariable Long id){
        try{
            return ResponseEntity.ok(waitersService.statistics(id));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @PostMapping("Iikowaiters")
    public ResponseEntity setPluginWaiter(@RequestBody ArrayList<WaitersPlugin> waitersPlugins){
        try{
            return ResponseEntity.ok(waitersService.waitersPlugins(waitersPlugins));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }



    /*@GetMapping("/")
    public String home(Map<String, Object> model){
        return "waiters/home";
    }

    @GetMapping("waiters/main")
    public String main(Map<String,Object> model){
        Iterable<WaitersEntity> waiters = waitersRepository.findAll();
        model.put("waiters",waiters);
        return "waiters/main";
    }

    @PostMapping("waiters/main")
    public String add(@RequestParam String name,@RequestParam int age,@RequestParam String contacts,Map<String,Object> model){
        WaitersEntity waiter=new WaitersEntity(name,age,contacts);
        waitersRepository.save(waiter);

        Iterable<WaitersEntity> waiters = waitersRepository.findAll();
        model.put("waiters",waiters);
        return "redirect:/waiters/main";
    }*/



    /*private final waitersDAO waitersDAO;

    @Autowired
    public WaiterController(waitersDAO waitersDAO){
        this.waitersDAO=waitersDAO;
    }
    

    @GetMapping()
    public String index(Model model){
        model.addAttribute("waiters",waitersDAO.index());
        return "waiters/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("waiters",waitersDAO.show(id));
        return "waiters/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("waiters") Waiters waiters) {
        return "waiters/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Waiters waiters,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "waiters/new";

        waitersDAO.save(waiters);
        return "redirect:/waiters";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("waiters", waitersDAO.show(id));
        return "waiters/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("waiters") @Valid Waiters waiters, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "waiters/edit";

        waitersDAO.update(id, waiters);
        return "redirect:/waiters";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        waitersDAO.delete(id);
        return "redirect:/waiters";
    }*/

}
