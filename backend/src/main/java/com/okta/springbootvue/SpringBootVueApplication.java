package com.okta.springbootvue;

import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

import com.okta.springbootvue.Referral.Entity.*;
import com.okta.springbootvue.Referral.Repository.*;
import com.okta.springbootvue.Appointment.Entity.Clinic;
import com.okta.springbootvue.Appointment.Entity.Demeanor;
import com.okta.springbootvue.Appointment.Entity.Reason;
import com.okta.springbootvue.Appointment.Repository.ClinicRepository;
import com.okta.springbootvue.Appointment.Repository.DemeanorRepository;
import com.okta.springbootvue.Appointment.Repository.ReasonRepository;
import com.okta.springbootvue.Diagnose.Entity.*;
import com.okta.springbootvue.Diagnose.Repository.*;
import com.okta.springbootvue.Diagnose.Repository.DoctorRepository;
import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Query.Entity.*;
import com.okta.springbootvue.RegisterDeaths.Entity.CauseofDeath;
import com.okta.springbootvue.RegisterDeaths.Entity.Place;
import com.okta.springbootvue.RegisterDeaths.Repository.CauseofDeathRepository;
import com.okta.springbootvue.RegisterDeaths.Repository.PlaceRepository;
import com.okta.springbootvue.Query.Repository.DurationRepository;
import com.okta.springbootvue.Query.Entity.CongenitalDisease;
import com.okta.springbootvue.Query.Entity.Duration;
import com.okta.springbootvue.Query.Repository.CongenitalDiseaseRepository;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}

	@Bean
	ApplicationRunner init(final RegisterpatientRepository registerpatientRepository, final GenderRepository genderRepository,
			final ProvinceRepository provinceRepository, final NameTitleRepository nameTitleRepository ,QueryRepository queryRepository, DurationRepository durationRepository,
			CongenitalDiseaseRepository congenitalDiseaseRepository,final CauseofDeathRepository causeofDeathRepository ,final PlaceRepository placeRepository,
			ReferralRepository referralRepository, DeliverRepository deliverRepository, ForwardTypeRepository forwardTypeRepository, ForwardToRepository forwardToRepository
			, final DiseaseRepository diseaseRepository, final DoctorRepository doctorRepository, final ClinicRepository clinicRepository
			, final DemeanorRepository demeanorRepository, final ReasonRepository reasonRepository) {
		return args -> {
			// ===================================================================
			final Gender gen1 = new Gender();
			gen1.setGender("หญิง");
			genderRepository.save(gen1);
			genderRepository.findAll().forEach(System.out::println);

			final Gender gen2 = new Gender();
			gen2.setGender("ชาย");
			genderRepository.save(gen2);
			genderRepository.findAll().forEach(System.out::println);

			Stream.of("กระบี่", "กรุงเทพมหานคร", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี",
					"ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด",
					"ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี",
					"นราธิวาส", "น่าน", "บุรีรัมย์", "บึงกาฬ", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
					"พะเยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "ภูเก็ต",
					"มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน", "ยโสธร", "ยะลา", "ร้อยเอ็ด", "ระนอง", "ระยอง", "ราชบุรี",
					"ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล", "สมุทรปราการ",
					"สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี",
					"สุราษฎร์ธานี", "สุรินทร์", "หนองคาย", "หนองบัวลำภู", "อยุธยา", "อ่างทอง", "อำนาจเจริญ", "อุดรธานี",
					"อุตรดิตถ์", "อุทัยธานี", "อุบลราชธานี").forEach(province -> {
						final Province p = new Province();
						p.setProvince(province);
						provinceRepository.save(p);
					});
			provinceRepository.findAll().forEach(System.out::println);

			final NameTitle title1 = new NameTitle();
			title1.setNametitle("นาย");
			nameTitleRepository.save(title1);
			nameTitleRepository.findAll().forEach(System.out::println);

			final NameTitle title2 = new NameTitle();
			title2.setNametitle("นาง");
			nameTitleRepository.save(title2);
			nameTitleRepository.findAll().forEach(System.out::println);

			final NameTitle title3 = new NameTitle();
			title3.setNametitle("นางสาว");
			nameTitleRepository.save(title3);
			nameTitleRepository.findAll().forEach(System.out::println);

			// ======================================================
			
			Stream.of("โรคหัวใจ", "โรคความดันโลหิตสูง", "โรคความดันโลหิต่ำ", "โรคความดันโลหิตสูง", "โรคเบาหวาน", 
						"โรคอ้วน", "โรคเลือดจาง", "โรคไมเกรน", "โรคไต", "โรคไขมันในเลือดสูง", "โรคหืดหอบ", "โรคภูมิแพ้").forEach(CongenitalDisease -> {
				CongenitalDisease c = new CongenitalDisease();
			    c.setCongenitalDisease(CongenitalDisease);
				congenitalDiseaseRepository.save(c);
			});


            Stream.of("1 วัน", "3-5 วัน", "1 สัปดาห์", "1 เดือน", "3 เดือน", "1 ปี").forEach(duration -> {
			Duration d = new Duration();
			d.setDuration(duration);
			durationRepository.save(d);
			});

			durationRepository.findAll().forEach(System.out::println);
			congenitalDiseaseRepository.findAll().forEach(System.out::println);

			Stream.of("โรคประจำตัว","อุบัติเหตุ","ฆาตกรรม","โรคติดต่อ","ไม่ทราบสาเหตุ").forEach(causeofdeath -> {
				final CauseofDeath c = new CauseofDeath();
				c.setCauseofDeath(causeofdeath);
				causeofDeathRepository.save(c);
			});
			causeofDeathRepository.findAll().forEach(System.out::println);

	Stream.of("โรงพยาบาล","บ้าน","บนท้องถนน","แม่น้ำ","สถานที่สาธารณะ").forEach(place -> {
				final Place pp = new Place();
				pp.setPlace(place);
				placeRepository.save(pp);
			});
			placeRepository.findAll().forEach(System.out::println);
			// ===================================================================
			Stream.of("ส่งจาก CUP", "ส่งในจังหวัด", "ส่งนอกจังหวัด").forEach(forwardType -> {
				ForwardType f = new ForwardType();
				f.setForwardType(forwardType);
				forwardTypeRepository.save(f);
			});
	forwardTypeRepository.findAll().forEach(System.out::println);


				Stream.of("โรงพยาบาลเทคโนโลยีสุรนารี", "โรงพยาบาลมหาราชนครราชสีมา", "โรงพยาบาลค่ายสุรนารี", "โรงพยาบาลป.แพทย์2", "โรงพยาบาลกรุงเทพราชสีมา", "โรงพยาบาลกรุงเทพปากช่อง", "โรงพยาบาลเทพรัตน์นครราชสีมา", "โรงพยาบาลเซนต์เมรี่", "โรงพยาบาลแก้งสนามนาง",
						  "โรงพยาบาลขามทะเลสอ", "โรงพยาบาลขามสะแกแสง", "โรงพยาบาลคง", "โรงพยาบาลครบุรี", "โรงพยาบาลจักราช", "โรงพยาบาลเฉลิมพระเกียรติ", "โรงพยาบาลโชคชัย", "โรงพยาบาลชุมพวง", "โรงพยาบาลด่านขุนทด", "โรงพยาบาลเทพารักษ์", "โรงพยาบาลโนนไทย",
						  "โรงพยาบาลโนนสูง", "โรงพยาบาลโนนแดง", "โรงพยาบาลบัวลาย", "โรงพยาบาลบัวใหญ่", "โรงพยาบาลบ้านเหลื่อม", "โรงพยาบาลประทาย", "โรงพยาบาลปักธงชัย", "โรงพยาบาลปากช่องนานา", "โรงพยาบาลพระทองคำ", "โรงพยาบาลพิมาย", "โรงพยาบาลเมืองยาง",
						  "โรงพยาบาลลำทะเมนชัย", "โรงพยาบาลวังน้ำเขียว", "โรงพยาบาลเสิงสาง", "โรงพยาบาลสีคิ้ว", "โรงพยาบาลสีดา", "โรงพยาบาลสูงเนิน", "โรงพยาบาลหนองบุญมาก", "โรงพยาบาลห้วยแถลง", "โรงพยาบาลบุรีรัมย์", "โรงพยาบาลเอกชนบุรีรัมย์", "โรงพยาบาลชัยภูมิ",
						  "โรงพยาบาลขอนแก่น", "โรงพยาบาลขอนแก่น ราม", "โรงพยาบาลศรีนครินทร์", "โรงพยาบาลรวมแพทย์", "โรงพยาบาลศรีสะเกษ", "โรงพยาบาลกาฬสินธุ์", "โรงพยาบาลค่ายจักรพงษ์", "โรงพยาบาลเจ้าพระยาอภัยภูเบศร", "โรงพยาบาลเมืองฉะเชิงเทรา", "โรงพยาบาลเกษมราษฎร์",
						  "โรงพยาบาลสระบุรี").forEach(forwardTo -> {
				ForwardTo t = new ForwardTo();
				t.setForwardTo(forwardTo);
				forwardToRepository.save(t);
			});
	forwardToRepository.findAll().forEach(System.out::println);


				Stream.of("รับไว้รักษาต่อ", "เกิดภาวะแทรกซ้อน", "ไม่สามารถรักษาได้", "การวินิจฉัย/รักษา", "ขาดเครื่องมือทางการแพทย์", "ขาดผู้เชี่ยวชาญเฉพาะด้าน", "ส่งต่อตามสิทธิการรักษา", "ความประสงค์ของผู้ป่วย/ญาติ").forEach(deliver -> {
				Deliver d = new Deliver();
				d.setDeliver(deliver);
				deliverRepository.save(d);
			});
	deliverRepository.findAll().forEach(System.out::println);
	// ===================================================================

		Stream.of("กรดไหลย้อน", "โรคเบาหวาน", "โรคความดันโลหิตสูง", "ไขมันในเลือดสูง", "โรคหลอดเลือดหัวใจ", "ถุงลมโป่งพอง" ,"ธาลัสซีเมีย",
		"กรวยไตอักเสบ", "มะเร็งปากมดลูก", "มะเร็งเต้านม", "โรคภูมิแพ้", "โรคหืด (หอบหืด)" , "โรคอ้วน/น้ำหนักตัวเกิน" , "วัณโรค", 
		"ไข้หวัดใหญ่", "ไข้เลือดออก", "ปอดอักเสบ (ปอดบวม)", "โรคมือเท้าปาก", "อีสุกอีใส", "อาหารเป็นพิษ", "พิษสุนัขบ้า", "ซิฟิลิส"
		).forEach(name -> {
		final Disease disease = new Disease(); // สร้าง Object Disease
		disease.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Disease
		diseaseRepository.save(disease); // บันทึก Objcet ชื่อ Disease
		});

		Stream.of("อดทน พากเพียร", "ดวงกมล เริงร่า", "นอนน้อย นอนนะ", "งามสี ยามเช้า").forEach(name -> {
		final Doctor doctor = new Doctor(); // สร้าง Object Doctor
		doctor.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Doctor
		doctorRepository.save(doctor); // บันทึก Objcet ชื่อ Doctor
		});


		Stream.of("มานี หนีมา", "ปราณา รสดี", "ทรงไท ไตรรัก", "ฝักใฝ่ ขาวสะอาด").forEach(name -> {
			Registerpatient registerpatient = new Registerpatient(); // สร้าง Object Member
			registerpatient.setFirstName(name); // set ชื่อ (name) ให้ Object ชื่อ Member
			registerpatientRepository.save(registerpatient); // บันทึก Objcet ชื่อ Member
		});
		Stream.of("โรคหัวใจ", "โรคกระดูก", "โรคผิวหนัง", "อายุรกรรมทั่วไป").forEach(clinic -> {
			Clinic c = new Clinic();
			c.setClinicName(clinic);
			clinicRepository.save(c);
		});

		Stream.of("งดเครื่องดื่ม งดอาหาร อย่างน้อย 8-10 ชั่วโมงก่อนการตรวจ", "งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ", "นำผลการตรวจหรือรายงานจากแพทย์มาด้วย", "เก็บตัวอย่างอุจจาระ").forEach(demeanor -> {
			Demeanor d = new Demeanor();
			d.setDemeanorName(demeanor);
			demeanorRepository.save(d);
		});

		Stream.of("ติดตามอาการ", "ตรวจโรค", "ตรวจครรภ์", "เจาะเลือด", "ฉีดวัคซีน", "ผ่าตัด").forEach(reason -> {
			Reason r = new Reason();
			r.setReasonName(reason);
			reasonRepository.save(r);
		});
		CongenitalDisease t1 = new CongenitalDisease();
		t1.setCongenitalDisease("เจ็บจี๊ดๆที่อก");
		congenitalDiseaseRepository.save(t1);

		CongenitalDisease t2 = new CongenitalDisease();
		t2.setCongenitalDisease("จามไม่หยุด คัดจมูก");
		congenitalDiseaseRepository.save(t2);

		Query query1 = new Query();
		query1.setRegisterpatient(registerpatientRepository.findById(1));
		query1.setCongenitalDisease(t1);
		queryRepository.save(query1);

		Query query2 = new Query();
		query2.setRegisterpatient(registerpatientRepository.findById(2));
		query2.setCongenitalDisease(t2);
		queryRepository.save(query2);

		diseaseRepository.findAll().forEach(System.out::println);
		doctorRepository.findAll().forEach(System.out::println);

	};
	}
}
