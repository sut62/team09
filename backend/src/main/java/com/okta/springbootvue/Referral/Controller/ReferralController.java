package com.cpe.backend.Referral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import com.cpe.backend.Referral.entity.Referral;
//import com.cpe.backend.Diagnose.entity.Diagnose;
//import com.cpe.backend.Diagnose.repository.DiagnoseRepository;
import com.cpe.backend.Referral.entity.Deliver;
import com.cpe.backend.Referral.entity.ForwardType;
import com.cpe.backend.Referral.entity.ForwardTo;
import com.cpe.backend.Referral.repository.ForwardTypeRepository;
import com.cpe.backend.Referral.repository.ForwardToRepository;
import com.cpe.backend.Referral.repository.DeliverRepository;
import com.cpe.backend.Referral.repository.ReferralRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080") //สะพานเชื่อม
@RestController 
public class ReferralController {
    @Autowired //ถ้าไม่ใส่ก็error
    private final ReferralRepository referralRepository; //ด้านหน้าประเภท ด้านหลังประกาศชื่อตัวแปร
    //@Autowired
    //private DiagnoseRepository diagnoseRepository;
    @Autowired
    private ForwardTypeRepository forwardTypeRepository;
    @Autowired
    private ForwardToRepository forwardToRepository;
    @Autowired
    private DeliverRepository deliverRepository;
    

    ReferralController(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }
    
    //ดึงข้อมูลReferralทั้งหมด
    @GetMapping("/referral")
    public Collection<Referral> Referral() {
        return referralRepository.findAll().stream().collect(Collectors.toList());
    }

    //เพิ่มข้อมูลตามตัวแปรเรียงกัน
    @PostMapping("/referral/{diagnoseId}/{forwardTypeId}/{forwardToId}/{deliverId}/{note}")
    public Referral memberShopAdvertise(@PathVariable Long diagnoseId,@PathVariable Long forwardTypeId,@PathVariable Long forwardToId,
                                        @PathVariable Long deliverId,@PathVariable String note) {
        Referral referral = new Referral();

        //findById ค้นหาด้วยid
        //Diagnose diagnose = diagnoseRepository.findById(diagnoseId).get();
        Deliver deliver = deliverRepository.findById(deliverId).get();
        ForwardType forwardType = forwardTypeRepository.findById(forwardTypeId).get();
        ForwardTo forwardTo = forwardToRepository.findById(forwardToId).get();

        //setค่าเข้ามา
        //referral.setDiagnose(diagnose);
        referral.setDeliver(deliver);
        referral.setForwardType(forwardType);
        referral.setForwardTo(forwardTo);
        
        //ประกาศเก็บเวลาปัจจุบัน
        Date date = new Date();
        referral.setForwardDate(date);

        referral.setNote(note);
       
        return referralRepository.save(referral); //บันทึก
    }
}
//Repositoryหน้าที่หลักคือติดต่อฐานข้อมูล เช่นการsave หรือการค้นหาข้อมูล    