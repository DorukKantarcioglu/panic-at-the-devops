package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.exception.HesCodeAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.StaffAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.StaffNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * operations related to staff entity
 * @version 1.0
 */
@Service
public class StaffService {
    private final StaffRepository staffRepository;

    /**
     * creates an instance
     * @param staffRepository staff repository
     */
    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * return all of the staff objects in repository
     * @return list of staff
     */
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    /**
     * @param id staff id
     * @throws StaffNotFoundException staff wasnt found in the repository
     * @return staff instance
     */
    public Staff findById(Long id) {
        return staffRepository.findById(id).orElseThrow(()
                -> new StaffNotFoundException("Staff with id " + id + " does not exist."));
    }

    /**
     * find the staff by given hes code in the repository
     * @param hesCode hes code
     * @throws StaffNotFoundException staff couldnt be found in the repository
     * @return found staff
     */
    public Staff findByHesCode(String hesCode) {
        if (staffRepository.findByHesCode(hesCode).isEmpty()) {
            throw new StaffNotFoundException("Staff with HES code " + hesCode + " does not exist.");
        }
        else {
            return staffRepository.findByHesCode(hesCode).iterator().next();
        }
    }

    /**
     * saves a staff object to the repository
     * @param staff staff object to be saved
     * @return staff instance
     */
    public Staff save(Staff staff) {
        if (staffRepository.existsById(staff.getId())) {
            throw new StaffAlreadyExistsException("Staff with id " + staff.getId() + " already exists.");
        }
        else {
            return staffRepository.save(staff);
        }
    }

    /**
     * changes hes code of staff
     * @param id staff id
     * @param hesCode hes code
     * @throws StaffNotFoundException staff couldnt be found in the repository
     * @throws HesCodeAlreadyExistsException a user with the same hes code already exists within the repository
     * @return staff instance
     */
    public Staff updateHesCode(Long id, String hesCode) {
        if (staffRepository.existsById(id)) {
            if (staffRepository.existsByHesCode(hesCode)) {
                throw new HesCodeAlreadyExistsException("HES code " + hesCode + " belongs to another Staff.");
            }
            Staff staff = staffRepository.findById(id).get();
            staff.setHesCode(hesCode);
            return staffRepository.save(staff);
        }
        else {
            throw new StaffNotFoundException("Staff with id " + id + " does not exist.");
        }
    }

    /**
     * changes phone number of staff
     * @param id staff id
     * @param phoneNumber staff phone number
     * @throws StaffNotFoundException staff couldnt be found in the repository
     * @return staff instance
     */
    public Staff updatePhoneNumber(Long id, String phoneNumber) {
        if (staffRepository.existsById(id)) {
            Staff staff = staffRepository.findById(id).get();
            staff.setPhoneNumber(phoneNumber);
            return staffRepository.save(staff);
        }
        else {
            throw new StaffNotFoundException("Staff with id " + id + " does not exist.");
        }
    }

    /**
     * deletes staff with id id from the repository
     * @param id staff id
     * @throws StaffNotFoundException staff with id couldnt be found in the repository
     */
    public void deleteById(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        }
        else {
            throw new StaffNotFoundException("Staff with id " + id + " does not exist.");
        }
    }
}
