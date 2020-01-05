package com.okta.springbootvue;

import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

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
	ApplicationRunner init(final RegisterpatientRepository registerpatient, final GenderRepository genderRepository,
			final ProvinceRepository provinceRepository, final NameTitleRepository nameTitleRepository) {
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
	};
	}
}
