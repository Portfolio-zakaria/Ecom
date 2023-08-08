package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.RoleRequest;
import com.zakaria.ecom.models.Role;
import com.zakaria.ecom.services.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
            Role role = roleService.get(id);
            if (role == null) {
                return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        return  new ResponseEntity<>(role, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Role> addRole(@Valid @RequestBody RoleRequest roleRequest) {
        Role role = new Role();
        BeanUtils.copyProperties(roleRequest,role);
        Role role1 = roleService.add(role);
        return  new ResponseEntity<>(role1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Role> editRole(@RequestBody RoleRequest roleRequest,@PathVariable Long id) {
        Role role = new Role();
        BeanUtils.copyProperties(roleRequest,role);
        Role role1 = roleService.update(id,role);
        if (role1 == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(role1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoty(@PathVariable Long id) {
      boolean check =  roleService.delete(id);
         if(check){
             return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
