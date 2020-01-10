package com.okta.springbootvue.Referral.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Referral.Repository.*;
import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;
import com.okta.springbootvue.Referral.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ReferralController {

    @Autowired
    private ForwardToRepository forwardToRepository;
    @Autowired
    private DeliverRepository deliverRepository;
    @Autowired
    private ForwardTypeRepository forwardTypeRepository;
    @Autowired
    private ReferralRepository referralRepository;
    @Autowired
    private DiagnoseRepository diagnoseRepository;

    public ReferralController(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;

    }

    @GetMapping("/referral")
    public Collection<Referral> referral() {
        return referralRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/referral/{diagnoseId}/{note}/{forwardToId}/{deliverId}/{forwardTypeId}")
    public Referral newReferral(@PathVariable String note,@PathVariable long diagnoseId,
            @PathVariable long forwardToId, @PathVariable long deliverId,
            @PathVariable long forwardTypeId) {
        Referral newReferral = new Referral();

        ForwardTo f = forwardToRepository.findById(forwardToId);
        Deliver d = deliverRepository.findById(deliverId);
        ForwardType t = forwardTypeRepository.findById(forwardTypeId);
        Diagnose diagnose = diagnoseRepository.findById(diagnoseId);
        newReferral.setForwardTo(f);
        newReferral.setDeliver(d);
        newReferral.setForwardType(t);
        newReferral.setNote(note);
        newReferral.setDiagnose(diagnose);

        return referralRepository.save(newReferral);
    }

}