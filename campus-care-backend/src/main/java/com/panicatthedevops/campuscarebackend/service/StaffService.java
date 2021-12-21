package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.exception.HesCodeAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.StaffAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.StaffNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id).orElseThrow(()
                -> new StaffNotFoundException("staff with id " + id + " does not exist."));
    }

    public Staff findByHesCode(String hesCode) {
        if (staffRepository.findByHesCode(hesCode).isEmpty()) {
            throw new StaffNotFoundException("staff with HES code " + hesCode + " does not exist.");
        }
        else {
            return staffRepository.findByHesCode(hesCode).iterator().next();
        }
    }

    public Staff save(Staff staff) {
        if (staffRepository.existsById(staff.getId())) {
            throw new StaffAlreadyExistsException("staff with id " + staff.getId() + " already exists.");
        }
        else {
            return staffRepository.save(staff);
        }
    }

    public Staff updateHesCode(Long id, String hesCode) {
        if (staffRepository.existsById(id)) {
            if (staffRepository.existsByHesCode(hesCode)) {
                throw new HesCodeAlreadyExistsException("HES code " + hesCode + " belongs to another staff.");
            }
            Staff staff = staffRepository.findById(id).get();
            staff.setHesCode(hesCode);
            return staffRepository.save(staff);
        }
        else {
            throw new StaffNotFoundException("staff with id " + id + " does not exist.");
        }
    }

    public void deleteById(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        }
        else {
            throw new StaffNotFoundException("staff with id " + id + " does not exist.");
        }
    }
}
