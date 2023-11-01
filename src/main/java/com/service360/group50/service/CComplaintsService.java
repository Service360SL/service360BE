package com.service360.group50.service;
import com.service360.group50.entity.Complaints;
import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Users;
import com.service360.group50.repo.CComplaintsRepository;
import com.service360.group50.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CComplaintsService {

    @Autowired
    private CComplaintsRepository cComplaintsRepository;

    private final UserRepository userRepository;

    public Complaints createcomplaints (Complaints newComplaints) {
        System.out.println (newComplaints );
        Users user = userRepository.findById(newComplaints.getUsers().getUserid()).orElse(null);
        if (user != null) {
            newComplaints.setUsers(user);
            return this.cComplaintsRepository.save(newComplaints);
        } else {
            return null;
        }
    }

    public List<Complaints> viewcomplaints() {
        return this.cComplaintsRepository.findAll();
    }

    public List<Complaints> viewcomplaintsbyuserid(Long userid) {
        return this.cComplaintsRepository.findByUsersUserid(userid);
    }

    public Complaints getComplaintsById(Long id) {
        Optional<Complaints> complaint = cComplaintsRepository.findById(id);
        return cComplaintsRepository.findById(id).orElse(null);
    }
    public String deleteJobsById(Long complaintid) {
        cComplaintsRepository.deleteById(complaintid);
        return "Complaint deleted successfully";
    }
    public Complaints updateComplaint(Complaints complaint) {
        return this.cComplaintsRepository.save(complaint);
    }


}
